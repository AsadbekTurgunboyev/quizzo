package com.example.quizzo.ui.home.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.quizzo.R

class RecyAdapter(): RecyclerView.Adapter<RecyAdapter.ViewHolderMy>() {

    inner class ViewHolderMy(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.carousel_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMy {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner_carousel,parent,false)
        return ViewHolderMy(view)
    }

    override fun getItemCount(): Int {
      return 4
    }

    override fun onBindViewHolder(holder: ViewHolderMy, position: Int) {

    }


}