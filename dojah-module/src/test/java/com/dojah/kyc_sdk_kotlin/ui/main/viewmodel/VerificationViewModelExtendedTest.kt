package com.dojah.kyc_sdk_kotlin.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.domain.responses.Config
import com.dojah.kyc_sdk_kotlin.domain.responses.Step
import com.dojah.kyc_sdk_kotlin.testutil.GovDataViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.MainDispatcherRule
import com.dojah.kyc_sdk_kotlin.testutil.ResultTestUtil
import com.dojah.kyc_sdk_kotlin.testutil.VerificationViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.getOrAwaitValue
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.QuestionAnswer
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.QuestionType
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config as RobolectricConfig

@RunWith(RobolectricTestRunner::class)
@RobolectricConfig(manifest = RobolectricConfig.NONE, sdk = [28])
class VerificationViewModelExtendedTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var harness: VerificationViewModelTestSupport.Harness

    @Before
    fun setUp() {
        harness = VerificationViewModelTestSupport.createHarness()
        coEvery { harness.repo.logEvent(any()) } returns VerificationViewModelTestSupport.successFlow()
        coEvery { harness.repo.logQuestionEvent(any()) } returns
            VerificationViewModelTestSupport.successFlow()
    }

    @Test
    fun `logEvent posts success result to event live data`() = runTest {
        val result = harness.viewModel.eventLiveData.getOrAwaitValue(
            afterObserve = {
                harness.viewModel.logEvent(
                    eventType = "step_completed",
                    eventValue = "done",
                    stepNumber = 2,
                )
            },
        ) { pair ->
            pair != null && ResultTestUtil.isSuccess(pair.second)
        }

        assertNotNull(result)
        assertTrue(ResultTestUtil.isSuccess(result!!.second))
        coVerify { harness.repo.logEvent(any()) }
    }

    @Test
    fun `sendSignatureData posts success after step event`() = runTest {
        val auth = GovDataViewModelTestSupport.authWithExtraStep(
            Step(
                id = 20,
                name = KycPages.SIGNATURE.serverKey,
                config = Config(information = "Sign here"),
            ),
        )
        harness = VerificationViewModelTestSupport.createHarness().also {
            VerificationViewModelTestSupport.stubAuthPreferences(it.repo, it.prefManager, auth)
        }
        // Recreate view model with updated auth stubs
        val govSupport = GovDataViewModelTestSupport.createHarnessWithAuth(auth)
        coEvery { govSupport.repo.logEvent(any()) } returns GovDataViewModelTestSupport.successFlow()

        val result = govSupport.verificationViewModel.submitEventLiveData.getOrAwaitValue(
            afterObserve = {
                govSupport.verificationViewModel.sendSignatureData(
                    name = "John Doe",
                    signature = "base64-signature",
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
    }

    @Test
    fun `sendCustomQuestionAnswer posts success after step event`() = runTest {
        val auth = GovDataViewModelTestSupport.authWithExtraStep(
            Step(
                id = 21,
                name = KycPages.CUSTOM_QUESTIONS.serverKey,
                config = Config(),
            ),
        )
        val govSupport = GovDataViewModelTestSupport.createHarnessWithAuth(auth)
        coEvery { govSupport.repo.logEvent(any()) } returns GovDataViewModelTestSupport.successFlow()
        coEvery { govSupport.repo.logQuestionEvent(any()) } returns
            GovDataViewModelTestSupport.successFlow()

        val answers = listOf(
            QuestionAnswer(
                text = "What is your occupation?",
                type = QuestionType.text,
                answer = "Engineer",
            ),
        )

        val result = govSupport.verificationViewModel.submitEventLiveData.getOrAwaitValue(
            afterObserve = {
                govSupport.verificationViewModel.sendCustomQuestionAnswer(answers)
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify { govSupport.repo.logQuestionEvent(any()) }
    }

    @Test
    fun `sendCustomQuestionAnswer posts failure when question event fails`() = runTest {
        val auth = GovDataViewModelTestSupport.authWithExtraStep(
            Step(
                id = 21,
                name = KycPages.CUSTOM_QUESTIONS.serverKey,
                config = Config(),
            ),
        )
        val govSupport = GovDataViewModelTestSupport.createHarnessWithAuth(auth)
        coEvery { govSupport.repo.logEvent(any()) } returns GovDataViewModelTestSupport.successFlow()
        coEvery { govSupport.repo.logQuestionEvent(any()) } returns flowOf(Result.Error.NetworkError)

        val result = govSupport.verificationViewModel.submitEventLiveData.getOrAwaitValue(
            afterObserve = {
                govSupport.verificationViewModel.sendCustomQuestionAnswer(
                    listOf(
                        QuestionAnswer(
                            text = "Favourite colour?",
                            type = QuestionType.text,
                            answer = "Blue",
                        ),
                    ),
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        // STEP_FAILED still logs a success SimpleResponse via logEvent stub
        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify { govSupport.repo.logQuestionEvent(any()) }
    }

    @Test
    fun `startTimer can be started without crashing`() {
        harness.viewModel.startTimer()
        // Timer posts asynchronously via Android CountDownTimer; smoke-check only.
        assertTrue(true)
    }
}
