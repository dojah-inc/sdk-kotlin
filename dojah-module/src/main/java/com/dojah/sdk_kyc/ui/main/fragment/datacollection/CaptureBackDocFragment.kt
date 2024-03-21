package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.FragmentCaptureDocumentBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.dialog.CameraPermissionDialogFragment
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.CameraUtil
import com.dojah.sdk_kyc.ui.utils.KycPages
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.openAppSystemSettings
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File


@AndroidEntryPoint
class CaptureBackDocFragment : ErrorFragment() {

    private val binding by viewBinding { FragmentCaptureDocumentBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()

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