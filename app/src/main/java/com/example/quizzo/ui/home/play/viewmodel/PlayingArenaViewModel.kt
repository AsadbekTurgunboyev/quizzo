package com.example.quizzo.ui.home.play.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzo.data.models.questions.QuestionResponse
import com.example.quizzo.domain.usecase.GetMainResponseUseCase
import com.example.quizzo.utils.ErrorHandler
import com.example.quizzo.utils.Resource
import com.example.quizzo.utils.ResourceState
import kotlinx.coroutines.launch

class PlayingArenaViewModel(private val mainResponseUseCase: GetMainResponseUseCase): ViewModel() {

    private val _questions = MutableLiveData<Resource<List<QuestionResponse>>>()
    val questions : LiveData<Resource<List<QuestionResponse>>> get() = _questions

    fun getQuestions(id: String) {
        _questions.value = Resource(ResourceState.LOADING)
        viewModelScope.launch {
            try {
                val response = mainResponseUseCase.getQuestions(id = id)
                _questions.value = Resource(ResourceState.SUCCESS, response, null)
            } catch (e: Exception) {
                val errorMessage = ErrorHandler.handle(e)
                _questions.value = Resource(ResourceState.ERROR, message = errorMessage)
            }
        }
    }
}