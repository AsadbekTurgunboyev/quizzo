package com.example.quizzo.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.quizzo.R
import com.example.quizzo.data.preference.PreferenceManager
import com.example.quizzo.di.injectFeature
import com.example.quizzo.ui.home.play.viewmodel.PlayingArenaViewModel
import com.example.quizzo.utils.LanguageUtils
import devlight.io.library.ntb.NavigationTabBar
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    val playingArenaViewModel: PlayingArenaViewModel by viewModel()
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFeature()
        preferenceManager = PreferenceManager(this)
        preferenceManager.getLanguage()?.let { LanguageUtils.setDefaultLocale(this, it) }
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

        navigationTabBar.setModelIndex(0,true)
        navigationTabBar.onTabBarSelectedIndexListener = object : NavigationTabBar.OnTabBarSelectedIndexListener {
            override fun onStartTabSelected(model: NavigationTabBar.Model, index: Int) {
                // Code here
            }

            override fun onEndTabSelected(model: NavigationTabBar.Model, index: Int) {
                val navController = findNavController(R.id.fragment_container) // Replace with your NavHostFragment ID
                when (index) {
                    0 -> navController.navigate(R.id.dashboardFragment) // Replace with your fragment IDs
                    1 -> navController.navigate(R.id.libraryFragment)
                    2 -> navController.navigate(R.id.libraryFragment)
                    3 -> navController.navigate(R.id.ratingFragment)
                    4 -> navController.navigate(R.id.profileFragment)
                    // Add more cases for other indices
                    else -> {
                    navController.navigate(R.id.dashboardFragment)
                    // Handle an unexpected index value if necessary
                    }
                }
            }
        }


    }


    companion object{
        fun open(activity: Activity) {
            val intent = Intent(activity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            activity.startActivity(intent)
            activity.finish()
        }
    }
}