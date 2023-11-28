package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Constants
import com.dojah.sdk_kyc.databinding.FragmentBusinessDataBinding
import com.dojah.sdk_kyc.databinding.FragmentGovDataBinding
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.base.SpinnerFragment
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.performOperationOnActivityAvailable
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class BusinessDataFragment : SpinnerFragment(R.layout.fragment_business_data) {
    private val binding by viewBinding { FragmentBusinessDataBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.nav_graph) { defaultViewModelProviderFactory }

//    private val viewModel by viewModels<VerificationViewModel>()

    private val navViewModel by activityViewModels<NavigationViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.countryLiveData.observe(this) {
//            binding.layoutSpinner.apply {
//                items = it.filter {
//                    return@filter listOf<String>(
//                        "Nigeria",
//                        "Ghana",
//                        "Kenya",
//                        "Uganda",
//                        "Zimbabwe",
//                        "South Africa"
//                    ).contains(it.name)
//                }
//            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        binding.apply {
            spinnerDocType.text.isNotEmpty().also { govIdSelected ->
                textRcNumber.isVisible = govIdSelected
                inputRcNumber.isVisible = govIdSelected
                textTinNumber.isVisible = govIdSelected
                inputTinNumber.isVisible = govIdSelected
            }
            spinnerDocType.setOnClickListener {
                val types = viewModel.getBussinessTypes()

                displaySpinnerDropdown(it, types, false) { index ->
                    spinnerDocType.setText(types[index])
                    spinnerDocType.text.apply {
                        contains("RC").apply {
                            textRcNumber.isVisible = this
                            inputRcNumber.isVisible = this
                        }
                        contains("TIN").apply {
                            textTinNumber.isVisible = this
                            inputTinNumber.isVisible = this
                        }
                    }
                }
            }


            btnContinue.setOnClickListener {
                navViewModel.navigate(R.id.frag_disclaimer)
            }
            performOperationOnActivityAvailable {
//                addMenu()
//                setAppBarData(viewModel.profileLiveData.value)
            }


            Timber.d("onCreateView>> toggleRewardBanner")
        }
    }


}