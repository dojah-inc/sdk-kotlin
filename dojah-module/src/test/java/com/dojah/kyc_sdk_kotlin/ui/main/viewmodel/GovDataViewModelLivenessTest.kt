package com.dojah.kyc_sdk_kotlin.ui.main.viewmodel

import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.domain.responses.Config
import com.dojah.kyc_sdk_kotlin.testutil.GovDataViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.MainDispatcherRule
import com.dojah.kyc_sdk_kotlin.testutil.ResultTestUtil
import com.dojah.kyc_sdk_kotlin.testutil.getOrAwaitValue
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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
class GovDataViewModelLivenessTest {

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
    fun `checkLiveness posts success when match is true`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.SELFIE)
        coEvery { harness.repo.checkLiveness(any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseLivenessCheckResponse(match = true)),
        )

        val result = harness.govViewModel.submitLivenessLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.checkLiveness(
                    image = "base64-selfie",
                    page = KycPages.SELFIE,
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify { harness.repo.checkLiveness(any()) }
    }

    @Test
    fun `checkLiveness posts failure event when match is false`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.SELFIE)
        coEvery { harness.repo.checkLiveness(any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseLivenessCheckResponse(match = false)),
        )

        val result = harness.govViewModel.submitLivenessLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.checkLiveness(
                    image = "base64-selfie",
                    page = KycPages.SELFIE,
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify { harness.repo.logEvent(any()) }
    }

    @Test
    fun `checkLiveness posts error when repository fails`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.SELFIE)
        coEvery { harness.repo.checkLiveness(any()) } returns flowOf(Result.Error.NetworkError)

        val result = harness.govViewModel.submitLivenessLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.checkLiveness(
                    image = "base64-selfie",
                    page = KycPages.SELFIE,
                )
            },
        ) { ResultTestUtil.isError(it) }

        assertTrue(ResultTestUtil.isError(result))
    }

    @Test
    fun `checkUtilityBillImage posts success when match is true`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.ADDRESS)
        coEvery { harness.repo.checkLiveness(any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseLivenessCheckResponse(match = true)),
        )

        val result = harness.govViewModel.submitLivenessLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.checkUtilityBillImage(
                    page = KycPages.ADDRESS,
                    image = "base64-bill",
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
    }

    @Test
    fun `checkLiveLocationImages posts success when match is true`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.ADDRESS)
        coEvery { harness.repo.checkLiveness(any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseLivenessCheckResponse(match = true)),
        )

        val result = harness.govViewModel.submitLiveLocationLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.checkLiveLocationImages(
                    page = KycPages.ADDRESS,
                    front = "front-b64",
                    outside = "outside-b64",
                    street = "street-b64",
                    latitude = 6.5244,
                    longitude = 3.3792,
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify { harness.repo.checkLiveness(any()) }
    }

    @Test
    fun `checkLiveLocationImages posts error when repository fails`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.ADDRESS)
        coEvery { harness.repo.checkLiveness(any()) } returns flowOf(Result.Error.NetworkError)

        val result = harness.govViewModel.submitLiveLocationLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.checkLiveLocationImages(
                    page = KycPages.ADDRESS,
                    front = "front-b64",
                    outside = "outside-b64",
                    street = "street-b64",
                    latitude = 6.5244,
                    longitude = 3.3792,
                )
            },
        ) { ResultTestUtil.isError(it) }

        assertTrue(ResultTestUtil.isError(result))
    }

    @Test
    fun `performDocImageAnalysis posts success when document details match`() = runTest {
        harness.verificationViewModel.selectDocType("International Passport")
        coEvery { harness.repo.performDocImageAnalysis(any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseSuccessfulDocImageAnalysis()),
        )

        val result = harness.govViewModel.docImageAnalysisLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.performDocImageAnalysis(
                    mainVm = harness.verificationViewModel,
                    image = "base64-doc",
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
    }

    @Test
    fun `performDocImageAnalysis posts ApiError on first failed detection`() = runTest {
        harness.verificationViewModel.selectDocType("International Passport")
        coEvery { harness.repo.performDocImageAnalysis(any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseFailedDocImageAnalysis()),
        )

        val result = harness.govViewModel.docImageAnalysisLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.performDocImageAnalysis(
                    mainVm = harness.verificationViewModel,
                    image = "base64-doc",
                )
            },
        ) { ResultTestUtil.isApiError(it) }

        assertTrue(ResultTestUtil.isApiError(result))
    }

    @Test
    fun `sendAdditionalDoc posts success after upload and step event`() = runTest {
        harness = GovDataViewModelTestSupport.createHarnessWithAuth(
            GovDataViewModelTestSupport.authWithStepConfig(
                KycPages.OTHER_DOCUMENT,
                Config(title = "Upload supporting document"),
            ),
        )
        coEvery { harness.repo.logEvent(any()) } returns GovDataViewModelTestSupport.successFlow()
        coEvery { harness.repo.uploadAdditionalFile(any()) } returns
            GovDataViewModelTestSupport.successFlow()

        val context = mockk<android.content.Context>(relaxed = true)
        val uri = Uri.parse("file:///storage/emulated/0/extra_doc.pdf")
        harness.verificationViewModel.setFrontDocUri(context, uri, isUpload = false)

        val result = harness.govViewModel.submitLivenessLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.sendAdditionalDoc(
                    mainVm = harness.verificationViewModel,
                    fileBase64 = "base64-file",
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify { harness.repo.uploadAdditionalFile(any()) }
    }
}
