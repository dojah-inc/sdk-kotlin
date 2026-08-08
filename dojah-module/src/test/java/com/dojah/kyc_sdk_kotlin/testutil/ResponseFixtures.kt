package com.dojah.kyc_sdk_kotlin.testutil

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object ResponseFixtures {

    data class SamplePayload(val id: String, val status: String = "success")

    fun successBody(json: String): ResponseBody =
        json.toResponseBody("application/json".toMediaType())

    fun successResponse(json: String): Response<ResponseBody> =
        Response.success(successBody(json))

    fun errorResponse(
        httpCode: Int,
        json: String? = null,
    ): Response<ResponseBody> {
        val body = json?.toResponseBody("application/json".toMediaType())
        return Response.error(httpCode, body ?: "".toResponseBody("application/json".toMediaType()))
    }

    fun emptySuccessResponse(): Response<ResponseBody> =
        Response.success(null)

    const val VALID_SUCCESS_JSON = """{"id":"user-123","status":"success"}"""

    const val SUCCESS_WITH_ERROR_KEY_JSON = """{"error":"Invalid credentials","status":"failed"}"""

    const val NESTED_ERROR_JSON = """{"error":{"message":"BVN lookup failed","code":"02"}}"""

    const val MALFORMED_JSON = """{"id":"user-123","""

    const val EMPTY_OBJECT_JSON = "{}"
}
