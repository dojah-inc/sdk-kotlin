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
import com.dojah.kyc_sdk_kotlin.databinding.FragmentCaptureUtilityBillBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.CameraUtil
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding

class CaptureUtilityBillFragment : ErrorFragment() {
    private val binding by viewBinding { FragmentCaptureUtilityBillBinding.bind(it) }
    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capture_utility_bill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            //start camera
            CameraUtil.startCamera(requireParentFragment(), binding.camera, isFront = false) {
                progressBg.isVisible = it == PreviewView.StreamState.IDLE
                progress.isVisible = it == PreviewView.StreamState.IDLE
            }

            captureBtn.setOnClickListener {
                CameraUtil.takePicture(
                    context = requireContext(),
                    tmpFileNamePrefix = "doc_type_utility_bill_",
                    onSaved = {
                        val savedUri = Uri.fromFile(it)
                        viewModel.setUtilityBillUri(requireContext(), savedUri)
                        navViewModel.navigate(Routes.preview_utility_billl_route)
                    })
            }

            uploadBtn.setOnClickListener {
                CameraUtil.closeCamera()
                navViewModel.navigate(Routes.upload_utility_billl_route)
            }

        }
    }

    override fun onDestroy() {
        CameraUtil.closeCamera()
        super.onDestroy()
    }

}