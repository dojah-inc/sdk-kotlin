package com.dojah_inc.dojah_android_sdk.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.text.toSpannable
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.core.Result
import com.dojah_inc.dojah_android_sdk.databinding.FragmentCountryBinding
import com.dojah_inc.dojah_android_sdk.domain.Country
import com.dojah_inc.dojah_android_sdk.ui.base.ErrorFragment
import com.dojah_inc.dojah_android_sdk.ui.base.NavigationViewModel
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.NavArguments
import com.dojah_inc.dojah_android_sdk.ui.main.fragment.Routes
import com.dojah_inc.dojah_android_sdk.ui.utils.*
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding
import com.dojah_inc.dojah_android_sdk.ui.utils.getAttr
import com.dojah_inc.dojah_android_sdk.ui.utils.performOperationOnActivityAvailable
import com.dojah_inc.dojah_android_sdk.ui.utils.setClickableText
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.logging.HttpLoggingInterceptor


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class CountryFragment : ErrorFragment(R.layout.fragment_country) {
    private val binding by viewBinding { FragmentCountryBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { defaultViewModelProviderFactory }

    private val navViewModel by activityViewModels<NavigationViewModel>()
    val logger = HttpLoggingInterceptor.Logger.DEFAULT

    private var selectedCountry: Country? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeEvents(KycPages.COUNTRY.serverKey)
        viewModel.eventLiveData.observe(this) {
            if (it?.first?.pageKey != KycPages.COUNTRY.serverKey) {
                return@observe
            }
            val response = it.second
            if (response is Result.Loading) {
                showLoading()
            } else {
                dismissLoading()
                logger.log("Country not loading")
                if (response is Result.Success) {
                    logger.log("SUCCESS Country")
                    if (viewModel.selectedCountryLiveData.value?.id?.lowercase() == "ng") {
                        navViewModel.navigateNextStep()
                    } else {
                        logger.log(" test Country ${viewModel.selectedCountryLiveData.value?.name}")
                        navViewModel.navigate(Routes.country_error_fragment, args = Bundle().apply {
                            putString(
                                NavArguments.option,
                                FailedReasons.GOV_DATA_NOT_AVAILABLE.message
                            )
                        })
                    }
                } else if (response is Result.Error) {
                    navigateToErrorPage(response)
                }

            }
        }
    }


    override fun onResume() {
        super.onResume()
        reloadCountries()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        reloadCountries()
        binding.apply {
            requireActivity().onBackPressedDispatcher.addCallback {
                if (layoutSpinner.spinnerPopUp.isShowing) {
                    layoutSpinner.spinnerPopUp.dismiss()
                } else {
                    navViewModel.popDojahBackStack()
                }
            }
            val policyTxt = "Privacy Policy"
            val termsTxt = "Terms of Use"
            policyTv.setClickableText(
                policyTv.text.indexOf(termsTxt),
                policyTv.text.indexOf(termsTxt) + termsTxt.length,
                context?.getAttr(androidx.appcompat.R.attr.colorPrimary)
            ) {
                Toast.makeText(context, "term", Toast.LENGTH_SHORT).show()
            }

            policyTv.setClickableText(
                policyTv.text.indexOf(policyTxt),
                policyTv.text.indexOf(policyTxt) + policyTxt.length,
                context?.getAttr(androidx.appcompat.R.attr.colorPrimary)
            ) {
                Toast.makeText(context, "policy", Toast.LENGTH_SHORT).show()
            }
            (getString(R.string.policy_term_text)).toSpannable().apply {

            }

            btnContinue.setOnClickListener {

                viewModel.logCountryEvents()

            }

            performOperationOnActivityAvailable {
//                addMenu()
//                setAppBarData(viewModel.profileLiveData.value)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun reloadCountries() {
        val serverCountries = viewModel.getCountriesFullFromPrefs(requireContext())
        val userCountry = viewModel.getUserCountryFromPrefs()
        viewModel.loadCountries()
        viewModel.countryLiveData.observe(viewLifecycleOwner) {
            binding.layoutSpinner.apply {
                onCountrySelected = { country ->
                    viewModel.setSelectedCountry(country)
                }

                val tmpItems = it.filter {
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
                items = tmpItems.ifEmpty { it }

            }
        }
    }


}