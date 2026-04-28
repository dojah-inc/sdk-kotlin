package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.FragmentUploadBuildingPhotoBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.load

class UploadBuildingPhotoFragment : ErrorFragment() {

    private val binding by viewBinding { FragmentUploadBuildingPhotoBinding.bind(it) }
    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }
    private lateinit var fileContract: ActivityResultLauncher<Array<String>>

    companion object {
        const val TOTAL_PHOTOS = 3
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_upload_building_photo, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fileContract = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            if (uri != null) {
                val photoIndex = viewModel.currentBuildingPhotoIndex
                viewModel.setBuildingPhotoUri(requireContext(), photoIndex, uri, isUpload = true)
                // Post to binding after view is available
                view?.post {
                    binding.btnUpload.isButtonEnabled = true
                    binding.textDocument.isVisible = false
                    binding.docPreview.isVisible = true
                    binding.docPreview.load(uri, isCenterCrop = true)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val photoIndex = viewModel.currentBuildingPhotoIndex
        val humanIndex = photoIndex + 1

        binding.apply {
            stepIndicator.text = getString(R.string.photo_of, humanIndex, TOTAL_PHOTOS)

            layoutUpload.setOnClickListener { showFilePicker() }
            textDocument.setOnClickListener { showFilePicker() }

            btnUpload.setOnClickListener {
                // Navigate to preview so the user can review before moving on
                navViewModel.navigate(Routes.preview_building_photo_route)
            }

            btnCapture.setOnClickListener {
                navViewModel.popBackStack()
            }
        }
    }

    private fun showFilePicker() {
        fileContract.launch(arrayOf("image/*"))
    }
}
