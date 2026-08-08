package com.dojah.kyc_sdk_kotlin.testutil

import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.data.network.NetworkManager
import com.dojah.kyc_sdk_kotlin.data.network.service.DojahService
import com.dojah.kyc_sdk_kotlin.data.repository.DojahRepository
import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object RepositoryTestSupport {

    val gson = Gson()

    data class TestHarness(
        val networkManager: NetworkManager,
        val prefManager: SharedPreferenceManager,
        val service: DojahService,
        val repository: DojahRepository,
    )

    fun createHarness(connected: Boolean = true): TestHarness {
        val networkManager = mockk<NetworkManager>()
        val prefManager = mockk<SharedPreferenceManager>(relaxed = true)
        val service = mockk<DojahService>()

        every { networkManager.isConnected } returns connected
        every { prefManager.getAndroidSource() } returns "android_native"
        every { prefManager.getWidgetId() } returns "widget-test-id"
        every { prefManager.getDeviceSignature() } returns "device-fingerprint"
        every { prefManager.getAppId() } returns "app-test-id"
        every { prefManager.getSessionId() } returns "session-test-id"
        every { prefManager.location } returns Pair(6.5244, 3.3792)

        val repository = DojahRepository(networkManager, gson, prefManager, service)

        return TestHarness(networkManager, prefManager, service, repository)
    }

    fun jsonResponse(json: String): Response<ResponseBody> =
        Response.success(json.toResponseBody("application/json".toMediaType()))

    fun apiErrorResponse(
        message: String = "Request failed",
        httpCode: Int = 400,
    ): Response<ResponseBody> =
        Response.error(
            httpCode,
            """{"error":"$message"}""".toResponseBody("application/json".toMediaType()),
        )

    suspend fun <T> Flow<Result<T>>.awaitResult(): Result<T> = first()

    fun sampleOtpRequest() = com.dojah.kyc_sdk_kotlin.domain.request.OtpRequest(
        senderId = "DOJAH",
        channel = "sms",
        length = 6,
    )

    fun sampleAdditionalDocRequest() = com.dojah.kyc_sdk_kotlin.domain.request.AdditionalDocRequest(
        fileBase64 = "base64",
        fileName = "doc.pdf",
        fileType = "application/pdf",
        verificationId = 1,
        title = "Utility Bill",
    )

    fun sampleUserDataRequest() = com.dojah.kyc_sdk_kotlin.domain.request.UserDataRequest(
        appId = "app-test-id",
        sessionId = "session-test-id",
        country = "NG",
        stepNumber = 1,
        verificationId = 1,
        dob = "1990-01-01",
        firstName = "John",
        lastName = "Doe",
        middleName = null,
        mobile = "+2348012345678",
        residenceCountry = "NG",
    )

    fun sampleLivenessCheckRequest() = com.dojah.kyc_sdk_kotlin.domain.request.LivenessCheckRequest(
        param = "face",
    )

    fun sampleLivenessVerifyRequest() = com.dojah.kyc_sdk_kotlin.domain.request.LivenessVerifyRequest(
        appId = "app-test-id",
        sessionId = "session-test-id",
        verificationId = 1,
    )
}
