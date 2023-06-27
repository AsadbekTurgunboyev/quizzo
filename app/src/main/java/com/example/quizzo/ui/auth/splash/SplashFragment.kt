package com.example.quizzo.ui.auth.splash

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizzo.R
import com.example.quizzo.databinding.FragmentSplashBinding
import com.google.android.material.card.MaterialCardView


class SplashFragment : Fragment() {

    lateinit var viewBinding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val login: MaterialCardView = view.findViewById(R.id.materialCardView)
        val people: ImageView = view.findViewById(R.id.imageView5)
        val handler = Handler()
        val delay = 3000L // Delay in milliseconds (1 second)


        viewBinding.registerButton.setOnClickListener {
            val navigation = findNavController()
            navigation.navigate(R.id.registerFragment)
        }
        handler.postDelayed({
            // Create an ObjectAnimator to animate the alpha property
            val animator = ObjectAnimator.ofFloat(login, "alpha", 0f, 1f)

            // Set the duration of the animation
            animator.duration = 1500 // Animation duration in milliseconds


// Alpha Animation
            val alphaAnimator = ObjectAnimator.ofFloat(people, "alpha", 0f, 1f)
            alphaAnimator.duration = 1000
            alphaAnimator.start()
// Translation Animation
            val translationAnimator = TranslateAnimation(-people.width.toFloat(), 0f, 0f, 0f)
            translationAnimator.duration = 2000

// Create an AnimationSet to combine the alpha and translation animations
            val animationSet = AnimationSet(true)

            animationSet.addAnimation(translationAnimator)







            people.startAnimation(animationSet)
            view.findViewById<LinearLayout>(R.id.imageView2).animate().translationYBy(-600f)
                .setListener(
                    object : Animator.AnimatorListener {
                        override fun onAnimationStart(p0: Animator) {
//                            val logo: ImageView = view.findViewById<ImageView>(R.id.imageView2)
//                            val newWidth = logo.width / 2// New width in pixels
//                            val newHeight = logo.height / 2  // New height in pixels
//
//                            logo.layoutParams.width = newWidth
//                            logo.layoutParams.height = newHeight
//                            logo.requestLayout()

                        }

                        override fun onAnimationEnd(p0: Animator) {

                        }

                        override fun onAnimationCancel(p0: Animator) {

                        }

                        override fun onAnimationRepeat(p0: Animator) {

                        }

                    }).duration = 1400


            // Start the animation
            animator.start()
        }, delay)


    }

    fun scaleView(v: View, startScale: Float, endScale: Float) {
        val anim: Animation = ScaleAnimation(
            1f, 1f,  // Start and end values for the X axis scaling
            startScale, endScale,  // Start and end values for the Y axis scaling
            Animation.RELATIVE_TO_SELF, 0f,  // Pivot point of X scaling
            Animation.RELATIVE_TO_SELF, 1f
        ) // Pivot point of Y scaling
        anim.fillAfter = true // Needed to keep the result of the animation
        anim.duration = 1000
        v.startAnimation(anim)
    }

}


