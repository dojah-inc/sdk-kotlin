package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.view.PreviewView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.FragmentCaptureDocumentBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.utils.*
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor


@AndroidEntryPoint
class CaptureDocumentFragment : ErrorFragment() {

    private val binding by viewBinding { FragmentCaptureDocumentBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()
    private val logger: HttpLoggingInterceptor.Logger = HttpLoggingInterceptor.Logger.DEFAULT


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capture_document, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            //start camera
            CameraUtil.startCamera(requireParentFragment(), binding.camera, isFront = false) {
                progressBg.isVisible = it == PreviewView.StreamState.IDLE
                progress.isVisible = it == PreviewView.StreamState.IDLE
            }

            logger.log("current page @frag capture: ${navViewModel.currentPage}")
            if (navViewModel.currentPage == KycPages.OTHER_DOCUMENT.serverKey) {
                val otherDocStep =
                    viewModel.getStepWithPageName(KycPages.OTHER_DOCUMENT.serverKey)
                title.text = otherDocStep?.config?.title
                infoText.text = otherDocStep?.config?.instruction
                logger.log("title: ${otherDocStep?.config?.title}")
                logger.log("info: ${otherDocStep?.config?.instruction}")

            } else {
                val selectedDoc = viewModel.docTypeLiveData.value
                title.text = selectedDoc?.title
                infoText.text = selectedDoc?.info
            }


            captureBtn.setOnClickListener {
                CameraUtil.takePicture(
                    context = requireContext(),
                    tmpFileNamePrefix = "doc_type_${viewModel.docTypeLiveData.value?.id}",
                    onSaved = {
                        val savedUri = Uri.fromFile(it)
                        viewModel.setFrontDocUri(requireContext(),savedUri, isUpload = false)
                        navViewModel.navigate(Routes.preview_doc_route)
                    })

            }

            uploadBtn.setOnClickListener {
                navViewModel.navigate(Routes.upload_doc_route)
            }

        }
    }
}