package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Constants
import com.dojah.sdk_kyc.databinding.FragmentGovDataBinding
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.base.SpinnerFragment
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDocType
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.performOperationOnActivityAvailable
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class GovDataFragment : SpinnerFragment(R.layout.fragment_gov_data) {
    private val binding by viewBinding { FragmentGovDataBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_nav_graph) { defaultViewModelProviderFactory }

//    private val viewModel by viewModels<VerificationViewModel>()

    private val navViewModel by activityViewModels<NavigationViewModel>()

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
                val gIds = viewModel.getGIDs(this@GovDataFragment.requireContext())

                displaySpinnerDropdown(it, gIds, false) { index ->
                    val type = gIds[index]
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
                val type = viewModel.getVerifyMethods()

                displaySpinnerDropdown(it, type, false) { index ->
                    val verifyType = type[index]
                    viewModel.selectVerificationType(verifyType)
                    spinnerVerifyWith.setText(verifyType)
                }
            }

            btnContinue.setOnClickListener {
                if(spinnerTextGid.text.isBlank()){
                    showShortToast("Please select a government ID")
                    return@setOnClickListener
                }
                if(viewModel.verificationTypeLiveData.value==null){
                    showShortToast("Please select a verification method")
                    return@setOnClickListener
                }
                navViewModel.navigate(R.id.gov_id_nav_graph)

            }
            performOperationOnActivityAvailable {
//                addMenu()
//                setAppBarData(viewModel.profileLiveData.value)
            }


            Timber.d("onCreateView>> toggleRewardBanner")
        }
    }


}