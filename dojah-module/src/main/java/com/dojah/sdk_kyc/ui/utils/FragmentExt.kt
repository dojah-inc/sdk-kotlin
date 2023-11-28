package com.dojah.sdk_kyc.ui.utils

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.dojah.sdk_kyc.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Fragment.getColorPrimary(): Int {
    return requireContext().getColorPrimary()
}

fun Fragment.performOperationOnActivityAvailable(onAvailable: () -> Unit) {
    lifecycleScope.launch {
        suspend fun checkAndRunOp() {
            if (activity == null) {
                delay(100)
                checkAndRunOp()
            } else onAvailable()
        }

        checkAndRunOp()
    }
}

fun Fragment.hasAppForIntent(action: String,
                             mimetype: String? = null,
                             uri: String? = null): Boolean {
    val intent = Intent(action).apply {
        if (!mimetype.isNullOrEmpty()) type = mimetype
        if (!uri.isNullOrEmpty()) data = Uri.parse(uri)
    }

    return false

//    return requireActivity().packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
//            .isNotEmpty().also {
//                if(!it) Toast.makeText(requireContext(), R.string.no_app_available, Toast.LENGTH_SHORT).show()
//            }
}