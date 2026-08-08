package com.dojah.kyc_sdk_kotlin.ui.main.viewmodel

import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dojah.kyc_sdk_kotlin.domain.Country
import com.dojah.kyc_sdk_kotlin.domain.CountryState
import com.dojah.kyc_sdk_kotlin.testutil.MainDispatcherRule
import com.dojah.kyc_sdk_kotlin.testutil.VerificationViewModelTestSupport
import com.dojah.kyc_sdk_kotlin.testutil.getOrAwaitValue
import com.dojah.kyc_sdk_kotlin.ui.utils.GovDocType
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
class VerificationViewModelStateTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var harness: VerificationViewModelTestSupport.Harness

    @Before
    fun setUp() {
        harness = VerificationViewModelTestSupport.createHarness()
    }

    @Test
    fun `setSelectedCountry updates selected country live data`() {
        val country = Country(id = "NG", name = "Nigeria", code = "NG", path = "ng")

        harness.viewModel.setSelectedCountry(country)

        assertEquals(country, harness.viewModel.selectedCountryLiveData.value)
    }

    @Test
    fun `selectDocType maps string to GovDocType`() {
        harness.viewModel.selectDocType("International Passport")

        assertEquals(GovDocType.PASSPORT, harness.viewModel.docTypeLiveData.value)
    }

    @Test
    fun `getStateCities returns empty list when states are not loaded`() {
        harness.viewModel.getStateCities("Lagos", filter = "ike")

        assertEquals(emptyList<String>(), harness.viewModel.cities.value)
    }

    @Test
    fun `getStateCities returns matching cities for loaded state`() = runTest {
        val states = listOf(
            CountryState(
                name = "Lagos",
                subdivision = listOf("Ikeja", "Epe", "Badagry"),
            ),
        )
        coEvery { harness.countryManager.getCountryStatesList("Nigeria") } returns states
        every { harness.prefManager.getUserCountryName() } returns "Nigeria"

        harness.viewModel.states.getOrAwaitValue(
            afterObserve = { harness.viewModel.loadUserCountryStates() },
        ) { it.isNotEmpty() }
        harness.viewModel.getStateCities("Lagos", filter = "epe")

        assertEquals(listOf("Epe"), harness.viewModel.cities.value)
    }

    @Test
    fun `setFrontDocUri stores front document metadata for capture path`() {
        val context = mockk<android.content.Context>(relaxed = true)
        val uri = Uri.parse("file:///storage/emulated/0/front_doc.jpg")

        val docInfo = harness.viewModel.setFrontDocUri(context, uri, isUpload = false)

        assertEquals("front_doc", docInfo?.docName)
        assertEquals("jpg", docInfo?.docType)
        assertEquals(uri, harness.viewModel.frontDocUriLiveData.value)
        assertFalse(harness.viewModel.isBackDocLiveData.value == true)
    }

    @Test
    fun `setBackDocUri stores back document metadata`() {
        val context = mockk<android.content.Context>(relaxed = true)
        val uri = Uri.parse("file:///storage/emulated/0/back_doc.png")

        val docInfo = harness.viewModel.setBackDocUri(context, uri, isUpload = false)

        assertEquals("back_doc", docInfo?.docName)
        assertEquals("png", docInfo?.docType)
        assertTrue(harness.viewModel.isBackDocLiveData.value == true)
        assertEquals(uri, harness.viewModel.backDocUriLiveData.value)
    }

    @Test
    fun `building photo state tracks index and clears all slots`() {
        val context = mockk<android.content.Context>(relaxed = true)
        val uri = Uri.parse("file:///storage/building.jpg")

        harness.viewModel.setCurrentBuildingPhotoIndex(1)
        harness.viewModel.setBuildingPhotoUri(context, index = 1, uri = uri, isUpload = false)

        assertEquals(1, harness.viewModel.currentBuildingPhotoIndex)
        assertNotNull(harness.viewModel.buildingPhotoUrisLiveData.value?.get(1))

        harness.viewModel.clearBuildingPhotos()

        assertEquals(0, harness.viewModel.currentBuildingPhotoIndex)
        assertEquals(listOf(null, null, null), harness.viewModel.buildingPhotoUrisLiveData.value)
    }

    @Test
    fun `setSelfieUri and setAnalysisResult update selfie state`() {
        val uri = Uri.parse("file:///storage/selfie.jpg")

        harness.viewModel.setSelfieUri(uri)
        harness.viewModel.setAnalysisResult("Face detected")

        assertEquals(uri, harness.viewModel.selfieUriLiveData.value)
        assertEquals("Face detected", harness.viewModel.selfieAnalyisResultLiveData.value)
    }

    @Test
    fun `getStepWithPageName returns configured step from cached auth data`() {
        val step = harness.viewModel.getStepWithPageName(KycPages.USER_DATA.serverKey)

        assertEquals("user-data", step?.name)
        assertEquals(2, step?.id)
    }

    @Test
    fun `isLastPage identifies final configured step`() {
        assertTrue(harness.viewModel.isLastPage(KycPages.OTHER_DOCUMENT))
        assertFalse(harness.viewModel.isLastPage(KycPages.USER_DATA))
    }

    @Test
    fun `buildEventRequest uses verification id from auth cache`() {
        val request = harness.viewModel.buildEventRequest(
            services = listOf("kyc_bvn"),
            eventType = "step_completed",
            eventValue = "done",
            stepNumber = 2,
        )

        assertEquals(17066, request.verificationId)
        assertEquals("step_completed", request.eventType)
        assertEquals(listOf("kyc_bvn"), request.services)
    }

    @Test
    fun `resetAuthVerificationCompleted clears completion flag`() {
        harness.viewModel.resetAuthVerificationCompleted()

        assertEquals(false, harness.viewModel.authVerificationCompletedLD.value)
    }
}
