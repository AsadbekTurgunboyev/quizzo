package com.example.quizzo.ui.home.play.usecases

import android.animation.*
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import com.example.quizzo.R

class AnimationManager {
    fun animateButton(view: View, onEnd: () -> Unit) {
        val bounce = AnimatorSet()

        val animX = ObjectAnimator.ofFloat(view, "scaleX", 1.05f, 0.95f, 1.0f)
        animX.duration = 500

        val animY = ObjectAnimator.ofFloat(view, "scaleY", 1.05f, 0.95f, 1.0f)
        animY.duration = 500

        val color = ValueAnimator.ofArgb(Color.parseColor("#FFFFFF"), view.context.getColor(R.color.primary))
        color.duration = 300
        color.addUpdateListener { animator ->
            view.backgroundTintList = ColorStateList.valueOf(animator.animatedValue as Int)
        }

        bounce.playTogether(animX, animY, color)
        bounce.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                view.backgroundTintList = ColorStateList.valueOf(Color.WHITE)

                onEnd()
            }
        })
        bounce.start()
    }
}
