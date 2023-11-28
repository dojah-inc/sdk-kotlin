package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.text.toSpannable
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.FragmentCountryBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.getAttr
import com.dojah.sdk_kyc.ui.utils.performOperationOnActivityAvailable
import com.dojah.sdk_kyc.ui.utils.setClickableText
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class CountryFragment : ErrorFragment(R.layout.fragment_country) {
    private val binding by viewBinding { FragmentCountryBinding.bind(it) }

    private val viewModel by viewModels<VerificationViewModel>()

    private val navViewModel by activityViewModels<NavigationViewModel>()

    override fun onResume() {
        super.onResume()
        reloadCountries()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        reloadCountries()
        binding.apply {
            (getString(R.string.policy_term_text)).toSpannable().apply {
                val policyTxt = "Privacy Policy"
                val termsTxt = "Terms of Use"
                policyTv.setClickableText(
                    indexOf(termsTxt),
                    indexOf(termsTxt) + termsTxt.length,
                    context?.getAttr(androidx.appcompat.R.attr.colorPrimary)
                ) {
                    Toast.makeText(context, "term", Toast.LENGTH_SHORT).show()
                }

                policyTv.setClickableText(
                    indexOf(policyTxt),
                    indexOf(policyTxt) + policyTxt.length,
                    context?.getAttr(androidx.appcompat.R.attr.colorPrimary)
                ) {
                    Toast.makeText(context, "policy", Toast.LENGTH_SHORT).show()
                }
            }

            btnContinue.setOnClickListener {
                navViewModel.navigate(R.id.frag_bio_data)
            }
            performOperationOnActivityAvailable {
//                addMenu()
//                setAppBarData(viewModel.profileLiveData.value)
            }


            Timber.d("onCreateView>> toggleRewardBanner")
        }
    }

    private fun reloadCountries() {
        viewModel.getCountries()
        viewModel.countryLiveData.observe(viewLifecycleOwner) {
            binding.layoutSpinner.apply {
                items = it.filter {
                    return@filter listOf<String>(
                        "Nigeria",
                        "Ghana",
                        "Kenya",
                        "Uganda",
                        "Zimbabwe",
                        "South Africa"
                    ).contains(it.name)
                }
            }
        }
    }


}