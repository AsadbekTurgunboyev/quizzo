package com.example.quizzo.ui.home.about_game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizzo.R
import com.example.quizzo.data.models.categories.CategoriesResponse
import com.example.quizzo.databinding.FragmentAboutGameBinding
import com.example.quizzo.ui.home.library.LibraryViewModel
import com.example.quizzo.ui.home.play.PlayingArenaViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AboutGameFragment : Fragment() {

    private val libraryViewModel : LibraryViewModel by sharedViewModel()
    private val playingArenaViewModel: PlayingArenaViewModel by sharedViewModel()
    lateinit var viewBinding: FragmentAboutGameBinding
    var categoryId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentAboutGameBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.playingButton.setOnClickListener {
            if (categoryId > 0){
                playingArenaViewModel.getQuestions(id = categoryId.toString())
                val navController = findNavController()
                navController.navigate(R.id.playingArenaFragment)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = arguments!!.getInt("KEY_ID")

        libraryViewModel.chooseCategory.observe(this){
            updateUi(it)
        }
    }

    private fun updateUi(response: CategoriesResponse?) {
        response?.let {
            viewBinding.titleCategory.text = it.name
            categoryId = it.id
        }
    }

}