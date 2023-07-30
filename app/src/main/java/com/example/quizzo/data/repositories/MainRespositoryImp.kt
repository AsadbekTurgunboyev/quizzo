package com.example.quizzo.data.repositories

import com.example.quizzo.data.models.categories.CategoriesResponse
import com.example.quizzo.data.models.questions.QuestionResponse
import com.example.quizzo.domain.repository.MainRepository
import com.example.quizzo.domain.source.ApiService
import io.reactivex.Observable

class MainRepositoryImp(private val apiService: ApiService): MainRepository {
    override fun getCategories(): Observable<List<CategoriesResponse>> {
        return apiService.getCategories()
    }

    override suspend fun getQuestions(id: String): List<QuestionResponse> {
            return apiService.getQuestions(id = id)

    }


}