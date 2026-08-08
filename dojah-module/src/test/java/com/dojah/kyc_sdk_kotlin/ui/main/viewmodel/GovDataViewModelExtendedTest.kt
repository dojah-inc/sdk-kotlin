package com.dojah.kyc_sdk_kotlin.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.domain.responses.Config
import com.dojah.kyc_sdk_kotlin.domain.responses.Step
import com.dojah.kyc_sdk_kotlin.testutil.GovDataViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.MainDispatcherRule
import com.dojah.kyc_sdk_kotlin.testutil.ResultTestUtil
import com.dojah.kyc_sdk_kotlin.testutil.getOrAwaitValue
import com.dojah.kyc_sdk_kotlin.ui.utils.CompanyType
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config as RobolectricConfig

@RunWith(RobolectricTestRunner::class)
@RobolectricConfig(manifest = RobolectricConfig.NONE, sdk = [28])
class GovDataViewModelExtendedTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var harness: GovDataViewModelTestSupport.Harness

    @Before
    fun setUp() {
        harness = GovDataViewModelTestSupport.createHarness()
        coEvery { harness.repo.logEvent(any()) } returns GovDataViewModelTestSupport.successFlow()
    }

    @Test
    fun `startLoadingImageAnalysis posts loading state`() {
        harness.govViewModel.startLoadingImageAnalysis()

        assertEquals(Result.Loading, harness.govViewModel.imageAnalysisLiveData.value)
    }

    @Test
    fun `performImageAnalysis posts success result`() = runTest {
        harness.govViewModel.selectVerificationType("Selfie")
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.GOVERNMENT_DATA)
        coEvery { harness.repo.performImageAnalysis(any(), any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseImageAnalysisResponse()),
        )

        val result = harness.govViewModel.imageAnalysisLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.performImageAnalysis(
                    image = "base64-image",
                    currentRoute = KycPages.GOVERNMENT_DATA.serverKey,
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify { harness.repo.performImageAnalysis("base64-image", "selfie") }
    }

    @Test
    fun `submitBusinessData completes CAC lookup and posts success`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.BUSINESS_DATA)
        harness.govViewModel.selectBizIdentity(GovDataViewModelTestSupport.cacIdentity())
        coEvery {
            harness.repo.lookupCac(any(), any(), any(), any())
        } returns flowOf(Result.Success(GovDataViewModelTestSupport.parseBizLookupResponse()))

        val result = harness.govViewModel.submitBizLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.submitBusinessData(
                    verificationVm = harness.verificationViewModel,
                    number = "805396",
                    companyName = "EFO GLOBAL SYSTEMS LIMITED",
                    companyType = CompanyType.BUSINESS_NAME,
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify {
            harness.repo.lookupCac(
                rcNumber = "805396",
                companyName = "EFO GLOBAL SYSTEMS LIMITED",
                companyType = CompanyType.BUSINESS_NAME.serverKey,
                appId = "62f5f4a92f94ab003408ba4c",
            )
        }
    }

    @Test
    fun `submitBusinessData posts error when CAC lookup fails`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.BUSINESS_DATA)
        harness.govViewModel.selectBizIdentity(GovDataViewModelTestSupport.cacIdentity())
        coEvery { harness.repo.lookupCac(any(), any(), any(), any()) } returns flowOf(
            Result.Error.NetworkError,
        )

        val result = harness.govViewModel.submitBizLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.submitBusinessData(
                    verificationVm = harness.verificationViewModel,
                    number = "805396",
                    companyType = CompanyType.BUSINESS_NAME,
                )
            },
        ) { ResultTestUtil.isError(it) }

        assertTrue(ResultTestUtil.isError(result))
    }

    @Test
    fun `submitSignature posts success after event logging`() = runTest {
        val signatureStep = Step(
            id = 20,
            name = KycPages.SIGNATURE.serverKey,
            config = Config(information = "Sign here"),
        )
        harness = GovDataViewModelTestSupport.createHarnessWithAuth(
            GovDataViewModelTestSupport.authWithExtraStep(signatureStep),
        )
        coEvery { harness.repo.logEvent(any()) } returns GovDataViewModelTestSupport.successFlow()
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.SIGNATURE)

        val result = harness.govViewModel.submitSignatureLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.submitSignature(
                    mainVm = harness.verificationViewModel,
                    name = "John Doe",
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
    }
}
