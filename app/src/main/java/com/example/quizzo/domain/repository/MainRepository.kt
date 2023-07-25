package com.example.quizzo.domain.repository

import com.example.quizzo.data.models.categories.CategoriesResponse
import io.reactivex.Observable

interface MainRepository {
    fun getCategories() : Observable<List<CategoriesResponse>>

}