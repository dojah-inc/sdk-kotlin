package com.dojah.kyc_sdk_kotlin.data.network.service

import com.dojah.kyc_sdk_kotlin.core.mock_data.*
import com.dojah.kyc_sdk_kotlin.domain.request.AdditionalDocRequest
import com.dojah.kyc_sdk_kotlin.domain.request.AddressRequest
import com.dojah.kyc_sdk_kotlin.domain.request.AuthRequest
import com.dojah.kyc_sdk_kotlin.domain.request.BaseAddressRequest
import com.dojah.kyc_sdk_kotlin.domain.request.CheckIpRequest
import com.dojah.kyc_sdk_kotlin.domain.request.EventRequest
import com.dojah.kyc_sdk_kotlin.domain.request.ImageAnalysisRequest
import com.dojah.kyc_sdk_kotlin.domain.request.LivenessCheckRequest
import com.dojah.kyc_sdk_kotlin.domain.request.LivenessVerifyRequest
import com.dojah.kyc_sdk_kotlin.domain.request.MetaDataRequest
import com.dojah.kyc_sdk_kotlin.domain.request.OtpRequest
import com.dojah.kyc_sdk_kotlin.domain.request.UserDataRequest
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

    @POST("${prefix}/kyc/image/analysis")
    suspend fun performImageAnalysis(
        @Body request: ImageAnalysisRequest,
    ): Response<ResponseBody>

    @POST("${prefix}/kyc/image/check")
    suspend fun livenessCheck(
        @Body request: LivenessCheckRequest,
    ): Response<ResponseBody>

    @POST("${prefix}/kyc/image/verify")
    suspend fun verifyLiveness(
        @Body request: LivenessVerifyRequest,
    ): Response<ResponseBody>

    @POST("${prefix}/kyc/user-data")
    suspend fun sendUserData(
        @Body request: UserDataRequest,
    ): Response<ResponseBody>

    @POST("${prefix}/kyc/files")
    suspend fun uploadAdditionalFile(
        @Body request: AdditionalDocRequest,
    ): Response<ResponseBody>

    @GET("${prefix}/decision")
    suspend fun makeFinalDecision(
        @Query("verification_id") verificationId: Int,
        @Query("session_id") sessionId: String,
    ): Response<ResponseBody>

    @POST("${prefix}/kyc/base-address")
    suspend fun sendBaseAddress(
        @Body request: BaseAddressRequest,
    ): Response<ResponseBody>

    @POST("${prefix}/kyc/address")
    suspend fun sendAddress(
        @Body request: AddressRequest,
    ): Response<ResponseBody>


    //http://api-dev.dojah.services/widget/kyc/cac?rc_number=805396&company_name=EFO GLOBAL SYSTEMS LIMITED
    @GET("${prefix}/kyc/cac")
    suspend fun lookupCac(
        @Query("rc_number") rcNumber: String,
        @Query("company_name") companyName: String? = null,
        @Query("company_type") companyType: String,
        @Query("app_id") appId: String,
    ): Response<ResponseBody>

    @GET("${prefix}/kyc/tin")
    suspend fun lookUpTin(
        @Query("tin") tin: String,
        @Query("company_name") companyName: String? = null,
        @Query("app_id") appId: String,
    ): Response<ResponseBody>

    //        app_id: appId,
    //        verification_id: verificationId,
    //        meta,
    @POST("${prefix}/kyc/metadata")
    suspend fun metadata(
        @Body request: MetaDataRequest
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

    override suspend fun performImageAnalysis(request: ImageAnalysisRequest): Response<ResponseBody> {
        delay(1000)
        val responseBody = imageAnalysisResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun livenessCheck(request: LivenessCheckRequest): Response<ResponseBody> {
        delay(1000)
        val responseBody = livenessCheckResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun verifyLiveness(request: LivenessVerifyRequest): Response<ResponseBody> {
        delay(1000)
        val responseBody = livenessVerifyResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun sendUserData(request: UserDataRequest): Response<ResponseBody> {
        delay(1000)
        val responseBody = simpleEventResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun uploadAdditionalFile(request: AdditionalDocRequest): Response<ResponseBody> {
        delay(1000)
        val responseBody = additionalDocResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun makeFinalDecision(
        verificationId: Int,
        sessionId: String
    ): Response<ResponseBody> {
        delay(1000)
        val responseBody = decisionResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun sendBaseAddress(
        @Body request: BaseAddressRequest,
    ): Response<ResponseBody> {
        delay(1000)
        val responseBody = simpleEventResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun sendAddress(
        @Body request: AddressRequest,
    ): Response<ResponseBody> {
        delay(1000)
        val responseBody = simpleEventResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun lookupCac(
        rcNumber: String, companyName: String?, companyType: String,
        appId: String,
    ): Response<ResponseBody> {
        delay(1000)
        val responseBody = cacLookUpResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun lookUpTin(
        tin: String, companyName: String?,
        appId: String,
        ): Response<ResponseBody> {
        delay(1000)
        val responseBody = cacLookUpResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun metadata(
        request: MetaDataRequest
    ): Response<ResponseBody> {
        TODO("Not yet implemented")
    }

}