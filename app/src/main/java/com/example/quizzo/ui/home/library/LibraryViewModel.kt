package com.example.quizzo.ui.home.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzo.data.models.categories.CategoriesResponse
import com.example.quizzo.domain.usecase.GetMainResponseUseCase
import com.example.quizzo.utils.ErrorHandler
import com.example.quizzo.utils.Resource
import com.example.quizzo.utils.ResourceState
import kotlinx.coroutines.launch

class LibraryViewModel(private val mainResponseUseCase: GetMainResponseUseCase): ViewModel() {

    private val _categories = MutableLiveData<Resource<List<CategoriesResponse>>>()
    val categories : LiveData<Resource<List<CategoriesResponse>>> get() = _categories

    private val _chooseCategory = MutableLiveData<CategoriesResponse>()
    val chooseCategory : LiveData<CategoriesResponse> get() = _chooseCategory


    fun getCategories(){
        _categories.postValue(Resource(ResourceState.LOADING))
        viewModelScope.launch {
            try {
                val response = mainResponseUseCase.getCategories()
                _categories.value = Resource(ResourceState.SUCCESS, response, null)
            } catch (e: Exception) {
                val errorMessage = ErrorHandler.handle(e)
                _categories.value = Resource(ResourceState.ERROR, message = errorMessage)
            }
        }

    }

    fun chooseCategory(categoriesResponse: CategoriesResponse){
        _chooseCategory.postValue(categoriesResponse)
    }


}