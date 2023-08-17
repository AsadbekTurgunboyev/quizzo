package com.example.quizzo.ui.home.profile.usecase

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewTreeObserver
import com.example.quizzo.databinding.FragmentRatingBinding

class AnimationHandler(private val viewBinding: FragmentRatingBinding){
    init {
    // Set initial alpha values
    viewBinding.circleImageView3.alpha = 0f
    viewBinding.circleImageView2.alpha = 0f
    viewBinding.circleImageView.alpha = 0f
}

private fun createAlphaAnimator(view: View): ObjectAnimator {
    return ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f).apply {
        duration = 1000
    }
}

private fun createHeightAnimator(view: View, heightRatio: Float): ValueAnimator {
    return ValueAnimator.ofInt(0, (view.height ).toInt()).apply {
        duration = 1000
        addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            view.layoutParams.height = animatedValue
            view.requestLayout()
        }
    }
}

fun startAnimations() {
    val animatorSet = AnimatorSet()

    viewBinding.view2.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewBinding.view2.viewTreeObserver.removeOnGlobalLayoutListener(this)
            animatorSet.playTogether(
                createAlphaAnimator(viewBinding.circleImageView3),
                createAlphaAnimator(viewBinding.circleImageView2),
                createAlphaAnimator(viewBinding.circleImageView),
                createHeightAnimator(viewBinding.view2, 0.2f),
                createHeightAnimator(viewBinding.view4, 0.13f),
                createHeightAnimator(viewBinding.view1, 0.16f)
                // Add other animations as needed
            )
            animatorSet.start()
        }
    })
}
}
