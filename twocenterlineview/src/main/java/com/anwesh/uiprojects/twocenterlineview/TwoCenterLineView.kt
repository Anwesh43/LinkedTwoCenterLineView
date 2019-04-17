package com.anwesh.uiprojects.twocenterlineview

/**
 * Created by anweshmishra on 17/04/19.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.content.Context
import android.app.Activity

val nodes : Int = 5
val second_part_size : Int = 2
val first_part_size : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#4CAF50")
val backColor : Int = Color.parseColor("#BDBDBD")
val rFactor : Float = 2.9f
val angleDeg : Float = 180f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + scaleFactor() * b.inverse()
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawTCL(i : Int, size : Float, w : Float, sc1 : Float, sc2 : Float, paint : Paint) {
    val r : Float = size / rFactor
    val sc : Float = sc2.divideScale(i, second_part_size)
    save()
    rotate(-angleDeg * i * sc1.divideScale(1, first_part_size))
    translate(size + (w + r) * sc, 0f)
    drawCircle(size, 0f, r, paint)
    drawLine( -size * sc1.divideScale(0, first_part_size), 0f, -size * sc, 0f, paint)
    restore()
}

fun Canvas.drawTCLNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    save()
    translate(w / 2, gap * (i + 1))
    for (j in 0..(second_part_size - 1)) {
        drawTCL(j, size, scale.divideScale(0, second_part_size), w / 2, scale.divideScale(1, second_part_size), paint)
    }
    restore()
}
