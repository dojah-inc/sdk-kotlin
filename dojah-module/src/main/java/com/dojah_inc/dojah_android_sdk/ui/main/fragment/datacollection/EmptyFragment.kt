package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection
import com.dojah_inc.dojah_android_sdk.DojahSdk

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.navGraphViewModels
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.databinding.FragmentEmptyBinding
import com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.NavArguments
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.GovDataViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.FailedReasons
import com.dojah_inc.dojah_android_sdk.ui.utils.KycPages
import com.dojah_inc.dojah_android_sdk.ui.utils.VerificationType
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding

import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor


@SuppressLint("UnsafeRepeatOnLifecycleDetector")

class EmptyFragment : ErrorFragment(R.layout.fragment_empty) {
    private val binding by viewBinding { FragmentEmptyBinding.bind(it) }

        private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.govViewModelFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}
    val logger = HttpLoggingInterceptor.Logger.DEFAULT


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.log("empty page created")
        arguments?.getString(NavArguments.option).also {
            logger.log("doc Args: $it")
        }

        // if this fragment is used as a default route,
        // i.e: first route reserved for handling incomplete verification process
        if (navViewModel.currentPage == null) {
            logger.log("empty page is default route")
            viewModel.authDataFromPref?.initData?.authData?.also {

                val countryStep =
                    it.pages.find { page -> page.name == KycPages.COUNTRY.serverKey }
                // if country step is completed before resuming verification,
                // select a country from config before proceeding
                if (countryStep == null) {
                    logger.log("country page is completed, a country will be selected from config")
                    viewModel.getCountries().addCallback { countries ->
                        countries.find {
                            it.id.equals(
                                viewModel.authDataFromPref?.initData?.authData?.countryAlpha2Code,
                                ignoreCase = true
                            )
                        }?.also { country ->
                            //select country when resuming verification
                            viewModel.setSelectedCountry(country)
                            logger.log("prev Selected country: ${country.name}")
                        }
                    }
                } else {
                    logger.log("country page is available")
                }

                val firstPage = it.pages.first()

                // if government-data step is completed before resuming verification,
                if (firstPage.name == KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey) {

                    it.verificationTypeSelected?.also { enumKey ->
                        //select government-data-verification type when resuming verification
                        govViewModel.selectGovIdentity(viewModel.dojahEnum.toMap()[enumKey])
                        logger.log("gov data type vm updated: $enumKey")
                    }

                    firstPage.config?.mode.let { modeTmp ->
                        if (modeTmp == null) {
                            navigateToErrorPage(FailedReasons.UNKNOWN.message)
                            return@also
                        }
                        val mode = modeTmp.lowercase()
                        //select government-data-verification mode when resuming verification
                        govViewModel.selectVerificationType(mode)
                        logger.log("gov data mode vm updated: $mode")
                        if (mode.equals(VerificationType.OTP.serverKey, true)) {
                            logger.log("otp to be sent from main activity")
                            val phoneNumber = firstPage.config?.phone
                            //send otp before resuming verification
                            sendOtp(
                                phoneNumber,
                            ) {
                                //resume verification
                                navViewModel.navigateNextStep(args = Bundle().apply {
                                    putString(
                                        NavArguments.option,
                                        mode
                                    )
                                })
                            }
                        } else {
                            //resume verification
                            navViewModel.navigateNextStep(args = Bundle().apply {
                                putString(
                                    NavArguments.option,
                                    mode
                                )
                            })
                        }
                    }
                    return@also
                } else {
                    navViewModel.navigateNextStep()
                }

            }

        } else {
            throw Exception("You can only use this page as a default route")
        }

    }

    private fun sendOtp(
        phoneNumber: String? = null,
        onOtpSent: (() -> Unit)
    ) {
        govViewModel.viewModelScope.launch {
            showLoading()
            if (phoneNumber == null) {
                dismissLoading()
                navigateToErrorPage(FailedReasons.OTP_NOT_SENT.message)
                return@launch
            }
            govViewModel.sendOtp(
                viewModel,
                phoneNumber,
                KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey,
                onSuccess = {
                    dismissLoading()
                    onOtpSent.invoke()
                },
                onError = {
                    dismissLoading()
                    navigateToErrorPage(FailedReasons.OTP_NOT_SENT.message)
                }
            )
        }
    }


}