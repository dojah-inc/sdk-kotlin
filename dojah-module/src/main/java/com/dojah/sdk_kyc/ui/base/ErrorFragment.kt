package com.dojah.sdk_kyc.ui.base

import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.dojah.sdk_kyc.BuildConfig
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Result
import com.dojah.sdk_kyc.ui.dialog.TransactionErrorDialogFragment

open class ErrorFragment : Fragment {

    private var errorDialog: AlertDialog? = null

    private var loadingDialog: AlertDialog? = null

    private var genericDialog: AlertDialog? = null

    constructor() : super()

    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

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
        errorField: String = "msg",
        onDismiss: (() -> Unit)? = null,
        action: (() -> Unit)? = null
    ) {

        var title = errorTitle

        var statusCode: String? = null

        val errorMessage = when (error) {
            is Result.Error.NetworkError -> getString(R.string.error_network)
            is Result.Error.TimeoutError -> getString(R.string.error_timeout)
            is Result.Error.NoDataError -> getString(R.string.error_no_data)
            is Result.Error.DuplicateTransactionError -> getString(R.string.duplicate_transaction_error)
            else -> {
                (error as Result.Error.ApiError).error?.let {
                    //save the status code
                    statusCode = it[errorField]?.toString()

                    //extract the message
                    if (errorTitleField.isNotEmpty()) title =
                        it[errorTitleField].toString().ifEmpty { errorTitle }

                    if (title.contains("Suspended", true))  statusCode = "09"

                    it[errorField]?.toString() ?: ""
                } ?: if (BuildConfig.DEBUG) "Something went wrong" else "Unknown error"
            }
        }

        if (useToast && !(statusCode == "09" || statusCode == "10")) Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
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

    fun showShortToast(text: String = ""){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }
    fun showLongToast(text: String = ""){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show()
    }

    fun showLoading(text: String = "") {
        if (loadingDialog == null) {
            loadingDialog = MaterialAlertDialogBuilder(requireContext())
                .setView(R.layout.dialog_loading)
                .setCancelable(false)
                .create()
        }

        loadingDialog?.apply {
            setOnShowListener {
                findViewById<TextView>(R.id.text_title)?.text = text
            }
        }
        loadingDialog?.show()
    }

    fun dismissLoading() {
        loadingDialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        loadingDialog?.dismiss()
        errorDialog?.dismiss()
        loadingDialog = null
        errorDialog = null
    }
}