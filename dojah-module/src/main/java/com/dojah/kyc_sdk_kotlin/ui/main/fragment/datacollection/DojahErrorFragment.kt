package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.FragmentErrorBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.NavArguments
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding



@SuppressLint("UnsafeRepeatOnLifecycleDetector")

class DojahErrorFragment : ErrorFragment(R.layout.fragment_error) {
    private val binding by viewBinding { FragmentErrorBinding.bind(it) }

//    private val viewModel by viewModels<VerificationViewModel>()

//    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}


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