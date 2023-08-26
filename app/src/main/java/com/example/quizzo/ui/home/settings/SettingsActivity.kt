package com.example.quizzo.ui.home.settings

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzo.data.preference.PreferenceManager
import com.example.quizzo.databinding.ActivitySettingsBinding
import com.example.quizzo.utils.LanguageUtils

class SettingsActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivitySettingsBinding
    lateinit var preferenceManager: PreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        preferenceManager = PreferenceManager(this)

        val language = arrayListOf<String>("Uzbek", "Kirril")


        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, language)
        viewBinding.autoCompleteTextView.setAdapter(arrayAdapter)

        viewBinding.autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
            if(i == 0 ){
                LanguageUtils.setLocale(this,"la", preferenceManager = preferenceManager)
            }else{
                LanguageUtils.setLocale(this,"uz", preferenceManager = preferenceManager)

            }
            viewBinding.autoCompleteTextView.threshold = 1
        }
    }

}