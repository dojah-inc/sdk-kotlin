package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.core.util.encrypted
import com.dojah.kyc_sdk_kotlin.databinding.FragmentPreviewUtilityBillBinding
import com.dojah.kyc_sdk_kotlin.domain.DocumentInfo
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.load
import okio.ByteString.Companion.toByteString
import kotlin.getValue

class PreviewUtilityBillFragment : ErrorFragment() {
    private val binding by viewBinding { FragmentPreviewUtilityBillBinding.bind(it) }

    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.govViewModelFactory }
    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview_utility_bill, container, false)
    }

    private fun observeLiveData() {
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
                val config = viewModel.getStepWithPageName(KycPages.ADDRESS.serverKey)?.config
                if (config?.liveLocation == true) {
                    viewModel.clearBuildingPhotos()
                    navViewModel.navigate(Routes.capture_building_photo_route)
                } else {
                    navViewModel.navigateNextStep()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            btnContinue.isButtonEnabled = true
            viewModel.utilityBillLiveData.value?.let { docInfo ->
                cameraPreview.load(docInfo.docUri, isCenterCrop = true)

                if (docInfo.docType != "pdf") {
                    pdfNameTv.isVisible = false
                    cameraPreview.load(docInfo.docUri, isCenterCrop = true)
                } else {
                    pdfNameTv.isVisible = true
                    pdfNameTv.text = docInfo.fullName
                }
            }

            btnRetake.setOnClickListener {
                navViewModel.popBackStack()
            }

            btnContinue.setOnClickListener {
                val page = navViewModel.currentPage?.let { KycPages.findPageEnum(it) }
                    ?: KycPages.ADDRESS
                getImageUrl()?.let { base64 ->
                    govViewModel.checkUtilityBillImage(page, base64.encrypted())
                }
            }
        }
    }

    private fun getImageUrl(): String? =
        viewModel.utilityBillLiveData.value?.docUri?.let {
            val stream = requireContext().contentResolver.openInputStream(it)
            val base64 = stream?.readBytes()?.toByteString()?.base64()
            stream?.close()
            base64
        }
}
