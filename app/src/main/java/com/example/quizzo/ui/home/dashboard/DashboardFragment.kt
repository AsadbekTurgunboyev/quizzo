package com.example.quizzo.ui.home.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.quizzo.R
import com.example.quizzo.databinding.FragmentDashboardBinding
import com.example.quizzo.ui.home.dashboard.banner.TournamentsAdapter

class DashboardFragment : Fragment() {


    var list = mutableListOf<Int>()
    lateinit var viewBinding: FragmentDashboardBinding
    private lateinit var dots: Array<View>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = view.findViewById(R.id.banner_viewpager)
        viewPager.adapter = TournamentsAdapter(requireContext())

        addDotsIndicator(5)

        viewBinding.upcomingRecy.adapter = UpComingPrize()
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Update the dots indicator when a new page is selected
                updateDotsIndicator(position)
            }
        })
    }

    private fun addDotsIndicator(numDots: Int) {
        val dotSize = resources.getDimensionPixelSize(R.dimen.dot_size)
        val dotMargin = resources.getDimensionPixelSize(R.dimen.dot_margin)

        dots = Array(numDots) { i ->
            val dotView = LayoutInflater.from(context).inflate(R.layout.layout_dots, viewBinding.dotsLayout, false)
            val layoutParams = LinearLayout.LayoutParams(dotSize, dotSize)
            layoutParams.setMargins(dotMargin, 0, dotMargin, 0)
            dotView.layoutParams = layoutParams
            viewBinding.dotsLayout.addView(dotView)
            dotView
        }
        // Set the initial selected dot
        updateDotsIndicator(0)
    }

    private fun updateDotsIndicator(selectedPosition: Int) {
        for (i in dots.indices) {
            val dot = dots[i]
            if (i == selectedPosition) {
                dot.setBackgroundResource(R.drawable.dot_selected)
            } else {
                dot.setBackgroundResource(R.drawable.dot_unselected)
            }
        }
    }
}