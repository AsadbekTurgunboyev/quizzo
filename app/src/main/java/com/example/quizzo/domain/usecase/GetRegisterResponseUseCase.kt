package com.example.quizzo.domain.usecase

import com.example.quizzo.data.models.register.RegisterRequest
import com.example.quizzo.domain.repository.RegisterRepository

class GetRegisterResponseUseCase(private val registerRepository: RegisterRepository) {


    fun register(request: RegisterRequest) = registerRepository.register(registerRequest = request)








}