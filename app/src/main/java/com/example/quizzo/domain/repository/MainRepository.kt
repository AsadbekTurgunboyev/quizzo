package com.example.quizzo.domain.repository

import com.example.quizzo.data.models.categories.CategoriesResponse
import com.example.quizzo.data.models.questions.QuestionResponse
import io.reactivex.Observable

interface MainRepository {

    suspend fun getCategories() : List<CategoriesResponse>

    suspend fun getQuestions(id: String): List<QuestionResponse>

}