package com.example.quizzo.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class TrapeziumView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val path = Path()

        // Coordinates
        val x1 = 100f
        val y1 = 100f
        val x2 = x1 + 200f
        val y2 = y1
        val x3 = x2 + 200 * cos(Math.toRadians(60.0)).toFloat()
        val y3 = y2 + 200 * sin(Math.toRadians(60.0)).toFloat()
        val x4 = x1 + 200 * cos(Math.toRadians(30.0)).toFloat()
        val y4 = y1 + 200 * sin(Math.toRadians(30.0)).toFloat()

        // Draw the trapezium
        path.moveTo(x1, y1) // Top left point (90°)
        path.lineTo(x2, y2) // Top right point (120°)
        path.lineTo(x3, y3) // Bottom right point (60°)
        path.lineTo(x4, y4) // Bottom left point (90°)
        path.close()
        canvas.drawPath(path, paint)
    }
}

