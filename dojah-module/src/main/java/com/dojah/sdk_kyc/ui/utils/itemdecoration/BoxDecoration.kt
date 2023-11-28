package com.dojah.sdk_kyc.ui.utils.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BoxDecoration(private val paddingTop: Int = 0, private val paddingBottom: Int = 0,
                    private val paddingStart: Int = 0, private val paddingEnd: Int = 0) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val pos = parent.getChildAdapterPosition(view)
        val count = parent.adapter?.itemCount ?: 0

        outRect.set(paddingStart, if (pos == 0) paddingTop else 0, paddingEnd, if (pos == count - 1) paddingBottom else 0)
    }
}