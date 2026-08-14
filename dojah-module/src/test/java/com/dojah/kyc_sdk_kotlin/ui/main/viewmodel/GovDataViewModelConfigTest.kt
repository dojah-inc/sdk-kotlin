package com.dojah.kyc_sdk_kotlin.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.domain.responses.Config
import com.dojah.kyc_sdk_kotlin.testutil.GovDataViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.MainDispatcherRule
import com.dojah.kyc_sdk_kotlin.testutil.VerificationViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.getOrAwaitValue
import com.dojah.kyc_sdk_kotlin.ui.utils.CompanyType
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.dojah.kyc_sdk_kotlin.ui.utils.VerificationType
import io.mockk.every
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config as RobolectricConfig

@RunWith(RobolectricTestRunner::class)
@RobolectricConfig(manifest = RobolectricConfig.NONE, sdk = [28])
class GovDataViewModelConfigTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var harness: GovDataViewModelTestSupport.Harness

    @Before
    fun setUp() {
        harness = GovDataViewModelTestSupport.createHarness()
    }

    @Test
    fun `selectVerificationType maps string to VerificationType`() {
        harness.govViewModel.selectVerificationType("SMS")

        assertEquals(
            VerificationType.OTP,
            harness.govViewModel.verificationTypeLiveData.getOrAwaitValue { it != null },
        )
    }

    @Test
    fun `selectVerificationType ignores null input`() {
        harness.govViewModel.selectVerificationType(null)

        assertNull(harness.govViewModel.verificationTypeLiveData.value)
    }

    @Test
    fun `selectGovIdentity and selectBizIdentity update live data`() {
        val bvn = GovDataViewModelTestSupport.bvnIdentity()
        val cac = GovDataViewModelTestSupport.cacIdentity()

        harness.govViewModel.selectGovIdentity(bvn)
        harness.govViewModel.selectBizIdentity(cac)

        assertEquals(bvn, harness.govViewModel.selectedGovDataLiveData.value)
        assertEquals(cac, harness.govViewModel.selectedBizDataLiveData.value)
    }

    @Test
    fun `prefillGovIdentity keeps existing value when null is passed`() {
        val bvn = GovDataViewModelTestSupport.bvnIdentity()
        harness.govViewModel.selectGovIdentity(bvn)

        harness.govViewModel.prefillGovIdentity(null)

        assertEquals(bvn, harness.govViewModel.selectedGovDataLiveData.value)
    }

    @Test
    fun `getGovIdTypes returns configured government id options`() {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.GOVERNMENT_DATA)

        val types = harness.govViewModel.getGovIdTypes(harness.verificationViewModel)

        assertEquals(4, types?.size)
        assertTrue(types?.any { it?.enum == "BVN" } == true)
        assertTrue(types?.any { it?.enum == "NIN" } == true)
        assertTrue(types?.any { it?.enum == "VNIN" } == true)
        assertTrue(types?.any { it?.enum == "DL" } == true)
    }

    @Test
    fun `getVerifyMethods returns otp when configured on government data step`() {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.GOVERNMENT_DATA)

        val methods = harness.govViewModel.getVerifyMethods(harness.verificationViewModel)

        assertEquals(listOf("SMS"), methods)
    }

    @Test
    fun `getDocIDTypes returns id options from id-options step config`() {
        val types = harness.govViewModel.getDocIDTypes(harness.verificationViewModel)

        assertEquals(4, types?.size)
        assertTrue(types?.isNotEmpty() == true)
    }

    @Test
    fun `getBusinessTypes returns configured business id options`() {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.BUSINESS_DATA)

        val types = harness.govViewModel.getBusinessTypes(harness.verificationViewModel)

        assertEquals(1, types?.size)
        assertEquals("RC-NUMBER", types?.first()?.enum)
    }

    @Test
    fun `getCompanyTypes returns all company type values`() {
        assertEquals(CompanyType.values().toList(), harness.govViewModel.getCompanyTypes())
    }

    @Test
    fun `getCurrentPage returns step when index matches page name`() {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.GOVERNMENT_DATA)

        val step = harness.govViewModel.getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey)

        assertEquals("government-data", step?.name)
        assertEquals(3, step?.id)
    }

    @Test
    fun `getCurrentPage returns null when index does not match requested page`() {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.USER_DATA)

        val step = harness.govViewModel.getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey)

        assertNull(step)
    }

    @Test
    fun `getCurrentPage returns null when page index is unset`() {
        every { harness.prefManager.getCurrentPageIndex() } returns -1

        val step = harness.govViewModel.getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey)

        assertNull(step)
    }

    @Test
    fun `reset helpers clear live data holders`() {
        harness.govViewModel.selectGovIdentity(GovDataViewModelTestSupport.bvnIdentity())
        harness.govViewModel.resetSubmitGovLiveData()
        harness.govViewModel.resetValidateOtpLiveData()
        harness.govViewModel.resetCollectedEmailLiveData()
        harness.govViewModel.resetImageAnalysisLiveData()
        harness.govViewModel.resetDocImageAnalysisLiveData()
        harness.govViewModel.resetDocTypeLiveData()

        assertNull(harness.govViewModel.submitGovLiveData.value)
        assertNull(harness.govViewModel.validateOtpLiveData.value)
        assertNull(harness.govViewModel.collectEmailLiveData.value)
        assertNull(harness.govViewModel.imageAnalysisLiveData.value)
        assertNull(harness.govViewModel.docImageAnalysisLiveData.value)
    }

    @Test
    fun `getGovIdTypes returns empty list when step config disables all ids`() {
        val auth = authWithGovConfig(Config())
        every {
            harness.repo.getLocalResponse(
                SharedPreferenceManager.KEY_AUTH_RESPONSE,
                com.dojah.kyc_sdk_kotlin.domain.responses.AuthResponse::class.java,
            )
        } returns Result.Success(auth)
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.GOVERNMENT_DATA)

        val types = harness.govViewModel.getGovIdTypes(harness.verificationViewModel)

        assertTrue(types.isNullOrEmpty())
    }

    private fun authWithGovConfig(config: Config) =
        VerificationViewModelTestSupport.parseAuthResponse().copy(
            initData = VerificationViewModelTestSupport.parseAuthResponse().initData?.copy(
                authData = VerificationViewModelTestSupport.parseAuthResponse().initData?.authData?.copy(
                    steps = VerificationViewModelTestSupport.parseAuthResponse().initData?.authData?.steps?.map { step ->
                        if (step.name == KycPages.GOVERNMENT_DATA.serverKey) {
                            step.copy(config = config)
                        } else {
                            step
                        }
                    } ?: emptyList(),
                ),
            ),
        )
}
