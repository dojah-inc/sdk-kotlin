package com.dojah.kyc_sdk_kotlin.data.repository.base

import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.network.NetworkManager
import com.dojah.kyc_sdk_kotlin.testutil.ResponseFixtures
import com.dojah.kyc_sdk_kotlin.testutil.ResponseFixtures.SamplePayload
import com.dojah.kyc_sdk_kotlin.testutil.TestBaseRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class BaseRepositoryTest {

    private lateinit var networkManager: NetworkManager
    private lateinit var repository: TestBaseRepository
    private val gson = Gson()

    @Before
    fun setUp() {
        networkManager = mockk(relaxed = true)
        repository = TestBaseRepository(networkManager, gson)
    }

    // region checkNetworkAndStartRequest

    @Test
    fun `checkNetworkAndStartRequest returns action result when connected`() = runTest {
        every { networkManager.isConnected } returns true

        val result = repository.invokeCheckNetworkAndStartRequest {
            Result.Success("ok")
        }

        assertTrue(result is Result.Success)
        assertEquals("ok", (result as Result.Success).data)
    }

    @Test
    fun `checkNetworkAndStartRequest returns NetworkError when disconnected`() = runTest {
        every { networkManager.isConnected } returns false

        val result = repository.invokeCheckNetworkAndStartRequest {
            Result.Success("should not run")
        }

        assertTrue(result is Result.Error.NetworkError)
    }

    @Test
    fun `checkNetworkAndStartRequest returns TimeoutError on UnknownHostException`() = runTest {
        every { networkManager.isConnected } returns true

        val result = repository.invokeCheckNetworkAndStartRequest<String> {
            throw UnknownHostException("dns failure")
        }

        assertTrue(result is Result.Error.TimeoutError)
    }

    @Test
    fun `checkNetworkAndStartRequest returns TimeoutError on SocketTimeoutException`() = runTest {
        every { networkManager.isConnected } returns true

        val result = repository.invokeCheckNetworkAndStartRequest<String> {
            throw SocketTimeoutException("read timed out")
        }

        assertTrue(result is Result.Error.TimeoutError)
    }

    @Test
    fun `checkNetworkAndStartRequest returns ApiError on unexpected exception`() = runTest {
        every { networkManager.isConnected } returns true

        val result = repository.invokeCheckNetworkAndStartRequest<String> {
            throw IllegalStateException("unexpected")
        }

        assertTrue(result is Result.Error.ApiError)
        assertNull((result as Result.Error.ApiError).error)
    }

    // endregion

    // region getResult

    @Test
    fun `getResult returns Success for valid body without error key`() {
        val response = ResponseFixtures.successResponse(ResponseFixtures.VALID_SUCCESS_JSON)

        val result: Result<SamplePayload> =
            repository.invokeGetResult(response, SamplePayload::class.java)

        assertTrue(result is Result.Success)
        assertEquals("user-123", (result as Result.Success).data.id)
    }

    @Test
    fun `getResult returns ApiError when body contains error key`() {
        val response = ResponseFixtures.successResponse(ResponseFixtures.SUCCESS_WITH_ERROR_KEY_JSON)

        val result: Result<SamplePayload> =
            repository.invokeGetResult(response, SamplePayload::class.java)

        assertTrue(result is Result.Error.ApiError)
        val errorMap = (result as Result.Error.ApiError).error
        assertNotNull(errorMap)
        assertEquals("Invalid credentials", errorMap!!["error"])
    }

    @Test
    fun `getResult returns ApiError with nested error payload`() {
        val response = ResponseFixtures.successResponse(ResponseFixtures.NESTED_ERROR_JSON)

        val result: Result<SamplePayload> =
            repository.invokeGetResult(response, SamplePayload::class.java)

        assertTrue(result is Result.Error.ApiError)
        val nested = (result as Result.Error.ApiError).error?.get("error")
        assertTrue(nested is Map<*, *>)
        assertEquals("BVN lookup failed", (nested as Map<*, *>)["message"])
    }

    @Test
    fun `getResult returns NoDataError when body and error body are empty`() {
        val response = ResponseFixtures.emptySuccessResponse()

        val result = repository.invokeGetResult(response, SamplePayload::class.java)

        assertTrue(result is Result.Error.NoDataError)
    }

    @Test
    fun `getResult returns ApiError from HTTP error body and preserves status code`() {
        val response = ResponseFixtures.errorResponse(
            httpCode = 404,
            json = """{"error":"Not found","code":"02"}""",
        )

        val result = repository.invokeGetResult(response, SamplePayload::class.java)

        assertTrue(result is Result.Error.ApiError)
        val apiError = result as Result.Error.ApiError
        assertEquals(404, apiError.code)
        assertEquals("Not found", apiError.error?.get("error"))
    }

    @Test
    fun `getResult returns ApiError when HTTP error body is empty`() {
        val response = ResponseFixtures.errorResponse(httpCode = 500, json = null)

        val result = repository.invokeGetResult(response, SamplePayload::class.java)

        assertTrue(result is Result.Error.ApiError)
        assertEquals(500, (result as Result.Error.ApiError).code)
    }

    // endregion

    // region stringToType and toJson

    @Test
    fun `stringToType deserializes JSON to requested type`() {
        val type = object : TypeToken<Map<String, String>>() {}.type

        val parsed = repository.invokeStringToType<Map<String, String>>(
            """{"name":"dojah"}""",
            type,
        )

        assertEquals("dojah", parsed["name"])
    }

    @Test
    fun `toJson serializes Success data`() {
        val result = Result.Success(SamplePayload(id = "abc"))

        val json = repository.invokeToJson(result)

        assertNotNull(json)
        assertTrue(json!!.contains("abc"))
    }

    @Test
    fun `toJson serializes ApiError map`() {
        val result = Result.Error.ApiError(mapOf("error" to "failed"))

        val json = repository.invokeToJson(result as Result<SamplePayload>)

        assertNotNull(json)
        assertTrue(json!!.contains("failed"))
    }

    @Test
    fun `toJson returns null for NetworkError`() {
        val json = repository.invokeToJson(Result.Error.NetworkError as Result<SamplePayload>)

        assertNull(json)
    }

    // endregion
}
