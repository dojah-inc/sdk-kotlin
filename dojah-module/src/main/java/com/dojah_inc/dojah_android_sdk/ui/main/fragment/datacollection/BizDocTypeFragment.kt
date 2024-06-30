package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection

import com.dojah_inc.dojah_android_sdk.DojahSdk

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.databinding.FragmentDocTypeBinding
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.base.SpinnerFragment
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.GovDataViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding
import com.dojah_inc.dojah_android_sdk.ui.utils.performOperationOnActivityAvailable

import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")

class BizDocTypeFragment : SpinnerFragment(R.layout.fragment_doc_type) {
    private val binding by viewBinding { FragmentDocTypeBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }
    private val govViewModel by navGraphViewModels<GovDataViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.govViewModelFactory }

    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }
    private var verificationType: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            spinnerTextType.setOnClickListener {
//                val methods = viewModel.getDocType()

//                displaySpinnerDropdown(it, methods, false) { index ->
//                    val selected = methods[index]
//                    spinnerTextType.setText(selected)
//                    viewModel.selectDocType(selected)
//
//                }
            }

            btnContinue.setOnClickListener {
                if (viewModel.docTypeLiveData.value == null) {
                    showShortToast("Please select a document type")
                    return@setOnClickListener
                }
//                if (viewModel.docTypeLiveData.value == GovDocType.BUSINESS) {
//                    navViewModel.navigate(R.id.frag_biz_data)
//                } else {
                navViewModel.navigateOld(R.id.frag_disclaimer)
//                }
            }
            performOperationOnActivityAvailable {
//                addMenu()
//                setAppBarData(viewModel.profileLiveData.value)
            }


            Timber.d("onCreateView>> toggleRewardBanner")
        }
    }


}