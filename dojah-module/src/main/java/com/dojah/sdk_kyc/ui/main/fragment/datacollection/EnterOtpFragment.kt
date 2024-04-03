package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
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
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.databinding.FragmentEnterOtp2Binding
import com.dojah.sdk_kyc.domain.responses.SendOtpEntity
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.MainActivity
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.FailedReasons
import com.dojah.sdk_kyc.ui.utils.KycPages
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
    private val activityGovViewModel by activityViewModels<GovDataViewModel>()

    private val navViewModel by activityViewModels<NavigationViewModel>()

    private var otpCode: String? = null
    private val logger = HttpLoggingInterceptor.Logger.DEFAULT


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
                                viewModel.prefManager.getMaterialButtonBgColor.let { brandColor ->
                                    if (brandColor == null) {
                                        context.getAttr(androidx.appcompat.R.attr.colorPrimary)
                                    } else {
                                        Color.parseColor(brandColor)
                                    }
                                }
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
                        govViewModel.resetSubmitGovLiveData()
                        if (it.data?.entity?.valid == false) {
                            showLongToast(FailedReasons.INVALID_OTP.message)
                            return@observe
                        }
                        logger.log("enter otp called next step")
                        if (navViewModel.currentPage != KycPages.EMAIL.serverKey) {
                            navViewModel.navigateNextStep()
                        }
                    } else if (it is Result.Error) {
                        navigateToErrorPage(it)
                    }
                }
            }
            govViewModel.collectEmailLiveData.observe(requireActivity()) {
                if (it == null) {
                    return@observe
                }
                if (it is Result.Success) {
                    dismissLoading()
                    govViewModel.resetCollectedEmailLiveData()
                    val emailEntity = it.data?.entity
                    navViewModel.resumeOngoingVerification(
                        requireActivity() as MainActivity,
                        emailEntity,
                    )
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
                val otpEntity: List<SendOtpEntity>? = getSendOtpEntity()

                val destination =
                    otpEntity?.first()?.destination

                if (destination != null) {
                    govViewModel.sendOtpSync(
                        viewModel,
                        destination,
                        currentRoute = navViewModel.currentPage
                            ?: KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey,
                        resent = true,
                        isEmail = navViewModel.currentPage == KycPages.EMAIL.serverKey,
                    )
                }
            }
        }
        binding.apply {
            val otpEntity: List<SendOtpEntity>? = getSendOtpEntity()
            val id =
                otpEntity?.first()?.destination
                    ?: "..."
            logger.log("enter otp id: $id")

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
                val endIndex = indexOf(resolvedId) + resolvedId.length
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
            otpView.chil
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
                val currentPageName =
                    navViewModel.currentPage ?: KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey
                val currentPage = KycPages.findPageEnum(currentPageName)
                    ?: KycPages.GOVERNMENT_DATA_VERIFICATION

                when (currentPage) {
                    KycPages.EMAIL -> {
                        govViewModel.validateEmailOtp(
                            viewModel,
                            otpCode!!,
                            currentPageName,
                            sentOtpEntity = getSendOtpEntity()?.first()
                        )
                    }

                    KycPages.PHONE_NUMBER -> {
                        govViewModel.validatePhoneOtp(
                            viewModel,
                            otpCode!!,
                            currentPageName,
                            sentOtpEntity = getSendOtpEntity()?.first()
                        )
                    }

                    else -> {
                        val sendOtpEntity = getSendOtpEntity()?.first()
                            ?: throw Exception("can't fetch otp reference")
                        val referenceId = sendOtpEntity.referenceId
                        govViewModel.validateGovDataPhoneOtp(
                            otpCode!!,
                            currentPageName,
                            referenceId = referenceId
                        )
                    }
                }
            }
        }
    }

    private fun getSendOtpEntity(): List<SendOtpEntity>? {
        val govVm =
            if (govViewModel.sendOtpLiveData.value is Result.Success) govViewModel else activityGovViewModel

        logger.log("enter otp val: ${govViewModel.sendOtpLiveData}")
        logger.log("enter otp value: ${govViewModel.sendOtpLiveData.value}")
        logger.log("act enter otp val: ${activityGovViewModel.sendOtpLiveData}")
        logger.log("act enter otp value: ${activityGovViewModel.sendOtpLiveData.value}")
        govVm.sendOtpLiveData.value?.also {
            if (it is Result.Success) {
                logger.log("enter otp entity: ${it.data?.entity}")
                return it.data?.entity
            }
        }
        return null
    }


}