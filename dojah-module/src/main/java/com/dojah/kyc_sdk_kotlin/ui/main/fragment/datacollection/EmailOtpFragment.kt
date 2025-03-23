package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection
import com.dojah.kyc_sdk_kotlin.DojahSdk

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.databinding.FragmentOtpEmailBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.DojahMainActivity
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.getText
import com.google.android.material.shape.MaterialShapeDrawable



@SuppressLint("UnsafeRepeatOnLifecycleDetector")

class EmailOtpFragment : ErrorFragment(R.layout.fragment_otp_email) {
    private val binding by viewBinding { FragmentOtpEmailBinding.bind(it) }

        private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.govViewModelFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        govViewModel.sendOtpLiveData.observe(this) {
            if (it == null) return@observe
            binding.root.post {
                binding.apply {
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
                                    page = KycPages.EMAIL,
                                    govDataViewModel = govViewModel
                                )
                            } else {
                                hideError()
                                navigateToErrorPage(
                                    it,
                                    page = KycPages.EMAIL,
                                    govDataViewModel = govViewModel
                                )
                            }
                        }
                    }
                }
            }
        }

        govViewModel.collectEmailLiveData.observe(requireActivity()) {
            if (it == null) {
                return@observe
            }
            if (view == null) {
                return@observe
            }
            if (it is Result.Loading) {
                showLoading()
            } else {
                dismissLoading()
                binding.root.post {
                    binding.apply {
                        if (it is Result.Success) {
                            govViewModel.resetValidateOtpLiveData()
                            govViewModel.resetCollectedEmailLiveData()
                            hideError()
                            val emailEntity = it.data?.entity
                            navViewModel.resumeOngoingVerification(
                                requireActivity() as DojahMainActivity,
                                emailEntity,
                            )
                        } else if (it is Result.Error) {
                            if (it is Result.Error.ApiError) {
                                errorTag.text = viewModel.getErrorMessage(
                                    it,
                                    page = KycPages.EMAIL,
                                    govDataViewModel = govViewModel
                                )
                                errorTag.isVisible = true

                                return@post
                            }
                            hideError()
                            navigateToErrorPage(it)
                        }
                    }
                }
            }
        }
    }

    private fun FragmentOtpEmailBinding.hideError() {
        errorTag.isVisible = false
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.readMailList(requireContext())
        binding.apply {
            errorTag.background = MaterialShapeDrawable().apply {
                setTint(ContextCompat.getColor(requireContext(), R.color.error_bg_color))

                setCornerSize(50.toFloat())

            }
            textInputEmail.editText?.addTextChangedListener {
                if (it.toString().isEmpty()) {
                    textInputEmail.error = null
                    btnContinue.isEnabled = false
                    return@addTextChangedListener
                }
                if (isValidEmail(it.toString())) {
                    if (viewModel.isDisposableMail(it.toString())) {
                        textInputEmail.error = "Please avoid using a disposable email address"
                        btnContinue.isEnabled = false
                    } else if (viewModel.isFreeMail(it.toString())) {
                        textInputEmail.error = "Please enter a valid business email address"
                        btnContinue.isEnabled = false
                    } else {
                        textInputEmail.error = null
                        btnContinue.isEnabled = true

                    }
                } else {
                    textInputEmail.error = "Invalid Email"
                    btnContinue.isEnabled = false
                }
            }
            btnContinue.setOnClickListener {
                val mailConfig = viewModel.getStepWithPageName(KycPages.EMAIL.serverKey)?.config
                if (mailConfig?.verification == true) {
                    govViewModel.sendOtpSync(
                        verificationVm = viewModel,
                        destination = textInputEmail.getText().trim(),
                        currentRoute = KycPages.EMAIL.serverKey,
                        isEmail = true
                    )
                } else {
                    govViewModel.collectEmailWithoutOtp(
                        viewModel,
                        email = textInputEmail.getText().trim(),
                    )
                }
            }

        }


    }
}

fun isValidEmail(email: CharSequence?): Boolean {
    return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}