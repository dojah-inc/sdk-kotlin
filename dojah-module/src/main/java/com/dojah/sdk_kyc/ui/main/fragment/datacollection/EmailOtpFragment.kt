package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.text.toSpannable
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Constants
import com.dojah.sdk_kyc.databinding.FragmentCountryBinding
import com.dojah.sdk_kyc.databinding.FragmentOtpEmailBinding
import com.dojah.sdk_kyc.databinding.FragmentOtpPhoneBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.getAttr
import com.dojah.sdk_kyc.ui.utils.getText
import com.dojah.sdk_kyc.ui.utils.performOperationOnActivityAvailable
import com.dojah.sdk_kyc.ui.utils.setClickableText
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class EmailOtpFragment : ErrorFragment(R.layout.fragment_otp_email) {
    private val binding by viewBinding { FragmentOtpEmailBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_nav_graph) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            btnContinue.setOnClickListener {
                navViewModel.navigate(R.id.frag_enter_otp, Bundle().apply {
                    putString(Constants.OTP_ID_BUNDLE,
                        textInputBvn.getText().ifEmpty { "sht@gmail.com" }
                    )
                })
            }

        }
    }


}