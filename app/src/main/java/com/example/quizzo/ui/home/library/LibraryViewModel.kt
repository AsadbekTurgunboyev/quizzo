package com.example.quizzo.ui.home.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizzo.data.models.MainResponse
import com.example.quizzo.data.models.categories.CategoriesResponse
import com.example.quizzo.domain.usecase.GetMainResponseUseCase
import com.example.quizzo.utils.Resource
import com.example.quizzo.utils.ResourceState
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class LibraryViewModel(val mainResponseUseCase: GetMainResponseUseCase): ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _categories = MutableLiveData<Resource<List<CategoriesResponse>>>()
    val categories : LiveData<Resource<List<CategoriesResponse>>> get() = _categories


    fun getCategories(){
        _categories.postValue(Resource(ResourceState.LOADING))
        compositeDisposable
            .add(mainResponseUseCase.getCategories()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {}
                .doOnTerminate {}
                .subscribe({ response ->

                    _categories.postValue(Resource(ResourceState.SUCCESS,response,null))


                },
                    { error ->

                        val errorMessage = if (error is HttpException) {
                            try {
                                val errorBody = error.response()?.errorBody()?.string()
                                val mainResponse = Gson().fromJson(errorBody, MainResponse::class.java)
                                mainResponse.code
                            } catch (e: Exception) {
                                "An error occurred"
                            }
                        } else {
                            "An error occurred"
                        }
                        _categories.postValue(Resource(ResourceState.ERROR, message = error.toString()))
                    })
            )
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}