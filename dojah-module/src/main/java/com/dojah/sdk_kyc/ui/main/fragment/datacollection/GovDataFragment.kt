package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.FragmentGovDataBinding
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.base.SpinnerFragment
import com.dojah.sdk_kyc.ui.main.fragment.NavArguments
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDocType
import com.dojah.sdk_kyc.ui.main.viewmodel.KycPages
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationType
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.performOperationOnActivityAvailable
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class GovDataFragment : SpinnerFragment(R.layout.fragment_gov_data) {
    private val binding by viewBinding { FragmentGovDataBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()
    private val logger = HttpLoggingInterceptor.Logger.DEFAULT

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        binding.apply {
            spinnerTextGid.text.isNotEmpty().also { govIdSelected ->
                textInputBvn.isVisible = govIdSelected
                textBvn.isVisible = govIdSelected
                textInputDriver.isVisible = govIdSelected
                textDriver.isVisible = govIdSelected
                inputVerifyWith.isVisible = govIdSelected
                textVerifyWith.isVisible = govIdSelected
                textInputNin.isVisible = govIdSelected
                textNin.isVisible = govIdSelected
            }
            spinnerTextGid.setOnClickListener {

                val gIds =
                    viewModel.getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey)?.config?.govIds?.map { govIdKey ->
                        GovDocType.findEnumWithKey(govIdKey)?.value ?: govIdKey
                    }

                displaySpinnerDropdown(it, gIds, false) { index ->
                    val type = gIds?.get(index)
                    spinnerTextGid.setText(type)
                    spinnerTextGid.text.apply {
                        contains("BVN").apply {
                            textInputBvn.isVisible = this
                            textBvn.isVisible = this
                        }
                        contains("Driver").apply {
                            textInputDriver.isVisible = this
                            textDriver.isVisible = this
                        }
                        contains("NIN").apply {
                            textInputNin.isVisible = this
                            textNin.isVisible = this
                        }
                    }

                    spinnerTextGid.text.isNotEmpty().also { govIdSelected ->
                        inputVerifyWith.isVisible = govIdSelected
                        textVerifyWith.isVisible = govIdSelected
                    }
                }
            }

            spinnerVerifyWith.setOnClickListener {
                val verificationMethods =
                    viewModel.getCurrentPage(KycPages.GOVERNMENT_DATA.serverKey)?.config?.verificationMethods?.map { method ->
                        VerificationType.findEnumWithKey(method)?.value ?: method
                    }

                displaySpinnerDropdown(it, verificationMethods, false) { index ->
                    val verifyType =
                        verificationMethods?.get(index) ?: return@displaySpinnerDropdown
                    viewModel.selectVerificationType(verifyType)
                    spinnerVerifyWith.setText(verifyType)
                }
            }

            btnContinue.setOnClickListener {

                if (spinnerTextGid.text.isBlank()) {
                    showShortToast("Please select a government ID")
                    return@setOnClickListener
                }
                if (viewModel.verificationTypeLiveData.value == null) {
                    showShortToast("Please select a verification method")
                    return@setOnClickListener
                }
                navViewModel.navigateNextStep(args = Bundle().apply {
                    putString(
                        NavArguments.option,
                        viewModel.verificationTypeLiveData.value?.serverKey
                    )
                })
//                return@setOnClickListener

//                val nextRoute = arguments?.getString(NavArguments.index_next_page)
//                HttpLoggingInterceptor.Logger.DEFAULT.log("next route click is $nextRoute")
//                navViewModel.navigate("$nextRoute")
            }
            performOperationOnActivityAvailable {
//                addMenu()
//                setAppBarData(viewModel.profileLiveData.value)
            }


            Timber.d("onCreateView>> toggleRewardBanner")
        }
    }


}