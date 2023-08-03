package com.example.quizzo.ui.home.play.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.quizzo.R
import com.example.quizzo.databinding.ActivityPlayingBinding
import com.example.quizzo.ui.home.play.viewmodel.PlayingArenaViewModel
import com.example.quizzo.utils.Const
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlayingActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPlayingBinding
    private val playingArenaViewModel: PlayingArenaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPlayingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val id = intent.getIntExtra(Const.CATEGORIES_ID, -1)

        val bundle = Bundle()
        bundle.putInt("KEY_ID", id)
//
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.aboutGameFragment, bundle)
//        val navController = findNavController()
//        navController.navigate(R.id.aboutGameFragment,bundle)
    }


    companion object {
        fun open(activity: Activity, categories_id: Int) {
            val intent = Intent(activity, PlayingActivity::class.java)
            intent.putExtra(Const.CATEGORIES_ID,categories_id)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            activity.startActivity(intent)
        }
    }
}