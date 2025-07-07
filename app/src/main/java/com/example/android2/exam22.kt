package com.example.android2

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

/*
    액티비티와 인텐트의 응용
    2. 암시적 인텐트
        - 약속된 액션을 지정하여 안드로이드에서 제공하는 기존 응용 프로그램을 실행
        👉 안드로이드 시스템에 "이런 행동을 하고 싶다"고 알려서 해당 액션을 처리할 수 있는 앱을 찾아 실행하는 방법
        [암시적 인텐트 개념 그림]

        [대표적인 암시적 인텐트 종류]
            - Intent.ACTION_VIEW: 콘텐츠(웹페이지, 이미지, 위치등)를 적절한 앱으로 보여줌
            - .ACTION_DIAL: 전화 다이얼 화면에 번호 표시
            - .ACTION_CALL: 지정된 전화번호로 바로 전화걸기(권한 필요)
            - .ACTION_SENDTO: sms나 메일 등 특정 URI 스킴 전용 앱 실행
            - .ACTION_SEND: 텍스트, 이미지 등 데이터를 다른 앱으로 공유
            - .ACTION_WEB_SEARCH: 웹 검색 앱 실행하여 검색어로 검색
            - .ACTION_EDIT: 콘텐츠를 수정할 수 있는 앱
            - .ACTION_PICK: 특정 데이터를 선택할 수 있는 앱
            - MediaStore.ACTION_IMAGE_CAP: 카메라 앱 실행하여 사진 촬영
        [동작 원리]
            1. 액션과 데이터 타입을 인텐트로 설정
            2. 안드로이드 시스템이 해당 인텐트를 처리할 수 있는 앱 목록 확인
            3. 여러개 일 경우 선택창 표시
            4. 해당 앱으로 실행
        [❗주의]
            - 일부 액션(ACTION_CALL, 위치, 카메라)은 권한 선언 필요
            - 인텐트에 필요한 URI형식 반드시 맞춰야 함(tel:, smsto:, mailto: ···)
            - 처리 가능한 앱이 없는 경우 ActivityNotFoundException 발생 가능 -> 예외처리 필수


    [전화 걸기/구글맵(필수) 사용 권한 추가]
        ```
        <uses-permission android:name="android.permission.CALL_PHONE"/>
        <!-- ⇓ 해당 디바이스가 전화 기능(telephony hardware)을 요구하는지 여부를 명시해야 함.(안하면 위에 코드 오류남) -->
        <uses-feature android:name="android.hardware.telephony" android:required="false" />

        <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
        ```
 */
class exam22 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam22)
        title ="암시적 인텐트 예제"

        var btnDial = findViewById<Button>(R.id.btnDial)
        var btnWeb = findViewById<Button>(R.id.btnWeb)
        var btnGoogle = findViewById<Button>(R.id.btnGoolge)
        var btnSearch = findViewById<Button>(R.id.btnSearch)
        var btnSms = findViewById<Button>(R.id.btnSms)
        var btnPhoto = findViewById<Button>(R.id.btnPhoto)

        // 전화 앱 열기
        btnDial.setOnClickListener {
            var uri = Uri.parse("tel:010-1234-5678") // 전화 걸기 위해 URI 문자열을 tel:전화번호 형식으로 사용
            var intent = Intent(Intent.ACTION_DIAL, uri) // ACTION_DIAL: 전화번호를 다이얼화면에 표시하고 사용자 확인 후 통화 가능
            startActivity(intent)
        }

        // 웹 브라우저 열기
        btnWeb.setOnClickListener {
            var uri = Uri.parse("https://velog.io/") // 웹 브라우저 열기 위한 URI "http://~~" 문자열 형식
            var intent = Intent(Intent.ACTION_VIEW, uri) // ACTION_VIEW: URL, 이미지, 파일 등 콘텐츠를 적절한 앱으로 보여줌
            startActivity(intent)
        }

        // 구글맵 열기
        btnGoogle.setOnClickListener {
            // 구글맵 주소와 경위도 형식 사용
            var uri = Uri.parse("https://www.google.co.kr/maps/place/%EC%9D%B8%EC%B2%9C%EA%B4%91%EC%97%AD%EC%8B%9C/@35.737686,123.0633915,7z/data=!3m1!4b1!4m6!3m5!1s0x35796f2596138247:0x7d37fd902cb76142!8m2!3d37.4751578!4d126.6312666!16zL20vMGh2MWI?hl=ko&entry=ttu&g_ep=EgoyMDI1MDYzMC4wIKXMDSoASAFQAw%3D%3D")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        // 웹 검색 실행
        btnSearch.setOnClickListener {
            var intent = Intent(Intent.ACTION_WEB_SEARCH) // ACTION_WEB_SEARCH: 웹 검색(구글, 네이버 등) 실행하여 검색어로 검색
            // 검색을 위해 putExtra()로 넘기는데, 첫 파라미터로 SearchManager.QUERY를 사용하고
            // 두번째 파라미터에는 검색할 단어를 넣음
            intent.putExtra(SearchManager.QUERY, "안드로이드")
            startActivity(intent)
        }

        // 문자 메시지 앱 열기
        btnSms.setOnClickListener {
            var intent = Intent(Intent.ACTION_SENDTO) // ACTION_SENDTO: 문자나 메일같은 특정 URI 스킴으로만 동작하는 앱 실행
            intent.putExtra("sms_body", "안녕하세요") // putExtra()로 첫 파라미터에 "sms_body"를 넣고, 두번째에 보낼 문자를 넣어 넘김
            // intent.data 속성에 값을 설정 필수 -> 어떤 앱으로 보낼지 지정하는 역할
            intent.data = Uri.parse("smsto:" + Uri.encode("010-1234-5678"))
            startActivity(intent)
        }

        // 카메라앱 실행
        // 실행 결과 가상화면이 나옴 => Emulator에는 실제 카메라 센서가 없어서 기본제공 가상 카메라화면이 나옴
        // 진짜 웹캠 영상 테스트 -> AVD Manager → 해당 가상 디바이스 Edit → Camera 설정에서 Front / Back 카메라를 Emulated 대신 Webcam0 변경
        btnPhoto.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // ACTION_IMAGE_CAPTURE: 카메라 앱을 실행하여 사진 촬영 화면 표시
            startActivity(intent)
        }
    }
}