package com.dojah.kyc_sdk_kotlin.ui.base

import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.databinding.ItemSpinnerBinding
import com.dojah.kyc_sdk_kotlin.databinding.PopupSpinnerBinding
import okhttp3.logging.HttpLoggingInterceptor

open class SpinnerFragment : ErrorFragment {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }

    constructor() : super()

    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

    protected var popupWindow: PopupWindow? = null

    private var spinnerBinding: PopupSpinnerBinding? = null
    private val errLogger = HttpLoggingInterceptor.Logger.DEFAULT

    /**
     * Displays a dropdown window anchored to the provided anchor
     * @param anchor The view the dropdown should be anchored to
     * @param data The list of data to display in the dropdown
     * @param hideProgress Used to determine if the progress indicator should be displayed or not
     *        true meaning it should be hidden and false the opposite
     *        it works together with the data to determine the progress indicator visibility
     * @param onItemClick The callback to be used when an item is clicked, it passes the index of the clicked item
     */
    protected fun displaySpinnerDropdown(
        anchor: View,
        data: List<String>?,
        hideProgress: Boolean,
        onItemClick: (Int) -> Unit
    ) {
        val width = anchor.measuredWidth
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        if (popupWindow?.isShowing == true) {
            popupWindow?.dismiss()
            return
        }
        if (popupWindow == null) {
            spinnerBinding = PopupSpinnerBinding.inflate(layoutInflater, null, false).apply {
                progressIndicator.isVisible = data.isNullOrEmpty() && !hideProgress
                recyclerView.adapter = PopupAdapter().apply {
                    onClick = {
                        onItemClick(it)
                        popupWindow?.dismiss()
                    }

                    submitList(data)
                }
            }

            popupWindow = PopupWindow(spinnerBinding!!.root, width, height).apply {
                isOutsideTouchable = true
                setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.popup_window_background
                    )
                )

                setOnDismissListener {
                    (spinnerBinding?.recyclerView?.adapter as PopupAdapter?)?.submitList(null)
                }
                contentView.post {

                    setTouchInterceptor { _: View?, motionEvent: MotionEvent ->
                        val spinnerLoc = IntArray(2)
                        anchor.getLocationOnScreen(spinnerLoc)

                        val popupLoc = IntArray(2)
                        contentView.getLocationOnScreen(popupLoc)
                        val x1 = contentView.x
                        val x2 = contentView.x + contentView.width
                        val y1 = contentView.y
                        val y2 = contentView.y + contentView.height
                        val y1WithTextField =
                            y1 + if (popupLoc[1] > spinnerLoc[1]) anchor.measuredHeight.unaryMinus() else 0
                        val y2WithTextField =
                            y2 + if (popupLoc[1] > spinnerLoc[1]) 0 else anchor.measuredHeight

                        val isWithinY = motionEvent.y in y1..y2
                        val isWithinTextFieldY: Boolean =
                            motionEvent.y in y1WithTextField..y2WithTextField
                        val isWithinX: Boolean = motionEvent.x in x1..x2

                        if (isWithinX && isWithinY) contentView.performClick()
                        else isWithinX && isWithinTextFieldY //it auto dismisses if false is returned
                    }
                }

                showAsDropDown(anchor, 0, 0, Gravity.BOTTOM)
            }

        } else {
            spinnerBinding!!.apply {
                (recyclerView.adapter as PopupAdapter).apply {
                    submitList(data)

                    onClick = {
                        onItemClick.invoke(it)
                        popupWindow!!.dismiss()
                    }
                }

                progressIndicator.isVisible = data.isNullOrEmpty() && !hideProgress
            }
            popupWindow!!.showAsDropDown(anchor, 0, 0, Gravity.BOTTOM)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        popupWindow = null
        spinnerBinding = null
    }

    inner class PopupAdapter : ListAdapter<String, PopupAdapter.ViewHolder>(DIFF_UTIL) {

        var onClick: ((Int) -> Unit)? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return LayoutInflater.from(requireContext())
                .inflate(R.layout.item_spinner, parent, false).run {
                    ViewHolder(this)
                }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val text = currentList[position]
            holder.binding.textName.text = text
        }

        inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val binding = ItemSpinnerBinding.bind(view)

            init {
                binding.imageFlag.isVisible = false

                itemView.setOnClickListener {
                    onClick?.invoke(absoluteAdapterPosition)
                }
            }
        }
    }
}