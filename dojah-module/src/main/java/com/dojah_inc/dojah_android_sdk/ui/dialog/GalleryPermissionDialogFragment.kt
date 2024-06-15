package com.dojah_inc.dojah_android_sdk.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.navGraphViewModels
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.databinding.DialogGalleryPermissionBinding
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding
import com.dojah_inc.dojah_android_sdk.ui.utils.setClickableText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GalleryPermissionDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val EXTRA_MESSAGE = "com.dojah.sdk_kyc.EXTRA_MESSAGE"

        fun getInstance(): GalleryPermissionDialogFragment {
            return GalleryPermissionDialogFragment()
        }
    }

    private val binding by viewBinding { DialogGalleryPermissionBinding.bind(it) }
    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    var onAllow: (() -> Unit)? = null

    var onExitClick: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_gallery_permission, container, false)
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
                onExitClick?.invoke()
                dismiss()
            }

            toolbar.closeView.setOnClickListener {
                onExitClick?.invoke()
                dismiss()
            }

            btnContinue.setOnClickListener {
                onAllow?.invoke()

                dismiss()
            }
        }
    }
}