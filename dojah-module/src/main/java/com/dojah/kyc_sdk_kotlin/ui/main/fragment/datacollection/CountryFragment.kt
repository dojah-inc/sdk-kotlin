package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection

import com.dojah.kyc_sdk_kotlin.DojahSdk

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.text.toSpannable
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.databinding.FragmentCountryBinding
import com.dojah.kyc_sdk_kotlin.domain.Country
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.NavArguments
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.utils.*
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.getAttr
import com.dojah.kyc_sdk_kotlin.ui.utils.performOperationOnActivityAvailable
import com.dojah.kyc_sdk_kotlin.ui.utils.setClickableText
import com.google.gson.annotations.SerializedName

import okhttp3.logging.HttpLoggingInterceptor
import kotlin.sequences.ifEmpty


@SuppressLint("UnsafeRepeatOnLifecycleDetector")

class CountryFragment : ErrorFragment(R.layout.fragment_country) {
    private val binding by viewBinding { FragmentCountryBinding.bind(it) }

    private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }

    private val navViewModel by activityViewModels<NavigationViewModel> { DojahSdk.dojahContainer.navViewModelFactory }
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
                if (response is Result.Success) {
                    if (viewModel.selectedCountryLiveData.value?.id?.lowercase() == "ng") {
                        navViewModel.navigateNextStep()
                    } else {
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
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun reloadCountries() {
        val serverCountries = viewModel.getCountriesFullFromPrefs(requireContext())
        val userCountry = viewModel.getUserCountryFromPrefs()

        val configs = viewModel.getStepWithPageName(KycPages.GOVERNMENT_DATA.serverKey)?.config

        // Extract available country codes from config
        val availableCountryCodes = configs?.let { config ->
            config::class.java.declaredFields
                .filter { it.type == Boolean::class.javaObjectType }
                .mapNotNull { field ->
                    field.isAccessible = true
                    if ((field.get(config) as? Boolean) == true) {
                        field.getAnnotation(SerializedName::class.java)?.value
                    } else null
                }
                .mapNotNull { key ->
                    val match = Regex("^([a-z]{2})-").find(key)
                    match?.groupValues?.get(1)?.lowercase()
                        ?: if (!key.contains("-")) "ng" else null
                }
                .toSet()
        } ?: emptySet()

        viewModel.countryLiveData.observe(viewLifecycleOwner) {
            binding.layoutSpinner.apply {
                onCountrySelected = { country ->
                    viewModel.setSelectedCountry(country)
                }

                val tmpItems = it.filter { country ->
                    country.name.equals(userCountry, ignoreCase = true).also { isUserCountry ->
                        country.selected = isUserCountry
                    }
                    val countryInServer = serverCountries?.contains(country.name) == true
                    val countryInConfig = availableCountryCodes.isEmpty() ||
                            availableCountryCodes.contains(country.id.lowercase())
                    countryInServer && countryInConfig
                }

                items = tmpItems.ifEmpty { it }.apply {
                    if (size == 1) {
                        first().selected = true
                        selectedCountry = first()
                        viewModel.setSelectedCountry(first())
                    }
                }
            }
        }
        viewModel.loadCountries()

    }


}