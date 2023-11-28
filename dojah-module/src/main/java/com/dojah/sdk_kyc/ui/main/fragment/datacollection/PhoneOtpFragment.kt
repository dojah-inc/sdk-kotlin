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
import com.dojah.sdk_kyc.databinding.FragmentOtpPhoneBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.getAttr
import com.dojah.sdk_kyc.ui.utils.performOperationOnActivityAvailable
import com.dojah.sdk_kyc.ui.utils.setClickableText
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.indexOfFirstNonAsciiWhitespace
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class PhoneOtpFragment : ErrorFragment(R.layout.fragment_otp_phone) {
    private val binding by viewBinding { FragmentOtpPhoneBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_nav_graph) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()


    override fun onResume() {
        super.onResume()
        reloadCountry()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        reloadCountry()
        binding.apply {
            btnContinue.setOnClickListener {
                navViewModel.navigate(R.id.frag_enter_otp, Bundle().apply {
                    val phoneNumber = layoutSpinner.getText()
                    putString(
                        Constants.OTP_ID_BUNDLE,
                        phoneNumber.ifEmpty { "+234 806 803 8410" }
                    )
                })
            }
        }
    }

    private fun reloadCountry() {
        viewModel.getCountries()
        viewModel.countryLiveData.observe(viewLifecycleOwner) {
            binding.layoutSpinner.items = it
        }
    }


}