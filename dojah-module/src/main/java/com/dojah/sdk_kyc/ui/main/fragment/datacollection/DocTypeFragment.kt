package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.core.Constants
import com.dojah.sdk_kyc.databinding.FragmentDocTypeBinding
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.base.SpinnerFragment
import com.dojah.sdk_kyc.ui.main.viewmodel.GovDocType
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.performOperationOnActivityAvailable
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class DocTypeFragment : SpinnerFragment(R.layout.fragment_doc_type) {
    private val binding by viewBinding { FragmentDocTypeBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(R.id.gov_id_nav_graph) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()
    private var verificationType: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

            spinnerTextType.setOnClickListener {
                val methods = viewModel.getDocType()

                displaySpinnerDropdown(it, methods, false) { index ->
                    val selected = methods[index]
                    spinnerTextType.setText(selected)
                    viewModel.selectDocType(selected)

                }
            }

            btnContinue.setOnClickListener {
                if(viewModel.docTypeLiveData.value == null){
                    showShortToast("Please select a document type")
                    return@setOnClickListener
                }
                if (viewModel.docTypeLiveData.value == GovDocType.BUSINESS) {
                    navViewModel.navigate(R.id.frag_biz_data)
                } else {
                    navViewModel.navigate(R.id.frag_disclaimer)
                }
            }
            performOperationOnActivityAvailable {
//                addMenu()
//                setAppBarData(viewModel.profileLiveData.value)
            }


            Timber.d("onCreateView>> toggleRewardBanner")
        }
    }


}