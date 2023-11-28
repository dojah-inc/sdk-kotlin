package com.dojah.sdk_kyc.ui.main.fragment.datacollection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.text.toSpannable
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.databinding.FragmentBioDataBinding
import com.dojah.sdk_kyc.ui.base.ErrorFragment
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.dialog.CalendarDialogFragment
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.utils.delegates.viewBinding
import com.dojah.sdk_kyc.ui.utils.getAttr
import com.dojah.sdk_kyc.ui.utils.openAppSystemSettings
import com.dojah.sdk_kyc.ui.utils.performOperationOnActivityAvailable
import com.dojah.sdk_kyc.ui.utils.setClickableText
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


@SuppressLint("UnsafeRepeatOnLifecycleDetector")
@AndroidEntryPoint
class BioDataFragment : ErrorFragment(R.layout.fragment_bio_data) {
    private val binding by viewBinding { FragmentBioDataBinding.bind(it) }

    private val viewModel by viewModels<VerificationViewModel>()

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")

    private val navViewModel by activityViewModels<NavigationViewModel>()

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
//        viewModel.countryLiveData.observe(this) {
//        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
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
                navViewModel.navigate(R.id.gov_nav_graph)
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


}