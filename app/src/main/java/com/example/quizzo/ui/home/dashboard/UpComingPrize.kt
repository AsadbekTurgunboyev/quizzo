package com.example.quizzo.ui.home.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzo.R

class UpComingPrize : RecyclerView.Adapter<UpComingPrize.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_waiting_prize, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}
