package com.dojah.kyc_sdk_kotlin.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.core.mock_data.simpleEventResponse
import com.dojah.kyc_sdk_kotlin.testutil.MainDispatcherRule
import com.dojah.kyc_sdk_kotlin.testutil.RepositoryTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.VerificationViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.ResultTestUtil
import com.dojah.kyc_sdk_kotlin.testutil.getOrAwaitValue
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
class VerificationViewModelAuthTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var harness: VerificationViewModelTestSupport.Harness

    @Before
    fun setUp() {
        harness = VerificationViewModelTestSupport.createHarness()
    }

    @Test
    fun `authenticate completes pre-auth auth and ip verification`() = runTest {
        VerificationViewModelTestSupport.stubSuccessfulAuthentication(harness)

        val preAuth = harness.viewModel.preAuthDataLiveData.getOrAwaitValue(
            afterObserve = { harness.viewModel.authenticate(widgetId = "widget-123") },
        ) { ResultTestUtil.isSuccess(it) }
        val auth = harness.viewModel.authDataLiveData.getOrAwaitValue {
            ResultTestUtil.isSuccess(it)
        }
        val ip = harness.viewModel.getIpDataLiveData.getOrAwaitValue {
            ResultTestUtil.isSuccess(it)
        }

        assertTrue(preAuth is Result.Success)
        assertTrue(auth is Result.Success)
        assertTrue(ip is Result.Success)
        verify { harness.repo.deleteAllAuthData() }
        verify { harness.prefManager.setMaterialButtonBgColor("#36635c") }
        coVerify { harness.repo.doPreAuth("widget-123", null) }
        coVerify { harness.repo.doAuth(any()) }
        coVerify { harness.repo.getUserIp() }
        coVerify { harness.repo.checkUserIp(any()) }
    }

    @Test
    fun `authenticate posts auth error when pre-auth fails`() = runTest {
        every { harness.repo.deleteAllAuthData() } returns Unit
        coEvery { harness.repo.doPreAuth(any(), any()) } returns flow {
            emit(Result.Error.NetworkError)
        }

        val errorMessage = harness.viewModel.authErrLiveData.getOrAwaitValue(
            afterObserve = { harness.viewModel.authenticate(widgetId = "widget-123") },
        ) { it.isNotEmpty() }

        assertEquals(
            "Check your internet connection and try again.",
            errorMessage,
        )
    }

    @Test
    fun `authenticate marks verification complete on 402 resume response`() = runTest {
        every { harness.repo.deleteAllAuthData() } returns Unit
        coEvery { harness.repo.doPreAuth(any(), any()) } returns flow {
            emit(Result.Success(VerificationViewModelTestSupport.parsePreAuthResponse()))
        }
        coEvery { harness.repo.doAuth(any()) } returns flow {
            emit(
                Result.Error.ApiError(
                    error = mapOf("error" to mapOf("code" to 402)),
                    code = 402,
                ),
            )
        }

        val completed = harness.viewModel.authVerificationCompletedLD.getOrAwaitValue(
            afterObserve = { harness.viewModel.authenticate(widgetId = "widget-123") },
        ) { it }
        assertTrue(completed)
    }

    @Test
    fun `authenticate sends metadata when extra user data contains metadata`() = runTest {
        VerificationViewModelTestSupport.stubSuccessfulAuthentication(harness)
        val metadata = mapOf("referral" to "campaign-a")
        val extraUserData = com.dojah.kyc_sdk_kotlin.domain.ExtraUserData(metadata = metadata)

        coEvery {
            harness.repo.sendMetadata(any(), any(), any())
        } returns flow {
            emit(
                RepositoryTestSupport.jsonResponse(simpleEventResponse()),
            )
        }

        harness.viewModel.authDataLiveData.getOrAwaitValue(
            afterObserve = {
                harness.viewModel.authenticate(
                    widgetId = "widget-123",
                    extraUserData = extraUserData,
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        coVerify {
            harness.repo.sendMetadata(
                appId = "62f5f4a92f94ab003408ba4c",
                verificationId = 17066,
                metadata = metadata,
            )
        }
    }
}
