package com.example.quizzo.ui.home.library

import com.example.quizzo.data.models.categories.CategoriesResponse

interface LibraryInterface {

    fun clickItem(categoriesResponse: CategoriesResponse)
}