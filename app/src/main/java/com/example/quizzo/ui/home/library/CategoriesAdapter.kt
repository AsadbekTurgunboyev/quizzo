package com.example.quizzo.ui.home.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzo.R
import com.example.quizzo.data.models.categories.CategoriesResponse

class CategoriesAdapter(private val list: List<CategoriesResponse>, private val libraryInterface: LibraryInterface): RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title: TextView = itemView.findViewById(R.id.title_cat_txt)


        fun bind( response: CategoriesResponse){
            title.text = response.name
            itemView.setOnClickListener {
                libraryInterface.clickItem()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz,parent,false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position])
    }
}