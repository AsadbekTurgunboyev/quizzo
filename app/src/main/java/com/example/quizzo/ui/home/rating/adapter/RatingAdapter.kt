package com.example.quizzo.ui.home.rating.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzo.R
import com.example.quizzo.data.models.rating.RatingResponse
import de.hdodenhof.circleimageview.CircleImageView

class RatingAdapter(val list: List<RatingResponse>) : RecyclerView.Adapter<RatingAdapter.MyRatingViewHolder>() {


    inner class MyRatingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val personName: TextView = itemView.findViewById(R.id.person_name_rating)
        val personImage: CircleImageView = itemView.findViewById(R.id.person_image_rating)
        private val personRatingPosition: TextView = itemView.findViewById(R.id.person_rating_position_rating)
        private val personRatingPx: TextView = itemView.findViewById(R.id.person_rating_px_rating)
        fun bind(model: RatingResponse){
            personName.text = model.userName
            personRatingPx.text = model.rating_px.toString()
            personRatingPosition.text = model.rating_position.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRatingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard,parent,false)
        return MyRatingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyRatingViewHolder, position: Int) {

        holder.bind(list[position])
    }
}