package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.content.ContentValues
import android.content.res.ColorStateList
import android.graphics.Color
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CaptureRequest
import android.hardware.camera2.CaptureResult
import android.hardware.camera2.TotalCaptureResult
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FallbackStrategy
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.databinding.FragmentCaptureSelfieBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.*
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale


@AndroidEntryPoint
class CaptureSelfieFragment : ErrorFragment() {

    private val binding by viewBinding { FragmentCaptureSelfieBinding.bind(it) }


    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val govDataViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()

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