package com.dojah.kyc_sdk_kotlin.ui.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.dojah.kyc_sdk_kotlin.utils.rotate
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetector
import java.io.File
import java.io.FileOutputStream

class FaceDetectionUtil(
    private val faceDetector: FaceDetector,
    private val onFacesDetected: (faces: List<Face>, image: Bitmap?) -> Unit
) : ImageAnalysis.Analyzer {

    @Volatile private var stopped = false
    private var singleFrame: Bitmap? = null

    fun start() {
        stopped = false
        singleFrame = null
    }

    fun stop() {
        stopped = true
    }

    fun getSingleFrameImage(context: Context): Uri? {
        val frame = singleFrame ?: return null

        return try {
            // Create temp file
            val photoFile = File.createTempFile(
                "capture_selfie_liveness",
                ".jpg",
                context.cacheDir
            )

            // Compress and save bitmap
            FileOutputStream(photoFile).use { outputStream ->
                frame.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
            }

            // Get URI using FileProvider
            Uri.fromFile(photoFile)
        } catch (_: Exception) {
            return null
        }
    }

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        if (stopped) {
            imageProxy.close()
            return
        }

        val mediaImage = imageProxy.image
        if (mediaImage == null) {
            imageProxy.close()
            return
        }

        try {
            // Capture bitmap BEFORE starting async ML Kit processing.
            // InputImage.fromMediaImage holds a reference to the same YUV ByteBuffers that
            // the JNI face detector reads natively. Calling toBitmap() after process() starts
            // causes concurrent native buffer access from two threads → segfault.
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            val bitmap = imageProxy.toBitmap().let { frame ->
                if (rotationDegrees == 0) frame else frame.rotate(rotationDegrees.toFloat())
            }
            singleFrame = bitmap
            val image = InputImage.fromMediaImage(mediaImage, rotationDegrees)

            this.faceDetector.process(image)
                .addOnSuccessListener { faces ->
                    onFacesDetected(faces, bitmap)
                }
                .addOnFailureListener { error ->
                    println("Error detecting faces: ${error.message}")
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        } catch (error: Exception) {
            imageProxy.close()
            println("Error preparing image for face detection: ${error.message}")
        }
    }

    companion object Companion {
        const val TAG = "FaceDetector"
    }
}
