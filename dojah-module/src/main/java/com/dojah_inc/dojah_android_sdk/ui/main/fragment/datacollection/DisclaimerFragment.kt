package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection
import com.dojah_inc.dojah_android_sdk.DojahSdk

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.devs.vectorchildfinder.VectorChildFinder
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager
import com.dojah_inc.dojah_android_sdk.databinding.DialogDisclaimerBinding
import com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.dialog.CameraPermissionDialogFragment
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.GovDocType
import com.dojah_inc.dojah_android_sdk.ui.utils.KycPages
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding



@SuppressLint("UnsafeRepeatOnLifecycleDetector")

class DisclaimerFragment : ErrorFragment(R.layout.dialog_disclaimer) {
    private val binding by viewBinding { DialogDisclaimerBinding.bind(it) }
    private lateinit var cameraContract: ActivityResultLauncher<String>

        private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cameraContract =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if (granted) {
                    navigateNextScreen()
                } else {
                    showPermissionError {
                        cameraContract.launch(Manifest.permission.CAMERA)
                    }
                }
            }
        binding.apply {
            val vector =
                VectorChildFinder(requireContext(), R.drawable.ic_verification_avatar, logo)

            SharedPreferenceManager(requireContext()).getMaterialButtonBgColor?.also {
                val color = Color.parseColor(it)
                vector.findPathByName("path1").fillColor = color
                vector.findPathByName("path1").fillAlpha = 0.4f
                vector.findPathByName("path2").strokeColor = color
                vector.findPathByName("path3").fillColor = color
            }

            logo.invalidate()
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
                    navigateNextScreen()
                }

            }

        }
    }

    private fun navigateNextScreen() {
        when (navViewModel.currentPage) {
            KycPages.BUSINESS_ID.serverKey -> {
                viewModel.selectDocType(GovDocType.BUSINESS.sName)
            }

            KycPages.OTHER_DOCUMENT.serverKey -> {
                viewModel.selectDocType(GovDocType.OTHER.sName)
            }
        }
        navViewModel.navigate(Routes.capture_doc_route)
    }


    private fun showPermissionError(onAllowClicked: () -> Unit) {

        CameraPermissionDialogFragment.getInstance(
        ).apply {
            onAllow = onAllowClicked
            onExitClick = {
//                navViewModel.popBackStack()
            }

            show(this@DisclaimerFragment.childFragmentManager, null)
        }
    }


}