package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Constants
import com.dojah.sdk_kyc.databinding.DialogSelfieDisclaimerBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.getAttr
import com.dojah.sdk_kyc.ui.utils.load
import com.dojah.sdk_kyc.ui.utils.loadGif
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class SelfieDisclaimerFragment : ErrorFragment(R.layout.dialog_selfie_disclaimer) {
    private val binding by viewBinding { DialogSelfieDisclaimerBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_nav_graph) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val verificationType = arguments?.getString(Constants.VERIFY_BUNDLE,"")
//        viewModel.selectVerificationType(verificationType?:"")
        binding.apply {
//            logo.loadGif(context?.getAttr(R.attr.facialVerificationAnim))
            btnContinue.setOnClickListener {
                navViewModel.navigate(R.id.frag_selfie_capture)
            }

        }
    }


}