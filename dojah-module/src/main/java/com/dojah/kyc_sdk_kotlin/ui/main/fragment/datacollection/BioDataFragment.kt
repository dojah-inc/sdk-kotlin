package com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection
import com.dojah.kyc_sdk_kotlin.DojahSdk

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.text.toSpannable
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.databinding.FragmentBioDataBinding
import com.dojah.kyc_sdk_kotlin.ui.base.ErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.dialog.CalendarDialogFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.delegates.viewBinding
import com.dojah.kyc_sdk_kotlin.ui.utils.getAttr
import com.dojah.kyc_sdk_kotlin.ui.utils.performOperationOnActivityAvailable
import com.dojah.kyc_sdk_kotlin.ui.utils.setClickableText
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import timber.log.Timber
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
class BioDataFragment : ErrorFragment(R.layout.fragment_bio_data) {
    private val binding by viewBinding { FragmentBioDataBinding.bind(it) }

        private val viewModel by navGraphViewModels<VerificationViewModel>(Routes.verification_route) { DojahSdk.dojahContainer.verificationViewModelFactory }

    private val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")

    private val navViewModel by activityViewModels<NavigationViewModel>{DojahSdk.dojahContainer.navViewModelFactory}

    private fun createDatePicker(time: String): MaterialDatePicker<Long> {

        val milliTime = if (time.isNotEmpty()) {
            LocalDate.from(dateFormatter.parse(time))
                .atTime(0, 0)
                .toInstant(ZoneOffset.UTC)
                .toEpochMilli()

        } else -1L

        val utcZone = ZoneId.ofOffset("UTC", ZoneOffset.UTC)
        val localTime = LocalDateTime.now().minus(10, ChronoUnit.YEARS)
        val tenYearsAgo = localTime.atZone(utcZone).toInstant().toEpochMilli()
        val two2005 = LocalDateTime.now().minus(17, ChronoUnit.YEARS).atZone(utcZone).toInstant()
            .toEpochMilli()

        val calendarConstraints = CalendarConstraints.Builder()
            .setEnd(tenYearsAgo)
            .setOpenAt(two2005)
            .build()

        return MaterialDatePicker.Builder
            .datePicker().run {
                setTitleText(R.string.select_date_of_birth)
                setCalendarConstraints(calendarConstraints)
                if (milliTime != -1L) setSelection(milliTime)
                build()
            }
            .apply {
                addOnPositiveButtonClickListener {
                    val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(it), utcZone)
                        .format(dateFormatter)
                    binding.spinnerTextDob.setText(date)
                }
            }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.submitUserLiveData.observe(this) {
            when (it) {
                is Result.Loading -> {
                    showLoading()
                    Timber.d("submitUserLiveData>> Result.Loading")
                }

                is Result.Success -> {
                    dismissLoading()
                    navViewModel.navigateNextStep()
                    Timber.d("submitUserLiveData>> Result.Success")
                }

                is Result.Error -> {
                    dismissLoading()
                    navigateToErrorPage(it)
                    Timber.d("submitUserLiveData>> Result.Error")
                }

                else -> {
                    Timber.d("submitUserLiveData>> else")
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        binding.apply {
            checkEmptyFields()
            listenToTextChanges()
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

                viewModel.sendUserData(
                    dob = spinnerTextDob.text.toString(),
                    firstName = textEdtFirstName.text.toString(),
                    lastName = textEdtLastName.text.toString(),
                    middleName = textEdtMiddleName.text.toString(),
                )
            }
            textInputDate.setEndIconOnClickListener {
                CalendarDialogFragment.getInstance(viewModel).apply {
                    onAllow = {
                        spinnerTextDob.setText(it)
                    }
                    onExitClick = {
                    }

                    show(this@BioDataFragment.childFragmentManager, null)
                }
//                createDatePicker(spinnerTextDob.text?.toString() ?: "")
//                    .show(this@BioDataFragment.childFragmentManager, null)
            }
            performOperationOnActivityAvailable {
//                addMenu()
//                setAppBarData(viewModel.profileLiveData.value)
            }


            Timber.d("onCreateView>> toggleRewardBanner")
        }
    }

    fun FragmentBioDataBinding.listenToTextChanges() {
        textEdtFirstName.addTextChangedListener {
            checkEmptyFields()
        }
        textEdtLastName.addTextChangedListener {
            checkEmptyFields()
        }
        textEdtMiddleName.addTextChangedListener {
            checkEmptyFields()
        }
        spinnerTextDob.addTextChangedListener {
            checkEmptyFields()
        }
    }

    private fun FragmentBioDataBinding.checkEmptyFields() {
        btnContinue.isEnabled = textEdtFirstName.text?.isNotEmpty() == true &&
                textEdtLastName.text?.isNotEmpty() == true &&
                textEdtMiddleName.text?.isNotEmpty() == true &&
                spinnerTextDob.text?.isNotEmpty() == true
    }


}