package com.example.quizzo.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzo.R
import com.example.quizzo.di.injectFeature
import devlight.io.library.ntb.NavigationTabBar


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFeature()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        if (supportActionBar != null) {
            supportActionBar!!.hide();
        }
        val colors = arrayListOf("#E57373","#FF8A65","#64B5F6","#F06292","#81C784")


        val navigationTabBar = findViewById<View>(R.id.bottomBar) as NavigationTabBar
        val models: ArrayList<NavigationTabBar.Model> = ArrayList()
        models.add(
            NavigationTabBar.Model.Builder(
                resources.getDrawable(R.drawable.ic_home),
                Color.parseColor(colors[0])
            ).title("Heart")
                .badgeTitle("NTB")
                .build()
        )
        models.add(
            NavigationTabBar.Model.Builder(
                resources.getDrawable(R.drawable.ic_category),
                Color.parseColor(colors[1])
            ).title("Cup")
                .badgeTitle("with")
                .build()
        )
        models.add(
            NavigationTabBar.Model.Builder(
                resources.getDrawable(R.drawable.ic_logo),
                Color.parseColor(colors[2])
            ).title("Diploma")
                .badgeTitle("state")
                .build()
        )
        models.add(
            NavigationTabBar.Model.Builder(
                resources.getDrawable(R.drawable.ic_cup_menu),
                Color.parseColor(colors[3])
            ).title("Flag")
                .badgeTitle("icon")
                .build()
        )
        models.add(
            NavigationTabBar.Model.Builder(
                resources.getDrawable(R.drawable.ic_profile),
                Color.parseColor(colors[4])
            ).title("Medal")
                .badgeTitle("777")
                .build()
        )
        navigationTabBar.models = models

    }
}