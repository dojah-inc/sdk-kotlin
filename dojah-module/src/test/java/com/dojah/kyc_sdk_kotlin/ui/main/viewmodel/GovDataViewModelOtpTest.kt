package com.dojah.kyc_sdk_kotlin.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.domain.responses.SendOtpEntity
import com.dojah.kyc_sdk_kotlin.domain.responses.ValidateOtpResponse
import com.dojah.kyc_sdk_kotlin.testutil.GovDataViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.MainDispatcherRule
import com.dojah.kyc_sdk_kotlin.testutil.ResultTestUtil
import com.dojah.kyc_sdk_kotlin.testutil.getOrAwaitValue
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.dojah.kyc_sdk_kotlin.ui.utils.VerificationMethod
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config as RobolectricConfig

@RunWith(RobolectricTestRunner::class)
@RobolectricConfig(manifest = RobolectricConfig.NONE, sdk = [28])
class GovDataViewModelOtpTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var harness: GovDataViewModelTestSupport.Harness

    @Before
    fun setUp() {
        harness = GovDataViewModelTestSupport.createHarness()
        coEvery { harness.repo.logEvent(any()) } returns GovDataViewModelTestSupport.successFlow()
    }

    @Test
    fun `sendOtpSync posts success when otp is sent`() = runTest {
        coEvery { harness.repo.sendOtp(any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseSendOtpResponse()),
        )

        val result = harness.govViewModel.sendOtpLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.sendOtpSync(
                    verificationVm = harness.verificationViewModel,
                    destination = "08012345678",
                    currentRoute = KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey,
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify { harness.repo.sendOtp(any()) }
    }

    @Test
    fun `sendOtpSync posts ApiError when otp send fails`() = runTest {
        coEvery { harness.repo.sendOtp(any()) } returns flowOf(Result.Error.NetworkError)

        val result = harness.govViewModel.sendOtpLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.sendOtpSync(
                    verificationVm = harness.verificationViewModel,
                    destination = "08012345678",
                    currentRoute = KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey,
                )
            },
        ) { ResultTestUtil.isApiError(it) }

        assertTrue(ResultTestUtil.isApiError(result))
    }

    @Test
    fun `validateGovDataPhoneOtp posts success for valid otp`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.GOVERNMENT_DATA_VERIFICATION)
        coEvery { harness.repo.validateOtp(any(), any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseValidateOtpResponse()),
        )

        val result = harness.govViewModel.validateOtpLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.validateGovDataPhoneOtp(
                    code = "1234",
                    currentRoute = KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey,
                    referenceId = "ref-001",
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
        coVerify { harness.repo.validateOtp("1234", "ref-001") }
    }

    @Test
    fun `validateGovDataPhoneOtp posts invalid result when otp is not valid`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.GOVERNMENT_DATA_VERIFICATION)
        val parsed = GovDataViewModelTestSupport.parseValidateOtpResponse()
        val invalidResponse = parsed.copy(
            entity = parsed.entity.copy(valid = false),
        )
        coEvery { harness.repo.validateOtp(any(), any()) } returns flowOf(
            Result.Success(invalidResponse),
        )

        val result = harness.govViewModel.validateOtpLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.validateGovDataPhoneOtp(
                    code = "0000",
                    currentRoute = KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey,
                    referenceId = "ref-001",
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        val success = result as Result.Success<ValidateOtpResponse?>
        assertEquals(false, success.data?.entity?.valid)
    }

    @Test
    fun `validateEmailOtp collects email after successful validation`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.EMAIL)
        coEvery { harness.repo.validateOtp(any(), any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseValidateOtpResponse()),
        )
        val sentOtpEntity = SendOtpEntity(
            referenceId = "ref-email",
            destination = "user@example.com",
            statusId = "status-id",
            status = "sent",
        )

        harness.govViewModel.validateOtpLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.validateEmailOtp(
                    viewModel = harness.verificationViewModel,
                    code = "1234",
                    currentRoute = KycPages.EMAIL.serverKey,
                    sentOtpEntity = sentOtpEntity,
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        coVerify { harness.repo.validateOtp("1234", "ref-email") }
    }

    @Test
    fun `validatePhoneOtp collects phone number after successful validation`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.PHONE_NUMBER)
        coEvery { harness.repo.validateOtp(any(), any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseValidateOtpResponse()),
        )
        val sentOtpEntity = SendOtpEntity(
            referenceId = "ref-phone",
            destination = "+2348012345678",
            statusId = "status-id",
            status = "sent",
        )

        harness.govViewModel.validateOtpLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.validatePhoneOtp(
                    viewModel = harness.verificationViewModel,
                    code = "1234",
                    currentRoute = KycPages.PHONE_NUMBER.serverKey,
                    sentOtpEntity = sentOtpEntity,
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        coVerify { harness.repo.validateOtp("1234", "ref-phone") }
    }

    @Test
    fun `collectEmailWithoutOtp posts collected email result`() = runTest {
        GovDataViewModelTestSupport.setCurrentPage(harness, KycPages.EMAIL)

        val result = harness.govViewModel.collectEmailLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.collectEmailWithoutOtp(
                    viewModel = harness.verificationViewModel,
                    email = "user@example.com",
                )
            },
        ) { ResultTestUtil.isSuccess(it) }

        assertTrue(ResultTestUtil.isSuccess(result))
    }

    @Test
    fun `sendOtpSync marks resend flag when resent is true`() = runTest {
        coEvery { harness.repo.sendOtp(any()) } returns flowOf(
            Result.Success(GovDataViewModelTestSupport.parseSendOtpResponse()),
        )

        harness.govViewModel.isResentOtpLiveData.getOrAwaitValue(
            afterObserve = {
                harness.govViewModel.sendOtpSync(
                    verificationVm = harness.verificationViewModel,
                    destination = "08012345678",
                    currentRoute = KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey,
                    resent = true,
                    verificationMethod = VerificationMethod.SMS,
                )
            },
        ) { it }

        assertEquals(true, harness.govViewModel.isResentOtpLiveData.value)
    }
}
