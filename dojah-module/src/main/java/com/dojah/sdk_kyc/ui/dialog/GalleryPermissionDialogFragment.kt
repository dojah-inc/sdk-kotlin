package com.dojah.sdk_kyc.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.DialogCameraPermissionBinding
import com.dojah.sdk_kyc.databinding.DialogGalleryPermissionBinding
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GalleryPermissionDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val EXTRA_MESSAGE = "com.dojah.sdk_kyc.EXTRA_MESSAGE"

        fun getInstance(): GalleryPermissionDialogFragment {
            return GalleryPermissionDialogFragment()
        }
    }

    private val binding by viewBinding { DialogGalleryPermissionBinding.bind(it) }

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