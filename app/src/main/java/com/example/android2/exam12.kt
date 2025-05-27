package com.example.android2

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/*
    Toast: 화면에 잠깐 나타났다 사라지는 메세지
    사용 방식
        Toast.makeText(Context context, String message, int duration).show()
            - Context: 현재 액티비티를 표시하기 위함 (this를 주로 사용)
            - duration: 화면에 나타나는 시간
                - Toast.LENGTH_LONG
                - Toast.LENGTH_SHORT
            - show(): 생성된 토스트를 화면에 출력
 */
class exam12 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam12)
        title = "토스트 연습"

        var button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            // 기본 토스트 메시지 생성
            var tMsg = Toast.makeText(applicationContext,"토스트 연습", Toast.LENGTH_SHORT)

            // 화면 크기 정보를 가져오기 위한 WindowManager 사용
            var display = (getSystemService(Context.WINDOW_SERVICE)as WindowManager).defaultDisplay
            // 화면 너비와 높이를 기반으로 무작위 x, y 오프셋 생성
            var xOffset = (Math.random() * display.width).toInt()
            var yOffset = (Math.random() * display.height).toInt()

            // 토스트 메세지를 화면 왼쪽 위 기준(xOffset, yOffset 위치)에 띄우도록 설정
            // setGravity: 토스트 메세지 출력 위치 변경
            // 단, 일부 기기나 Android 버전(API 30 이상)에서는 setGravity가 무시되어 기본위치에 뜸
            tMsg.setGravity(Gravity.TOP or Gravity.LEFT, xOffset, yOffset)
            // xOffset & yOffset: 떨어진 거리

            // 토스트 메세지 표시
            tMsg.show()
        }
    }
}