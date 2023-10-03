package com.example.linerotsqdownview

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.graphics.Paint
import android.graphics.Color
import android.graphics.RectF
import android.graphics.Canvas
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
val parts : Int = 4
val scGap : Float = 0.04f / parts
val strokeFactor : Float = 90f
val sizeFactor : Float = 4.9f
val rot : Float = 180f
val rectFactor : Float = 4.2f
val backColor : Int = Color.parseColor("#BDBDBD")