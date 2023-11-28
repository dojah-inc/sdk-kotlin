package com.dojah.sdk_kyc.data.network.service

import com.dojah.sdk_kyc.core.mock_data.*
import com.dojah.sdk_kyc.domain.request.DataSampleRequest
import kotlinx.coroutines.delay
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import retrofit2.http.*

const val prefix = "v1/mobile/"

interface DojahService {
    @GET("${prefix}get-data")
    suspend fun getData(): Response<ResponseBody>


    @POST("${prefix}send-data")
    suspend fun sendData(@Body accept: DataSampleRequest): Response<ResponseBody>

    @GET("${prefix}get-history")
    suspend fun getHistory(
        @Query("transaction_type") txtType: String?,
        @Query("filter_by") filter: String?,
        @Query("campaign_type") campaignType: String?,
        @Query("page") page: Int?
    ): Response<ResponseBody>
}


class DojahServiceMock : DojahService {
    override suspend fun getData(): Response<ResponseBody> {
//        delay(1000 * 2)
        val responseBody = weeklyTxnSample().replace("\n", "").toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun getHistory(
        txtType: String?,
        filter: String?,
        campaignType: String?,
        page: Int?
    ): Response<ResponseBody> {
        delay(1000 * 2)
        val responseBody = weeklyTxnSample().trimIndent().toResponseBody()
        return Response.success(responseBody)
    }

    override suspend fun sendData(accept: DataSampleRequest): Response<ResponseBody> {
        delay(1000 * 2)
        val raw = weeklyTxnSample().replace("\n", "")
        return Response.success(raw.toResponseBody())
    }
}