package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.Manifest
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.FragmentCaptureSelfieBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.dialog.CameraPermissionDialogFragment
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.*
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.openAppSystemSettings
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale


@AndroidEntryPoint
class CaptureSelfieFragment : ErrorFragment() {

    private val binding by viewBinding { FragmentCaptureSelfieBinding.bind(it) }

    private lateinit var cameraContract: ActivityResultLauncher<String>

    private var imageCapture: ImageCapture? = null

    private var videoCapture: VideoCapture<Recorder>? = null

    private var cameraProvider: ProcessCameraProvider? = null


    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val govDataViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this.requireActivity())

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            cameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.camera.surfaceProvider)
                }

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            val isVideo =
                govDataViewModel.verificationTypeLiveData.value == VerificationType.SelfieVideo
            if (isVideo) {

                val qualitySelector = QualitySelector.fromOrderedList(
                    listOf(Quality.UHD, Quality.FHD, Quality.HD, Quality.SD),
                    FallbackStrategy.lowerQualityOrHigherThan(Quality.SD)
                )
                val recorder = Recorder.Builder().apply {
                    setAspectRatio(AspectRatio.RATIO_4_3)
                    setQualitySelector(qualitySelector)
                }.build()
//                val photoFile =
//                    File.createTempFile("driveLicence", ".jpg", requireContext().cacheDir)

                videoCapture = VideoCapture.withOutput(recorder)

                try {
                    // Unbind use cases before rebinding
                    cameraProvider?.unbindAll()

                    // Bind use cases to camera
                    cameraProvider?.bindToLifecycle(
                        this, cameraSelector, preview, videoCapture
                    )

//                    cameraProvider.
                } catch (exc: Exception) {
                    Timber.e("Use case binding failed")
                }

            } else {
//                cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                imageCapture = ImageCapture.Builder()
                    .build()
                try {
                    // Unbind use cases before rebinding
                    cameraProvider?.unbindAll()
                    // Bind use cases to camera
                    cameraProvider?.bindToLifecycle(
                        this, cameraSelector, preview, imageCapture
                    )
                } catch (exc: Exception) {
                    Timber.e("Use case binding failed")
                }
            }


        }, ContextCompat.getMainExecutor(this.requireContext()))
    }

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
        cameraContract =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if (granted) {
                    startCamera()
                } else {
//                    cameraContract.launch(Manifest.permission.CAMERA)
                    showPermissionError()
                }
            }

        cameraContract.launch(Manifest.permission.CAMERA)
        binding.apply {
            val type = govDataViewModel.verificationTypeLiveData.value
            titleText.text = type?.title
            (type == VerificationType.SelfieVideo).also { isVideo ->
                if (isVideo) {
                    startRecording.isVisible = true
                    captureBtn.isVisible = false
                }
            }
            camera.setOnClickListener {
                cameraContract.launch(Manifest.permission.CAMERA)
            }
            captureBtn.setOnClickListener {
                takePicture(
                    onSaved = {
                        val savedUri = Uri.fromFile(it)
                        viewModel.setSelfieUri(savedUri)
                        previewSelfie()
                    })
            }
            var recordingObj: Recording? = null
            startRecording.setOnClickListener {
                startRecording.isVisible = false
                doneBtn.isVisible = true

                recordingObj = recordVideo(onSaved = { videoUri ->
                    viewModel.setSelfieUri(videoUri)
                    previewSelfie()
                })
            }
            doneBtn.setOnClickListener {
                if (recordingObj != null) {
                    recordingObj!!.close()
                    cameraProvider?.unbindAll()
                }
            }

        }
    }

    private fun previewSelfie() {
        navViewModel.navigate(Routes.preview_selfie_fragment)
    }

    private fun recordVideo(
        tmpFileNamePrefix: String = "Dojah-video_verify-recording-",
        photoExtension: String = ".mp4",
        onSaved: (photoFile: Uri) -> Unit
    ): Recording? {
        // Create MediaStoreOutputOptions for our recorder
        val name = tmpFileNamePrefix +
                SimpleDateFormat("dd-mm-yy", Locale.US)
                    .format(System.currentTimeMillis()) + photoExtension
        val contentValues = ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, name)
        }


        val mediaStoreOutput = MediaStoreOutputOptions.Builder(
            requireActivity().contentResolver,
            MediaStore.Video.Media.INTERNAL_CONTENT_URI
        ).setContentValues(contentValues).build()

        // 2. Configure Recorder and Start recording to the mediaStoreOutput.
        val recording =
            videoCapture?.output?.prepareRecording(requireContext(), mediaStoreOutput)
                ?.start(
                    ContextCompat.getMainExecutor(requireContext()),
                ) {
                    if (it is VideoRecordEvent.Finalize) {
                        ///take path and send to preview screen
                        val videoUri = it.outputResults.outputUri
                        onSaved(videoUri)
                    }
                }
        return recording
    }

    private fun takePicture(
        tmpFileNamePrefix: String = "capture_selfie",
        photoExtension: String = ".jpg",
        onSaved: (photoFile: File) -> Unit
    ) {
        val photoFile =
            File.createTempFile(tmpFileNamePrefix, photoExtension, requireContext().cacheDir)

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture?.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    cameraProvider?.unbindAll()
                    Toast.makeText(requireContext(), exc.message, Toast.LENGTH_SHORT).show()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    cameraProvider?.unbindAll()

                    onSaved(photoFile)

                }
            })
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

            show(this@CaptureSelfieFragment.childFragmentManager, null)
        }
    }
}