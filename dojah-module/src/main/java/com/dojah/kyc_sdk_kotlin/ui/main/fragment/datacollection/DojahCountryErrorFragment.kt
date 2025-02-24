package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.FragmentErrorBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.utils.FailedReasons
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding



@SuppressLint("UnsafeRepeatOnLifecycleDetector")

class DojahCountryErrorFragment : ErrorFragment(R.layout.fragment_error_country ) {
    private val binding by viewBinding { FragmentErrorBinding.bind(it) }

//    private val viewModel by viewModels<VerificationViewModel>()

//    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            msg.text = FailedReasons.GOV_DATA_NOT_AVAILABLE.message

        }
    }


}