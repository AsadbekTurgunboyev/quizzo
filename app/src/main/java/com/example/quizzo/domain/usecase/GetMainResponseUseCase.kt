package com.example.quizzo.domain.usecase

import com.example.quizzo.data.models.categories.CategoriesResponse
import com.example.quizzo.domain.repository.MainRepository
import com.example.quizzo.domain.source.ApiService
import io.reactivex.Observable

class GetMainResponseUseCase(private val mainRepository: MainRepository) {


    fun getCategories() = mainRepository.getCategories()

    suspend fun getQuestions(id: String) = mainRepository.getQuestions(id = id)
}