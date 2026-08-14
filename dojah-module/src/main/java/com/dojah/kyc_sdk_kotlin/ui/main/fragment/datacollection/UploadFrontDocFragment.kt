package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.text.toSpannable
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.FragmentUploadDocBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.getAttr
import com.dojah.kyc_sdk_kotlin.ui.utils.load
import com.dojah.kyc_sdk_kotlin.ui.utils.setClickableText
import okhttp3.logging.HttpLoggingInterceptor


class UploadFrontDocFragment : ErrorFragment() {


    private val binding by viewBinding { FragmentUploadDocBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}
    private lateinit var fileContract: ActivityResultLauncher<Array<String>>
    private val logger = HttpLoggingInterceptor.Logger.DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fileContract = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            if (it == null || !isAdded || view == null) return@registerForActivityResult
            binding.apply {
                btnUpload.isButtonEnabled = true
                val frontDocInfo =
                    viewModel.setFrontDocUri(requireContext(), it, isUpload = true)
                frontDocInfo?.also { info ->
                    logger.log("file name: $info")
                    if (info.docType != "pdf") {
                        pdfNameTv.isVisible = false
                        docPreview.isVisible = true
                        docPreview.load(it, isCenterCrop = true)
                    } else {
                        textDocument.isVisible = false
                        pdfNameTv.isVisible = true
                        pdfNameTv.text = info.fullName
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upload_doc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.apply {
            val selectedDoc = viewModel.docTypeLiveData.value
            textTitle.text = selectedDoc?.title
            (getString(R.string.click__select_from_device)).toSpannable().apply {
                val painted = "Click here to select"
                textDocument.setClickableText(
                    indexOf(painted),
                    indexOf(painted) + painted.length,
                    context?.getAttr(androidx.appcompat.R.attr.colorPrimary)
                ) {
                    showFilePicker()
                }
            }
            layoutUpload.setOnClickListener {
                showFilePicker()
            }

            btnUpload.setOnClickListener {
                navViewModel.navigate(Routes.preview_doc_route)
            }

            btnCapture.setOnClickListener {
                navViewModel.popBackStack()
            }
        }
    }

    private fun showFilePicker() {
        fileContract.launch(arrayOf("image/*", "application/pdf"))

    }

}
