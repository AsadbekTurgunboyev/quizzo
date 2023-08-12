package com.example.quizzo.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class RightAngleTriangleView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val path = Path()

        // Coordinates for the triangle's vertices
        val x1 = 100f
        val y1 = 100f
        val x2 = 200f
        val y2 = 100f
        val x3 = 100f
        val y3 = 200f

        // Draw the triangle with one 90-degree corner
        path.moveTo(x1, y1) // Vertex 1
        path.lineTo(x2, y2) // Vertex 2
        path.lineTo(x3, y3) // Vertex 3 (90-degree corner)
        path.close()
        canvas.drawPath(path, paint)
    }
}
