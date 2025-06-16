package com.example.android2

import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity

// 터치 이벤트

class exam18 : AppCompatActivity() {
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                // 손가락으로 화면을 누르기 시작했을 때 할 일
            }
            MotionEvent.ACTION_MOVE -> {
                // 터치 후 손가락을 움직일 때 할 일
            }
            MotionEvent.ACTION_UP -> {
                // 손가락을 화면에서 뗄 때 할 일
            }

            MotionEvent.ACTION_UP -> {
                // 터치가 취소될 때 할 일
            }
        }
        return true
    }
}