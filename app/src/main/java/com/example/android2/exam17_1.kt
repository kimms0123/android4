package com.example.android2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class exam17_1 : AppCompatActivity() {
    // 직접 풀어보기 9-1
    // 다음 메소드를 사용하여 kotlin을 코딩하라
    // - Paint.setStrokeCap(): 선 끝모양 설정
    //       - BUTT: 선이 끝나는 지점에서 딱 끊김(기본값)
    //       - ROUND: 끝을 반원 모양으로 둥글게 마감
    //       - SQUARE: 선이 끝나는 지점에서 정사각형처럼 조금 더 연장됨
    // - Canvas.drawOval()
    // - Paint.setColor(Color.argb())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyGraphicView(this))
    }

    private class MyGraphicView(context: Context) : View(context){
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            val paint = Paint()
            paint.isAntiAlias = true

            // 굵은 선1
            paint.color = Color.BLACK
            paint.strokeWidth = 20f
            canvas.drawLine(10f, 10f, 150f, 10f, paint)

            // 끝이 둥근 선2
            paint.color = Color.BLACK
            paint.strokeCap = Paint.Cap.ROUND // 선 끝 둥글게
            paint.strokeWidth = 20f
            canvas.drawLine(10f, 10f, 150f, 10f, paint)

            // 검은 타원(채우기)
            paint.color = Color.BLACK
            paint.style = Paint.Style.FILL
            val oval = RectF(100f, 100f, 300f, 200f)
            canvas.drawOval(oval, paint)

            // 부채꼴 모양( 채우기)
            paint.color = Color.BLACK
            paint.style = Paint.Style.FILL
            val rect = RectF(100f, 100f, 200f, 200f)
            canvas.drawArc(rect, 180f, 180f, true, paint)

        }
    }
}