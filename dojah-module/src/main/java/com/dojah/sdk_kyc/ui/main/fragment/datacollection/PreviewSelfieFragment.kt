package com.dojah.sdk_kyc.ui.main.fragment.datacollection

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
import com.dojah.sdk_kyc.ui.main.fragment.NavArguments
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.utils.*
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.analysisRetryMax
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.load
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

                    btnContinue.isLoading = result is Result.Loading
                    if (result is Result.Error || result is Result.Loading) {
                        btnContinue.isButtonEnabled = false
                    }
                    if (result is Result.Success) {
                        val faceResult = result.data?.entity?.face
                        val config =
                            viewModel.getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey)?.config
                        if (faceResult?.faceSuccess(config) == false) {
                            errorTag.text = faceResult.faceMessage(config)
                            errorTag.isVisible = true
                        } else {
                            errorTag.isVisible = false
                            btnContinue.isButtonEnabled = true
                        }
                    } else if (result is Result.Error) {
                        errorTag.text = viewModel.getErrorMessage(result)
                        errorTag.isVisible = true
                    }
                }
            }
        }

        govViewModel.analysisRetryCountLiveData.observe(this) {
            binding.root.post {
                binding.apply {
                    if (it >= analysisRetryMax) {
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
            if (it is Result.Loading) {
                showLoading("Loading...")
            } else {
                dismissLoading()
                if (it is Result.Error) {
                    ///show error
                    navViewModel.navigate(Routes.error_fragment, args = Bundle().apply {
                        putString(NavArguments.option, viewModel.getErrorMessage(it))
                    })
                } else if (it is Result.Success) {

                    val liveNessStep = Routes.getOptionRoute(
                        KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey,
                        optionPageName = govViewModel.verificationTypeLiveData.value?.serverKey,
                    )
                    if (navViewModel.currentStepLiveData.value?.lastOrNull() == liveNessStep) {
                        //TODO:if no more steps, make decision
                        showLongToast("This is the last step, hence, make decision")
                    } else {
                        // else continue to next step
                        navViewModel.navigateNextStep()
                    }
                }
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
            verificationImage = uri.toFile().readBytes().toByteString().base64()
            govViewModel.performImageAnalysis(verificationImage!!)
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
                processing.visibility = View.VISIBLE


                if (verificationType == VerificationType.Selfie) {
                    verificationImage?.let { image ->
                        govViewModel.checkLiveness(image)
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