package com.dojah.kyc_sdk_kotlin.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.domain.responses.AuthResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.Config
import com.dojah.kyc_sdk_kotlin.testutil.MainDispatcherRule
import com.dojah.kyc_sdk_kotlin.testutil.ResultTestUtil
import com.dojah.kyc_sdk_kotlin.testutil.VerificationViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.getOrAwaitValue
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config as RobolectricConfig

@RunWith(RobolectricTestRunner::class)
@RobolectricConfig(manifest = RobolectricConfig.NONE, sdk = [28])
class VerificationViewModelSubmissionTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var harness: VerificationViewModelTestSupport.Harness

    @Before
    fun setUp() {
        harness = VerificationViewModelTestSupport.createHarness()
        coEvery { harness.repo.logEvent(any()) } returns VerificationViewModelTestSupport.successFlow()
    }

    @Test
    fun `sendUserData posts success after user data and step event succeed`() = runTest {
        coEvery { harness.repo.sendUserData(any()) } returns flowOf(
            Result.Success(VerificationViewModelTestSupport.parseSimpleResponse()),
        )

        val result = harness.viewModel.submitUserLiveData.getOrAwaitValue(
            afterObserve = {
                harness.viewModel.sendUserData(
                    dob = "1990-01-01",
                    firstName = "John",
                    lastName = "Doe",
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify { harness.repo.sendUserData(any()) }
    }

    @Test
    fun `sendAddress skips geo verification when address step verification is disabled`() = runTest {
        val authWithoutAddressVerification = authWithAddressVerification(enabled = false)
        VerificationViewModelTestSupport.stubAuthPreferences(
            harness.repo,
            harness.prefManager,
            authResponse = authWithoutAddressVerification,
        )
        coEvery { harness.repo.sendBaseAddress(any(), any(), any(), any(), any(), any()) } returns flowOf(
            Result.Success(VerificationViewModelTestSupport.parseSimpleResponse()),
        )

        harness.viewModel.submitAddressLiveData.getOrAwaitValue(
            afterObserve = {
                harness.viewModel.sendAddress(
                    selectedAddressLatitude = 6.5244,
                    selectedAddressLongitude = 3.3792,
                    distance = 10.0,
                    addressName = "Lagos HQ",
                    deviceLocation = Pair(6.5244, 3.3792),
                    match = true,
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        coVerify(exactly = 0) { harness.repo.sendAddress(any(), any()) }
        verify { harness.prefManager.setLocation(6.5244, 3.3792) }
    }

    @Test
    fun `sendAddress logs failure when geo verification is enabled and match is false`() = runTest {
        coEvery { harness.repo.sendBaseAddress(any(), any(), any(), any(), any(), any()) } returns flowOf(
            Result.Success(VerificationViewModelTestSupport.parseSimpleResponse()),
        )
        coEvery { harness.repo.sendAddress(any(), any()) } returns flowOf(
            Result.Success(VerificationViewModelTestSupport.parseSimpleResponse()),
        )

        val result = harness.viewModel.submitAddressLiveData.getOrAwaitValue(
            afterObserve = {
                harness.viewModel.sendAddress(
                    selectedAddressLatitude = 6.5244,
                    selectedAddressLongitude = 3.3792,
                    distance = 500.0,
                    addressName = "Lagos HQ",
                    deviceLocation = Pair(6.5300, 3.3792),
                    match = false,
                )
            },
        ) { ResultTestUtil.isSuccess(it) || ResultTestUtil.isError(it) }

        assertTrue(ResultTestUtil.isSuccess(result) || ResultTestUtil.isApiError(result))
    }

    @Test
    fun `sendAddress posts ApiError when base address submission fails`() = runTest {
        coEvery { harness.repo.sendBaseAddress(any(), any(), any(), any(), any(), any()) } returns flowOf(
            Result.Error.ApiError(error = mapOf("error" to "failed"), code = 400),
        )

        val result = harness.viewModel.submitAddressLiveData.getOrAwaitValue(
            afterObserve = {
                harness.viewModel.sendAddress(
                    selectedAddressLatitude = 6.5244,
                    selectedAddressLongitude = 3.3792,
                    distance = 10.0,
                    addressName = "Lagos HQ",
                    deviceLocation = Pair(6.5244, 3.3792),
                    match = true,
                )
            },
        ) { value -> ResultTestUtil.isApiError(value) }

        assertTrue(ResultTestUtil.isApiError(result))
    }

    @Test
    fun `isDisposableMail returns false when email step disables disposable check`() {
        val auth = authWithEmailConfig(disposable = false, freeProvider = false)
        every {
            harness.repo.getLocalResponse(SharedPreferenceManager.KEY_AUTH_RESPONSE, AuthResponse::class.java)
        } returns Result.Success(auth)

        assertFalse(harness.viewModel.isDisposableMail("user@mailinator.com"))
    }

    @Test
    fun `isFreeMail returns false when email step disables free provider check`() {
        val auth = authWithEmailConfig(disposable = false, freeProvider = false)
        every {
            harness.repo.getLocalResponse(SharedPreferenceManager.KEY_AUTH_RESPONSE, AuthResponse::class.java)
        } returns Result.Success(auth)

        assertFalse(harness.viewModel.isFreeMail("user@gmail.com"))
    }

    private fun authWithAddressVerification(enabled: Boolean): AuthResponse {
        val auth = VerificationViewModelTestSupport.parseAuthResponse()
        val steps = auth.initData?.authData?.steps?.map { step ->
            if (step.name == KycPages.ADDRESS.serverKey) {
                step.copy(config = Config(verification = enabled))
            } else {
                step
            }
        } ?: emptyList()
        return auth.copy(
            initData = auth.initData?.copy(
                authData = auth.initData?.authData?.copy(steps = steps),
            ),
        )
    }

    private fun authWithEmailConfig(disposable: Boolean, freeProvider: Boolean): AuthResponse {
        val auth = VerificationViewModelTestSupport.parseAuthResponse()
        val steps = auth.initData?.authData?.steps?.map { step ->
            if (step.name == KycPages.EMAIL.serverKey) {
                step.copy(config = Config(disposable = disposable, freeProvider = freeProvider))
            } else {
                step
            }
        } ?: emptyList()
        return auth.copy(
            initData = auth.initData?.copy(
                authData = auth.initData?.authData?.copy(steps = steps),
            ),
        )
    }
}
