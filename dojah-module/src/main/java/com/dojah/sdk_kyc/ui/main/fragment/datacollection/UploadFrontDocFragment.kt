package com.dojah.sdk_kyc.ui.main.fragment.datacollection

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
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.FragmentUploadDocBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.dialog.GalleryPermissionDialogFragment
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDocType
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.getAttr
import com.dojah.sdk_kyc.ui.utils.load
import com.dojah.sdk_kyc.ui.utils.openAppSystemSettings
import com.dojah.sdk_kyc.ui.utils.setClickableText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UploadFrontDocFragment : ErrorFragment() {


    private val binding by viewBinding { FragmentUploadDocBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_id_nav_graph) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()

    private lateinit var documentContract: ActivityResultLauncher<Array<String>>

    private lateinit var permissionContract: ActivityResultLauncher<Array<String>>

    private lateinit var fileContract: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fileContract = registerForActivityResult(ActivityResultContracts.OpenDocument()) {

            if (it != null) {
                binding.apply {
                    docPreview.isVisible = true
                    docPreview.load(it, isCenterCrop = true)
                    btnUpload.isButtonEnabled = true
                    viewModel.setFrontDocUri(it)
                }
            }
        }

        permissionContract =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {

                val readPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Manifest.permission.READ_MEDIA_IMAGES
                } else {
                    Manifest.permission.READ_EXTERNAL_STORAGE
                }

                if (it.getOrDefault(readPermission, false)) {
                    fileContract.launch(arrayOf("image/*"))
                } else {
                    showPermissionError()
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
            if (selectedDoc != GovDocType.DRIVER_LICENCE) {
                textTitle.text = selectedDoc?.title
//                infoText.text = selectedDoc?.info
            }
            (getString(R.string.click__select_from_device)).toSpannable().apply {
                val painted = "Click here to select"
                textDocument.setClickableText(
                    indexOf(painted),
                    indexOf(painted) + painted.length,
                    context?.getAttr(androidx.appcompat.R.attr.colorPrimary)
                ) {
                    val readPermission =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            Manifest.permission.READ_MEDIA_IMAGES
                        } else {
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        }
                    permissionContract.launch(arrayOf(readPermission))
                }
            }

            btnUpload.setOnClickListener {
                navViewModel.navigateOld(R.id.frag_preview_doc, Bundle().apply {
                    putBoolean("isUpload", true)
                })
            }

            btnCapture.setOnClickListener {
                navViewModel.popBackStack()
            }
        }
    }


    private fun showPermissionError() {
        GalleryPermissionDialogFragment.getInstance(
        ).apply {
            onAllow = {
                requireContext().openAppSystemSettings()
            }
            onExitClick = {
            }

            show(this@UploadFrontDocFragment.childFragmentManager, null)
        }

    }
}