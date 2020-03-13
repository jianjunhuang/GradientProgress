package com.jianjun.gradientprogress

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.FloatRange

class GradientProgress : View {
    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dividePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val trianglePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private @FloatRange(from = 0.0, to = 1.0)
    var progress = 0f

    private val startColor = Color.parseColor("#0c65c0")
    private val centerColor = Color.parseColor("#7af272")
    private val endColor = Color.parseColor("#e33f3e")
    private val defaultColor = Color.parseColor("#828282")

    private var textArray = arrayOf("slower", "normal", "faster")
    private var colorArray = intArrayOf(startColor, centerColor, endColor)

    private val dashPathEffect = DashPathEffect(floatArrayOf(4f, 2f), 0f)

    private val trianglePath = Path()

    private var linearGradient: LinearGradient? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, -1)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        textPaint.textSize = sp2px(12f)
        textPaint.color = defaultColor

        dividePaint.style = Paint.Style.STROKE
        dividePaint.color = Color.parseColor("#1a000000")
        dividePaint.strokeWidth = dp2px(2f)

        progressPaint.style = Paint.Style.STROKE
        progressPaint.strokeWidth = dp2px(2f)

        val triangleWidth = dp2px(6f)
        trianglePath.moveTo(0f, 0f)
        trianglePath.lineTo(triangleWidth, 0f)
        trianglePath.lineTo(triangleWidth / 2f, triangleWidth)
        trianglePath.lineTo(0f, 0f)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        linearGradient =
            LinearGradient(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                colorArray,
                null,
                Shader.TileMode.CLAMP
            )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

    private fun dp2px(dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f)
    }

    private fun sp2px(spValue: Float): Float {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f)
    }
}