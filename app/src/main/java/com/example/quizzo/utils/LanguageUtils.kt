package com.example.quizzo.utils

import android.app.Activity
import android.content.res.Configuration
import com.example.quizzo.data.preference.PreferenceManager
import java.util.*

object LanguageUtils {

    fun setLocale(activity: Activity, languageCode: String, preferenceManager: PreferenceManager) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        activity.baseContext.resources.updateConfiguration(
            config,
            activity.baseContext.resources.displayMetrics
        )

        // Optional: Save the selected language in shared preferences
        preferenceManager.saveLanguage(languageCode)

        // Restart the activity
        activity.recreate()
    }

    fun setDefaultLocale(activity: Activity, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(activity.resources.configuration)
        config.setLocale(locale)

        activity.baseContext.resources.updateConfiguration(
            config,
            activity.baseContext.resources.displayMetrics
        )
    }

}