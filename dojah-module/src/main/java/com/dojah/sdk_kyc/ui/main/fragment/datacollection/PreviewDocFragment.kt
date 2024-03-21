package com.dojah.sdk_kyc.ui.main.fragment.datacollection

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
import com.dojah.sdk_kyc.databinding.FragmentPreviewDriverLicenceBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.fragment.NavArguments
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.utils.*
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.load
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import okio.ByteString.Companion.toByteString


@AndroidEntryPoint
class PreviewDocFragment : ErrorFragment() {


    private val binding by viewBinding { FragmentPreviewDriverLicenceBinding.bind(it) }


    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()
    private val logger = HttpLoggingInterceptor.Logger.DEFAULT

    private var verificationImage: String? = null
    private val isBusinessDocPage: Boolean
        get() =
            navViewModel.currentPage == KycPages.BUSINESS_ID.serverKey
    private val isOtherDocPage: Boolean
        get() =
            navViewModel.currentPage == KycPages.OTHER_DOCUMENT.serverKey
    private val isIdPage: Boolean
        get() =
            navViewModel.currentPage == KycPages.ID.serverKey
    private val analysisNeeded: Boolean
        get() = isIdPage && viewModel.isUploadDocLiveData.value == false
    private val isLastPage: Boolean
        get() {
            val lastServerStepName = viewModel.lastStep?.name
            val currentStep = navViewModel.currentPage
            logger.log("lastStepName: $lastServerStepName, currentStep: $currentStep")
            return lastServerStepName == currentStep
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
        if (analysisNeeded) {
            performAnalysis()
        }
    }

    private fun observeLiveData() {
        if (analysisNeeded)
            govViewModel.docImageAnalysisLiveData.observe(this) { result ->
                binding.root.post {
                    binding.apply {
                        errorTag.isVisible = false
                        binding.processing.isVisible = result is Result.Loading
                        btnContinue.isLoading = result is Result.Loading

                        if (result is Result.Error || result is Result.Loading) {
                            btnContinue.isButtonEnabled = false
                        }

                        if (result is Result.Success) {
                            btnRetake.isEnabled = false
                            errorTag.isVisible = false
                            btnContinue.isButtonEnabled = true
                        } else if (result is Result.Error) {
                            btnRetake.isEnabled = true
                            errorTag.text = FailedReasons.GOV_ID_CAPTURE.message
                            errorTag.isVisible = true
                        }
                    }
                }
            }

        govViewModel.submitLivenessLiveData.observe(this) {
            binding.root.post {
                binding.processing.isVisible = it is Result.Loading
                binding.btnContinue.isLoading = it is Result.Loading
                binding.pdfNameTv.isVisible = it !is Result.Loading
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
        val isNormalDoc = true
        val isBackDoc = viewModel.isBackDocLiveData.value
        val uri: Uri? = if (isBackDoc == true) {
            viewModel.backDocUriLiveData.value
        } else {
            viewModel.frontDocUriLiveData.value
        }
        if (isNormalDoc) {
            if (uri == null) {
                throw Exception("Uri is null")
            }
            verificationImage = uri.toFile().readBytes().toByteString().base64()
            govViewModel.performDocImageAnalysis(viewModel, verificationImage!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview_driver_licence, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            val docType = viewModel.docTypeLiveData.value
            val isBackDoc = viewModel.isBackDocLiveData.value
            val isDocUpload = viewModel.isUploadDocLiveData.value
            val hasBack = isIdPage && docType?.hasBack == true


            val uri: Uri? = if (isBackDoc == true) {
                viewModel.backDocUriLiveData.value
            } else {
                viewModel.frontDocUriLiveData.value
            }

            errorTag.background = MaterialShapeDrawable().apply {
                setTint(ContextCompat.getColor(requireContext(), R.color.error_bg_color))

                setCornerSize(136.toFloat())

            }
            errorTag.isVisible = false
            ///if analysis is needed, disable continue button
            btnContinue.isButtonEnabled = !analysisNeeded


            val info = if (isBackDoc == true) {
                viewModel.docInfoLiveData.value?.second
            } else {
                viewModel.docInfoLiveData.value?.first
            }
            logger.log("file name: ${info?.fullName}")
            if (info?.docType != "pdf") {
                pdfNameTv.isVisible = false
                cameraPreview.load(uri, isCenterCrop = true)
            } else {
                pdfNameTv.isVisible = true
                pdfNameTv.text = info.fullName
            }

            btnRetake.setOnClickListener {
                navViewModel.popBackStack()
            }
            btnContinue.setOnClickListener {
                if (isBackDoc != true && hasBack) {
                    if (isDocUpload == true) {
                        navViewModel.navigate(Routes.upload_back_doc_route)
                    } else {
                        navViewModel.navigate(Routes.capture_back_doc_route)
                    }
                } else {
                    val (verificationImage1, image2) = getActualUriImages(isDocUpload)
                    verificationImage1?.let { image1 ->
                        if (isOtherDocPage) {
                            govViewModel.sendAdditionalDoc(
                                mainVm = viewModel,
                                image1,
                            )
                        } else {
                            val currentPage =
                                navViewModel.currentPage ?: throw Exception("Current page is null")
                            govViewModel.doCheckForDocId(
                                mainVm = viewModel,
                                image1,
                                image2,
                                page = KycPages.findPageEnum(currentPage) ?: KycPages.ID,
                                selfieType = if (isBusinessDocPage) null else "selfie_type",
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getActualUriImages(isDocUpload: Boolean?): Pair<String?, String?> {
        val verificationImage1 =
            if (isDocUpload == true) {
                val frontUri = viewModel.frontDocUriLiveData.value
                    ?: throw Exception("Front uri is null")

                val stream = requireContext().contentResolver.openInputStream(frontUri)
                val base64 = stream?.readBytes()?.toByteString()?.base64()
                stream?.close()
                base64

            } else {
                if (viewModel.frontDocUriLiveData.value == null) {
                    null
                } else {
                    try {
                        viewModel.frontDocUriLiveData.value?.toFile()?.readBytes()
                            ?.toByteString()
                            ?.base64()
                    } catch (e: Exception) {
                        val stream =
                            requireContext().contentResolver.openInputStream(viewModel.frontDocUriLiveData.value!!)
                        val base64 = stream?.readBytes()?.toByteString()?.base64()
                        stream?.close()
                        base64

                    }
                }

            }
        val backUri = viewModel.backDocUriLiveData.value
        val image2 =
            if (isDocUpload == true && backUri != null) {
                val stream =
                    requireContext().contentResolver.openInputStream(backUri)
                val base64 = stream?.readBytes()?.toByteString()?.base64()
                stream?.close()
                base64
            } else {
                if (viewModel.backDocUriLiveData.value == null) {
                    null
                } else {
                    try {
                        viewModel.backDocUriLiveData.value?.toFile()?.readBytes()
                            ?.toByteString()
                            ?.base64()
                    } catch (e: Exception) {
                        val stream =
                            requireContext().contentResolver.openInputStream(viewModel.backDocUriLiveData.value!!)
                        val base64 = stream?.readBytes()?.toByteString()?.base64()
                        stream?.close()
                        base64

                    }
                }
            }
        return Pair(verificationImage1, image2)
    }
}