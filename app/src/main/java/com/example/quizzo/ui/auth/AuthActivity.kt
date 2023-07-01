package com.example.quizzo.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.quizzo.R
import com.example.quizzo.di.injectFeature

class AuthActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.authFragNavHost) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFeature()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}