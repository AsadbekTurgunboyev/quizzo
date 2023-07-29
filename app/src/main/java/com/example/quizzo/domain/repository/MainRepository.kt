package com.example.quizzo.domain.repository

import com.example.quizzo.data.models.categories.CategoriesResponse
import com.example.quizzo.data.models.questions.QuestionResponse
import io.reactivex.Observable

interface MainRepository {

    fun getCategories() : Observable<List<CategoriesResponse>>

    fun getQuestions(id: String): Observable<List<QuestionResponse>>

}