package com.dojah_inc.dojah_android_sdk.ui.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.navGraphViewModels
import com.devs.vectorchildfinder.VectorChildFinder
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager
import com.dojah_inc.dojah_android_sdk.databinding.DialogCameraPermissionBinding
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding
import com.dojah_inc.dojah_android_sdk.ui.utils.setClickableText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CameraPermissionDialogFragment : BottomSheetDialogFragment() {
    companion object {

        fun getInstance(): CameraPermissionDialogFragment {
            return CameraPermissionDialogFragment()
        }
    }

    private val binding by viewBinding { DialogCameraPermissionBinding.bind(it) }
    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    var onAllow: (() -> Unit)? = null

    var onExitClick: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.dialog_camera_permission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            val vector =
                VectorChildFinder(requireContext(), R.drawable.ic_cam_permit, logo)

            SharedPreferenceManager(requireContext()).getMaterialButtonBgColor?.also {
                val color = Color.parseColor(it)
                vector.findPathByName("path1").fillColor = color
            }

            logo.invalidate()

            viewModel.getDojahAppAttribute(requireContext())?.let {
                toolbar.logoUrl = it.logo
            }
            howToPermit.setClickableText(
                0,
                howToPermit.text.length,
                changeSize = false
            ) {
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