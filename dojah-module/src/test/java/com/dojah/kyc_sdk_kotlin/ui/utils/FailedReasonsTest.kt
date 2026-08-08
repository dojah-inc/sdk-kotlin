package com.dojah.kyc_sdk_kotlin.ui.utils

import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.domain.responses.DojahEnumAttr
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class FailedReasonsTest {

    @Test
    fun `getStatusCodeReason maps HTTP 402 to LOW_BALANCE`() {
        val error = Result.Error.ApiError(error = emptyMap(), code = 402)

        assertEquals(FailedReasons.LOW_BALANCE, FailedReasons.getStatusCodeReason(error))
    }

    @Test
    fun `getStatusCodeReason maps HTTP 404 to ID_INVALID_NOT_FOUND`() {
        val error = Result.Error.ApiError(error = emptyMap(), code = 404)

        assertEquals(FailedReasons.ID_INVALID_NOT_FOUND, FailedReasons.getStatusCodeReason(error))
    }

    @Test
    fun `getStatusCodeReason maps HTTP 424 to THIRD_PARTY`() {
        val error = Result.Error.ApiError(error = emptyMap(), code = 424)

        assertEquals(FailedReasons.THIRD_PARTY, FailedReasons.getStatusCodeReason(error))
    }

    @Test
    fun `getStatusCodeReason maps HTTP 500 to 599 range to UNKNOWN`() {
        val error = Result.Error.ApiError(error = emptyMap(), code = 503)

        assertEquals(FailedReasons.UNKNOWN, FailedReasons.getStatusCodeReason(error))
    }

    @Test
    fun `getStatusCodeReason returns null for unmapped status codes`() {
        val error = Result.Error.ApiError(error = emptyMap(), code = 401)

        assertNull(FailedReasons.getStatusCodeReason(error))
    }

    @Test
    fun `getGovBizMsg replaces ID template for government data`() {
        val idType = DojahEnumAttr(name = "BVN Number")

        val message = FailedReasons.ID_INVALID_NOT_FOUND.getGovBizMsg(idType)

        assertEquals(
            "Invalid BVN Number. Input a valid BVN Number or try another means \nof Identification",
            message,
        )
    }

    @Test
    fun `getGovBizMsg uses generic ID when id type is null`() {
        val message = FailedReasons.THIRD_PARTY.getGovBizMsg(null)

        assertEquals(
            "ID Number is currently not available. Please try another means of identification",
            message,
        )
    }
}
