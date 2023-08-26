package com.example.quizzo.ui.home.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizzo.R
import com.example.quizzo.databinding.FragmentProfileBinding
import com.example.quizzo.ui.home.settings.SettingsActivity

class ProfileFragment : Fragment() {


    lateinit var viewBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentProfileBinding.inflate(inflater,container,false)
        return viewBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewBinding.btnOptions.setOnClickListener {
//            val intent = Intent(requireContext(),SettingsActivity::class.java)
//            startActivity(intent)
//        }
    }


}