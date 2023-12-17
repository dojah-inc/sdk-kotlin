package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.databinding.FragmentGovDataBinding
import com.dojah.sdk_kyc.domain.responses.EnumAttr
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.base.SpinnerFragment
import com.dojah.sdk_kyc.ui.main.fragment.NavArguments
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import com.dojah.sdk_kyc.ui.utils.*


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class GovDataFragment : SpinnerFragment(R.layout.fragment_gov_data) {
    private val binding by viewBinding { FragmentGovDataBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()
    private val logger = HttpLoggingInterceptor.Logger.DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        govViewModel.submitGovLiveData.observe(this) {
            if (it is Result.Loading) {
                showLoading("Loading...")
            } else {
                dismissLoading()
                if (it is Result.Success) {
                    navViewModel.navigateNextStep(args = Bundle().apply {
                        putString(
                            NavArguments.option,
                            govViewModel.verificationTypeLiveData.value?.serverKey
                        )
                    })
                } else if (it is Result.Error) {
                    navViewModel.navigate(Routes.error_fragment, args = Bundle().apply {
                        putString(NavArguments.option, viewModel.getErrorMessage(it))
                    })
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val gIds = govViewModel.getGovIdTypes(viewModel)

        val verificationMethods = govViewModel.getVerifyMethods(viewModel)

        binding.apply {
            govViewModel.selectedGovDataLiveData.observe(viewLifecycleOwner) {
                spinnerTextGid.setText(it?.name)
                updateUIwithSelectedGovId(it)
            }

            govViewModel.selectGovIdentity(gIds?.first())

            verificationMethods?.isNotEmpty().also { noVerifyMethod ->
                inputVerifyWith.isVisible = noVerifyMethod == true
                textVerifyWith.isVisible = noVerifyMethod == true
            }
            spinnerTextGid.setOnClickListener {
                displaySpinnerDropdown(it, gIds?.map { enum -> enum?.name ?: "" }, false) { index ->
                    val type = gIds?.get(index)
                    govViewModel.selectGovIdentity(type)
                }
            }

            spinnerVerifyWith.setOnClickListener {
                displaySpinnerDropdown(it, verificationMethods, false) { index ->
                    val verifyType =
                        verificationMethods?.get(index) ?: return@displaySpinnerDropdown
                    govViewModel.selectVerificationType(verifyType)
                    spinnerVerifyWith.setText(verifyType)
                }
            }

            btnContinue.setOnClickListener {

                if (spinnerTextGid.text.isBlank()) {
                    showShortToast("Please select a government ID")
                    return@setOnClickListener
                }
                if (govViewModel.verificationTypeLiveData.value == null) {
                    showShortToast("Please select a verification method")
                    return@setOnClickListener
                }
                val selectedID = govViewModel.selectedGovDataLiveData.value
                val maxLength = selectedID?.maxLength?.toIntOrNull()
                val minLength = selectedID?.minLength?.toIntOrNull()

                val inputText = textEdtBvn.text
                if (maxLength == null || minLength == null) {
                    textEdtBvn.error = null
                } else if (inputText.isNullOrBlank()) {
                    textEdtBvn.error = "Invalid input"
                    return@setOnClickListener
                } else if ((inputText.length > maxLength)) {
                    textEdtBvn.error = "Invalid input, maximum value is $maxLength"
                    return@setOnClickListener
                } else if ((inputText.length < minLength)) {
                    textEdtBvn.error = "Invalid input, minimum value is ${minLength.toInt()}"
                    return@setOnClickListener
                } else {
                    textEdtBvn.error = null
                }


                govViewModel.submitGovDataForm(viewModel, textEdtBvn.text.toString())

            }

//            performOperationOnActivityAvailable {}


            Timber.d("onCreateView>> toggleRewardBanner")
        }
    }

    private fun FragmentGovDataBinding.updateUIwithSelectedGovId(govId: EnumAttr?) {
        textInputBvn.isVisible = govId != null
        textBvn.isVisible = govId != null
        textBvn.text = govId?.name
        textEdtBvn.hint = govId?.placeholder
        textEdtBvn.filters = arrayOf(InputFilter.LengthFilter(govId?.maxLength?.toInt() ?: 100))

        textEdtBvn.inputType =
            if (govId?.inputType == "text") InputType.TYPE_CLASS_TEXT else InputType.TYPE_CLASS_NUMBER

    }


}