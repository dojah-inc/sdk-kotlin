package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection
import com.dojah.kyc_sdk_kotlin.DojahSdk

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.databinding.FragmentOtpPhoneBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.FailedReasons
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")

class PhoneOtpFragment : ErrorFragment(R.layout.fragment_otp_phone) {
    private val binding by viewBinding { FragmentOtpPhoneBinding.bind(it) }

        private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.govViewModelFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}


    override fun onResume() {
        super.onResume()
        reloadCountry()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        reloadCountry()
        binding.apply {
            requireActivity().onBackPressedDispatcher.addCallback {
                if (layoutSpinner.spinnerPopUp.isShowing) {
                    layoutSpinner.spinnerPopUp.dismiss()
                } else {
                    navViewModel.popBackStack()
                }
            }
            layoutSpinner.editText.addTextChangedListener {
                if (layoutSpinner.getTextWithPrefix().isEmpty()) {
                    layoutSpinner.editText.error = null
                    btnContinue.isEnabled = false
                    return@addTextChangedListener
                }
                if (isValidPhone(layoutSpinner.getTextWithPrefix())) {
                    layoutSpinner.editText.error = null
                    btnContinue.isEnabled = true
                } else {
                    layoutSpinner.editText.error = "Invalid Phone Number"
                    btnContinue.isEnabled = false
                }
            }
            btnContinue.setOnClickListener {
                val phoneConfig =
                    viewModel.getStepWithPageName(KycPages.PHONE_NUMBER.serverKey)?.config
//                HttpLoggingInterceptor.Logger.DEFAULT.log("phoneConfig: $phoneConfig")
//                HttpLoggingInterceptor.Logger.DEFAULT.log("phone verify: ${phoneConfig?.verification}")
                if (phoneConfig?.verification == true) {
                    govViewModel.sendOtpSync(
                        verificationVm = viewModel,
                        destination = layoutSpinner.getTextWithPrefix(),
                        currentRoute = KycPages.PHONE_NUMBER.serverKey,
                        isEmail = false
                    )
                } else {
                    govViewModel.collectPhoneNumber(
                        viewModel,
                        phone = layoutSpinner.getTextWithPrefix(),
                    )
                }
            }
        }
    }

    private fun observeLiveData() {
        govViewModel.sendOtpLiveData.observe(requireActivity()) {
            if(view==null){
                return@observe
            }
            binding.root.post {
                binding.apply {

                    if (it == null) return@post

                    if (it is Result.Loading) {
                        showLoading()
                    } else {
                        dismissLoading()
                        govViewModel.resetSubmitGovLiveData()
                        if (it is Result.Success) {
                            hideError()
                            navViewModel.navigate(Routes.otp_route)
                        } else if (it is Result.Error) {
                            if (it is Result.Error.ApiError) {
                                errorTag.text = viewModel.getErrorMessage(
                                    it,
                                    page = KycPages.PHONE_NUMBER,
                                    govDataViewModel = govViewModel
                                )
                                errorTag.isVisible = true

                            } else {
                                hideError()
                                navigateToErrorPage(
                                    it,
                                    page = KycPages.PHONE_NUMBER,
                                    govDataViewModel = govViewModel
                                )
                            }
                        }
                    }
                }
            }
        }


        govViewModel.validateOtpLiveData.observe(requireActivity()) {
            if (it == null) {
                return@observe
            }
            if(view==null){
                return@observe
            }
            binding.root.post {
                binding.apply {

                    if (it is Result.Loading) {
                        showLoading()
                    } else {
                        dismissLoading()
                        if (it is Result.Success) {
                            govViewModel.resetValidateOtpLiveData()
                            hideError()
                            if (it.data?.entity?.valid == true) {
                                navViewModel.navigateNextStep()
                            } else {
                                errorTag.text = FailedReasons.INVALID_OTP.message
                                errorTag.isVisible = true
                            }
                        } else if (it is Result.Error) {
                            if (it is Result.Error.ApiError) {
                                errorTag.text = viewModel.getErrorMessage(
                                    it,
                                    page = KycPages.PHONE_NUMBER,
                                    govDataViewModel = govViewModel
                                )
                                errorTag.isVisible = true
                            } else {
                                hideError()
                                navigateToErrorPage(it)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun reloadCountry() {
        viewModel.loadCountries()
        viewModel.countryLiveData.observe(viewLifecycleOwner) {
            binding.layoutSpinner.items = it
        }
    }


    private fun FragmentOtpPhoneBinding.hideError() {
        errorTag.isVisible = false
    }

}

fun isValidPhone(email: CharSequence?): Boolean {
    return !email.isNullOrEmpty() && Patterns.PHONE.matcher(email).matches()
}
