package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.View
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.navGraphViewModels
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.core.Result
import com.dojah_inc.dojah_android_sdk.databinding.FragmentGovDataBinding
import com.dojah_inc.dojah_android_sdk.domain.responses.EnumAttr
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.base.SpinnerFragment
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.NavArguments
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.GovDataViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor
import com.dojah_inc.dojah_android_sdk.ui.utils.*
import com.google.android.material.shape.MaterialShapeDrawable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
                showLoading()
            } else {
                dismissLoading()
                govViewModel.resetSubmitGovLiveData()
                if (it is Result.Success) {
                    hideError()
                    navViewModel.navigateNextStep(args = Bundle().apply {
                        putString(
                            NavArguments.option,
                            govViewModel.verificationTypeLiveData.value?.serverKey
                        )
                    })
                } else if (it is Result.Error) {
                    if (it is Result.Error.ApiError) {
                        binding.root.post {
                            binding.apply {
                                errorTag.text = viewModel.getErrorMessage(
                                    it,
                                    page = KycPages.GOVERNMENT_DATA,
                                    govDataViewModel = govViewModel
                                )
                                errorTag.isVisible = true
                                infoTag.isVisible = false
                            }
                        }
                        return@observe
                    }
                    hideError()
                    navigateToErrorPage(
                        it,
                        page = KycPages.GOVERNMENT_DATA,
                        govDataViewModel = govViewModel
                    )
                }
            }
        }
    }

    private fun hideError() {
        binding.root.post {
            binding.apply {
                errorTag.isVisible = false
                infoTag.isVisible = false
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val gIds = govViewModel.getGovIdTypes(viewModel)

        val verificationMethods = govViewModel.getVerifyMethods(viewModel)

        binding.apply {
            errorTag.background = MaterialShapeDrawable().apply {
                setTint(ContextCompat.getColor(requireContext(), R.color.error_bg_color))

                setCornerSize(30.toFloat())

            }
            errorTag.isVisible = false
            requireActivity().onBackPressedDispatcher.addCallback {
                if (popupWindow?.isShowing == true) {
                    popupWindow?.dismiss()
                } else {
                    navViewModel.popBackStack()
                }
            }
            govViewModel.selectedGovDataLiveData.observe(viewLifecycleOwner) {
                spinnerTextGid.setText(it?.name)
                updateUIwithSelectedGovId(it)
            }

            govViewModel.prefillGovIdentity(gIds?.first())
            prefillVerificationMethod(verificationMethods)

            (verificationMethods?.isNotEmpty() == true && verificationMethods.size > 1).also { noVerifyMethod ->
                inputVerifyWith.isVisible = noVerifyMethod == true
                textVerifyWith.isVisible = noVerifyMethod == true
            }
            spinnerTextGid.setOnClickListener {

                displaySpinnerDropdown(it, gIds?.map { enum -> enum?.name ?: "" }, false) { index ->
                    val type = gIds?.get(index)
                    govViewModel.selectGovIdentity(type)
                    prefillVerificationMethod(verificationMethods)
                }
            }

            spinnerVerifyWith.setOnClickListener {
//                logger.log("popupWindow?.isShowing: ${popupWindow?.isShowing}}")
                val tmpMethods = getFilteredMethods(verificationMethods)
                displaySpinnerDropdown(it, tmpMethods, false) { index ->
                    val verifyType =
                        tmpMethods?.get(index) ?: return@displaySpinnerDropdown
                    govViewModel.selectVerificationType(verifyType)
                    spinnerVerifyWith.setText(verifyType)
                }
            }

            btnContinue.setOnClickListener {

                if (spinnerTextGid.text.isBlank()) {
                    showShortToast("Please select a government ID")
                    return@setOnClickListener
                }

                if (govViewModel.selectedGovDataLiveData.value?.id == GovDocType.DL.id &&
                    verificationMethods?.size == 1 &&
                    verificationMethods.first().lowercase() == VerificationType.OTP.serverKey.lowercase()
                    ) {
                    showShortToast("No verification method available for Driver's License")
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
                if (inputText.isNullOrBlank()) {
                    textEdtBvn.error = "Invalid input"
                    return@setOnClickListener
                }
                if (maxLength == null || minLength == null) {
                    textEdtBvn.error = null
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


        }
    }

    private fun FragmentGovDataBinding.prefillVerificationMethod(verificationMethods: List<String>?) {
        govViewModel.viewModelScope.launch {
            delay(200)
            val tmpMethods = getFilteredMethods(verificationMethods)
            if (tmpMethods?.size == 1) {
                val verifyType =
                    tmpMethods.first()
                govViewModel.selectVerificationType(verifyType)
                spinnerVerifyWith.setText(verifyType)
            } else {
                govViewModel.selectVerificationType(null)
                spinnerVerifyWith.text = null
            }
        }
    }

    private fun getFilteredMethods(verificationMethods: List<String>?): List<String>? {
        if (govViewModel.selectedGovDataLiveData.value?.id == GovDocType.DL.id) {
            return verificationMethods?.filter { method ->
                logger.log("prefillVerificationMethod each origin: $method")

                return@filter method.lowercase() != VerificationType.OTP.serverKey.lowercase()
            }
        } else {
            return verificationMethods
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