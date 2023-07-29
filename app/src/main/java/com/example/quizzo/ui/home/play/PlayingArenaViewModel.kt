package com.example.quizzo.ui.home.play

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizzo.data.models.MainResponse
import com.example.quizzo.data.models.questions.QuestionResponse
import com.example.quizzo.domain.usecase.GetMainResponseUseCase
import com.example.quizzo.utils.Resource
import com.example.quizzo.utils.ResourceState
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.qualifier._q
import retrofit2.HttpException

class PlayingArenaViewModel(private val mainResponseUseCase: GetMainResponseUseCase): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _questions = MutableLiveData<Resource<List<QuestionResponse>>>()
    val questions : LiveData<Resource<List<QuestionResponse>>> get() = _questions

    val id = -1





    fun getQuestions(id: String){
        _questions.postValue(Resource(ResourceState.LOADING))
        compositeDisposable
            .add(mainResponseUseCase.getQuestions(id = id)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {}
                .doOnTerminate {}
                .subscribe({ response ->
                    _questions.postValue(Resource(ResourceState.SUCCESS,response,null))

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
                        Log.d("savollar", "getQuestions: ${error}")

                        _questions.postValue(Resource(ResourceState.ERROR, message = error.toString()))
                    })
            )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}