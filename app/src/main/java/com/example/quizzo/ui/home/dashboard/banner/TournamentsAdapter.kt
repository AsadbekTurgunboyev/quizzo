package com.example.quizzo.ui.home.dashboard.banner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzo.R

class TournamentsAdapter(private val context: Context) :
    RecyclerView.Adapter<TournamentsAdapter.BannerViewHolder>() {
    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

    }

}