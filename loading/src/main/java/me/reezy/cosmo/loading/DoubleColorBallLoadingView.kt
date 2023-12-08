package me.reezy.cosmo.loading

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class DoubleColorBallLoadingView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attributeSet, defStyleAttr) {

    private var color1 = Color.parseColor("#FE2C55")
    private var color2 = Color.parseColor("#25F4EE")
    private var paint: Paint = Paint().apply {
        isAntiAlias = true
        isDither = true
        style = Paint.Style.FILL
    }
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
    private var isRunning: Boolean = false
    private var isAnimation: Boolean = false
    private var isInitialized: Boolean = false

    private var progress: Float = 0f
    private var cycleBias: Int = 0
    private var size: Int = 0

    private var startTime: Long = -1L

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == 0) {
            start()
        } else {
            stop()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stop()
    }

    fun start() {
        init()
        isAnimation = true
        isRunning = true
        postInvalidate()
    }

    fun stop() {
        isRunning = false
        isInitialized = false
        progress = 0.0f
    }



    fun setSize(value: Int) {
        if (value > 0) {
            size = value
        }
    }

    fun setCycleBias(value: Int) {
        cycleBias = value
    }

    fun setProgress(value: Float) {
        if (!isInitialized) {
            init()
        }
        progress = value
        isRunning = false
        isAnimation = false
        postInvalidate()
    }

    override fun onMeasure(ws: Int, hs: Int) {
        super.onMeasure(ws, hs)
        val viewSize = min(MeasureSpec.getSize(ws), MeasureSpec.getSize(hs))
        if (viewSize in 1 until size) {
            setSize(viewSize)
        }
    }

    private fun init() {
        startTime = -1L
        if (size <= 0) {
            setSize((context.resources.displayMetrics.density * 36).toInt())
        }
        val viewSize = min(measuredHeight, measuredWidth)
        if (viewSize in 1 until size) {
            setSize(viewSize)
        }
        isInitialized = true
    }

    override fun onDraw(canvas: Canvas) {
        if (isInitialized && (!isAnimation || isRunning)) {
            var colorFlag = false
            if (isAnimation) {
                val ms = System.nanoTime() / 1000000L
                if (startTime < 0L) {
                    startTime = ms
                }
                progress = (ms - startTime)  / 400.0f
                val cycle = progress.toInt()
                progress -= cycle
                colorFlag = cycle + cycleBias and 1 == 1
            }
            val saveCount: Int = canvas.saveLayer(0.0f, 0.0f, size.toFloat(), size.toFloat(), paint)

            val cy = size / 2f
            val radius = 0.16f * size
            val start = 0.32f * size
            val distance = size - 2 * start
            val np = transform(progress)
            val scale = if (np < 0.5) np * 2.0f else 2.0f * (1 - np)

            val x1 = start + np * distance
            val r1 = radius * (1 + scale * 0.25f)
            paint.color = if (colorFlag) color2 else color1
            canvas.drawCircle(x1, cy, r1, paint)

            val x2 = size - x1
            val r2 = radius * (1 - scale * 0.375f)
            paint.color = if (colorFlag) color1 else color2
            paint.xfermode = xfermode
            canvas.drawCircle(x2, cy, r2, paint)
            paint.xfermode = null

            canvas.restoreToCount(saveCount)
            postInvalidateDelayed(17L)
        }
    }

    private fun transform(value: Float): Float {
        return if (value < 0.5) 2.0f * value * value else (2.0f * value * (2.0f - value) - 1f)
    }
}