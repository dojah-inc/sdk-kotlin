package com.dojah.sdk_kyc.ui.base

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.dojah.sdk_kyc.BuildConfig
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.ui.dialog.TransactionErrorDialogFragment
import com.dojah.sdk_kyc.ui.main.MainActivity
import com.dojah.sdk_kyc.ui.main.fragment.NavArguments
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDataViewModel
import com.dojah.sdk_kyc.ui.utils.*
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel

open class ErrorFragment : Fragment {

    private var errorDialog: AlertDialog? = null

    private var loadingDialog: AlertDialog? = null

    private var genericDialog: AlertDialog? = null

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }
    private val navViewModel by activityViewModels<NavigationViewModel>()


    constructor() : super()

    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

    fun observeEvents(eventValue: String, argument: Bundle? = null) {
        viewModel.eventLiveData.observe(this) {
            if (it == null) {
                return@observe
            }
            if (it.second is Result.Loading) {
                showLoading()
            } else {
                dismissLoading()
                if (it.second is Result.Success) {
                    when (it.first.eventType) {
                        EventTypes.STEP_COMPLETED.serverKey -> {
                            when (eventValue) {
                                KycPages.INDEX.serverKey -> navViewModel.navigateNextStep(args = argument)

                                KycPages.GOVERNMENT_DATA.serverKey -> navViewModel.navigateNextStep(
                                    args = argument
                                )

                            }
                        }

                        EventTypes.COUNTRY_SELECTED.serverKey -> {
                            when (eventValue) {
                                KycPages.COUNTRY.serverKey -> {
                                    navViewModel.navigateNextStep(args = argument)
                                }
                            }
                        }

                    }
                    viewModel.resetEventData()

                } else {
                    navViewModel.navigate(Routes.error_fragment)
                    viewModel.resetEventData()

                }
            }
        }
    }

    fun navigateToErrorPage(
        it: Result.Error,
        page: KycPages? = null,
        govDataViewModel: GovDataViewModel? = null,
    ) {
        navViewModel.navigate(Routes.error_fragment, args = Bundle().apply {
            putString(
                NavArguments.option,
                viewModel.getErrorMessage(it, page = page, govDataViewModel = govDataViewModel)
            )
        })
    }

    /**
     * Used to handle all possible [Result.Error]
     * @param useToast to determine if a toast is displayed or a dialog
     * @param errorTitle the title to use to display the dialog, only used for a dialog
     * @param errorTitleField a json key value which will be used to search through the
     * class passed into [Result.Error.ApiError]. This will determine the title of the dialog
     * It takes precedence over @param errorTitle.
     * @param errorField a json key value which will be used to determine the error message displayed
     */
    fun handleError(
        error: Result.Error,
        useToast: Boolean = true,
        errorTitle: String = "",
        errorTitleField: String = "title",
        errorField: String = "error",
        onDismiss: (() -> Unit)? = null,
        action: (() -> Unit)? = null
    ) {

        var title = errorTitle

        var statusCode: String? = null

        val errorMessage = when (error) {
            is Result.Error.NetworkError -> getString(R.string.error_network)
            is Result.Error.TimeoutError -> getString(R.string.error_timeout)
            is Result.Error.NoDataError -> getString(R.string.error_no_data)
            else -> {
                (error as Result.Error.ApiError).error?.let {
                    //save the status code
                    statusCode = it[errorField]?.toString()

                    //extract the message
                    if (errorTitleField.isNotEmpty()) title =
                        it[errorTitleField].toString().ifEmpty { errorTitle }

                    it[errorField]?.toString() ?: ""
                } ?: if (BuildConfig.DEBUG) "Something went wrong" else "Unknown error"
            }
        }

        if (useToast && !(statusCode == "09" || statusCode == "10")) Toast.makeText(
            requireContext(),
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
        else {
            TransactionErrorDialogFragment.getInstance(title, errorMessage, statusCode).apply {
                action?.also {
                    onPositiveButtonClicked = {
                        it()
                        dismiss()
                    }
                }

                onNegativeButtonClicked = { dismiss() }

                this.onDismiss = { onDismiss?.invoke() }

                show(this@ErrorFragment.childFragmentManager, null)
            }
        }
    }

    fun showShortToast(text: String = "") {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(text: String = "") {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    fun showLoading() {
        (requireActivity() as MainActivity).showLoading()
    }

    fun dismissLoading() {
        (requireActivity() as MainActivity).dismissLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
        loadingDialog?.dismiss()
        errorDialog?.dismiss()
        loadingDialog = null
        errorDialog = null
    }
}