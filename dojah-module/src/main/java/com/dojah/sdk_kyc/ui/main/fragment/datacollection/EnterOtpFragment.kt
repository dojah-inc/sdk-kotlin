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
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.FailedReasons
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.getAttr
import com.dojah.sdk_kyc.ui.utils.setClickableText
import com.otpview.OTPListener
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class EnterOtpFragment : ErrorFragment(R.layout.fragment_enter_otp2) {
    private val binding by viewBinding { FragmentEnterOtp2Binding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()

    private var otpCode: String? = null
    private val logger = HttpLoggingInterceptor.Logger.DEFAULT



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
                if (it == null) {
                    return@observe
                }
                if (it is Result.Loading) {
                    showLoading()
                } else {
                    dismissLoading()
                    if (it is Result.Success) {
                        govViewModel.resetValidateOtpLiveData()
                        if (it.data?.entity?.valid == false) {
                            showLongToast(FailedReasons.INVALID_OTP.message)
                            return@observe
                        }
                        navViewModel.navigateNextStep()
                    } else if (it is Result.Error) {
                        navigateToErrorPage(it)
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

            var start = 0
            var end: Int? = null
            if (id.length >= 12) {
                start = 4
                end = id.length - 3
            } else if (id.length > 3) {
                start = 0
                end = id.length - 3
            }
            logger.log("actual id: $id")
            logger.log("start: $start")
            logger.log("end: $end")
            val resolvedId = if (end != null) {
                val aestericks = (start..end).map {
                    "*"
                }.joinToString("")
                id.replaceRange(start, end, aestericks)
            } else {
                id
            }
            logger.log("resolved id: $resolvedId")

            (getString(R.string.enter_the_code_sent, resolvedId)).toSpannable().apply {

                val startIndex = indexOf(resolvedId)
                val endIndex = indexOf(resolvedId) + id.length
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