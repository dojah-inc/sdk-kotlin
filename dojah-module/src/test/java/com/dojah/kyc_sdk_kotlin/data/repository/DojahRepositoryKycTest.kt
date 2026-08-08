package com.dojah.kyc_sdk_kotlin.data.repository

import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.core.mock_data.additionalDocResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.bvnResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.cacLookUpResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.decisionResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.driverLicenceResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.imageAnalysisResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.livenessCheckResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.livenessVerifyResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.ninResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.sendOtpResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.simpleEventResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.validateOtpResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.vNinResponse
import com.dojah.kyc_sdk_kotlin.domain.request.AddressRequest
import com.dojah.kyc_sdk_kotlin.domain.request.BaseAddressRequest
import com.dojah.kyc_sdk_kotlin.domain.request.EventRequest
import com.dojah.kyc_sdk_kotlin.domain.request.ImageAnalysisRequest
import com.dojah.kyc_sdk_kotlin.domain.request.MetaDataRequest
import com.dojah.kyc_sdk_kotlin.domain.request.QuestionsEventRequest
import com.dojah.kyc_sdk_kotlin.testutil.RepositoryTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.RepositoryTestSupport.awaitResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.slot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DojahRepositoryKycTest {

    private lateinit var harness: RepositoryTestSupport.TestHarness

    @Before
    fun setUp() {
        harness = RepositoryTestSupport.createHarness()
    }

    @Test
    fun `logEvent injects app and session ids`() = runTest {
        val eventSlot = slot<EventRequest>()
        coEvery { harness.service.logEvent(capture(eventSlot)) } returns
            RepositoryTestSupport.jsonResponse(simpleEventResponse())

        val result = harness.repository.logEvent(
            EventRequest(verificationId = 1, eventType = "step_completed"),
        ).awaitResult()

        assertTrue(result is Result.Success)
        assertEquals("app-test-id", eventSlot.captured.appId)
        assertEquals("session-test-id", eventSlot.captured.sessionId)
    }

    @Test
    fun `logQuestionEvent injects app and session ids`() = runTest {
        val eventSlot = slot<QuestionsEventRequest>()
        coEvery { harness.service.logEvent(capture(eventSlot)) } returns
            RepositoryTestSupport.jsonResponse(simpleEventResponse())

        harness.repository.logQuestionEvent(QuestionsEventRequest(verificationId = 2)).awaitResult()

        assertEquals("app-test-id", eventSlot.captured.appId)
        assertEquals("session-test-id", eventSlot.captured.sessionId)
    }

    @Test
    fun `lookUpBvn uses standard endpoint by default`() = runTest {
        coEvery { harness.service.lookUpBvn("22171234567") } returns
            RepositoryTestSupport.jsonResponse(bvnResponse())

        val result = harness.repository.lookUpBvn("22171234567").awaitResult()

        assertTrue(result is Result.Success)
        coVerify { harness.service.lookUpBvn("22171234567") }
        coVerify(exactly = 0) { harness.service.lookUpBvnAdvance(any()) }
    }

    @Test
    fun `lookUpBvn uses advance endpoint when requested`() = runTest {
        coEvery { harness.service.lookUpBvnAdvance("22171234567") } returns
            RepositoryTestSupport.jsonResponse(bvnResponse())

        harness.repository.lookUpBvn("22171234567", isAdvance = true).awaitResult()

        coVerify { harness.service.lookUpBvnAdvance("22171234567") }
        coVerify(exactly = 0) { harness.service.lookUpBvn(any()) }
    }

    @Test
    fun `lookUpNin returns entity on success`() = runTest {
        coEvery { harness.service.lookUpNin("70123456789") } returns
            RepositoryTestSupport.jsonResponse(ninResponse())

        val result = harness.repository.lookUpNin("70123456789").awaitResult()

        assertTrue(result is Result.Success)
        assertEquals("70123456789", (result as Result.Success).data.entity?.nin)
    }

    @Test
    fun `lookUpVnin returns entity on success`() = runTest {
        coEvery { harness.service.lookUpVNin("AB012345678910YZ") } returns
            RepositoryTestSupport.jsonResponse(vNinResponse())

        val result = harness.repository.lookUpVnin("AB012345678910YZ").awaitResult()

        assertTrue(result is Result.Success)
        assertEquals("AB012345678910YZ", (result as Result.Success).data.entity?.vnin)
    }

    @Test
    fun `lookUpDriverLicense returns entity on success`() = runTest {
        coEvery { harness.service.lookUpDriverLicence("FKJ494A2133") } returns
            RepositoryTestSupport.jsonResponse(driverLicenceResponse())

        val result = harness.repository.lookUpDriverLicense("FKJ494A2133").awaitResult()

        assertTrue(result is Result.Success)
        assertEquals("FKJ494A2133", (result as Result.Success).data.entity?.licenseNo)
    }

    @Test
    fun `lookupCac passes query parameters`() = runTest {
        coEvery {
            harness.service.lookupCac("805396", "EFO GLOBAL", "COMPANY", "app-test-id")
        } returns RepositoryTestSupport.jsonResponse(cacLookUpResponse())

        val result = harness.repository.lookupCac(
            rcNumber = "805396",
            companyName = "EFO GLOBAL",
            companyType = "COMPANY",
            appId = "app-test-id",
        ).awaitResult()

        assertTrue(result is Result.Success)
        assertEquals("805396", (result as Result.Success).data.entity?.rcNumber)
    }

    @Test
    fun `lookupTin passes query parameters`() = runTest {
        coEvery {
            harness.service.lookUpTin("1234567890", null, "app-test-id")
        } returns RepositoryTestSupport.jsonResponse(cacLookUpResponse())

        harness.repository.lookupTin("1234567890", appId = "app-test-id").awaitResult()

        coVerify { harness.service.lookUpTin("1234567890", null, "app-test-id") }
    }

    @Test
    fun `sendOtp returns entity on success`() = runTest {
        coEvery { harness.service.sendOtp(any()) } returns
            RepositoryTestSupport.jsonResponse(sendOtpResponse())

        val result = harness.repository.sendOtp(RepositoryTestSupport.sampleOtpRequest()).awaitResult()

        assertTrue(result is Result.Success)
        assertEquals(1, (result as Result.Success).data.entity?.size)
    }

    @Test
    fun `validateOtp forwards code and reference id`() = runTest {
        coEvery { harness.service.validateOtp("123456", "ref-001") } returns
            RepositoryTestSupport.jsonResponse(validateOtpResponse())

        val result = harness.repository.validateOtp("123456", "ref-001").awaitResult()

        assertTrue(result is Result.Success)
        assertEquals(true, (result as Result.Success).data.entity?.valid)
    }

    @Test
    fun `performImageAnalysis sends image and type`() = runTest {
        val requestSlot = slot<ImageAnalysisRequest>()
        coEvery { harness.service.performImageAnalysis(capture(requestSlot)) } returns
            RepositoryTestSupport.jsonResponse(imageAnalysisResponse())

        harness.repository.performImageAnalysis("base64-image", "selfie").awaitResult()

        assertEquals("base64-image", requestSlot.captured.image)
        assertEquals("selfie", requestSlot.captured.imageType)
    }

    @Test
    fun `performDocImageAnalysis always uses id image type`() = runTest {
        val requestSlot = slot<ImageAnalysisRequest>()
        coEvery { harness.service.performImageAnalysis(capture(requestSlot)) } returns
            RepositoryTestSupport.jsonResponse(imageAnalysisResponse())

        harness.repository.performDocImageAnalysis("doc-base64").awaitResult()

        assertEquals("id", requestSlot.captured.imageType)
    }

    @Test
    fun `checkLiveness returns entity on success`() = runTest {
        coEvery { harness.service.livenessCheck(any()) } returns
            RepositoryTestSupport.jsonResponse(livenessCheckResponse())

        val result = harness.repository.checkLiveness(RepositoryTestSupport.sampleLivenessCheckRequest()).awaitResult()

        assertTrue(result is Result.Success)
        assertEquals(false, (result as Result.Success).data.entity?.match)
    }

    @Test
    fun `verifyLiveness returns entity on success`() = runTest {
        coEvery { harness.service.verifyLiveness(any()) } returns
            RepositoryTestSupport.jsonResponse(livenessVerifyResponse())

        val result = harness.repository.verifyLiveness(RepositoryTestSupport.sampleLivenessVerifyRequest()).awaitResult()

        assertTrue(result is Result.Success)
        assertNotNull((result as Result.Success).data.entity?.overall)
    }

    @Test
    fun `sendUserData returns success response`() = runTest {
        coEvery { harness.service.sendUserData(any()) } returns
            RepositoryTestSupport.jsonResponse(simpleEventResponse())

        val result = harness.repository.sendUserData(RepositoryTestSupport.sampleUserDataRequest()).awaitResult()

        assertTrue(result is Result.Success)
    }

    @Test
    fun `uploadAdditionalFile returns success response`() = runTest {
        coEvery { harness.service.uploadAdditionalFile(any()) } returns
            RepositoryTestSupport.jsonResponse(additionalDocResponse())

        val result = harness.repository.uploadAdditionalFile(RepositoryTestSupport.sampleAdditionalDocRequest()).awaitResult()

        assertTrue(result is Result.Success)
    }

    @Test
    fun `makeFinalDecision forwards verification and session ids`() = runTest {
        coEvery { harness.service.makeFinalDecision(17066, "session-test-id") } returns
            RepositoryTestSupport.jsonResponse(decisionResponse())

        val result = harness.repository.makeFinalDecision(17066, "session-test-id").awaitResult()

        assertTrue(result is Result.Success)
        assertEquals("approved", (result as Result.Success).data.entity?.overallCheck)
    }

    @Test
    fun `sendBaseAddress builds request from preferences`() = runTest {
        val requestSlot = slot<BaseAddressRequest>()
        coEvery { harness.service.sendBaseAddress(capture(requestSlot)) } returns
            RepositoryTestSupport.jsonResponse(simpleEventResponse())

        harness.repository.sendBaseAddress(
            selectedAddressLatitude = 6.5244,
            selectedAddressLongitude = 3.3792,
            addressName = "Lagos HQ",
            state = "Lagos",
        ).awaitResult()

        assertEquals("app-test-id", requestSlot.captured.appId)
        assertEquals("session-test-id", requestSlot.captured.sessionId)
        assertEquals("Lagos HQ", requestSlot.captured.name)
        assertEquals("Lagos", requestSlot.captured.state)
    }

    @Test
    fun `sendAddress builds request from stored location`() = runTest {
        val requestSlot = slot<AddressRequest>()
        coEvery { harness.service.sendAddress(capture(requestSlot)) } returns
            RepositoryTestSupport.jsonResponse(simpleEventResponse())

        harness.repository.sendAddress(match = true, distance = 25.0).awaitResult()

        assertEquals(6.5244, requestSlot.captured.latitude, 0.0001)
        assertEquals(3.3792, requestSlot.captured.longitude, 0.0001)
        assertEquals(true, requestSlot.captured.match)
        assertEquals(25.0, requestSlot.captured.distance, 0.0001)
    }

    @Test
    fun `sendMetadata forwards metadata payload`() = runTest {
        val metadata = mapOf<String, Any>("platform" to "android")
        coEvery {
            harness.service.metadata(
                MetaDataRequest(
                    appId = "app-test-id",
                    verificationId = 99,
                    meta = metadata,
                ),
            )
        } returns RepositoryTestSupport.jsonResponse(simpleEventResponse())

        val response = harness.repository.sendMetadata("app-test-id", 99, metadata).first()

        assertTrue(response.isSuccessful)
        coVerify {
            harness.service.metadata(
                MetaDataRequest(
                    appId = "app-test-id",
                    verificationId = 99,
                    meta = metadata,
                ),
            )
        }
    }

    @Test
    fun `lookup operations return ApiError on HTTP failure`() = runTest {
        coEvery { harness.service.lookUpBvn(any()) } returns
            RepositoryTestSupport.apiErrorResponse("BVN not found", httpCode = 404)

        val result = harness.repository.lookUpBvn("00000000000").awaitResult()

        assertTrue(result is Result.Error.ApiError)
        assertEquals(404, (result as Result.Error.ApiError).code)
    }

    @Test
    fun `sendOtp returns NetworkError when offline`() = runTest {
        every { harness.networkManager.isConnected } returns false

        val result = harness.repository.sendOtp(RepositoryTestSupport.sampleOtpRequest()).awaitResult()

        assertTrue(result is Result.Error.NetworkError)
        coVerify(exactly = 0) { harness.service.sendOtp(any()) }
    }
}
