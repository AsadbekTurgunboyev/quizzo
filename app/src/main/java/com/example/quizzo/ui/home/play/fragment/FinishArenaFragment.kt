package com.example.quizzo.ui.home.play.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import com.example.quizzo.R
import com.example.quizzo.databinding.FragmentFinishArenaBinding

class FinishArenaFragment : Fragment() {


    lateinit var viewBinding: FragmentFinishArenaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        viewBinding = FragmentFinishArenaBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animator = ValueAnimator.ofInt(0, 80).apply {
            duration = 2000
            interpolator = LinearInterpolator()
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Int
                viewBinding.progress.progress = progress.toFloat()
            }
            start()
        }
    }

}