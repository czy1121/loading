package me.reezy.cosmo.loading

import android.content.Context
import android.graphics.drawable.Animatable
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.Interpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView

class SimpleLoadingView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : ImageView(context, attributeSet, defStyleAttr) {


    fun start() {
        val d = drawable ?: return
        if (d is Animatable) {
            clearAnimation()
            d.start()
        } else {
            val spinner = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            spinner.repeatCount = Animation.INFINITE
            spinner.duration = 1000
            spinner.interpolator = Interpolator { (it * 12).toInt() / 12f }
            startAnimation(spinner)
        }
    }

    fun stop() {
        val d = drawable ?: return
        if (d is Animatable) {
            d.stop()
        }
        clearAnimation()
    }
}