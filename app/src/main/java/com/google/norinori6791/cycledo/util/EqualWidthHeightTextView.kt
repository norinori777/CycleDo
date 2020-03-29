package com.google.norinori6791.cycledo.util

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class EqualWidthHeightTextView : AppCompatTextView {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val r = Math.max(measuredWidth, measuredHeight)
        setMeasuredDimension(r, r)
    }
}