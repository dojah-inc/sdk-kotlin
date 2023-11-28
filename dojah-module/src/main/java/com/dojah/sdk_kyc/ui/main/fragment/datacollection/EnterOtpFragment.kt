package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Spannable
import android.text.style.TextAppearanceSpan
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.text.toSpannable
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Constants
import com.dojah.sdk_kyc.databinding.FragmentEnterOtp2Binding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.getAttr
import com.dojah.sdk_kyc.ui.utils.setClickableText
import com.otpview.OTPListener
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class EnterOtpFragment : ErrorFragment(R.layout.fragment_enter_otp2) {
    private val binding by viewBinding { FragmentEnterOtp2Binding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_nav_graph) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.apply {
            startTimer()
            timerOtpLiveData.observe(viewLifecycleOwner) {
                binding.resendTxt.text = getString(R.string.resend_code_in, it)

            }
            timerOtpDoneLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    binding.resendTxt.apply {
                        text = getString(R.string.resend_code)
                        binding.resendTxt.isEnabled = true
                        setTextColor(
                            ColorStateList.valueOf(
                                context.getAttr(androidx.appcompat.R.attr.colorPrimary)
                            )
                        )
                    }
                }
            }
        }
        binding.resendTxt.apply {
            isEnabled = false
            setTextColor(
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black_200
                    )
                )
            )
            setOnClickListener {
                showShortToast("Resent")
            }
        }
        binding.apply {
            val id = arguments?.getString(Constants.OTP_ID_BUNDLE) ?: "..."
            (getString(R.string.enter_the_code_sent, id)).toSpannable().apply {

                val startIndex = indexOf(id)
                val endIndex = indexOf(id) + id.length
                setSpan(
                    TextAppearanceSpan(requireContext(), R.style.TextAppearance_Dojah_Header5),
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                policyTv.text = this
                policyTv.setClickableText(
                    startIndex,
                    endIndex,
                    context?.getAttr(androidx.appcompat.R.attr.colorPrimary)
                ) {
                    Toast.makeText(context, "Id clicked", Toast.LENGTH_SHORT).show()
                }
            }
            otpView.requestFocusOTP()
            otpView.otpListener = object : OTPListener {
                override fun onInteractionListener() {

                }

                override fun onOTPComplete(otp: String) {
                    btnContinue.isButtonEnabled = true
                }
            }

            btnContinue.setOnClickListener {
                navViewModel.navigate(R.id.frag_success, Bundle().apply {
                    putString(
                        Constants.SUCCESS_BUNDLE,
                        "Your ${viewModel.verificationTypeLiveData.value?.title ?: "identification"} has been successfully verified, you will now be redirected."
                    )
                })
            }
        }
    }


}