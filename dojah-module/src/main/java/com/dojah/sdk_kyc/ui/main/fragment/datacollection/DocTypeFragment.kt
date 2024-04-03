package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.databinding.FragmentDocTypeBinding
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.base.SpinnerFragment
import com.dojah.sdk_kyc.ui.main.fragment.NavArguments
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.performOperationOnActivityAvailable
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import kotlin.math.log


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class DocTypeFragment : SpinnerFragment(R.layout.fragment_doc_type) {
    private val binding by viewBinding { FragmentDocTypeBinding.bind(it) }

    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val viewModelActivity by activityViewModels<VerificationViewModel> { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()
    private var verificationType: String? = null
    val logger = HttpLoggingInterceptor.Logger.DEFAULT


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(NavArguments.option).also {
            logger.log("doc Args: $it")
        }

        govViewModel.submitGovLiveData.observe(this) {
            if (it is Result.Loading) {
                showLoading()
            } else {
                dismissLoading()
                if (it is Result.Success) {
                    navViewModel.navigateNextStep()
                    logger.log("id options triggered")
                    govViewModel.resetSubmitGovLiveData()
                } else if (it is Result.Error) {
                    navigateToErrorPage(it)
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            requireActivity().onBackPressedDispatcher.addCallback {
                if (popupWindow?.isShowing == true) {
                    popupWindow?.dismiss()
                } else {
                    navViewModel.popBackStack()
                }
            }
            spinnerTextType.setOnClickListener {
                val gIds = govViewModel.getDocIDTypes(viewModel)

                if (gIds.isNullOrEmpty()) {
                    showShortToast("No document type available")
                    return@setOnClickListener
                }
                displaySpinnerDropdown(it, gIds.map { enum -> enum?.name ?: "" }, false) { index ->
                    val selected = gIds[index]?.name ?: ""
                    spinnerTextType.setText(selected)
                    viewModel.selectDocType(selected)
                }
            }

            btnContinue.setOnClickListener {
                if (viewModel.docTypeLiveData.value == null) {
                    showShortToast("Please select a document type")
                    return@setOnClickListener
                }
                govViewModel.logIdOptionEvents(
                    navGraphVm = viewModel,
                    activityVm = viewModelActivity
                )
            }

            performOperationOnActivityAvailable {
                ///alter activity UI
            }
        }
    }


}