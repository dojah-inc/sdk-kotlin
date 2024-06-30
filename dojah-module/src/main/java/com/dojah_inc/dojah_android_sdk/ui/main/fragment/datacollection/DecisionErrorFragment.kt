package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection
import com.dojah_inc.dojah_android_sdk.DojahSdk

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.databinding.FragmentDecisionErrorBinding
import com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.NavArguments
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding



@SuppressLint("UnsafeRepeatOnLifecycleDetector")
class DecisionErrorFragment : ErrorFragment(R.layout.fragment_decision_error) {
    private val binding by viewBinding { FragmentDecisionErrorBinding.bind(it) }

//    private val viewModel by viewModels<VerificationViewModel>()

    private val navViewModel by activityViewModels<NavigationViewModel>{ DojahSdk.dojahContainer.navViewModelFactory}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            logo.addValueCallback(
                KeyPath("**"),
                LottieProperty.COLOR_FILTER
            ) {
                PorterDuffColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.red),
                    PorterDuff.Mode.SRC_ATOP
                )
            }
            msg.text = arguments?.getString(NavArguments.option, null)
                ?: getString(R.string.success_msg)

        }
    }


}