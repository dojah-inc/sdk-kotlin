package com.dojah.sdk_kyc.ui.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
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
import androidx.fragment.app.Fragment
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class CameraUtil {

    companion object {

        private var imageCapture: ImageCapture? = null

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
            isVideo: Boolean = false,
            isFront: Boolean = true,
            onPreviewUpdate: (PreviewView.StreamState) -> Unit
        ) {
            camera.previewStreamState.observe(fragment.viewLifecycleOwner) {
                onPreviewUpdate(it)
            }

            val cameraProviderFuture = ProcessCameraProvider.getInstance(fragment.requireContext())

            cameraProviderFuture.addListener({
                // Used to bind the lifecycle of cameras to the lifecycle owner
                cameraProvider = cameraProviderFuture.get()


                // Preview
                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(camera.surfaceProvider)
                    }

                val cameraSelector =
                    if (isFront) CameraSelector.DEFAULT_FRONT_CAMERA else CameraSelector.DEFAULT_BACK_CAMERA


                if (isVideo) {

                    val qualitySelector = QualitySelector.fromOrderedList(
                        listOf(Quality.UHD, Quality.FHD, Quality.HD, Quality.SD),
                        FallbackStrategy.lowerQualityOrHigherThan(Quality.SD)
                    )
                    val recorder = Recorder.Builder().apply {
                        setAspectRatio(AspectRatio.RATIO_4_3)
                        setQualitySelector(qualitySelector)
                    }.build()

                    videoCapture = VideoCapture.withOutput(recorder)

                    try {
                        // Unbind use cases before rebinding
                        cameraProvider?.unbindAll()

                        // Bind use cases to camera
                        cameraProvider?.bindToLifecycle(
                            fragment.viewLifecycleOwner, cameraSelector, preview, videoCapture
                        )
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
                            fragment.viewLifecycleOwner, cameraSelector, preview, imageCapture
                        )
                    } catch (exc: Exception) {
                        Timber.e("Use case binding failed")
                    }
                }

            }, ContextCompat.getMainExecutor(fragment.requireContext()))
        }

    }
}