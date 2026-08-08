package com.dojah.kyc_sdk_kotlin.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.domain.responses.DojahEnumAttr
import com.dojah.kyc_sdk_kotlin.testutil.GovDataViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.MainDispatcherRule
import com.dojah.kyc_sdk_kotlin.testutil.ResultTestUtil
import com.dojah.kyc_sdk_kotlin.testutil.getOrAwaitValue
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config as RobolectricConfig

@RunWith(RobolectricTestRunner::class)
@RobolectricConfig(manifest = RobolectricConfig.NONE, sdk = [28])
class GovDataViewModelSubmissionTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var harness: GovDataViewModelTestSupport.Harness

    @Before
    fun setUp() {
        harness = GovDataViewModelTestSupport.createHarness()
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.GOVERNMENT_DATA)
        coEvery { harness.repo.logEvent(any()) } returns GovDataViewModelTestSupport.successFlow()
    }

    @Test
    fun `submitGovDataForm completes BVN lookup and posts success`() = runTest {
        submitWithIdentity(GovDataViewModelTestSupport.bvnIdentity(), "22171234567")

        coVerify { harness.repo.lookUpBvn("22171234567", false) }

        val result = harness.govViewModel.submitGovLiveData.getOrAwaitValue {
            ResultTestUtil.isSuccess(it)
        }
        assertTrue(ResultTestUtil.isSuccess(result))
    }

    @Test
    fun `submitGovDataForm uses NIN lookup branch`() = runTest {
        submitWithIdentity(GovDataViewModelTestSupport.ninIdentity(), "70123456789")

        coVerify { harness.repo.lookUpNin("70123456789") }

        val result = harness.govViewModel.submitGovLiveData.getOrAwaitValue {
            ResultTestUtil.isSuccess(it)
        }
        assertTrue(ResultTestUtil.isSuccess(result))
    }

    @Test
    fun `submitGovDataForm uses vNIN lookup branch`() = runTest {
        submitWithIdentity(GovDataViewModelTestSupport.vninIdentity(), "AB012345678910YZ")

        coVerify { harness.repo.lookUpVnin("AB012345678910YZ") }

        val result = harness.govViewModel.submitGovLiveData.getOrAwaitValue {
            ResultTestUtil.isSuccess(it)
        }
        assertTrue(ResultTestUtil.isSuccess(result))
    }

    @Test
    fun `submitGovDataForm uses driver licence lookup branch`() = runTest {
        submitWithIdentity(GovDataViewModelTestSupport.dlIdentity(), "FKJ494A2133")

        coVerify { harness.repo.lookUpDriverLicense("FKJ494A2133") }

        val result = harness.govViewModel.submitGovLiveData.getOrAwaitValue {
            ResultTestUtil.isSuccess(it)
        }
        assertTrue(ResultTestUtil.isSuccess(result))
    }

    @Test
    fun `submitGovDataForm posts error when lookup fails`() = runTest {
        coEvery { harness.repo.lookUpBvn(any(), any()) } returns flowOf(Result.Error.NetworkError)
        harness.govViewModel.selectGovIdentity(GovDataViewModelTestSupport.bvnIdentity())

        val result = harness.govViewModel.submitGovLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.submitGovDataForm(
                    verifyVm = harness.verificationViewModel,
                    userInputId = "22171234567",
                )
            },
        ) { ResultTestUtil.isError(it) }

        assertTrue(ResultTestUtil.isError(result))
        coVerify { harness.repo.lookUpBvn("22171234567", false) }
    }

    @Test
    fun `submitGovDataForm triggers OTP flow when verification type is SMS`() = runTest {
        coEvery { harness.repo.sendOtp(any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseSendOtpResponse()),
        )
        GovDataViewModelTestSupport.selectNigeria(harness)
        harness.govViewModel.selectVerificationType("SMS")
        harness.govViewModel.selectGovIdentity(GovDataViewModelTestSupport.bvnIdentity())
        GovDataViewModelTestSupport.stubLookupForIdentity(harness, GovDataViewModelTestSupport.bvnIdentity())

        val result = harness.govViewModel.submitGovLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.submitGovDataForm(
                    verifyVm = harness.verificationViewModel,
                    userInputId = "22171234567",
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify { harness.repo.sendOtp(any()) }
    }

    private fun submitWithIdentity(identity: DojahEnumAttr, userInputId: String) {
        GovDataViewModelTestSupport.stubLookupForIdentity(harness, identity)
        harness.govViewModel.selectGovIdentity(identity)

        harness.govViewModel.submitGovLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.submitGovDataForm(
                    verifyVm = harness.verificationViewModel,
                    userInputId = userInputId,
                )
            },
        ) { ResultTestUtil.isSuccess(it) }
    }
}
