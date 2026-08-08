package com.dojah.kyc_sdk_kotlin.data.repository

import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.core.mock_data.authResponseSample
import com.dojah.kyc_sdk_kotlin.core.mock_data.checkIpResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.getIpResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.preAuthResponseSample
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.domain.ExtraUserData
import com.dojah.kyc_sdk_kotlin.domain.UserData
import com.dojah.kyc_sdk_kotlin.domain.request.AuthRequest
import com.dojah.kyc_sdk_kotlin.domain.request.CheckIpRequest
import com.dojah.kyc_sdk_kotlin.domain.responses.AuthResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.PreAuthResponse
import com.dojah.kyc_sdk_kotlin.testutil.RepositoryTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.RepositoryTestSupport.awaitResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DojahRepositoryAuthTest {

    private lateinit var harness: RepositoryTestSupport.TestHarness

    @Before
    fun setUp() {
        harness = RepositoryTestSupport.createHarness()
    }

    @Test
    fun `doPreAuth saves preferences on success`() = runTest {
        coEvery { harness.service.doPreAuth("widget-123") } returns
            RepositoryTestSupport.jsonResponse(preAuthResponseSample())

        val result = harness.repository.doPreAuth("widget-123", null).awaitResult()

        assertTrue(result is Result.Success)
        assertEquals("test_pk_PxxjNNcn6fAy1DfOazC2lKKdH", (result as Result.Success).data.publicKey)
        verify { harness.prefManager.saveJsonResponse(any(), SharedPreferenceManager.KEY_PRE_AUTH_RESPONSE) }
        verify { harness.prefManager.setPKey("test_pk_PxxjNNcn6fAy1DfOazC2lKKdH") }
        verify { harness.prefManager.setAppId("62f5f4a92f94ab003408ba4c") }
        verify { harness.prefManager.setBearerToken(any()) }
    }

    @Test
    fun `doPreAuth persists extra user data when provided`() = runTest {
        coEvery { harness.service.doPreAuth(any()) } returns
            RepositoryTestSupport.jsonResponse(preAuthResponseSample())
        val extraUserData = ExtraUserData(userData = UserData(email = "user@example.com"))

        harness.repository.doPreAuth("widget-123", extraUserData).awaitResult()

        verify { harness.prefManager.setExtraUserData(extraUserData) }
    }

    @Test
    fun `doPreAuth returns ApiError when response contains error key`() = runTest {
        coEvery { harness.service.doPreAuth(any()) } returns
            RepositoryTestSupport.jsonResponse("""{"error":"Widget not found"}""")

        val result = harness.repository.doPreAuth("widget-123", null).awaitResult()

        assertTrue(result is Result.Error.ApiError)
        verify(exactly = 0) { harness.prefManager.setAppId(any()) }
    }

    @Test
    fun `doPreAuth returns NetworkError when offline`() = runTest {
        every { harness.networkManager.isConnected } returns false

        val result = harness.repository.doPreAuth("widget-123", null).awaitResult()

        assertTrue(result is Result.Error.NetworkError)
        coVerify(exactly = 0) { harness.service.doPreAuth(any()) }
    }

    @Test
    fun `doAuth enriches request with source and widget id`() = runTest {
        val authSlot = slot<AuthRequest>()
        coEvery { harness.service.doAuth(capture(authSlot)) } returns
            RepositoryTestSupport.jsonResponse(authResponseSample())

        harness.repository.doAuth(AuthRequest(publicKey = "pk_test")).awaitResult()

        assertEquals("android_native", authSlot.captured.source)
        assertEquals("widget-test-id", authSlot.captured.widgetId)
    }

    @Test
    fun `doAuth saves auth details on success`() = runTest {
        coEvery { harness.service.doAuth(any()) } returns
            RepositoryTestSupport.jsonResponse(authResponseSample())

        val result = harness.repository.doAuth(AuthRequest()).awaitResult()

        assertTrue(result is Result.Success)
        verify { harness.prefManager.addIdToHistory(any()) }
        verify { harness.prefManager.saveJsonResponse(any(), SharedPreferenceManager.KEY_AUTH_RESPONSE) }
        verify { harness.prefManager.setSessionId("656dbe56d2c43c0031e7ed56") }
        verify { harness.prefManager.setReference("DJ-EDF9CAAC99") }
    }

    @Test
    fun `saveAuthDetailsToPrefs stores session and reference`() {
        val authResponse = RepositoryTestSupport.gson.fromJson(
            authResponseSample(),
            AuthResponse::class.java,
        )
        val success = Result.Success(authResponse)

        harness.repository.saveAuthDetailsToPrefs(success)

        verify { harness.prefManager.saveJsonResponse(any(), SharedPreferenceManager.KEY_AUTH_RESPONSE) }
        verify { harness.prefManager.setSessionId("656dbe56d2c43c0031e7ed56") }
        verify { harness.prefManager.setReference("DJ-EDF9CAAC99") }
    }

    @Test
    fun `getUserIp saves response on success`() = runTest {
        coEvery { harness.service.getUserIp() } returns
            RepositoryTestSupport.jsonResponse(getIpResponse())

        val result = harness.repository.getUserIp().awaitResult()

        assertTrue(result is Result.Success)
        assertEquals("54.86.50.139", (result as Result.Success).data.ip)
        verify { harness.prefManager.saveJsonResponse(any(), SharedPreferenceManager.KEY_CHECK_IP_RESPONSE) }
    }

    @Test
    fun `checkUserIp sends device fingerprint from preferences`() = runTest {
        val requestSlot = slot<CheckIpRequest>()
        coEvery { harness.service.checkUserIp(capture(requestSlot)) } returns
            RepositoryTestSupport.jsonResponse(checkIpResponse())

        val result = harness.repository.checkUserIp(CheckIpRequest(ip = "54.86.50.139")).awaitResult()

        assertTrue(result is Result.Success)
        assertEquals("device-fingerprint", requestSlot.captured.deviceFingerprint)
        verify { harness.prefManager.saveJsonResponse(any(), SharedPreferenceManager.KEY_CHECK_IP_RESPONSE) }
    }

    @Test
    fun `getLocalResponse returns cached success payload`() {
        every { harness.prefManager.getSavedJsonResponse(SharedPreferenceManager.KEY_PRE_AUTH_RESPONSE) } returns
            RepositoryTestSupport.jsonResponse(preAuthResponseSample())

        val cached = harness.repository.getLocalResponse(
            SharedPreferenceManager.KEY_PRE_AUTH_RESPONSE,
            PreAuthResponse::class.java,
        )

        assertNotNull(cached)
        assertEquals("test_pk_PxxjNNcn6fAy1DfOazC2lKKdH", cached!!.data.publicKey)
    }

    @Test
    fun `getLocalResponse returns null when cache is missing`() {
        every { harness.prefManager.getSavedJsonResponse(any()) } returns null

        val cached = harness.repository.getLocalResponse(
            SharedPreferenceManager.KEY_AUTH_RESPONSE,
            AuthResponse::class.java,
        )

        assertNull(cached)
    }

    @Test
    fun `deleteAllAuthData clears auth session state`() {
        harness.repository.deleteAllAuthData()

        verify { harness.prefManager.setBearerToken(null) }
        verify { harness.prefManager.setSessionId("") }
        verify { harness.prefManager.saveJsonResponse(null, SharedPreferenceManager.KEY_PRE_AUTH_RESPONSE) }
        verify { harness.prefManager.saveJsonResponse(null, SharedPreferenceManager.KEY_AUTH_RESPONSE) }
        verify { harness.prefManager.saveJsonResponse(null, SharedPreferenceManager.KEY_CHECK_IP_RESPONSE) }
        verify { harness.prefManager.saveJsonResponse(null, SharedPreferenceManager.KEY_GET_IP_RESPONSE) }
    }
}
