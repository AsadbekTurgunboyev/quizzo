package com.example.quizzo.utils

import kotlin.math.roundToInt

object ConversionUtils {

    fun convertFromMillToSeconds(millis: Long) : Int = (millis / 1000.0).roundToInt()
}