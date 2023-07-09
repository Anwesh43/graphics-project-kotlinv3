package com.example.linesqcatchdropview

import android.view.View
import android.app.Activity
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Color
import android.content.Context

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
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")
val rot : Float = 90f
val sizeFactor : Float = 4.9f
val strokeFactor : Float = 90f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n

fun Canvas.drawXY(x : Float, y : Float, cb : () -> Unit) {
    save()
    translate(x, y)
    cb()
    restore()
}

fun Canvas.drawLineSqCatchDrop(scale : Float, w : Float, h : Float, paint : Paint) {
    val dsc : (Int) -> Float = {
        scale.divideScale(it, parts)
    }
    val size : Float = Math.min(w, h) / sizeFactor
    val sqSize : Float = size / 4
    drawXY(w / 2, h / 2) {
        drawXY(sqSize / 2 - size / 2 + (size / 2) * dsc(2), -h / 2 + (h / 2) * dsc(1)) {
            rotate(rot * dsc(3))
            drawRect(RectF(-sqSize, -sqSize, 0f, 0f), paint)
        }
        drawXY(-w * 0.5f * dsc(4), 0f) {
            drawLine(0f, 0f, -size * dsc(0), 0f, paint)
        }
    }
}

fun Canvas.drawLSCDNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawLineSqCatchDrop(scale, w, h, paint)
}

