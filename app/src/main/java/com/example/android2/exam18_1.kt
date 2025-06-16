package com.example.android2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class exam18_1 : AppCompatActivity() {
    // 간단 그림판 앱
    companion object {
        const val LINE = 1
        const val CIRCLE = 2
        var curShape = LINE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyGraphicView(this))
        title = "간단 그림판"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menu!!.add(0, 1, 0, "선 그리기")
        menu!!.add(0, 2, 0, "원 그리기")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when(item.itemId) {
            1 -> {
                curShape = LINE // 선
                return true
            }
            2 -> {
                curShape = CIRCLE // 원
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private class MyGraphicView(context: Context) : View(context) {
        var startX = -1f
        var startY = -1f
        var stopX = -1f
        var stopY = -1f

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            when(event!!.action) {
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                }
                MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                    stopX = event.x
                    startY = event.y
                    this.invalidate()
                }
            }
            return true
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val paint = Paint()
            paint.isAntiAlias = true
            paint.strokeWidth = 5f
            paint.style = Paint.Style.STROKE
            paint.color = Color.RED
            when(curShape) {
                LINE -> canvas.drawLine(startX, startY, stopX, stopY, paint)
                CIRCLE -> {
                    var radius = Math.sqrt(Math.pow( (stopX - startX).toDouble(), 2.0) + Math.pow((stopY - startY).toDouble(), 2.0))
                    canvas.drawCircle(startX, startY, radius.toFloat(), paint)
                }
            }
        }
    }
}