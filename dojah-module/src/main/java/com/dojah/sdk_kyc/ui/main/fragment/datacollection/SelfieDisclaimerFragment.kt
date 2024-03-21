package com.dojah.sdk_kyc.ui.main.fragment.datacollection

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
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.DialogSelfieDisclaimerBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.dialog.CameraPermissionDialogFragment
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class SelfieDisclaimerFragment : ErrorFragment(R.layout.dialog_selfie_disclaimer) {
    private val binding by viewBinding { DialogSelfieDisclaimerBinding.bind(it) }
    private lateinit var cameraContract: ActivityResultLauncher<String>

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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