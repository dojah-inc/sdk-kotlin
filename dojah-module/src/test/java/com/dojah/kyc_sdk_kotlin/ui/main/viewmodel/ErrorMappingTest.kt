package com.dojah.kyc_sdk_kotlin.ui.main.viewmodel

import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.io.CountryManager
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.data.repository.DojahRepository
import com.dojah.kyc_sdk_kotlin.ui.utils.FailedReasons
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ErrorMappingTest {

    private lateinit var viewModel: VerificationViewModel

    @Before
    fun setUp() {
        viewModel = VerificationViewModel(
            prefManager = mockk<SharedPreferenceManager>(relaxed = true),
            repo = mockk<DojahRepository>(relaxed = true),
            countryManager = mockk<CountryManager>(relaxed = true),
        )
    }

    @Test
    fun `getErrorMessage maps NetworkError to connectivity message`() {
        val message = viewModel.getErrorMessage(Result.Error.NetworkError)

        assertEquals("Check your internet connection and try again.", message)
    }

    @Test
    fun `getErrorMessage maps TimeoutError to timeout message`() {
        val message = viewModel.getErrorMessage(Result.Error.TimeoutError)

        assertEquals("Timeout, Check your network and try again later.", message)
    }

    @Test
    fun `getErrorMessage maps NoDataError to data not received message`() {
        val message = viewModel.getErrorMessage(Result.Error.NoDataError())

        assertEquals("Data not received", message)
    }

    @Test
    fun `getErrorMessage maps ApiError status code to known failure message`() {
        val error = Result.Error.ApiError(error = emptyMap(), code = 402)

        val message = viewModel.getErrorMessage(error)

        assertEquals(FailedReasons.LOW_BALANCE.message, message)
    }

    @Test
    fun `getErrorMessage extracts nested server error message`() {
        val error = Result.Error.ApiError(
            error = mapOf(
                "error" to mapOf("message" to "Provider unavailable"),
            ),
            code = 400,
        )

        val message = viewModel.getErrorMessage(error)

        assertEquals("Provider unavailable", message)
    }

    @Test
    fun `getErrorMessage returns unknown message for empty ApiError payload`() {
        val error = Result.Error.ApiError(error = emptyMap(), code = 400)

        val message = viewModel.getErrorMessage(error)

        assertEquals(FailedReasons.UNKNOWN.message, message)
    }

    @Test
    fun `getFailureCode returns null for network errors`() {
        assertNull(getFailureCode(Result.Error.NetworkError, KycPages.GOVERNMENT_DATA))
        assertNull(getFailureCode(Result.Error.TimeoutError, KycPages.GOVERNMENT_DATA))
    }

    @Test
    fun `getFailureCode maps ApiError status code to failure code`() {
        val error = Result.Error.ApiError(error = emptyMap(), code = 404)

        val code = getFailureCode(error, KycPages.GOVERNMENT_DATA)

        assertEquals(FailedReasons.ID_INVALID_NOT_FOUND.code, code)
    }

    @Test
    fun `getFailureCode uses explicit failedReasons when ApiError is unmapped`() {
        val error = Result.Error.ApiError(error = emptyMap(), code = 400)

        val code = getFailureCode(
            error = error,
            page = KycPages.ADDRESS,
            failedReasons = FailedReasons.INVALID_ADDRESS,
        )

        assertEquals(FailedReasons.INVALID_ADDRESS.code, code)
    }

    @Test
    fun `getFailureCode returns third party code for government data page`() {
        val error = Result.Error.ApiError(error = emptyMap(), code = 424)

        val code = getFailureCode(error, KycPages.GOVERNMENT_DATA)

        assertEquals(FailedReasons.THIRD_PARTY.code, code)
    }
}
