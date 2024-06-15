package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager
import com.dojah_inc.dojah_android_sdk.databinding.SuccessViewBinding
import com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.NavArguments
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class SuccessFragment : ErrorFragment(R.layout.success_view) {
    private val binding by viewBinding { SuccessViewBinding.bind(it) }


    private val navViewModel by activityViewModels<NavigationViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            val brandColor = SharedPreferenceManager(requireContext()).getMaterialButtonBgColor
            HttpLoggingInterceptor.Logger.DEFAULT.log("BTN: Brand color: ${brandColor}");
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