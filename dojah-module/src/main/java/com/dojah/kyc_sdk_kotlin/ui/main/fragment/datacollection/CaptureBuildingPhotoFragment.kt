package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.view.PreviewView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.FragmentCaptureBuildingPhotoBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.CameraUtil
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding

class CaptureBuildingPhotoFragment : ErrorFragment() {

    private val binding by viewBinding { FragmentCaptureBuildingPhotoBinding.bind(it) }
    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }

    companion object {
        const val TOTAL_PHOTOS = 3
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_capture_building_photo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val photoIndex = viewModel.currentBuildingPhotoIndex
        val humanIndex = photoIndex + 1
        val instructions = listOf(
            getString(R.string.building_photo_instruction_1),
            getString(R.string.building_photo_instruction_2),
            getString(R.string.building_photo_instruction_3),
        )

        val titles = listOf(
            getString(R.string.building_photo_title_1),
            getString(R.string.building_photo_title_2),
            getString(R.string.building_photo_title_3),
        )

        binding.apply {
            stepIndicator.text = getString(R.string.photo_of, humanIndex, TOTAL_PHOTOS)
            infoText.text = instructions.getOrElse(photoIndex) {
                getString(R.string.building_photo_instruction_1)
            }

            title.text = titles.getOrElse(photoIndex) {
                getString(R.string.building_photo_title_1)
            }

            CameraUtil.startCamera(
                requireParentFragment(),
                camera,
                isFront = false
            ) { streamState ->
                progressBg.isVisible = streamState == PreviewView.StreamState.IDLE
                progress.isVisible = streamState == PreviewView.StreamState.IDLE
            }

            captureBtn.setOnClickListener {
                CameraUtil.takePicture(
                    context = requireContext(),
                    tmpFileNamePrefix = "building_photo_${photoIndex}_",
                    onSaved = { file ->
                        val savedUri = Uri.fromFile(file)
                        viewModel.setBuildingPhotoUri(requireContext(), photoIndex, savedUri)
                        navViewModel.navigate(Routes.preview_building_photo_route)
                    }
                )
            }

            uploadBtn.setOnClickListener {
                CameraUtil.closeCamera()
                navViewModel.navigate(Routes.upload_building_photo_route)
            }
        }
    }

    override fun onDestroy() {
        CameraUtil.closeCamera()
        super.onDestroy()
    }
}

