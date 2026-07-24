package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection

import android.graphics.Bitmap
import android.graphics.Outline
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import androidx.camera.view.PreviewView
import androidx.core.graphics.get
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.util.CancellableCountDownTimer
import com.dojah.kyc_sdk_kotlin.databinding.FragmentCaptureSelfieBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.CameraUtil
import com.dojah.kyc_sdk_kotlin.ui.utils.FaceDetectionUtil
import com.dojah.kyc_sdk_kotlin.ui.utils.VerificationType
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.load
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.math.abs


class CaptureSelfieFragment : ErrorFragment() {

    private var cameraExecutor: ExecutorService? = null
    private lateinit var faceDetection: FaceDetectionUtil
    lateinit var faceDetector: FaceDetector
    private var verificationImage: String? = null

    private val binding by viewBinding { FragmentCaptureSelfieBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val govDataViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }

    @RequiresApi(Build.VERSION_CODES.O)
    private val captureReadyTimer = CancellableCountDownTimer(
        totalSeconds = 3L,
        onTick = {
            if (it > 0) {
                binding.cameraReadyCounter.text = it.toString()
                binding.titleText.text = getString(R.string.about_to_capture_selfie_label)
            } else {
                binding.titleText.text = getString(R.string.now_capturing_selfie_label)
            }

            binding.animationView.isVisible = false
        },
        onStart = {
            binding.cameraReadyCounter.isVisible = true
        },
        onFinish = {
            faceDetection.getSingleFrameImage(requireContext())?.let {
                faceDetection.stop()
                cameraExecutor?.shutdown()
                cameraExecutor = null

                viewModel.setSelfieUri(it)

                binding.root.post {
                    binding.cameraReadyCounter.isVisible = false
                    binding.animationView.isVisible = true
                    binding.camera.isVisible = false
                    binding.cameraPreview.load(it, isCenterCrop = true)
                    binding.selfieImageNotifierView.isVisible = false
                    binding.cameraPreview.isVisible = true
                }
            }
        }
    )

//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun performAnalysis(uri: Uri? = null) {
//        val verificationType = govDataViewModel.verificationTypeLiveData.value
//        if (verificationType == VerificationType.Selfie) {
//            if (uri == null) {
//                throw Exception("Uri is null")
//            }
//            govDataViewModel.startLoadingImageAnalysis()
//            verificationImage = uri.toFile().readBytes().toByteString().base64()
//            govDataViewModel.performImageAnalysis(
//                verificationImage!!.encrypted(),
//                currentRoute = navViewModel.currentPage
//            )
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun registerObserver() {
        viewModel.selfieUriLiveData.observe(this) { uri ->
            uri?.let {
                viewModel.setAnalysisResult(null)
                previewSelfie()
                //performAnalysis(uri)
            }
        }

//        govDataViewModel.imageAnalysisLiveData.observe(this) { result ->
//            binding.root.post {
//                with(binding) {
//                    animationView.isVisible = result is Result.Loading
//
//                    when (result) {
//                        is Result.Success -> {
//                            val faceResult = result.data?.entity?.face
//                            val config = viewModel.getStepWithPageName(
//                                navViewModel.currentPage ?: KycPages.GOVERNMENT_DATA.serverKey
//                            )?.config
//
//                            val error = when {
//                                faceResult == null -> FailedReasons.SELFIE_NO_CAPTURE.message
//                                faceResult.faceSuccess(config) -> null
//                                else -> faceResult.getFaceErrorMessage(config)
//                            }
//
//                            viewModel.setAnalysisResult(error)
//                            previewSelfie()
//                        }
//
//                        is Result.Error -> {
//                            viewModel.setAnalysisResult(FailedReasons.SELFIE_NO_CAPTURE.message)
//                            previewSelfie()
//                        }
//
//                        else -> {}
//                    }
//                }
//            }
//        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .build()

        registerObserver()
        faceDetector = FaceDetection.getClient(options)

        faceDetection = FaceDetectionUtil(
            faceDetector = faceDetector,
            onFacesDetected = ::onFacesDetected,
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startTimer() {
        captureReadyTimer.start()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun stopTimer() {
        captureReadyTimer.stop()
        binding.cameraReadyCounter.isVisible = false
    }

    private fun getDistanceError(face: Face, image: Bitmap): String? {
        val bounds = face.boundingBox
        val faceWidth = bounds.width().toFloat()
        val imageWidth = image.width.toFloat()
        val faceProportion = faceWidth / imageWidth

        val minProportion = 0.3f // Minimum acceptable proportion
        val maxProportion = 0.8f // Maximum acceptable proportion

        return when {
            faceProportion < minProportion -> getString(R.string.move_closer)
            faceProportion > maxProportion -> getString(R.string.move_back)
            else -> null
        }
    }

    private fun getCenteringError(face: Face, image: Bitmap?): String? {
        if (image == null) return null
        val bounds = face.boundingBox
        val faceCenterX = bounds.centerX().toFloat()
        val faceCenterY = bounds.centerY().toFloat()
        val imageCenterX = image.width.toFloat() / 2
        val imageCenterY = image.height.toFloat() / 2

        val offsetX = abs(faceCenterX - imageCenterX)
        val offsetY = abs(faceCenterY - imageCenterY)

        val thresholdX = image.width * 0.1f // 10% of image width
        val thresholdY = image.height * 0.1f // 10% of image height

        return when {
            offsetX > thresholdX
                    || offsetY > thresholdY -> getString(R.string.centre_face)

            else -> null
        }
    }

    private fun getLightingError(image: Bitmap?): String? {
        if (image == null) return null
        var totalBrightness = 0L
        val width = image.width
        val height = image.height
        val totalPixels = width * height

        for (x in 0 until width step 10) { // Sample every 10th pixel for performance
            for (y in 0 until height step 10) {
                val pixel = image[x, y]
                val r = (pixel shr 16) and 0xff
                val g = (pixel shr 8) and 0xff
                val b = pixel and 0xff
                val brightness = (r + g + b) / 3
                totalBrightness += brightness
            }
        }

        val avgBrightness = totalBrightness / (totalPixels / 100) // Adjusted for sampling

        return when {
            avgBrightness < 50 -> getString(R.string.more_lighting)
            avgBrightness > 200 -> getString(R.string.reduce_lighting)
            else -> null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onFacesDetected(faces: List<Face>, image: Bitmap?) {
        with(binding) {

            val outlinedPattern: Int = if (faces.isNotEmpty() && faces.size == 1) {
                R.drawable.ic_camera_face_detected
            } else {
                R.drawable.ic_capture_ready
            }

            selfieImageNotifierView.setBackgroundResource(outlinedPattern)

            if (faces.isEmpty()) {
                titleText.text = getString(R.string.place_your_face_in_the_circle_and_click_capture)
                stopTimer()
                return
            }

            if (faces.size > 1) {
                titleText.text = getString(R.string.multiple_faces_detected)
                stopTimer()
                return
            }

            val result = getLightingError(image) ?: getDistanceError(
                faces.firstOrNull() ?: return,
                image ?: return
            ) ?: getCenteringError(faces.first(), image)

            if (result != null) {
                titleText.text = result
                stopTimer()
                selfieImageNotifierView.setBackgroundResource(R.drawable.ic_capture_ready)

                return
            }

            if (faces.isNotEmpty()) {
                startTimer()
            } else {
                stopTimer()
                titleText.text = getString(R.string.place_your_face_in_the_circle_and_click_capture)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capture_selfie, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDestroy() {
        faceDetection.stop()
        cameraExecutor?.shutdown()
        captureReadyTimer.stop()
        cameraExecutor = null
        faceDetector.close()
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val type = govDataViewModel.verificationTypeLiveData.value
        val isVideo =
            type == VerificationType.SelfieVideo

        cameraExecutor = Executors.newSingleThreadExecutor()

        binding.apply {

            // Set an outline provider to clip the CardView to a perfect oval
            cardView.outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    val width = view.width
                    val height = view.height
                    val margin = view.resources.getDimensionPixelSize(R.dimen.margin_18dp)
                    // Adjust for margins to align with the camera_overlay_view drawable
                    val left = margin
                    val top = margin
                    val right = width - margin
                    val bottom = height - margin
                    // Create a vertically stretched elliptical outline
                    outline.setOval(left, top, right, bottom)
                }
            }
            cardView.clipToOutline = true // Enable clipping based on the outline
            animationView.isVisible = false

//            viewModel.prefManager.getMaterialButtonBgColor?.also {
//                try {
//                    innerDot.backgroundTintList = ColorStateList.valueOf(Color.parseColor(it))
//                } catch (e: Exception) {
//                    HttpLoggingInterceptor.Logger.DEFAULT.log("${e.message}")
//                }
//            }

//            val currentPageName =
//                navViewModel.currentPage ?: KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey
//            val isFront = viewModel.getStepWithPageName(currentPageName)?.config?.flipCamera?:false
            //start camera
            CameraUtil.startCamera(
                requireParentFragment(),
                binding.camera,
                isVideo = isVideo,
                isLiveness = !isVideo,
                executor = cameraExecutor,
                onImageChanged = { imageProxy ->
                    cameraExecutor?.let {
                        faceDetection.analyze(imageProxy)
                    }
                }) {
                progressBg.isVisible = it == PreviewView.StreamState.IDLE
                progress.isVisible = it == PreviewView.StreamState.IDLE

                if (it != PreviewView.StreamState.IDLE) {
                    stopTimer()
                }
            }

            titleText.text = getString(R.string.place_your_face_in_the_circle_and_click_capture)

            (type == VerificationType.SelfieVideo).also { isVideo ->
                if (isVideo) {
                    startRecording.isVisible = true
                    // captureBtn.isVisible = false
                }
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