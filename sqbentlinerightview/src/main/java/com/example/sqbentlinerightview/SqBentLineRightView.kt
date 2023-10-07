package com.example.sqbentlinerightview

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.RectF

val colors : Array<Int> = arrayOf(
    "#1A237E",
    "#EF5350",
    "#AA00FF",
    "#C51162",
    "#00C853"
).map {
    Color.parseColor(it)
}.toTypedArray()
val parts : Int = 5
val scGap : Float = 0.04f / parts
val strokeFactor : Float = 90f
val sizeFactor : Float = 4.9f
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")
val rot : Float = 90f
val deg : Float = 45f

fun Int.inverse() = 1f / this
fun Float.maxScale(i : Int, n : Int) = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) = Math.min(n.inverse(), maxScale(i, n)) * n

fun Canvas.drawXY(x : Float, y : Float, cb : () -> Unit) {
    save()
    translate(x, y)
    cb()
    restore()
}

fun Canvas.drawSqBentLineRot(scale : Float, w : Float, h : Float, paint : Paint) {
    val dsc : (Int) -> Float = {
        scale.divideScale(it, parts)
    }
    val size : Float = Math.min(w, h) / sizeFactor
    drawXY(w / 2 + (w / 2 + size) * dsc(4), h / 2) {
        drawXY(0f, 0f) {
            rotate(rot * dsc(2))
            drawRect(RectF(-size, 0f, 0f, size * dsc(0)), paint)
        }
        drawXY(0f, 0f) {
            rotate(-deg * dsc(3))
            drawLine(0f, 0f, 0f, -size * dsc(1), paint)
        }
    }
}

fun Canvas.drawSBLRNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    drawSqBentLineRot(scale, w, h, paint)
}
