package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Constants
import com.dojah.sdk_kyc.databinding.DialogSelfieDisclaimerBinding
import com.dojah.sdk_kyc.databinding.FragmentErrorBinding
import com.dojah.sdk_kyc.databinding.SuccessViewBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.fragment.NavArguments
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.getAttr
import com.dojah.sdk_kyc.ui.utils.load
import com.dojah.sdk_kyc.ui.utils.loadGif
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class DojahErrorFragment : ErrorFragment(R.layout.fragment_error) {
    private val binding by viewBinding { FragmentErrorBinding.bind(it) }

//    private val viewModel by viewModels<VerificationViewModel>()

    private val navViewModel by activityViewModels<NavigationViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
//            logo.loadGif(context?.getAttr(R.attr.verifiedAnim))
            msg.text = arguments?.getString(NavArguments.option, null)
                ?: getString(R.string.success_msg)

        }
    }


}