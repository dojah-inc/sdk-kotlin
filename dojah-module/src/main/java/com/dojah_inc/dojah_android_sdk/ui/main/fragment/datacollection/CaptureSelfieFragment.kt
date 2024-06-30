package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection
import com.dojah_inc.dojah_android_sdk.DojahSdk

import android.content.res.ColorStateList
import android.graphics.Color
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
import com.dojah_inc.dojah_android_sdk.databinding.FragmentCaptureSelfieBinding
import com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.GovDataViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.*
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding

import okhttp3.logging.HttpLoggingInterceptor



class CaptureSelfieFragment : ErrorFragment() {

    private val binding by viewBinding { FragmentCaptureSelfieBinding.bind(it) }


        private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val govDataViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capture_selfie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val type = govDataViewModel.verificationTypeLiveData.value
        val isVideo =
            type == VerificationType.SelfieVideo

        binding.apply {

            viewModel.prefManager.getMaterialButtonBgColor?.also {
                try {
                    innerDot.backgroundTintList = ColorStateList.valueOf(Color.parseColor(it))
                } catch (e: Exception) {
                    HttpLoggingInterceptor.Logger.DEFAULT.log("${e.message}")
                }
            }

//            val currentPageName =
//                navViewModel.currentPage ?: KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey
//            val isFront = viewModel.getStepWithPageName(currentPageName)?.config?.flipCamera?:false
            //start camera
            CameraUtil.startCamera(requireParentFragment(), binding.camera, isVideo = isVideo ) {
                progressBg.isVisible = it == PreviewView.StreamState.IDLE
                progress.isVisible = it == PreviewView.StreamState.IDLE
            }

            titleText.text = type?.title

            (type == VerificationType.SelfieVideo).also { isVideo ->
                if (isVideo) {
                    startRecording.isVisible = true
                    captureBtn.isVisible = false
                }
            }
            captureBtn.setOnClickListener {
                CameraUtil.takePicture(
                    context = requireContext(),
                    onSaved = {
                        val savedUri = Uri.fromFile(it)
                        viewModel.setSelfieUri(savedUri)
                        previewSelfie()
                    })
            }
            startRecording.setOnClickListener {
                startRecording.isVisible = false
                doneBtn.isVisible = true
                CameraUtil.recordVideo(
                    context = requireContext(),
                    onSaved = { videoUri ->
                        viewModel.setSelfieUri(videoUri)
                        previewSelfie()
                    })
            }
            doneBtn.setOnClickListener {
                CameraUtil.closeCamera()
            }

        }
    }

    private fun previewSelfie() {
        navViewModel.navigate(Routes.preview_selfie_fragment)
    }


}