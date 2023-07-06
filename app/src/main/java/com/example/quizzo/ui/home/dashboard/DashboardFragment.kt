package com.example.quizzo.ui.home.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.quizzo.R
import com.example.quizzo.databinding.FragmentDashboardBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class DashboardFragment : Fragment() {


    var list = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//
//        val dotsIndicator = view.findViewById<DotsIndicator>(R.id.mydots_indicator)
//
//        view.findViewById<ViewPager2>(R.id.banner_viewpager).adapter = RecyAdapter()
//        dotsIndicator.attachTo(view.findViewById<ViewPager2>(R.id.banner_viewpager))
    }




}