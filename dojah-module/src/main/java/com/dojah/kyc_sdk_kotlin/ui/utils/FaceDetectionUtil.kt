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

    private var singleFrame: Bitmap? = null

    fun getSingleFrameImage(context: Context): Uri? {
        return try {
            // Create temp file
            val photoFile = File.createTempFile(
                "capture_selfie_liveness",
                ".jpg",
                context.cacheDir
            )

            // Compress and save bitmap
            FileOutputStream(photoFile).use { outputStream ->
                singleFrame?.rotate(-90F)?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
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
        imageProxy.image?.let {
            val image = InputImage.fromMediaImage(it, imageProxy.imageInfo.rotationDegrees)
            singleFrame = imageProxy.toBitmap()

            this.faceDetector.process(image)
                .addOnSuccessListener { faces ->
                    onFacesDetected(faces, singleFrame)
                }
                .addOnFailureListener { error ->
                    // Handle any errors here
                    println("Error detecting faces: ${error.message}")
                }
                .addOnCompleteListener {
                    // When done, close the image
                    imageProxy.close()
                }

        }?.run { imageProxy.close() }
    }

    companion object Companion {
        const val TAG = "FaceDetector"
    }
}
