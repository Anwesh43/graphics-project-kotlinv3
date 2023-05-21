package com.example.bilinesmallcircleview

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Color
import android.graphics.RectF
import android.graphics.Canvas

val colors : Array<Int> = arrayOf(
    "#1A237E",
    "#EF5350",
    "#AA00FF",
    "#C51162",
    "#00C853"
).map {
    Color.parseColor(it)
}.toTypedArray()
val parts : Int = 4
val scGap : Float = 0.04f / parts
val rot : Float = 90f
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20
val strokeFactor : Float = 90f
val sizeFactor  : Float = 4.9f
val rFactor : Float = 3.2f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n

fun Canvas.drawXY(x : Float, y : Float, cb : () -> Unit) {
    save()
    translate(x, y)
    cb()
    restore()
}

fun Canvas.drawBiLineSmallCircle(scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = Math.min(w, h) / sizeFactor
    val dsc : (Int) -> Float = {
        scale.divideScale(it, parts)
    }
    val r : Float = size / rFactor
    drawXY(w / 2 + (w / 2 + size) * dsc(3), h / 2) {
        rotate(-rot * dsc(2))
        for (j in 0..1) {
            scale(1f - 2 * j, 1f)
            drawXY(size, -h / 2 + (h / 2) * dsc(0)) {
                drawLine(0f, 0f, 0f, -size, paint)
                drawArc(RectF(-r / 2, 0f, r, r / 2), -90f, 360f * dsc(1), false, paint)
            }
        }
    }
}

fun Canvas.drawBLSCNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawBiLineSmallCircle(scale, w, h, paint)
}
