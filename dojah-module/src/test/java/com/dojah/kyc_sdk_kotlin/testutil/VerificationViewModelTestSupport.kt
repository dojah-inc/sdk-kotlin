package com.dojah.kyc_sdk_kotlin.testutil

import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.core.mock_data.authResponseSample
import com.dojah.kyc_sdk_kotlin.core.mock_data.checkIpResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.getIpResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.preAuthResponseSample
import com.dojah.kyc_sdk_kotlin.core.mock_data.pricing
import com.dojah.kyc_sdk_kotlin.core.mock_data.simpleEventResponse
import com.dojah.kyc_sdk_kotlin.data.io.CountryManager
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.data.repository.DojahRepository
import com.dojah.kyc_sdk_kotlin.domain.responses.AuthResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.CheckIpResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.DojahPricing
import com.dojah.kyc_sdk_kotlin.domain.responses.GetIpResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.PreAuthResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.SimpleResponse
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

object VerificationViewModelTestSupport {

    val gson = Gson()

    class Harness(
        val prefManager: SharedPreferenceManager,
        val repo: DojahRepository,
        val countryManager: CountryManager,
        val viewModel: VerificationViewModel,
    )

    fun createHarness(): Harness {
        val prefManager = mockk<SharedPreferenceManager>(relaxed = true)
        val repo = mockk<DojahRepository>(relaxed = true)
        val countryManager = mockk<CountryManager>(relaxed = true)

        stubAuthPreferences(repo, prefManager)
        every { repo.dojahPricing } returns Result.Success(
            gson.fromJson(pricing.trimIndent(), DojahPricing::class.java),
        )

        val viewModel = VerificationViewModel(prefManager, repo, countryManager)
        return Harness(prefManager, repo, countryManager, viewModel)
    }

    fun stubAuthPreferences(
        repo: DojahRepository,
        prefManager: SharedPreferenceManager,
        authResponse: AuthResponse = parseAuthResponse(),
        preAuthResponse: PreAuthResponse = parsePreAuthResponse(),
    ) {
        every {
            repo.getLocalResponse(SharedPreferenceManager.KEY_AUTH_RESPONSE, AuthResponse::class.java)
        } returns Result.Success(authResponse)
        every {
            repo.getLocalResponse(
                SharedPreferenceManager.KEY_PRE_AUTH_RESPONSE,
                PreAuthResponse::class.java,
            )
        } returns Result.Success(preAuthResponse)
        every { prefManager.getSessionId() } returns "session-test-id"
        every { prefManager.getCurrentPageIndex() } returns 2
    }

    fun parseAuthResponse(): AuthResponse =
        gson.fromJson(authResponseSample(), AuthResponse::class.java)

    fun parsePreAuthResponse(): PreAuthResponse =
        gson.fromJson(preAuthResponseSample(), PreAuthResponse::class.java)

    fun parseSimpleResponse(): SimpleResponse =
        gson.fromJson(simpleEventResponse(), SimpleResponse::class.java)

    fun parseGetIpResponse(): GetIpResponse =
        gson.fromJson(getIpResponse(), GetIpResponse::class.java)

    fun parseCheckIpResponse(): CheckIpResponse =
        gson.fromJson(checkIpResponse(), CheckIpResponse::class.java)

    fun successFlow(value: SimpleResponse = parseSimpleResponse()) =
        flowOf(Result.Success(value))

    fun stubSuccessfulAuthentication(harness: Harness) {
        every { harness.repo.deleteAllAuthData() } returns Unit
        coEvery { harness.repo.doPreAuth(any(), any()) } returns flow {
            emit(Result.Success(parsePreAuthResponse()))
        }
        coEvery { harness.repo.doAuth(any()) } returns flow {
            emit(Result.Success(parseAuthResponse()))
        }
        coEvery { harness.repo.getUserIp() } returns flow {
            emit(Result.Success(parseGetIpResponse()))
        }
        coEvery { harness.repo.checkUserIp(any()) } returns flow {
            emit(Result.Success(parseCheckIpResponse()))
        }
        coEvery { harness.repo.logEvent(any()) } returns successFlow()
        coEvery { harness.countryManager.getCountryStatesList(any()) } returns emptyList()
    }
}
