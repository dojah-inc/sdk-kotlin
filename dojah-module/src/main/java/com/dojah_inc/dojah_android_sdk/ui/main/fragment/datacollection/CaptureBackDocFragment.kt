package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection
import com.dojah_inc.dojah_android_sdk.DojahSdk

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.view.PreviewView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.databinding.FragmentCaptureDocumentBinding
import com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.dialog.CameraPermissionDialogFragment
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.CameraUtil
import com.dojah_inc.dojah_android_sdk.ui.utils.KycPages
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding
import com.dojah_inc.dojah_android_sdk.ui.utils.openAppSystemSettings




class CaptureBackDocFragment : ErrorFragment() {

    private val binding by viewBinding { FragmentCaptureDocumentBinding.bind(it) }

        private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>{ DojahSdk.dojahContainer.navViewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capture_document, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.apply {

            CameraUtil.startCamera(requireParentFragment(), binding.camera, isFront = false) {
                progressBg.isVisible = it == PreviewView.StreamState.IDLE
                progress.isVisible = it == PreviewView.StreamState.IDLE
            }

            if (navViewModel.currentPage == KycPages.OTHER_DOCUMENT.serverKey) {
                val otherDocStep =
                    viewModel.getStepWithPageName(KycPages.OTHER_DOCUMENT.serverKey)
                infoText.text = otherDocStep?.config?.instruction
            } else {
                val selectedDoc = viewModel.docTypeLiveData.value
                infoText.text = selectedDoc?.info
            }

            title.text = getString(R.string.capture_the_back_of_your_id)

            captureBtn.setOnClickListener {
                CameraUtil.takePicture(
                    context = requireContext(),
                    tmpFileNamePrefix = "doc_type_back_${viewModel.docTypeLiveData.value?.id}",
                    onSaved = {
                        val savedUri = Uri.fromFile(it)
                        viewModel.setBackDocUri(requireContext(), savedUri, isUpload = false)
                        navViewModel.navigate(Routes.preview_doc_route)
                    })
            }

            uploadBtn.setOnClickListener {
                navViewModel.navigate(Routes.upload_back_doc_route)
            }

        }
    }


    private fun showPermissionError() {

        CameraPermissionDialogFragment.getInstance(
        ).apply {
            onAllow = {
                requireContext().openAppSystemSettings()
            }
            onExitClick = {
//                navViewModel.popBackStack()
            }

            show(this@CaptureBackDocFragment.childFragmentManager, null)
        }


    }
}