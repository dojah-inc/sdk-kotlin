package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection
import com.dojah.kyc_sdk_kotlin.DojahSdk

import android.Manifest
import android.os.Build
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
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.FragmentUploadDocBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.dialog.GalleryPermissionDialogFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.getAttr
import com.dojah.kyc_sdk_kotlin.ui.utils.load
import com.dojah.kyc_sdk_kotlin.ui.utils.setClickableText

import okhttp3.logging.HttpLoggingInterceptor



class UploadFrontDocFragment : ErrorFragment() {


    private val binding by viewBinding { FragmentUploadDocBinding.bind(it) }

        private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.govViewModelFactory }
    private var permissionContract: ActivityResultLauncher<Array<String>>? = null
    private var readImagePermissionString: String? = null

    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}
    private lateinit var fileContract: ActivityResultLauncher<Array<String>>
    private val logger = HttpLoggingInterceptor.Logger.DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fileContract = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            if (it != null) {
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
        permissionContract =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                if (it.getOrDefault(readImagePermissionString, false)) {
                    fileContract.launch(arrayOf("image/*", "application/pdf"))
                } else {
                    showPermissionError {
                        fileContract.launch(arrayOf("image/*", "application/pdf"))
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
        //                showPermissionError {
//                    fileContract.launch(arrayOf("image/*", "application/pdf"))
//                }
        readImagePermissionString = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        readImagePermissionString?.also {
            permissionContract?.launch(arrayOf(it))
        }
    }


    private fun showPermissionError(onAllowClicked: () -> Unit) {
        GalleryPermissionDialogFragment.getInstance(
        ).apply {
            onAllow = onAllowClicked
            onExitClick = {
            }

            show(this@UploadFrontDocFragment.childFragmentManager, null)
        }

    }
}
