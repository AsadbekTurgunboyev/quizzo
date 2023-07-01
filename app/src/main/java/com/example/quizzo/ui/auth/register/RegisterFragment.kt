package com.example.quizzo.ui.auth.register

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.quizzo.R
import com.example.quizzo.data.models.MainResponse
import com.example.quizzo.data.models.register.RegisterRequest
import com.example.quizzo.data.models.register.RegisterResponse
import com.example.quizzo.databinding.FragmentRegisterBinding
import com.example.quizzo.utils.DatePickerUtils
import com.example.quizzo.utils.Resource
import com.example.quizzo.utils.ResourceState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    lateinit var viewBinding: FragmentRegisterBinding
    lateinit var dialog: Dialog
    lateinit var registerRequest: RegisterRequest

    val registerViewModel : RegisterViewModel by viewModel()

    val regions = arrayListOf<String>(
        "Toshkent",
        "Farg'ona",
        "Andijon",
        "Namangan",
        "Buxoro",
        "Samarqand",
        "Jizzax",
        "Sirdaryo",
        "Qashqadaryo",
        "Surxondaryo",
        "Qoraqalpog'iston",
        "Navoiy",
        "Xorazm"
    )

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
        setDialog()

        registerViewModel.registerResponse.observe(viewLifecycleOwner){
            updateRegisterUi(it)
        }



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

        with(viewBinding) {
            continueButton.setOnClickListener {
                val name = nameEditText.editText?.editableText.toString()
                val phone = inputPhoneNumberEditText.editText?.editableText.toString()
                val birth = inputBirthDay.editText?.editableText.toString()
                val city = inputCity.editText?.editableText.toString()

                if (name.isEmpty()) {
                    Toast.makeText(requireContext(), "Ismingizni kiriting", Toast.LENGTH_SHORT)
                        .show()
                    nameEditText.error = "Ismingizni kiriting"
                    return@setOnClickListener
                }
                if (phone.isEmpty() or !phone.isDigitsOnly()) {
                    Toast.makeText(
                        requireContext(),
                        "Telefon raqamingizni kiriting",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                if (birth.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Tug'ilgan kuningizni kiriting",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }


                registerRequest = RegisterRequest(
                    username = name,
                    phone_number = phone,
//                    birthday = birth,
//                    city = city
                )

                viewLifecycleOwner.lifecycleScope.launch {
                    linearData.visibility = View.GONE
                    linearPassword.visibility = View.VISIBLE
                }


            }

            submitButton.setOnClickListener {
                val password1 = password1.editText?.editableText.toString()
                val password2 = password2.editText?.editableText.toString()
                registerRequest.password = password1
                registerRequest.password2 = password2

                if ((password1.isEmpty() or password2.isEmpty())
                    or (password1 != password2)
                ) {
                    Toast.makeText(requireContext(), "Parrollar noto'g'ri!", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                if ((password1.length < 8)
                    or (password2.length < 8)){
                    Toast.makeText(requireContext(), "Parollar uzunligi kamida 8 ta belgi bo'lishi zarur!", Toast.LENGTH_SHORT).show()
                    viewBinding.password1.isCounterEnabled = true
                    viewBinding.password2.isCounterEnabled = true
                }


                Log.e("tekshirish", "onViewCreated: $registerRequest", )
                registerViewModel.register(registerRequest)
            }
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

    private fun updateRegisterUi(resource: Resource<MainResponse<RegisterResponse>>?) {
        resource?.let {
            when(it.state){
                ResourceState.LOADING ->{
                    Log.e("tekshirish", "updateRegisterUi: loading", )
                }
                ResourceState.ERROR ->{
                    Log.e("tekshirish", "updateRegisterUi: ${it.message}", )
                }
                ResourceState.SUCCESS ->{
                    Log.e("tekshirih", "updateRegisterUi: ${it.data?.data?.token}", )
                }
            }
        }
    }

    private fun setDialog() {

        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_success)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

    }
}

