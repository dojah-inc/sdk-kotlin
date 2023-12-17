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
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.databinding.FragmentEnterOtp2Binding
import com.dojah.sdk_kyc.domain.responses.SendOtpEntity
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.fragment.NavArguments
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
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

    //    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_nav_graph) { defaultViewModelProviderFactory }
    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()

    private var otpCode: String? = null


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
            govViewModel.validateOtpLiveData.observe(requireActivity()) {
                if (it is Result.Loading) {
                    showLoading()
                } else {
                    dismissLoading()
                    if (it is Result.Success) {
                        if (it.data?.entity?.valid == false) {
                            showLongToast("Invalid Otp")
                            return@observe
                        }
                        navViewModel.navigateNextStep()
                    } else if (it is Result.Error) {
                        navViewModel.navigate(Routes.error_fragment, Bundle().apply {
                            putString(NavArguments.option, getErrorMessage(it))
                        })
                    }
                }
            }
            govViewModel.sendOtpLiveData.observe(requireActivity()) {
                if (govViewModel.isResentOtpLiveData.value == true) {
                    if (it is Result.Loading) {
                        showLoading()
                    } else {
                        dismissLoading()
                        if (it is Result.Success) {
                            showShortToast("Resent")
                        }
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
                govViewModel.sendOtpSync(viewModel, true)
            }
        }
        binding.apply {
            var otpEntity: List<SendOtpEntity>? = null
            if (govViewModel.sendOtpLiveData.value is Result.Success) {
                otpEntity = (govViewModel.sendOtpLiveData.value as Result.Success).data?.entity
            }
            val id =
                otpEntity?.first()?.destination
                    ?: "..."
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
                    otpCode = otp
                    btnContinue.isButtonEnabled = true
                }
            }

            btnContinue.setOnClickListener {
                if (otpCode == null) {
                    throw Exception("otp is null")
                }
                govViewModel.validateOtp(otpCode!!)
            }
        }
    }


}