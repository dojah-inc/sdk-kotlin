package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.databinding.FragmentPreviewSelfieBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.analysisRetryMax
import com.dojah.sdk_kyc.ui.utils.*
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.echodev.resizer.Resizer
import okhttp3.logging.HttpLoggingInterceptor
import okio.ByteString.Companion.toByteString


@AndroidEntryPoint
class PreviewSelfieFragment : ErrorFragment() {


    private val binding by viewBinding { FragmentPreviewSelfieBinding.bind(it) }


    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()

    private var verificationImage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
        performAnalysis()
    }

    private fun observeLiveData() {
        govViewModel.imageAnalysisLiveData.observe(this) { result ->
            binding.root.post {
                binding.apply {
                    val (rootView: ViewGroup, windowBackground: Drawable?) = getBlurView()
                    val verificationType = govViewModel.verificationTypeLiveData.value

                    errorTag.isVisible = false
                    btnContinue.isLoading = result is Result.Loading
                    showLoadingProgress(
                        verificationType = verificationType,
                        uri = viewModel.selfieUriLiveData.value,
                        rootView = rootView,
                        windowBackground = windowBackground,
                        loading = result is Result.Loading
                    )
                    if (result is Result.Error || result is Result.Loading) {
                        btnContinue.isButtonEnabled = false
                    }
                    HttpLoggingInterceptor.Logger.DEFAULT.log("faceResult def frag ${result}")

                    if (result is Result.Success) {
                        val faceResult = result.data?.entity?.face
                        val config =
                            viewModel.getStepWithPageName(KycPages.GOVERNMENT_DATA.serverKey)?.config
                        HttpLoggingInterceptor.Logger.DEFAULT.log("faceResult $faceResult")
                        if (faceResult == null) {
                            errorTag.text = FailedReasons.SELFIE_NO_CAPTURE.message
                            errorTag.isVisible = true
                            btnContinue.isButtonEnabled = false
                        } else if (faceResult.faceSuccess(config)) {
                            errorTag.isVisible = false
                            btnContinue.isButtonEnabled = true
                            errorTag.isVisible = false
                        } else {
                            errorTag.text = faceResult.getFaceErrorMessage(config)
                            errorTag.isVisible = true
                        }
                    } else if (result is Result.Error) {
                        errorTag.text = FailedReasons.SELFIE_NO_CAPTURE.message
                        errorTag.isVisible = true
                    }
//                    govViewModel.resetImageAnalysisLiveData()
                }
            }
        }

        govViewModel.analysisRetryCountLiveData.observe(this) {
            binding.root.post {
                binding.apply {
                    if (it >= analysisRetryMax) {
                        errorTag.isVisible = false
                        btnRetake.isEnabled = false
                        btnContinue.isButtonEnabled = true
                    } else {
                        btnRetake.isEnabled = true
                        btnContinue.isButtonEnabled = false
                    }
                }
            }
        }
        govViewModel.submitLivenessLiveData.observe(this) {
            binding.root.post {
                binding.processing.isVisible = it is Result.Loading
                binding.btnContinue.isLoading = it is Result.Loading
            }

            if (it is Result.Error) {
                govViewModel.resetDocTypeLiveData()
                ///show error
                navigateToErrorPage(it)
            } else if (it is Result.Success) {
                govViewModel.resetDocTypeLiveData()
                navViewModel.navigateNextStep()
            }

        }
    }

    private fun performAnalysis(
    ) {
        val verificationType = govViewModel.verificationTypeLiveData.value
        val uri: Uri? =
            viewModel.selfieUriLiveData.value
        if (verificationType == VerificationType.Selfie) {
            if (uri == null) {
                throw Exception("Uri is null")
            }
            govViewModel.startLoadingImageAnalysis()
            govViewModel.viewModelScope.launch {
                val resizedImage = Resizer(requireContext())
                    .setTargetLength(1080)
                    .setQuality(80)
                    .setOutputFilename("resized_image")
                    .setSourceImage(uri.toFile())
                    .resizedFile
                verificationImage = resizedImage.readBytes().toByteString().base64()
                govViewModel.performImageAnalysis(verificationImage!!)
            }
        } else {
            viewModel.viewModelScope.launch {
                delay(2000)
                binding.root.post {
                    binding.apply {
                        btnContinue.isButtonEnabled = true
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview_selfie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel.prefManager.getMaterialButtonBgColor?.also {
                try {
                    innerDot.backgroundTintList = ColorStateList.valueOf(Color.parseColor(it))
                } catch (e: Exception) {
                    HttpLoggingInterceptor.Logger.DEFAULT.log("${e.message}")
                }
            }
            val verificationType = govViewModel.verificationTypeLiveData.value
            val uri: Uri? =
                viewModel.selfieUriLiveData.value

            errorTag.background = MaterialShapeDrawable().apply {
                setTint(ContextCompat.getColor(requireContext(), R.color.error_bg_color))

                setCornerSize(136.toFloat())

            }
            errorTag.isVisible = false
            btnContinue.isButtonEnabled = false
            title.text = verificationType?.preview
            displayCapturedView(verificationType, uri)
            val (rootView: ViewGroup, windowBackground: Drawable?) = getBlurView()

            btnRetake.setOnClickListener {
                navViewModel.popBackStack()
            }
            btnContinue.setOnClickListener {
                showLoadingProgress(verificationType, uri, rootView, windowBackground)


                if (verificationType == VerificationType.Selfie) {
                    verificationImage?.let { image ->
                        govViewModel.checkLiveness(
                            image,
                            page = KycPages.GOVERNMENT_DATA_VERIFICATION
                        )
                    }
                } else {
                    viewModel.viewModelScope.launch {
                        delay(2000)
                        processing.visibility = View.GONE
                        ///go to success screen
                        navViewModel.navigate(
                            "${Routes.success_route}/${getString(R.string.success_msg)}"
                        )
                    }

                }


            }
        }
    }

    private fun FragmentPreviewSelfieBinding.showLoadingProgress(
        verificationType: VerificationType?,
        uri: Uri?,
        rootView: ViewGroup,
        windowBackground: Drawable?,
        loading: Boolean = true,
    ) {
        processing.isVisible = loading
        blurView.isVisible = loading
        if (verificationType == VerificationType.SelfieVideo) {
            videoPreview.pause()

            val mediaReceiver = MediaMetadataRetriever()
            mediaReceiver.setDataSource(requireContext(), uri)
            val frameAtTime =
                mediaReceiver.getFrameAtTime(videoPreview.currentPosition.toLong())
            videoPreview.isVisible = false
            cameraPreview.isVisible = true
            cameraPreview.load(frameAtTime, isCenterCrop = true)

        }
        val radius = 20f
        blurView.setupWith(
            rootView,
            RenderScriptBlur(requireContext())
        ) // or RenderEffectBlur
            .setFrameClearDrawable(windowBackground) // Optional
            .setBlurRadius(radius)
    }

    private fun FragmentPreviewSelfieBinding.displayCapturedView(
        verificationType: VerificationType?,
        uri: Uri?
    ) {
        if (verificationType == VerificationType.SelfieVideo) {
            videoPreview.isVisible = true
            cameraPreview.isVisible = false
            videoPreview.setVideoURI(uri)
            videoPreview.start()
        } else {
            cameraPreview.load(uri, isCenterCrop = true)
        }
    }

    private fun FragmentPreviewSelfieBinding.getBlurView(): Pair<ViewGroup, Drawable?> {
        val decorView = cardPreview
        // ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        val rootView: ViewGroup = cardPreview
        val windowBackground: Drawable? = decorView.background
        return Pair(rootView, windowBackground)
    }
}