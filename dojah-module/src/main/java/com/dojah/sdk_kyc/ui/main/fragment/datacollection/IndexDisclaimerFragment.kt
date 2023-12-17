package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.DialogDisclaimerBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.*
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class IndexDisclaimerFragment : ErrorFragment(R.layout.dialog_disclaimer) {
    private val binding by viewBinding { DialogDisclaimerBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvents(KycPages.INDEX.serverKey)
//        viewModel.eventLiveData.observe(this) {
//            if (it.second is Result.Loading) {
//                showLoading("Loading...")
//            } else {
//                dismissLoading()
//                if (it.second is Result.Success) {
//                    when (it.first.eventType) {
//                        EventTypes.STEP_COMPLETED.serverKey -> {
//                            navViewModel.navigateNextStep()
//                        }
//                    }
//                } else {
//                    navViewModel.navigate(Routes.error_fragment)
//                }
//            }
//        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        binding.apply {

            btnContinue.setOnClickListener {
                val stepNumber = viewModel.getCurrentPage(KycPages.INDEX.serverKey)?.id
                    ?: throw Exception("No stepNumber")

                viewModel.logEvent(
                    EventTypes.STEP_COMPLETED.serverKey,
                    KycPages.INDEX.serverKey,
                    stepNumber = stepNumber,
                )
            }

        }
    }


}