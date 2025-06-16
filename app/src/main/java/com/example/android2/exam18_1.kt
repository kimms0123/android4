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
    // companion object : 정적 상수와 현재 선택 도형 타입 변수 선언
    companion object {
        const val LINE = 1        // 선 그리기 상수
        const val CIRCLE = 2      // 원 그리기 상수
        var curShape = LINE       // 현재 선택된 도형 (초기값: 선)
    }

    // 액티비티 생성 시 호출되는 함수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyGraphicView(this)) // 커스텀 뷰를 화면으로 설정
        title = "간단 그림판"
    }

    // 옵션 메뉴 생성 (상단 메뉴 버튼)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        // 메뉴 항목 추가: 선 그리기 / 원 그리기
        menu!!.add(0, 1, 0, "선 그리기")
        menu!!.add(0, 2, 0, "원 그리기")
        return true
    }

    // 메뉴 선택 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when(item.itemId) {
            1 -> {
                curShape = LINE   // 선 그리기 선택 시
                return true
            }
            2 -> {
                curShape = CIRCLE // 원 그리기 선택 시
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 커스텀 뷰 클래스 (그래픽 뷰)
    private class MyGraphicView(context: Context) : View(context) {
        // 터치 시작/끝 좌표 변수 (초기값: -1f)
        var startX = -1f
        var startY = -1f
        var stopX = -1f
        var stopY = -1f

        // 터치 이벤트 처리
        override fun onTouchEvent(event: MotionEvent?): Boolean {
            when(event!!.action) {
                // 손가락 터치 시작
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                }
                // 이동 중이거나 손 뗐을 때
                MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                    stopX = event.x
                    stopY = event.y
                    this.invalidate() // 화면 다시 그리기 요청
                }
            }
            return true
        }

        // 화면에 그리기 작업 수행
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            // 페인트 객체 생성 및 설정
            val paint = Paint()
            paint.isAntiAlias = true           // 부드럽게
            paint.strokeWidth = 5f             // 선 두께
            paint.style = Paint.Style.STROKE   // 선만 그리기
            paint.color = Color.RED            // 빨간색

            // 현재 선택된 도형에 따라 그리기
            when(curShape) {
                LINE ->
                    // 선 그리기 (시작점 → 끝점)
                    canvas.drawLine(startX, startY, stopX, stopY, paint)
                CIRCLE -> {
                    // 반지름 계산 (피타고라스 정리)
                    val radius = Math.sqrt(
                        Math.pow((stopX - startX).toDouble(), 2.0) +
                                Math.pow((stopY - startY).toDouble(), 2.0)
                    )
                    // 원 그리기 (중심: startX, startY / 반지름)
                    canvas.drawCircle(startX, startY, radius.toFloat(), paint)
                }
            }
        }
    }
}
