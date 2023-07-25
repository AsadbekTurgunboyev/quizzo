package com.example.quizzo.ui.home.library

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quizzo.R
import com.example.quizzo.data.models.categories.CategoriesResponse
import com.example.quizzo.databinding.FragmentLibraryBinding
import com.example.quizzo.utils.Resource
import com.example.quizzo.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel

class LibraryFragment : Fragment(), LibraryInterface {

    private lateinit var viewBinding: FragmentLibraryBinding
    private val libraryViewModel : LibraryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentLibraryBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        libraryViewModel.getCategories()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        libraryViewModel.categories.observe(this){
            setCategories(it)
        }
    }

    private fun setCategories(resource: Resource<List<CategoriesResponse>>) {
        resource.let {
            when(it.state){
                ResourceState.LOADING ->{
                    Log.d("tekshirish", "setCategories: loading ")
                }
                ResourceState.ERROR ->{
                    Log.d("tekshirish", "setCategories: oshibka ${it.message}")

                }
                ResourceState.SUCCESS ->{
                    Log.d("tekshirish", "setCategories: ${it.data} ")


                    viewBinding.categoriesRecy.adapter = it.data?.let { it1 -> CategoriesAdapter(it1,this) }

                }

            }
        }
    }

    override fun clickItem() {
       val navController = findNavController()
        navController.navigate(R.id.aboutGameFragment)
    }

}