package com.dojah.kyc_sdk_kotlin.ui.base

import android.view.LayoutInflater
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

open class RadioSpinnerFragment : ErrorFragment {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Pair<String, Boolean>>() {
            override fun areItemsTheSame(
                oldItem: Pair<String, Boolean>,
                newItem: Pair<String, Boolean>
            ) = oldItem.first == newItem.first

            override fun areContentsTheSame(
                oldItem: Pair<String, Boolean>,
                newItem: Pair<String, Boolean>
            ) = oldItem.first == newItem.first
        }
    }

    constructor() : super()

    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

    protected var popupWindow: PopupWindow? = null

    private var spinnerBinding: PopupSpinnerBinding? = null

    /**
     * Displays a dropdown window anchored to the provided anchor
     * @param anchor The view the dropdown should be anchored to
     * @param data The list of data to display in the dropdown, the boolean value is used to determine if the item is selected or not
     * @param hideProgress Used to determine if the progress indicator should be displayed or not
     *        true meaning it should be hidden and false the opposite
     *        it works together with the data to determine the progress indicator visibility
     * @param onItemClick The callback to be used when an item is clicked, it passes the index of the clicked item
     */
    protected fun displaySpinnerDropdown(
        anchor: View,
        data: List<Pair<String, Boolean>>?,
        hideProgress: Boolean,
        onItemClick: (position: Int, selected: Pair<String, Boolean>?) -> Unit
    ) {
        val width = anchor.measuredWidth
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        if (popupWindow == null) {
            spinnerBinding = PopupSpinnerBinding.inflate(layoutInflater, null, false).apply {
                progressIndicator.isVisible = data.isNullOrEmpty() && !hideProgress
                recyclerView.adapter = PopupAdapter().apply {
                    onClick = {
                        onItemClick(it, data?.get(it))
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

                showAsDropDown(anchor, 0, 0)
            }

        } else {
            spinnerBinding!!.apply {
                (recyclerView.adapter as PopupAdapter).apply {
                    submitList(data)

                    onClick = {
                        onItemClick(it, data?.get(it))
                        popupWindow!!.dismiss()
                    }
                }

                progressIndicator.isVisible = data.isNullOrEmpty() && !hideProgress
            }

            popupWindow!!.showAsDropDown(anchor, 0, 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        popupWindow = null
        spinnerBinding = null
    }

    inner class PopupAdapter :
        ListAdapter<Pair<String, Boolean>, PopupAdapter.ViewHolder>(DIFF_UTIL) {

        var onClick: ((Int) -> Unit)? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return LayoutInflater.from(requireContext())
                .inflate(R.layout.item_spinner, parent, false).run {
                    ViewHolder(this)
                }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = currentList[position]
            val text = item.first
            val isSelect = item.second
            holder.binding.textName.text = text
            holder.binding.radioButton.isChecked = isSelect
        }

        inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val binding = ItemSpinnerBinding.bind(view)

            init {
                binding.imageFlag.isVisible = false
                binding.radioButton.isVisible = true
                binding.radioButton.setOnClickListener { view ->
                    onClick?.invoke(absoluteAdapterPosition) }

                itemView.setOnClickListener {
                    onClick?.invoke(absoluteAdapterPosition)
                }
            }
        }
    }
}