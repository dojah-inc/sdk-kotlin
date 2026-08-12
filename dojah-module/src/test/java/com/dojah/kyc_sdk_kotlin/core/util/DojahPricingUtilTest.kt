package com.dojah.kyc_sdk_kotlin.core.util

import com.dojah.kyc_sdk_kotlin.core.mock_data.pricing
import com.dojah.kyc_sdk_kotlin.domain.responses.Config
import com.dojah.kyc_sdk_kotlin.domain.responses.DojahEnumAttr
import com.dojah.kyc_sdk_kotlin.domain.responses.DojahPricing
import com.dojah.kyc_sdk_kotlin.domain.responses.Step
import com.dojah.kyc_sdk_kotlin.testutil.GovDataViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.MainDispatcherRule
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.EventTypes
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DojahPricingUtilTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var pricingData: DojahPricing
    private lateinit var govViewModel: GovDataViewModel

    @Before
    fun setUp() {
        pricingData = Gson().fromJson(pricing.trimIndent(), DojahPricing::class.java)
        govViewModel = mockk(relaxed = true)
    }

    @Test
    fun `returns empty list for non step completed or failed events`() {
        val services = DojahPricingUtil.getPricingServices(
            page = KycPages.EMAIL,
            pricing = pricingData,
            event = EventTypes.COUNTRY_SELECTED,
            govViewModel = govViewModel,
        )

        assertTrue(services.isEmpty())
    }

    @Test
    fun `email page with verification enabled adds email otp price`() {
        every { govViewModel.getCurrentPage(KycPages.EMAIL.serverKey) } returns Step(
            id = 7,
            name = KycPages.EMAIL.serverKey,
            config = Config(verification = true),
        )

        val services = DojahPricingUtil.getPricingServices(
            page = KycPages.EMAIL,
            pricing = pricingData,
            event = EventTypes.STEP_COMPLETED,
            govViewModel = govViewModel,
        )

        assertEquals(listOf("email_otp"), services)
    }

    @Test
    fun `email page without verification returns empty list`() {
        every { govViewModel.getCurrentPage(KycPages.EMAIL.serverKey) } returns Step(
            id = 7,
            name = KycPages.EMAIL.serverKey,
            config = Config(verification = false),
        )

        val services = DojahPricingUtil.getPricingServices(
            page = KycPages.EMAIL,
            pricing = pricingData,
            event = EventTypes.STEP_COMPLETED,
            govViewModel = govViewModel,
        )

        assertTrue(services.isEmpty())
    }

    @Test
    fun `business data page adds cac price`() {
        val services = DojahPricingUtil.getPricingServices(
            page = KycPages.BUSINESS_DATA,
            pricing = pricingData,
            event = EventTypes.STEP_COMPLETED,
            govViewModel = null,
        )

        assertEquals(listOf("kyc_cac"), services)
    }

    @Test
    fun `id page adds document analysis price`() {
        val services = DojahPricingUtil.getPricingServices(
            page = KycPages.ID,
            pricing = pricingData,
            event = EventTypes.STEP_FAILED,
            govViewModel = null,
        )

        assertEquals(listOf("kyc_document_analysis"), services)
    }

    @Test
    fun `government data page adds bvn price for selected identity`() {
        every { govViewModel.selectedGovDataLiveData.value } returns DojahEnumAttr(
            name = "Bank Verification Number",
            enum = "BVN",
        )
        every { govViewModel.getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey) } returns Step(
            id = 3,
            name = KycPages.GOVERNMENT_DATA.serverKey,
            config = Config(bvn = true, bvnAdvance = false),
        )

        val services = DojahPricingUtil.getPricingServices(
            page = KycPages.GOVERNMENT_DATA,
            pricing = pricingData,
            event = EventTypes.STEP_COMPLETED,
            govViewModel = govViewModel,
        )

        assertEquals(listOf("kyc_bvn_full"), services)
    }

    @Test
    fun `government data page uses bvnAdvance price when configured`() {
        every { govViewModel.selectedGovDataLiveData.value } returns
            GovDataViewModelTestSupport.bvnIdentity()
        every { govViewModel.getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey) } returns Step(
            id = 3,
            name = KycPages.GOVERNMENT_DATA.serverKey,
            config = Config(bvnAdvance = true),
        )

        val services = DojahPricingUtil.getPricingServices(
            page = KycPages.GOVERNMENT_DATA,
            pricing = pricingData,
            event = EventTypes.STEP_COMPLETED,
            govViewModel = govViewModel,
        )

        assertEquals(listOf("kyc_bvn_advance"), services)
    }

    @Test
    fun `selfie page adds liveness selfie price for selected verification type`() {
        every { govViewModel.verificationTypeLiveData.value } returns
            com.dojah.kyc_sdk_kotlin.ui.utils.VerificationType.Selfie

        val services = DojahPricingUtil.getPricingServices(
            page = KycPages.SELFIE,
            pricing = pricingData,
            event = EventTypes.STEP_COMPLETED,
            govViewModel = govViewModel,
        )

        assertEquals(listOf("kyc_liveness_selfie"), services)
    }
}
