package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection
import com.dojah_inc.dojah_android_sdk.DojahSdk

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.View
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.core.Result
import com.dojah_inc.dojah_android_sdk.databinding.FragmentBusinessDataBinding
import com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.base.SpinnerFragment
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.GovDataViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.KycPages
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding



@SuppressLint("UnsafeRepeatOnLifecycleDetector")

class BusinessDataFragment : SpinnerFragment(R.layout.fragment_business_data) {
    private val binding by viewBinding { FragmentBusinessDataBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.govViewModelFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        govViewModel.submitBizLiveData.observe(this) {
            if (it is Result.Loading) {
                showLoading()
            } else {
                dismissLoading()
                if (it is Result.Success) {
                    navViewModel.navigateNextStep()
                } else if (it is Result.Error) {
                    navigateToErrorPage(it,page =KycPages.BUSINESS_DATA, govDataViewModel =govViewModel)
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val types = govViewModel.getBusinessTypes(viewModel)

        binding.apply {
            requireActivity().onBackPressedDispatcher.addCallback {
                if (popupWindow?.isShowing == true) {
                    popupWindow?.dismiss()
                } else {
                    navViewModel.popBackStack()
                }
            }
            govViewModel.selectedBizDataLiveData.observe(viewLifecycleOwner) {
                spinnerDocType.setText(it?.abbr)
                updateUIWithSelectedBizId(it)
            }

            govViewModel.prefillBizId(types?.first())


            spinnerDocType.setOnClickListener {

                if (types.isNullOrEmpty()) {
                    showShortToast("No document type available")
                    return@setOnClickListener
                }
                displaySpinnerDropdown(it, types.map { enum -> enum?.abbr ?: "" }, false) { index ->
                    val type = types[index]

                    govViewModel.selectBizIdentity(type)
                    spinnerDocType.setText(type?.abbr ?: "")

                }
            }


            btnContinue.setOnClickListener {
                if (spinnerDocType.text.isBlank()) {
                    showShortToast("Please select a Business ID")
                    return@setOnClickListener
                }

                val selectedID = govViewModel.selectedBizDataLiveData.value
                val maxLength = selectedID?.maxLength?.toIntOrNull()
                val minLength = selectedID?.minLength?.toIntOrNull()

                val bizNumber = inputRcNumber.editText!!.text
                val businessName = inputBizName.editText!!.text
                if (bizNumber.isNullOrBlank()) {
                    inputRcNumber.editText!!.error = "Field can't be empty"
                    return@setOnClickListener
                }

                if (businessName.isNullOrBlank()) {
                    inputBizName.editText!!.error = "Field can't be empty"
                    return@setOnClickListener
                }

                if (maxLength == null || minLength == null) {
                    inputRcNumber.editText!!.error = null
                } else {
                    if ((bizNumber.length > maxLength)) {
                        inputRcNumber.editText!!.error =
                            "Invalid input, maximum value is $maxLength"
                        return@setOnClickListener
                    } else if ((bizNumber.length < minLength)) {
                        inputRcNumber.editText!!.error =
                            "Invalid input, minimum value is ${minLength.toInt()}"
                        return@setOnClickListener
                    } else {
                        inputRcNumber.editText!!.error = null
                    }
                }
                govViewModel.submitBusinessData(
                    viewModel,
                    bizNumber.toString(),
                    businessName.toString()
                )
            }
        }
    }

    private fun FragmentBusinessDataBinding.updateUIWithSelectedBizId(bizId: EnumAttr?) {
        inputRcNumber.isVisible = bizId != null
        textRcNumber.isVisible = bizId != null
        textRcNumber.text = bizId?.abbr
        inputRcNumber.editText!!.hint = bizId?.placeholder
        inputRcNumber.editText!!.filters =
            arrayOf(InputFilter.LengthFilter(bizId?.maxLength?.toInt() ?: 100))

        inputRcNumber.editText!!.inputType =
            if (bizId?.inputType == "text") InputType.TYPE_CLASS_TEXT else InputType.TYPE_CLASS_NUMBER

    }


}