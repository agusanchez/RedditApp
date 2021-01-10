package com.reddit.app.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.reddit.app.R

class DividerItemDecorator(context: Context) : RecyclerView.ItemDecoration() {

    var mDivider: Drawable? = null

    init {
        mDivider = ResourcesCompat.getDrawable(context.resources,
                R.drawable.divider_item_decorator_line, context.theme)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + mDivider!!.intrinsicHeight
            mDivider!!.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider!!.draw(c)
        }
    }
}