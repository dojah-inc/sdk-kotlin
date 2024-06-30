package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection
import com.dojah_inc.dojah_android_sdk.DojahSdk

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.databinding.FragmentErrorBinding
import com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment
import com.dojah_inc.dojah_android_sdk.ui.utils.FailedReasons
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding



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