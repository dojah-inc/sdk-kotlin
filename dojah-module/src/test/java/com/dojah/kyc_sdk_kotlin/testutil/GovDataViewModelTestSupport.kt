package com.dojah.kyc_sdk_kotlin.testutil

import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.core.mock_data.bvnResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.cacLookUpResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.driverLicenceResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.enumData
import com.dojah.kyc_sdk_kotlin.core.mock_data.imageAnalysisResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.ninResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.pricing
import com.dojah.kyc_sdk_kotlin.core.mock_data.sendOtpResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.simpleEventResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.vNinResponse
import com.dojah.kyc_sdk_kotlin.core.mock_data.validateOtpResponse
import com.dojah.kyc_sdk_kotlin.data.io.CountryManager
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.data.repository.DojahRepository
import com.dojah.kyc_sdk_kotlin.domain.Country
import com.dojah.kyc_sdk_kotlin.domain.responses.AuthResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.BizLookupResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.BvnLookUpResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.Config
import com.dojah.kyc_sdk_kotlin.domain.responses.DojahEnum
import com.dojah.kyc_sdk_kotlin.domain.responses.DojahEnumAttr
import com.dojah.kyc_sdk_kotlin.domain.responses.DojahPricing
import com.dojah.kyc_sdk_kotlin.domain.responses.DriverLicenceResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.ImageAnalysisResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.NinLookUpResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.SendOtpResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.SimpleResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.Step
import com.dojah.kyc_sdk_kotlin.domain.responses.ValidateOtpResponse
import com.dojah.kyc_sdk_kotlin.domain.responses.VninLookUpResponse
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.CompanyType
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf

object GovDataViewModelTestSupport {

    val gson = Gson()

    class Harness(
        val prefManager: SharedPreferenceManager,
        val repo: DojahRepository,
        val countryManager: CountryManager,
        val verificationViewModel: VerificationViewModel,
        val govViewModel: GovDataViewModel,
    )

    fun createHarness(
        authResponse: AuthResponse = VerificationViewModelTestSupport.parseAuthResponse(),
    ): Harness {
        val prefManager = mockk<SharedPreferenceManager>(relaxed = true)
        val repo = mockk<DojahRepository>(relaxed = true)
        val countryManager = mockk<CountryManager>(relaxed = true)

        stubSharedPreferences(prefManager, repo, authResponse)
        stubDojahEnum(repo)
        every { repo.dojahPricing } returns Result.Success(parsePricing())
        coEvery { repo.logEvent(any()) } returns successFlow()

        val verificationViewModel = VerificationViewModel(prefManager, repo, countryManager)
        val govViewModel = GovDataViewModel(prefManager, repo)
        return Harness(prefManager, repo, countryManager, verificationViewModel, govViewModel)
    }

    fun stubSharedPreferences(
        prefManager: SharedPreferenceManager,
        repo: DojahRepository,
        authResponse: AuthResponse = VerificationViewModelTestSupport.parseAuthResponse(),
    ) {
        VerificationViewModelTestSupport.stubAuthPreferences(
            repo,
            prefManager,
            authResponse = authResponse,
        )
        every { prefManager.getAppId() } returns "62f5f4a92f94ab003408ba4c"
    }

    fun setCurrentPage(harness: Harness, page: KycPages) {
        val pages = harness.verificationViewModel.getPagesFromPrefs() ?: emptyList()
        val index = pages.indexOfFirst { it.name == page.serverKey }.coerceAtLeast(0)
        every { harness.prefManager.getCurrentPageIndex() } returns index
    }

    fun authWithExtraStep(step: Step): AuthResponse {
        val auth = VerificationViewModelTestSupport.parseAuthResponse()
        val steps = auth.initData?.authData?.steps.orEmpty() + step
        return auth.copy(
            initData = auth.initData?.copy(
                authData = auth.initData?.authData?.copy(steps = steps),
            ),
        )
    }

    fun createHarnessWithAuth(authResponse: AuthResponse): Harness =
        createHarness(authResponse = authResponse)

    fun stubDojahEnum(repo: DojahRepository) {
        every { repo.getDojahEnum } returns Result.Success(parseDojahEnum())
    }

    fun parseDojahEnum(): DojahEnum =
        gson.fromJson(enumData, DojahEnum::class.java)

    fun parsePricing(): DojahPricing =
        gson.fromJson(pricing.trimIndent(), DojahPricing::class.java)

    fun parseSimpleResponse(): SimpleResponse =
        gson.fromJson(simpleEventResponse(), SimpleResponse::class.java)

    fun parseBvnResponse(): BvnLookUpResponse =
        gson.fromJson(bvnResponse(), BvnLookUpResponse::class.java)

    fun parseNinResponse(): NinLookUpResponse =
        gson.fromJson(ninResponse(), NinLookUpResponse::class.java)

    fun parseVNinResponse(): VninLookUpResponse =
        gson.fromJson(vNinResponse(), VninLookUpResponse::class.java)

    fun parseDriverLicenceResponse(): DriverLicenceResponse =
        gson.fromJson(driverLicenceResponse(), DriverLicenceResponse::class.java)

    fun parseBizLookupResponse(): BizLookupResponse =
        gson.fromJson(cacLookUpResponse(), BizLookupResponse::class.java)

    fun parseImageAnalysisResponse(): ImageAnalysisResponse =
        gson.fromJson(imageAnalysisResponse(), ImageAnalysisResponse::class.java)

    fun parseSendOtpResponse(): SendOtpResponse =
        gson.fromJson(sendOtpResponse(), SendOtpResponse::class.java)

    fun parseValidateOtpResponse(): ValidateOtpResponse =
        gson.fromJson(validateOtpResponse(), ValidateOtpResponse::class.java)

    fun successFlow(value: SimpleResponse = parseSimpleResponse()) =
        flowOf(Result.Success(value))

    fun bvnIdentity(): DojahEnumAttr = parseDojahEnum().bvn

    fun ninIdentity(): DojahEnumAttr = parseDojahEnum().nin

    fun vninIdentity(): DojahEnumAttr = parseDojahEnum().vnin

    fun dlIdentity(): DojahEnumAttr = parseDojahEnum().dl

    fun cacIdentity(): DojahEnumAttr = parseDojahEnum().cac

    fun stubSuccessfulGovSubmission(harness: Harness) {
        coEvery { harness.repo.logEvent(any()) } returns successFlow()
    }

    fun selectNigeria(harness: Harness) {
        harness.verificationViewModel.setSelectedCountry(
            Country(id = "NG", name = "Nigeria", code = "234", path = "ng"),
        )
    }

    fun stubLookupForIdentity(harness: Harness, identity: DojahEnumAttr) {
        when (identity.enum) {
            "BVN" -> coEvery { harness.repo.lookUpBvn(any(), any()) } returns flowOf(
                Result.Success(parseBvnResponse()),
            )
            "NIN" -> coEvery { harness.repo.lookUpNin(any()) } returns flowOf(
                Result.Success(parseNinResponse()),
            )
            "VNIN" -> coEvery { harness.repo.lookUpVnin(any()) } returns flowOf(
                Result.Success(parseVNinResponse()),
            )
            "DL" -> coEvery { harness.repo.lookUpDriverLicense(any()) } returns flowOf(
                Result.Success(parseDriverLicenceResponse()),
            )
        }
    }
}
