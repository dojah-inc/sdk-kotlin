package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection
import com.dojah.kyc_sdk_kotlin.DojahSdk

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.dojah.kyc_sdk_kotlin.DOJAH_RESULT_KEY
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.FragmentDecisionErrorBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.NavArguments
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
class DecisionErrorFragment : ErrorFragment(R.layout.fragment_decision_error) {
    private val binding by viewBinding { FragmentDecisionErrorBinding.bind(it) }

//    private val viewModel by viewModels<VerificationViewModel>()

    private val navViewModel by activityViewModels<NavigationViewModel>{ DojahSdk.dojahContainer.navViewModelFactory}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch{
            delay(3000)

            val finalResultStatus =
                DojahSdk.dojahContainer.sharedPreferenceManager.getFinalResultStatus()
            HttpLoggingInterceptor.Logger.DEFAULT.log("finalResultStatus: $finalResultStatus")
            activity?.setResult(
                Activity.RESULT_OK,
                Intent().putExtra(DOJAH_RESULT_KEY, finalResultStatus)
            )
            activity?.finish()
        }
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