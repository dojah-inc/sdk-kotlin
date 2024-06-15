package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.core.Result
import com.dojah_inc.dojah_android_sdk.databinding.FragmentSignatureBinding
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.base.SpinnerFragment
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.GovDataViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class SignatureFragment : SpinnerFragment(R.layout.fragment_signature) {
    private val binding by viewBinding { FragmentSignatureBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        govViewModel.submitSignatureLiveData.observe(this) {
            if (it is Result.Loading) {
                showLoading()
            } else {
                dismissLoading()
                if (it is Result.Success) {
                    navViewModel.navigateNextStep()
                } else if (it is Result.Error) {
                    navigateToErrorPage(it)
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            textInfo.text =
                govViewModel.signatureInfo

            btnContinue.setOnClickListener {
                val signNameEdt = inputSignName.editText!!
                if (signNameEdt.text.isNullOrBlank()) {
                    signNameEdt.error = "Field can't be empty"
                    return@setOnClickListener
                }
                signNameEdt.error = null
                govViewModel.submitSignature(
                    viewModel,
                    signNameEdt.text.toString()
                )
            }
        }
    }
}