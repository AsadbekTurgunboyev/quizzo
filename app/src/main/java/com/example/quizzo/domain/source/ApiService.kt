package com.example.quizzo.domain.source


import com.example.quizzo.data.models.MainResponse
import com.example.quizzo.data.models.categories.CategoriesResponse
import io.reactivex.Observable
import com.example.quizzo.data.models.register.RegisterRequest
import com.example.quizzo.data.models.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("accounts/register/")
    fun register(@Body registerRequest: RegisterRequest): Observable<MainResponse<RegisterResponse>>

    @GET("categories/")
    fun getCategories(): Observable<List<CategoriesResponse>>




}