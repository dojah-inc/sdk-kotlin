package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.capturesignature

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.capturesignature.composable.CaptureSignatureScreen
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import kotlin.getValue

class CaptureSignatureFragment : ErrorFragment() {

    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }
    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOutput()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )

            setContent {
                val pageConfig = remember {
                    val config =
                        viewModel.getStepWithPageName(KycPages.SIGNATURE.serverKey)?.config
                    CaptureSignatureUIData(
                        title = config?.title.orEmpty(),
                        description = config?.information.orEmpty()
                    )
                }

                MaterialTheme {
                    CaptureSignatureScreen(data = pageConfig) { name, signature ->
                        viewModel.sendSignatureData(name,  "data:image/jpeg;base64,$signature")
                    }
                }
            }

        }
    }

    private fun observeOutput() {
        viewModel.submitEventLiveData.observe(viewLifecycleOwner) {
            it ?: return@observe
            when (it) {
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    viewModel.resetQuestionEvent()
                    navViewModel.navigateNextStep()
                }

                else -> {
                    dismissLoading()
                    showLongToast("Failed to submit answers. Please try again.")
                }
            }
        }
    }
}