package com.dojah.sdk_kyc.data.network.service

import com.dojah.sdk_kyc.core.mock_data.*
import com.dojah.sdk_kyc.domain.request.AuthRequest
import com.dojah.sdk_kyc.domain.request.CheckIpRequest
import com.dojah.sdk_kyc.domain.request.EventRequest
import com.dojah.sdk_kyc.domain.request.OtpRequest
import kotlinx.coroutines.delay
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import retrofit2.http.*

const val prefix = "widget"

interface DojahService {
    @GET("${prefix}/get-data")
    suspend fun getData(): Response<ResponseBody>

    @GET("${prefix}/pre-auth")
    suspend fun doPreAuth(@Query("widget_id") widgetType: String): Response<ResponseBody>

    @POST("${prefix}/auth")
    suspend fun doAuth(@Body data: AuthRequest): Response<ResponseBody>

    @GET("api/v1/ip")
    suspend fun getUserIp(): Response<ResponseBody>

    @POST("${prefix}/kyc/checks")
    suspend fun checkUserIp(@Body data: CheckIpRequest): Response<ResponseBody>

    @POST("${prefix}/kyc/events")
    suspend fun logEvent(@Body data: EventRequest): Response<ResponseBody>

    @GET("${prefix}/kyc/bvn")
    suspend fun lookUpBvn(@Query("bvn") bvn: String): Response<ResponseBody>

    @GET("${prefix}/kyc/nin")
    suspend fun lookUpNin(@Query("nin") nin: String): Response<ResponseBody>

    @GET("${prefix}/kyc/vnin")
    suspend fun lookUpVNin(@Query("vnin") nin: String): Response<ResponseBody>

    @GET("${prefix}/kyc/dl")
    suspend fun lookUpDriverLicence(@Query("license_number") licenseNumber: String): Response<ResponseBody>

    @GET("${prefix}/kyc/mobile/basic")
    suspend fun lookUpPhoneNumber(@Query("phone_number") phoneNumber: String): Response<ResponseBody>

    @POST("${prefix}/sms/otp")
    suspend fun sendOtp(@Body() body: OtpRequest): Response<ResponseBody>

    @GET("${prefix}/sms/otp/validate")
    suspend fun validateOtp(
        @Query("code") code: String,
        @Query("reference_id") referenceId: String
    ): Response<ResponseBody>

}


class DojahServiceMock : DojahService {
    override suspend fun getData(): Response<ResponseBody> {
        delay(1000)
        val responseBody = authResponseSample().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun doPreAuth(widgetType: String): Response<ResponseBody> {
        delay(1000)
        val responseBody = preAuthResponseSample().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun doAuth(data: AuthRequest): Response<ResponseBody> {
        delay(1000)
        val responseBody = authResponseSample().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun getUserIp(): Response<ResponseBody> {
        delay(1000)
        val responseBody = getIpResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun checkUserIp(data: CheckIpRequest): Response<ResponseBody> {
        delay(1000)
        val responseBody = checkIpResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun logEvent(data: EventRequest): Response<ResponseBody> {
        delay(1000 * 2)
        val responseBody = simpleEventResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun lookUpBvn(bvn: String): Response<ResponseBody> {
        delay(1000)
        val responseBody = bvnResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun lookUpNin(nin: String): Response<ResponseBody> {
        delay(1000)
        val responseBody = ninResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun lookUpVNin(nin: String): Response<ResponseBody> {
        delay(1000)
        val responseBody = vNinResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun lookUpDriverLicence(licenseNumber: String): Response<ResponseBody> {
        delay(1000)
        val responseBody = driverLicenceResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun lookUpPhoneNumber(phoneNumber: String): Response<ResponseBody> {
        delay(1000)
        val responseBody = simpleEventResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun sendOtp(body: OtpRequest): Response<ResponseBody> {
        delay(1000)
        val responseBody = sendOtpResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun validateOtp(code: String, referenceId: String): Response<ResponseBody> {
        delay(1000)
        val responseBody = validateOtpResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

}