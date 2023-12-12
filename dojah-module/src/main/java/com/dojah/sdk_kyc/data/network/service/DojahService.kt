package com.dojah.sdk_kyc.data.network.service

import com.dojah.sdk_kyc.core.mock_data.*
import com.dojah.sdk_kyc.domain.request.AuthRequest
import com.dojah.sdk_kyc.domain.request.CheckIpRequest
import kotlinx.coroutines.delay
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import retrofit2.http.*

const val prefix = "widget/"

interface DojahService {
    @GET("${prefix}get-data")
    suspend fun getData(): Response<ResponseBody>

    @GET("${prefix}pre-auth")
    suspend fun doPreAuth(@Query("widget_id") widgetType: String): Response<ResponseBody>

    @POST("${prefix}auth")
    suspend fun doAuth(@Body data: AuthRequest): Response<ResponseBody>

    @GET("api/v1/ip")
    suspend fun getUserIp(): Response<ResponseBody>

    @POST("${prefix}kyc/checks")
    suspend fun checkUserIp(@Body data: CheckIpRequest): Response<ResponseBody>

//    @POST("${prefix}send-data")
//    suspend fun sendData(@Body accept: DataSampleRequest): Response<ResponseBody>

}


class DojahServiceMock : DojahService {
    override suspend fun getData(): Response<ResponseBody> {
        delay(1000 * 2)
        val responseBody = weeklyTxnSample().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun doPreAuth(widgetType: String): Response<ResponseBody> {
        delay(1000 * 2)
        val responseBody = preAuthResponseSample().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun doAuth(data: AuthRequest): Response<ResponseBody> {
        delay(1000 * 2)
        val responseBody = authResponseSample().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun getUserIp(): Response<ResponseBody> {
        delay(1000 * 2)
        val responseBody = getIpResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun checkUserIp(data: CheckIpRequest): Response<ResponseBody> {
        delay(1000 * 2)
        val responseBody = checkIpResponse().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

}