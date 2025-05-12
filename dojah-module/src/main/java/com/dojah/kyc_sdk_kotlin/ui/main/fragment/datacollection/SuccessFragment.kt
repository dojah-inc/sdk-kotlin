package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection

import com.dojah.kyc_sdk_kotlin.DojahSdk

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.dojah.kyc_sdk_kotlin.DOJAH_CLOSED_RESULT
import com.dojah.kyc_sdk_kotlin.DOJAH_RESULT_KEY
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.databinding.SuccessViewBinding
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

class SuccessFragment : ErrorFragment(R.layout.success_view) {
    private val binding by viewBinding { SuccessViewBinding.bind(it) }


    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
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

            val brandColor = SharedPreferenceManager(requireContext()).getMaterialButtonBgColor
//            HttpLoggingInterceptor.Logger.DEFAULT.log("BTN: Brand color: ${brandColor}")
            if (brandColor != null) {
                try {
                    logo.addValueCallback(
                        KeyPath("**"),
                        LottieProperty.COLOR_FILTER
                    ) {
                        PorterDuffColorFilter(
                            Color.parseColor(brandColor),
                            PorterDuff.Mode.SRC_ATOP
                        )
                    }
                } catch (e: Exception) {
                    HttpLoggingInterceptor.Logger.DEFAULT.log("${e.message}")
                }
            }
            arguments?.getString(NavArguments.option, null).also {
                if (it == null) {
                    msg.text = getString(R.string.success_msg)
                }
                val verifyKey = "|verify99"
                if (it!!.contains(verifyKey)) {
                    val split = it.split("|")
                    title.text = split.getOrNull(2) ?: ""
                    msg.text = split.firstOrNull() ?: ""
                } else {
                    msg.text = it
                }
            }

        }
    }
}