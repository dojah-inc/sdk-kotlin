package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.core.util.encrypted
import com.dojah.kyc_sdk_kotlin.data.LocationManager
import com.dojah.kyc_sdk_kotlin.databinding.FragmentPreviewBuildingPhotoBinding
import com.dojah.kyc_sdk_kotlin.domain.DocumentInfo
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.load
import okio.ByteString.Companion.toByteString
import kotlin.getValue

class PreviewBuildingPhotoFragment : ErrorFragment() {

    private val binding by viewBinding { FragmentPreviewBuildingPhotoBinding.bind(it) }
    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.govViewModelFactory }

    private val locationManager: LocationManager by lazy {
        DojahSdk.dojahContainer.locationManager
    }

    companion object {
        const val TOTAL_PHOTOS = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_preview_building_photo, container, false)

    private fun observeLiveData() {
        govViewModel.submitLiveLocationLiveData.observe(this) {
            binding.root.post {
                binding.processing.isVisible = it is Result.Loading
                binding.btnContinue.isLoading = it is Result.Loading
                binding.btnContinue.isButtonEnabled = it !is Result.Loading
                binding.btnRetake.isEnabled = it !is Result.Loading
            }

            if (it is Result.Error) {
                govViewModel.resetDocTypeLiveData()
                navigateToErrorPage(it)
            } else if (it is Result.Success) {
                navViewModel.navigateNextStep()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val photoIndex = viewModel.currentBuildingPhotoIndex
        val humanIndex = photoIndex + 1

        binding.apply {
            stepIndicator.text = getString(R.string.photo_of, humanIndex, TOTAL_PHOTOS)
            processing.isVisible = false
            btnContinue.isButtonEnabled = true

            // Load the photo for the current index
            val docInfo = viewModel.buildingPhotoUrisLiveData.value?.getOrNull(photoIndex)
            docInfo?.docUri?.let { uri ->
                cameraPreview.load(uri, isCenterCrop = true)
            }

            btnRetake.setOnClickListener {
                // Go back to capture/upload screen for the same index
                navViewModel.popBackStack()
            }

            btnContinue.setOnClickListener {
                val nextIndex = photoIndex + 1
                if (nextIndex < TOTAL_PHOTOS) {
                    // Move to next photo: update index then navigate to capture
                    viewModel.setCurrentBuildingPhotoIndex(nextIndex)
                    navViewModel.navigate(Routes.capture_building_photo_route)
                } else {
                    // All 3 photos collected — proceed to next SDK step
                    val page = navViewModel.currentPage?.let { KycPages.findPageEnum(it) }
                        ?: KycPages.ADDRESS
                    val frontImage = viewModel.buildingPhotoUrisLiveData.value?.firstOrNull()
                        ?.imageUrl(requireContext()) ?: ""
                    val outsideImage = viewModel.buildingPhotoUrisLiveData.value?.get(1)
                        ?.imageUrl(requireContext()) ?: ""
                    val streetImage = viewModel.buildingPhotoUrisLiveData.value?.get(2)
                        ?.imageUrl(requireContext()) ?: ""

                    val latlng = locationManager.lastLocation
                    govViewModel.checkLiveLocationImages(
                        page,
                        frontImage.encrypted(),
                        outsideImage.encrypted(),
                        streetImage.encrypted(),
                        latlng?.first ?: 0.0,
                        latlng?.second ?: 0.0
                    )
                }
            }
        }
    }
}

fun DocumentInfo.imageUrl(context: Context): String? {
    return docUri?.let {
        val stream = context.contentResolver.openInputStream(it)
        val base64 = stream?.readBytes()?.toByteString()?.base64()
        stream?.close()
        base64
    }
}

