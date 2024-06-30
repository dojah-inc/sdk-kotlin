package com.dojah_inc.dojah_android_sdk.ui.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.dojah_inc.dojah_android_sdk.DojahSdk
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.databinding.DialogTransactionErrorBinding
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding

class TransactionErrorDialogFragment : BottomSheetDialogFragment() {
    companion object{
        private const val EXTRA_TITLE = "com.africave.kippapay.TransactionError.TITLE"

        private const val EXTRA_TEXT = "com.africave.kippapay.TransactionError.TEXT"

        private const val EXTRA_STATUS = "com.africave.kippapay.TransactionError.STATUS"

        private const val STATUS_09 = "09"
        private const val STATUS_10 = "10"

        fun getInstance(title: String, text: String, status: String? = "00") = TransactionErrorDialogFragment().apply {
            arguments = bundleOf(EXTRA_TITLE to title, EXTRA_TEXT to text, EXTRA_STATUS to status)
        }
    }

    private val binding by viewBinding { DialogTransactionErrorBinding.bind(it) }

    private val navViewModel by activityViewModels<NavigationViewModel>{ DojahSdk.dojahContainer.navViewModelFactory}

    var onPositiveButtonClicked: (() -> Unit)? = null

    var onNegativeButtonClicked: (() -> Unit)? = null

    var onDismiss: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_transaction_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            val status = arguments?.getString(EXTRA_STATUS)

            onPositiveButtonClicked?.let {
                buttonFundWallet.isVisible = true

                if(status == STATUS_09 || status == STATUS_10) {
                    buttonFundWallet.text = getString(R.string.unlock_device)
                }
            }

            textTitle.text = arguments?.getString(EXTRA_TITLE)

            textText.text = arguments?.getString(EXTRA_TEXT)

            buttonFundWallet.setOnClickListener {
                if(status == STATUS_09 || status == STATUS_10) {
//                    navViewModel.navigate(R.id.frag_change_device)
                } else onPositiveButtonClicked?.invoke()
            }

            textMaybeLater.setOnClickListener { onNegativeButtonClicked?.invoke() }

            imageClose.setOnClickListener { dismiss() }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        onDismiss?.invoke()
    }
}