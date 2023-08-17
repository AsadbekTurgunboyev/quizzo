package com.example.quizzo.ui.home.rating

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.quizzo.R
import com.example.quizzo.databinding.FragmentRatingBinding
import com.example.quizzo.ui.home.profile.usecase.AnimationHandler


class RatingFragment : Fragment() {

    lateinit var viewBinding: FragmentRatingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        viewBinding = FragmentRatingBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animationHandler = AnimationHandler(viewBinding)
        animationHandler.startAnimations()


    }
}