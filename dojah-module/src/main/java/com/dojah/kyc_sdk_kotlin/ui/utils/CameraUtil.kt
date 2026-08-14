package com.dojah.kyc_sdk_kotlin.ui.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Size
import android.widget.Toast
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
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
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor

class CameraUtil {

    companion object {

        private var imageCapture: ImageCapture? = null
        private var imageAnalyzer: ImageAnalysis? = null
        private var videoCapture: VideoCapture<Recorder>? = null

        @SuppressLint("StaticFieldLeak")
        private var cameraProvider: ProcessCameraProvider? = null
        private var recordingObj: Recording? = null

        fun closeCamera() {
            if (recordingObj != null) {
                recordingObj?.close()
                cameraProvider?.unbindAll()
            }
        }

        fun takePicture(
            context: Context,
            tmpFileNamePrefix: String = "capture_selfie",
            photoExtension: String = ".jpg",
            onSaved: (photoFile: File) -> Unit
        ) {
            val photoFile =
                File.createTempFile(tmpFileNamePrefix, photoExtension, context.cacheDir)

            // Create output options object which contains file + metadata
            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            imageCapture?.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {
                        cameraProvider?.unbindAll()
                        Toast.makeText(context, exc.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        cameraProvider?.unbindAll()

                        onSaved(photoFile)

                    }
                })
        }

        fun recordVideo(
            context: Context,
            tmpFileNamePrefix: String = "Dojah-video_verify-recording-",
            photoExtension: String = ".mp4",
            onSaved: (photoFile: Uri) -> Unit
        ) {
            // Create MediaStoreOutputOptions for our recorder
            val name = tmpFileNamePrefix +
                    SimpleDateFormat("dd-mm-yy", Locale.US)
                        .format(System.currentTimeMillis()) + photoExtension
            val contentValues = ContentValues().apply {
                put(MediaStore.Video.Media.DISPLAY_NAME, name)
            }


            val mediaStoreOutput = MediaStoreOutputOptions.Builder(
                context.contentResolver,
                MediaStore.Video.Media.INTERNAL_CONTENT_URI
            ).setContentValues(contentValues).build()

            // 2. Configure Recorder and Start recording to the mediaStoreOutput.
            recordingObj =
                videoCapture?.output?.prepareRecording(context, mediaStoreOutput)
                    ?.start(
                        ContextCompat.getMainExecutor(context),
                    ) {
                        if (it is VideoRecordEvent.Finalize) {
                            ///take path and send to preview screen
                            val videoUri = it.outputResults.outputUri
                            onSaved(videoUri)
                        }
                    }
        }

        fun startCamera(
            fragment: Fragment,
            camera: PreviewView,
            executor: Executor? = null,
            isVideo: Boolean = false,
            isFront: Boolean = true,
            isLiveness: Boolean = false,
            onImageChanged: (ImageProxy) -> Unit = {},
            onPreviewUpdate: (PreviewView.StreamState) -> Unit
        ) {
            // TextureView works on emulators and inside NestedScrollView / clipToOutline.
            // SurfaceView (PERFORMANCE) commonly stays black in those cases.
            camera.implementationMode = PreviewView.ImplementationMode.COMPATIBLE

            camera.previewStreamState.observe(fragment.viewLifecycleOwner) { state ->
                if (fragment.view == null) return@observe
                onPreviewUpdate(state)
            }

            camera.doOnLayout {
                if (!fragment.isAdded || fragment.view == null) return@doOnLayout
                bindCameraToLifecycle(
                    fragment = fragment,
                    camera = camera,
                    executor = executor,
                    isVideo = isVideo,
                    isFront = isFront,
                    isLiveness = isLiveness,
                    onImageChanged = onImageChanged
                )
            }
        }

        private fun bindCameraToLifecycle(
            fragment: Fragment,
            camera: PreviewView,
            executor: Executor?,
            isVideo: Boolean,
            isFront: Boolean,
            isLiveness: Boolean,
            onImageChanged: (ImageProxy) -> Unit
        ) {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(fragment.requireContext())

            cameraProviderFuture.addListener({
                if (!fragment.isAdded || fragment.view == null) return@addListener

                val provider = cameraProviderFuture.get().also { cameraProvider = it }
                val cameraSelector = resolveCameraSelector(provider, isFront) ?: run {
                    Timber.e("No camera available on this device or emulator")
                    return@addListener
                }

                val preview = Preview.Builder()
                    .build()
                    .also { it.setSurfaceProvider(camera.surfaceProvider) }

                var secondaryUseCase: UseCase? = when {
                    isVideo -> {
                        val qualitySelector = QualitySelector.fromOrderedList(
                            listOf(Quality.UHD, Quality.FHD, Quality.HD, Quality.SD),
                            FallbackStrategy.lowerQualityOrHigherThan(Quality.SD)
                        )
                        val recorder = Recorder.Builder().apply {
                            setAspectRatio(AspectRatio.RATIO_4_3)
                            setQualitySelector(qualitySelector)
                        }.build()
                        VideoCapture.withOutput(recorder).also { videoCapture = it }
                    }

                    isLiveness -> {
                        buildImageAnalyzer(preferTargetResolution = true, executor, onImageChanged)
                            .also { imageAnalyzer = it }
                    }

                    else -> ImageCapture.Builder().build().also { imageCapture = it }
                }

                var bound = bindUseCases(fragment, cameraSelector, preview, secondaryUseCase)

                // Emulator HALs often reject Preview + ImageAnalysis at a fixed resolution.
                if (!bound && isLiveness) {
                    imageAnalyzer = buildImageAnalyzer(
                        preferTargetResolution = false,
                        executor = executor,
                        onImageChanged = onImageChanged
                    )
                    secondaryUseCase = imageAnalyzer
                    bound = bindUseCases(fragment, cameraSelector, preview, secondaryUseCase)
                }

                if (!bound) {
                    val fallbackSelector = oppositeCameraSelector(cameraSelector)
                    if (fallbackSelector != null && provider.hasCamera(fallbackSelector)) {
                        bindUseCases(fragment, fallbackSelector, preview, secondaryUseCase)
                    }
                }
            }, ContextCompat.getMainExecutor(fragment.requireContext()))
        }

        private fun buildImageAnalyzer(
            preferTargetResolution: Boolean,
            executor: Executor?,
            onImageChanged: (ImageProxy) -> Unit
        ): ImageAnalysis {
            val builder = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)

            if (preferTargetResolution) {
                builder.setResolutionSelector(
                    ResolutionSelector.Builder()
                        .setResolutionStrategy(
                            ResolutionStrategy(
                                Size(640, 480),
                                ResolutionStrategy.FALLBACK_RULE_CLOSEST_LOWER_THEN_HIGHER
                            )
                        )
                        .build()
                )
            }

            return builder.build().also { analyzer ->
                executor?.let {
                    analyzer.setAnalyzer(it) { imageProxy ->
                        onImageChanged(imageProxy)
                    }
                }
            }
        }

        private fun resolveCameraSelector(
            provider: ProcessCameraProvider,
            preferFront: Boolean
        ): CameraSelector? {
            val preferred =
                if (preferFront) CameraSelector.DEFAULT_FRONT_CAMERA else CameraSelector.DEFAULT_BACK_CAMERA
            if (provider.hasCamera(preferred)) return preferred

            val fallback = oppositeCameraSelector(preferred) ?: return null
            if (provider.hasCamera(fallback)) {
                Timber.w("Preferred camera unavailable, falling back to the other lens")
                return fallback
            }
            return null
        }

        private fun oppositeCameraSelector(selector: CameraSelector): CameraSelector? {
            return when (selector) {
                CameraSelector.DEFAULT_FRONT_CAMERA -> CameraSelector.DEFAULT_BACK_CAMERA
                CameraSelector.DEFAULT_BACK_CAMERA -> CameraSelector.DEFAULT_FRONT_CAMERA
                else -> null
            }
        }

        private fun bindUseCases(
            fragment: Fragment,
            cameraSelector: CameraSelector,
            preview: Preview,
            secondaryUseCase: UseCase?
        ): Boolean {
            val provider = cameraProvider ?: return false
            return try {
                provider.unbindAll()
                if (secondaryUseCase != null) {
                    provider.bindToLifecycle(
                        fragment.viewLifecycleOwner,
                        cameraSelector,
                        preview,
                        secondaryUseCase
                    )
                } else {
                    provider.bindToLifecycle(
                        fragment.viewLifecycleOwner,
                        cameraSelector,
                        preview
                    )
                }
                true
            } catch (exc: Exception) {
                Timber.e(exc, "Use case binding failed")
                false
            }
        }

    }
}
