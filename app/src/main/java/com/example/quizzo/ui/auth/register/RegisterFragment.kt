package com.example.quizzo.ui.auth.register

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizzo.databinding.FragmentRegisterBinding
import com.example.quizzo.utils.DatePickerUtils

class RegisterFragment : Fragment() {

    lateinit var viewBinding: FragmentRegisterBinding

    val regions = arrayListOf<String>("Toshkent","Farg'ona","Andijon","Namangan","Buxoro","Samarqand","Jizzax","Sirdaryo","Qashqadaryo","Surxondaryo","Qoraqalpog'iston","Navoiy","Xorazm")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            regions ?: emptyList()
        )
        viewBinding.autotextView.setAdapter(adapter)

        viewBinding.buttonBack.setOnClickListener {
            val nav = findNavController()
            nav.popBackStack()
        }

        with(viewBinding.inputBirth) {
            keyListener = null
            setOnClickListener {
                DatePickerUtils.showDatePickerDialog(requireActivity()) {
                    setText(it)
                }
            }


        }

    }
}

