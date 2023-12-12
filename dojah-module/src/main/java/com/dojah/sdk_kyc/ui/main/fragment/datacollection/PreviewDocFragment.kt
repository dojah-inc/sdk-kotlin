package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.FragmentPreviewDriverLicenceBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDocType
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationType
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.load
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PreviewDocFragment : ErrorFragment() {


    private val binding by viewBinding { FragmentPreviewDriverLicenceBinding.bind(it) }


    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_id_nav_graph) { defaultViewModelProviderFactory }
    private val govViewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_nav_graph) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview_driver_licence, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            val uri: Uri?
            uri = if (viewModel.isBackDocLiveData.value == true) {
                viewModel.backDocUriLiveData.value
            } else {
                viewModel.frontDocUriLiveData.value
            }
            cameraPreview.load(uri, isCenterCrop = true)

            btnRetake.setOnClickListener {
                navViewModel.popBackStack()
            }
            btnContinue.setOnClickListener {
                if (viewModel.isBackDocLiveData.value != true &&
                    viewModel.docTypeLiveData.value == GovDocType.DRIVER_LICENCE
                ) {
                    if (arguments?.getBoolean("isUpload") == true) {
                        navViewModel.navigateOld(R.id.frag_upload_back_doc)
                    } else {
                        navViewModel.navigateOld(R.id.frag_capture_back_doc)
                    }
                } else {
                    //Doc verification done, move to verify with options
                    when (val verificationType = govViewModel.verificationTypeLiveData.value) {
                        VerificationType.HOME_ADDRESS -> {
                            navViewModel.navigateOld(R.id.frag_address)
                        }

                        VerificationType.EMAIL_OTP -> {
                            navViewModel.navigateOld(R.id.frag_email_otp)
                        }

                        VerificationType.PHONE_OTP -> {
                            navViewModel.navigateOld(R.id.frag_phone_otp)
                        }

                        else -> {
                            navViewModel.navigateOld(R.id.selfie_nav_graph)
                        }
                    }
                }
            }
        }
    }
}