package com.example.quizzo.domain.source


import com.example.quizzo.data.models.MainResponse
import com.example.quizzo.data.models.categories.CategoriesResponse
import com.example.quizzo.data.models.questions.QuestionResponse
import io.reactivex.Observable
import com.example.quizzo.data.models.register.RegisterRequest
import com.example.quizzo.data.models.register.RegisterResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("accounts/register/")
    fun register(@Body registerRequest: RegisterRequest): Observable<MainResponse<RegisterResponse>>

    @GET("categories/")
    suspend fun getCategories(): List<CategoriesResponse>


    @GET("categories/{id}/questions/")
    suspend fun getQuestions(@Path("id") id: String): List<QuestionResponse>


}