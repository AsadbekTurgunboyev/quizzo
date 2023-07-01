package com.example.quizzo.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizzo.data.models.MainResponse
import com.example.quizzo.data.models.register.RegisterRequest
import com.example.quizzo.data.models.register.RegisterResponse
import com.example.quizzo.domain.usecase.GetRegisterResponseUseCase
import com.example.quizzo.utils.Resource
import com.example.quizzo.utils.ResourceState
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(private val getRegisterResponseUseCase: GetRegisterResponseUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()


    private val _registerResponse = MutableLiveData<Resource<MainResponse<RegisterResponse>>>()
    val registerResponse: LiveData<Resource<MainResponse<RegisterResponse>>> get() =  _registerResponse



    fun register(request: RegisterRequest){
        _registerResponse.postValue(Resource(ResourceState.LOADING))
        compositeDisposable
            .add(getRegisterResponseUseCase.register(request =  request)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {}
                .doOnTerminate {}
                .subscribe({ response ->

                       _registerResponse.postValue(Resource(ResourceState.SUCCESS,response,null))


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
                       _registerResponse.postValue(Resource(ResourceState.ERROR, message = error.toString()))
                    })
            )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}