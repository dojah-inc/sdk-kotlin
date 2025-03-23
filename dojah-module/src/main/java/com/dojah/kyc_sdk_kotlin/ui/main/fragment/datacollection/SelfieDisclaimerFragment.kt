package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection
import com.dojah.kyc_sdk_kotlin.DojahSdk

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.DialogSelfieDisclaimerBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.dialog.CameraPermissionDialogFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.dojah.kyc_sdk_kotlin.ui.utils.VerificationType
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding



@SuppressLint("UnsafeRepeatOnLifecycleDetector")

class SelfieDisclaimerFragment : ErrorFragment(R.layout.dialog_selfie_disclaimer) {
    private val binding by viewBinding { DialogSelfieDisclaimerBinding.bind(it) }
    private lateinit var cameraContract: ActivityResultLauncher<String>

        private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val govDataViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.govViewModelFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (navViewModel.currentPage == KycPages.SELFIE.serverKey) {
            val version = viewModel.getStepWithPageName(KycPages.SELFIE.serverKey)?.config?.version
            if (version == 3) {
                /**auto select [VerificationType.Selfie] if page
                is standalone selfie page and version is 3 **/
                govDataViewModel.selectVerificationType(VerificationType.Selfie.value)
            } else {
                /**auto select [VerificationType.SelfieVideo] if page
                is standalone selfie page and version is not 3 **/
                govDataViewModel.selectVerificationType(VerificationType.SelfieVideo.value)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        cameraContract =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if (granted) {
                    navViewModel.navigate(Routes.capture_selfie_fragment)
                } else {
                    showPermissionError {
                        cameraContract.launch(Manifest.permission.CAMERA)
                    }
                }
            }


        binding.apply {
//            logo.loadGif(context?.getAttr(R.attr.facialVerificationAnim))
            btnContinue.setOnClickListener {

                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    showPermissionError(onAllowClicked = {
                        cameraContract.launch(Manifest.permission.CAMERA)
                    })
                } else {
                    navViewModel.navigate(Routes.capture_selfie_fragment)
                }
            }

        }
    }

    private fun showPermissionError(onAllowClicked: () -> Unit) {

        CameraPermissionDialogFragment.getInstance(
        ).apply {
            onAllow = onAllowClicked
            onExitClick = {
//                navViewModel.popBackStack()
            }

            show(this@SelfieDisclaimerFragment.childFragmentManager, null)
        }
    }


}