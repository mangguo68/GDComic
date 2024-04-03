package com.graduation.design.domian.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.graduation.design.domian.utils.MUtils

class Disguise : View {
    var r = 0f
    private var strokeWidth = 300f
    private var disguiseHeight = 32 * MUtils.getScreenDensity(
        context
    )

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    fun getDisguiseHeight(): Float {
        return disguiseHeight
    }

    fun setDisguiseHeight(disguiseHeight: Float) {
        this.disguiseHeight = disguiseHeight
        invalidate()
    }

    fun getStrokeWidth(): Float {
        return strokeWidth
    }

    fun setStrokeWidth(strokeWidth: Float) {
        this.strokeWidth = strokeWidth
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        r = (disguiseHeight * disguiseHeight + width / 2f * (width / 2f)) / (2 * disguiseHeight)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE
        paint.color = Color.WHITE
        paint.strokeWidth = strokeWidth
        canvas.drawCircle(width / 2f, -(r - strokeWidth / 2f - height), r, paint)
    }
}
