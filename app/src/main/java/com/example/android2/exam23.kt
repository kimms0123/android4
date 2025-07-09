package com.example.android2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/*
    3. 액티비티 생명주기
        - Activity는 화면을 담당하는 컴포넌트인데, 액티비티의 상태 변화에 따라 호출되는 일련의 메서드
    [동작 흐름]
        onCreate() -> onStart() -> onResume()
        (화면 전환 또는 백그라운 이동시)
        onPause() -> onStop() -> onDestory()
    [주요 생명주기 메소드]
        [실행]
        메소드                 호출 시점                                       설명
        -----------------------------------------------------------------------------------------
        - onCreate()        액티비티가 처음 생성될 때 호출                       초기화 작업(레이아웃 설정, 변수 초기화)
        - onStart()         액티비티가 사용자에게 보여지기 직전 호출               액티비티가 화면에 표시될 준비
        - onResume()        액티비티가 화면에서 사용자와 상호작용 가능할 때 호출     실제 화면 활성화 및 입력 처리
        [종료]
        - onPause()         다른 액티비티가 시작되려 할 때 호출                   사용자와의 상호작용 중단, 리소스 해제
        - onStop()          액티비티가 화면에서 보이지 않을 때 호출                더 이상 사용자에게 보여지지 않음, 리소스 반납
        - onDestroy()       액티비티가 완전히 종료될 때 호출                      리소스 정리 및 메모리 해제
    [생명주기 호출 순서]
        onCreate()
        onStart()
        onResume()
        (홈버튼 클릭)
        onPause()
        onStop() (실행된 앱 살아있음 -> 현 진행 메모리 삭제x)
        (다시 실행)
        onRestart() // 액티비티가 중지 상태(onStop)에서 다시 시작될 때 호출되는 메소드
        onStart()
        onResume()
        (앱 종료)
        onPause()
        onStop()
        onDestroy() (리소스 해제/메모리에서 현진행 내용 삭제 -> 앱 중단)

    📌 로그캣(Logcat)
        - 안드로이드 스튜디오에서 앱 실행 중 로그 메세지를 확인할 수 있는 콘솔창
        (개발자가 디버깅이나 앱 상태 확인 시 사용)
    [메소드 종류_Log 클래스]
        - Log.v(TAG, message)       Verbose(자세한 로그, 가장 낮은 레벨)      VERBOSE
        - Log.d(TAG, message)       Debug(디버깅용)                         DEBUG
        - Log.i(TAG, message)       Info(일반 정보 출력)                     INFO
        - Log.w(TAG, message)       Warning(경고 메세지)                     WARN
        - Log.e(TAG, message)       Error(에러 메세지)                       ERROR
        ```
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            Log.d("MainActivity", "onCreate 호출됨")
        }
        ```
        - TAG : 보통 클래스 이름이나 고유 문자열 사용
        - message : 출력할 로그 메세지
 */
class exam23 : AppCompatActivity() {
    // 실습(10-3) 로그캣을 이용하여 액티비티 생명주기 확인
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam23_main)
        title= "액티비티23 테스트 예제"
        android.util.Log.i("액티비티 테스트", "onCreate()")

        var btnDial = findViewById<Button>(R.id.btnDial)
        btnDial.setOnClickListener {
            var intent = Intent(Intent.ACTION_DIAL)
            startActivity(intent)
        }

        var btnFinish = findViewById<Button>(R.id.btnFinish)
        btnFinish.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        android.util.Log.i("액티비티 테스트", "onDestroy()")
    }

    override fun onPause() {
        super.onPause()
        android.util.Log.i("액티비티 테스트","onPause()")
    }

    override fun onRestart() {
        super.onRestart()
        android.util.Log.i("액티비티 테스트","onRestart()")
    }

    override fun onStart() {
        super.onStart()
        android.util.Log.i("액티비티 테스트","onStart()")
    }

    override fun onStop() {
        super.onStop()
        android.util.Log.i("액티비티 테스트","onStop()")
    }
}