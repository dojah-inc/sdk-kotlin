package com.dojah.sdk_kyc.domain.responses


import com.google.gson.annotations.SerializedName
import okhttp3.logging.HttpLoggingInterceptor

data class ImageAnalysisResponse(
    val entity: ImageAnalysisEntity?
) {
    data class ImageAnalysisEntity(
        val face: ImageAnalysisFace?
    ) {
        data class ImageAnalysisFace(
            @SerializedName("bounding_box") val boundingBox: ImageAnalysisBoundingBox?,
            val confidence: Double?, // 99.99983215332031
            val details: ImageAnalysisDetails?,
            @SerializedName("face_detected") val faceDetected: Boolean?, // true
            val message: String?, // face detected
            @SerializedName("multiface_detected") val multifaceDetected: Boolean?, // false
            val quality: ImageAnalysisQuality?
        ) {

            fun faceMessage(preAuthConfig: Config?): String {
                return if (message != null) {
                    message
                } else {
                    if (preAuthConfig == null) {
                        throw Exception("Config can't be null")
                    }
                    val brightness = quality?.brightness
                    val isBright =
                        brightness != null && brightness >= preAuthConfig.brightnessThreshold
                    val hasGlasses =
                        preAuthConfig.glassesCheck == (details?.eyeglasses?.value ?: false || details?.sunglasses?.value ?: false)

                    if (faceDetected == false) {
                        "No face detected"
                    } else if (multifaceDetected == true) {
                        "Multiple faces detected"
                    } else if (!isBright) {
                        "Not bright enough. Please move to a brighter area"
                    } else if (!hasGlasses) {
                        "Glasses detected. Retake selfie without your glasses"
                    } else
                        "Something went wrong"
                }
            }

            fun faceSuccess(preAuthConfig: Config?): Boolean {
                if (preAuthConfig == null) {
                    throw Exception("Config can't be null")
                }
                val brightness = quality?.brightness
                val isBright = brightness != null && brightness >= preAuthConfig.brightnessThreshold
                val hasGlasses =
                    preAuthConfig.glassesCheck == (details?.eyeglasses?.value ?: false || details?.sunglasses?.value ?: false)
                return faceDetected == true
                        && multifaceDetected == false
                        && isBright
                        && hasGlasses

            }

            data class ImageAnalysisBoundingBox(
                val height: Double?, // 0.41968902945518494
                val left: Double?, // 0.40705186128616333
                val top: Double?, // 0.3246147930622101
                val width: Double? // 0.17492765188217163
            )


            data class ImageAnalysisDetails(
                @SerializedName("age_range") val ageRange: ImageAnalysisAgeRange?,
                val beard: ImageAnalysisBeard?,
                val emotions: List<ImageAnalysisEmotion?>?,
                val eyeglasses: ImageAnalysisEyeglasses?,
                @SerializedName("eyes_open") val eyesOpen: ImageAnalysisEyesOpen?,
                val gender: ImageAnalysisGender?,
                @SerializedName("mouth_open") val mouthOpen: ImageAnalysisMouthOpen?,
                val mustache: ImageAnalysisMustache?,
                val smile: ImageAnalysisSmile?,
                val sunglasses: ImageAnalysisSunglasses?
            ) {

                data class ImageAnalysisAgeRange(
                    val high: Int?, // 33
                    val low: Int? // 25
                )


                data class ImageAnalysisBeard(
                    val confidence: Double?, // 77.6823959350586
                    val value: Boolean? // true
                )


                data class ImageAnalysisEmotion(
                    val confidence: Double?, // 92.67578125
                    val type: String? // CALM
                )


                data class ImageAnalysisEyeglasses(
                    val confidence: Int?, // 100
                    val value: Boolean? // true
                )


                data class ImageAnalysisEyesOpen(
                    val confidence: Double?, // 98.76229858398438
                    val value: Boolean? // true
                )


                data class ImageAnalysisGender(
                    val confidence: Double?, // 99.9473876953125
                    val value: String? // Male
                )


                data class ImageAnalysisMouthOpen(
                    val confidence: Double?, // 95.53598022460938
                    val value: Boolean? // false
                )


                data class ImageAnalysisMustache(
                    val confidence: Double?, // 94.59651947021484
                    val value: Boolean? // false
                )


                data class ImageAnalysisSmile(
                    val confidence: Double?, // 99.9901123046875
                    val value: Boolean? // false
                )


                data class ImageAnalysisSunglasses(
                    val confidence: Double?, // 99.99800109863281
                    val value: Boolean? // false
                )
            }


            data class ImageAnalysisQuality(
                val brightness: Double?, // 65.3563461303711
                val sharpness: Double? // 98.08562469482422
            )
        }
    }
}