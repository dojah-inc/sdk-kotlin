package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.text.toSpannable
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.FragmentCountryBinding
import com.dojah.sdk_kyc.domain.Country
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.utils.*
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.getAttr
import com.dojah.sdk_kyc.ui.utils.performOperationOnActivityAvailable
import com.dojah.sdk_kyc.ui.utils.setClickableText
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class CountryFragment : ErrorFragment(R.layout.fragment_country) {
    private val binding by viewBinding { FragmentCountryBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()

    private var selectedCountry: Country? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeEvents(KycPages.COUNTRY.serverKey)

//        viewModel.eventLiveData.observe(this) {
//            if (it.second is Result.Loading) {
//                showLoading("Loading...")
//            } else {
//                dismissLoading()
//                if (it.second is Result.Success) {
//                    when (it.first.eventType) {
//                        EventTypes.COUNTRY_SELECTED.serverKey -> {
//                            navViewModel.navigateNextStep()
//                        }
//                    }
//                } else {
//                    navViewModel.navigate(Routes.error_fragment)
//                }
//            }
//        }
    }


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
                val stepNumber = viewModel.getCurrentPage(KycPages.INDEX.serverKey)?.id
                    ?: throw Exception("No stepNumber")

                viewModel.logEvent(
                    EventTypes.COUNTRY_SELECTED.serverKey,
                    viewModel.selectedCountryLiveData.value?.name ?: "",
                    stepNumber = stepNumber
                )
//                navViewModel.navigateNextStep()
            }

            performOperationOnActivityAvailable {
//                addMenu()
//                setAppBarData(viewModel.profileLiveData.value)
            }


            Timber.d("onCreateView>> toggleRewardBanner")
        }
    }

    private fun reloadCountries() {
        val serverCountries = viewModel.getCountriesFullFromPrefs(requireContext())
        val userCountry = viewModel.getUserCountryFromPrefs()
        viewModel.getCountries()
        viewModel.countryLiveData.observe(viewLifecycleOwner) {
            binding.layoutSpinner.apply {
                onCountrySelected = { country ->
                    viewModel.setSelectedCountry(country)
                }

                val tmpItems = it.filter {
//                    HttpLoggingInterceptor.Logger.DEFAULT.log(" nonTest Country ${it.name} == $userCountry")
                    it.name.equals(
                        userCountry,
                        ignoreCase = true
                    ).let { isUserCountry ->
                        HttpLoggingInterceptor.Logger.DEFAULT.log(" test Country ${it.name} == $userCountry")
                        it.selected = isUserCountry
                    }
                    return@filter serverCountries
                        ?.contains(it.name) == true
                }
//                setSelectedItem(viewModel.getUserCountryFromPrefs(requireContext()))
                items = tmpItems.ifEmpty { it }
//                it.forEach {
//                    HttpLoggingInterceptor.Logger.DEFAULT.log(" nonTest Country ${it.name} == $userCountry")
//                    it.name.equals(
//                        userCountry,
//                        ignoreCase = true
//                    ).let { _ ->
//                        HttpLoggingInterceptor.Logger.DEFAULT.log(" test Country ${it.name} == $userCountry")
//                        setSelectedItem(it)
//                    }
//                }

            }
        }
    }


}