package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.customquestions.composables.CustomQuestionsScreen
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages
import java.util.UUID
import kotlin.getValue

class CustomQuestionsFragment : ErrorFragment() {
    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }
    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }

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
                // Read config once and memoize — avoids SharedPreferences disk read and
                // _pages.postValue() side-effect on every recomposition.
                val questions = remember {
                    val config =
                        viewModel.getStepWithPageName(KycPages.CUSTOM_QUESTIONS.serverKey)?.config
                    QuestionConfig(
                        title = config?.title ?: "Compliance Questions",
                        questions = config?.questions?.map { question ->
                            Question(
                                text = question.text ?: "",
                                type = when (question.type) {
                                    "text" -> QuestionType.text
                                    "single" -> QuestionType.single
                                    else -> QuestionType.multiple
                                },
                                // Filter null elements — Gson bypasses Kotlin's non-null
                                // List<String> annotation and can inject nulls from the JSON,
                                // causing NPE in option.second.toTitleCase() at render time.
                                options = question.options
                                    ?.filterNotNull()
                                    ?.map { Pair(UUID.randomUUID().toString(), it) }
                                    ?: emptyList()
                            )
                        } ?: emptyList()
                    )
                }

                MaterialTheme {
                    CustomQuestionsScreen(
                        input = questions,
                        onContinue = { output ->
                            handleQuestionsOutput(output)
                        }
                    )
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOutput()
    }

    private fun observeOutput() {
        viewModel.submitEventLiveData.observe(viewLifecycleOwner) {
            it ?: return@observe
            when (it) {
                is Result.Loading -> {
                    showLoading()
                }

                is Result.Success -> {
                    dismissLoading()
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

    private fun handleQuestionsOutput(output: QuestionsOutput) {
        viewModel.sendCustomQuestionAnswer(output.eventValue)
    }
}