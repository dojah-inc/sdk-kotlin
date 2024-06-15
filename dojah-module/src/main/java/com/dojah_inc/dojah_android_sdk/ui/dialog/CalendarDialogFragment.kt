package com.dojah_inc.dojah_android_sdk.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.lifecycle.viewModelScope
import com.dojah_inc.dojah_android_sdk.R
import com.dojah_inc.dojah_android_sdk.data.io.SharedPreferenceManager
import com.dojah_inc.dojah_android_sdk.databinding.CalendarDayLayoutBinding
import com.dojah_inc.dojah_android_sdk.databinding.DojahCalenderFragmentBinding
import com.dojah_inc.dojah_android_sdk.ui.base.SpinnerDialogFragment
import com.dojah_inc.dojah_android_sdk.ui.main.viewmodel.VerificationViewModel
import com.dojah_inc.dojah_android_sdk.ui.utils.delegates.viewBinding
import com.google.android.material.shape.MaterialShapeDrawable
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.MonthScrollListener
import com.kizitonwose.calendar.view.ViewContainer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class CalendarDialogFragment : SpinnerDialogFragment(R.layout.dojah_calender_fragment) {
    companion object {
        private var mViewModel: VerificationViewModel? = null

        fun getInstance(viewModel: VerificationViewModel): CalendarDialogFragment {
            mViewModel = viewModel
            return CalendarDialogFragment()
        }
    }

    private val binding by viewBinding { DojahCalenderFragmentBinding.bind(it) }

    var onAllow: ((date: String) -> Unit)? = null

    var onExitClick: (() -> Unit)? = null

    private var selectedMonth: Month? = null
    private var selectedYear: Int? = null
    private var selectedDate: LocalDate? = null
    private var lengthOfSelectedMonth: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dojah_calender_fragment, container, false)
    }

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {

        val builder = AlertDialog.Builder(context)
        val bindedView = DojahCalenderFragmentBinding.bind(
            LayoutInflater.from(requireContext())
                .inflate(R.layout.dojah_calender_fragment, null, false)
        ).apply {
            MaterialShapeDrawable().apply {
//                setCornerSize(180F)
                root.background = this
            }
            root.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.card_back_color
                )
            )

            val daysOfWeek = daysOfWeek()
            calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
                // Called only when a new container is needed.
                override fun create(view: View) = DayViewContainer(view)

                // Called every time we need to reuse a container.
                override fun bind(container: DayViewContainer, data: CalendarDay) {
                    val day = data.date.dayOfMonth.toString()
                    container.textView.text = day
                    container.textView.alpha =
                        if (data.position == DayPosition.MonthDate) 1f else 0.3f
                    if (data.date == selectedDate) {
                        MaterialShapeDrawable().apply {
                            setCornerSize(300F)

                            SharedPreferenceManager(requireContext()).getMaterialButtonBgColor.let {
                                if (it != null) {
                                    setTint(Color.parseColor(it))
                                    alpha = 230
                                } else {
                                    setTint(
                                        ContextCompat.getColor(
                                            requireContext(), R.color.brand_dojah
                                        )
                                    )
                                }
                            }

                            container.textView.background = this
                        }
                        container.textView.setTextColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.white
                            )
                        )
                    } else {
                        container.textView.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.transparent
                            )
                        )
                        container.textView.setTextColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.black
                            )
                        )
                    }

                    container.textView.setOnClickListener {
                        if (data.position != DayPosition.MonthDate) {
                            return@setOnClickListener
                        }
                        calDateSpin.calDateTxt.setText(day)
                        val currentSelection = selectedDate
                        if (currentSelection != data.date) {
                            selectedDate = data.date
                            // Reload the newly selected date so the dayBinder is
                            // called and we can ADD the selection background.
                            calendarView.notifyDateChanged(data.date)
                            if (currentSelection != null) {
                                // We need to also reload the previously selected
                                // date so we can REMOVE the selection background.
                                calendarView.notifyDateChanged(currentSelection)
                            }
                        }

                        val date =
                            "${selectedDate?.dayOfMonth}-${selectedMonth?.value}-${selectedYear}"
                        updateCalenderLabel()
                        mViewModel?.viewModelScope?.launch {
                            delay(1000)
                            onAllow?.invoke(date)
                            dismiss()
                        }
                    }
                }
            }
            calendarView.monthScrollListener = object : MonthScrollListener {
                override fun invoke(data: CalendarMonth) {
                    val month =
                        data.yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
                    val year = data.yearMonth.year
                    calMonthSpin.spinnerCalTxt.text = month
                    calYearSpin.spinnerCalTxt.text = year.toString()
                    lengthOfSelectedMonth = data.yearMonth.lengthOfMonth()
                }
            }
            calendarView.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                    SharedPreferenceManager(requireContext()).getMaterialButtonBgColor?.let { color ->
                        container.titlesContainer.children.forEach {
                            if (it is TextView) {
                                it.setTextColor(Color.parseColor(color))
                                it.alpha = 0.8F
                            }

                        }
                    }
                    if (container.titlesContainer.tag == null) {
                        container.titlesContainer.tag = data.yearMonth
                        val month = data.yearMonth.month.getDisplayName(
                            TextStyle.FULL, Locale.getDefault()
                        )

                        val year = data.yearMonth.year
                        calMonthSpin.spinnerCalTxt.text = month
                        calYearSpin.spinnerCalTxt.text = year.toString()
                        container.titlesContainer.children.map { it as TextView }
                            .forEachIndexed { index, textView ->
                                val dayOfWeek = daysOfWeek[index]
                                val title =
                                    dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                                        .uppercase()
                                textView.text = title
                            }
                    }
                }
            }
            val currentMonth = YearMonth.now()
            val startMonth = currentMonth.minusYears(1000)
            selectedMonth = currentMonth.month
            selectedYear = currentMonth.year
            selectedDate = LocalDate.now()
            calDateSpin.calDateTxt.setText(selectedDate?.dayOfMonth.toString())
            calendarView.setup(startMonth, currentMonth, daysOfWeek.first())
            calendarView.scrollToMonth(currentMonth)

            val months = DateFormatSymbols().months.toList()
            lengthOfSelectedMonth = currentMonth.lengthOfMonth()
            updateCalenderLabel()

            calMonthSpin.inputCalTxt.setOnClickListener {
                displaySpinnerDropdown(
                    it, months, false
                ) { index ->
                    calMonthSpin.spinnerCalTxt.setText(months[index])
                    selectedMonth = Month.of(index + 1)
                    if (selectedYear == null) {
                        return@displaySpinnerDropdown
                    }
                    calendarView.scrollToMonth(YearMonth.of(selectedYear!!, selectedMonth))
                    updateCalenderLabel()
                }
            }

            calMonthSpin.spinnerCalTxt.apply {
                ellipsize = TextUtils.TruncateAt.MARQUEE
                isSelected = true
                isSingleLine = true
                marqueeRepeatLimit = -1
            }
            calYearSpin.spinnerCalTxt.apply {
                ellipsize = TextUtils.TruncateAt.MARQUEE
                isSelected = true
                isSingleLine = true
                marqueeRepeatLimit = -1
            }
            calYearSpin.inputCalTxt.setOnClickListener {
                val years = (startMonth.year..currentMonth.year).map { year ->
                    year.toString()
                }.reversed().toList()
                displaySpinnerDropdown(
                    it, years, false
                ) { index ->
                    calYearSpin.spinnerCalTxt.setText(years[index])
                    selectedYear = years[index].toInt()
//                    if(selectedMonth == null){
//                        return@displaySpinnerDropdown
//                    }
                    calendarView.scrollToMonth(YearMonth.of(selectedYear!!, selectedMonth))
                    updateCalenderLabel()
                }
            }

        }
//        builder.setTitle("Title")
        builder.setView(bindedView.root)

        return builder.create()
    }

    private fun DojahCalenderFragmentBinding.updateCalenderLabel() {
        val dayOfMonth = selectedDate?.dayOfMonth
        val dateLabel =
            "${
                selectedMonth?.getDisplayName(
                    TextStyle.FULL,
                    Locale.getDefault()
                )
            }${if (dayOfMonth == null) "," else " $dayOfMonth,"} $selectedYear"
        selectedDateLabel.text = dateLabel.uppercase()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {

        }
    }

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
    }

    inner class MonthViewContainer(view: View) : ViewContainer(view) {
        // Alternatively, you can add an ID to the container layout and use findViewById()
        val titlesContainer = view as ViewGroup
    }
}

