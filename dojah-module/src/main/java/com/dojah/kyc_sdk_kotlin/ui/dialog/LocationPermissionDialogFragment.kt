package com.dojah.kyc_sdk_kotlin.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.DialogLocationPermissionBinding
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.setClickableText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LocationPermissionDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val EXTRA_MESSAGE = "com.dojah.sdk_kyc.EXTRA_MESSAGE"

        fun getInstance(): LocationPermissionDialogFragment {
            return LocationPermissionDialogFragment()
        }
    }

    private val binding by viewBinding { DialogLocationPermissionBinding.bind(it) }
        private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }


    var onAllow: (() -> Unit)? = null

    var onExitClick: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.dialog_location_permission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel.getDojahAppAttribute(requireContext())?.let {
                toolbar.logoUrl = it.logo
            }
            howToPermit.setClickableText(
                0,
                howToPermit.text.length,
                changeSize = false
            ){
                Toast.makeText(context, "help clicked", Toast.LENGTH_SHORT).show()
            }

            toolbar.backView.setOnClickListener {
                dismiss()
                onExitClick?.invoke()
            }

            toolbar.closeView.setOnClickListener {
                dismiss()
                onExitClick?.invoke()
            }

            btnContinue.setOnClickListener {
                dismiss()
                onAllow?.invoke()
            }
        }
    }
}