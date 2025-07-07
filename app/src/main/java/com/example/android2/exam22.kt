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
        [암시적 인텐트 개념 그림]
    [전화 걸기/구글맵 사용 권한 추가]
    ```
    <uses-permission android:name="android.permission.CALL_PHONE"/>
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

        btnDial.setOnClickListener {
            var uri = Uri.parse("tel:010-1234-5678") // 전화 걸기 위해 URI 문자열을 tel:전화번호 형식으로 사용
            var intent = Intent(Intent.ACTION_DIAL, uri) // ACTION_DIAL:
            startActivity(intent)
        }

        btnWeb.setOnClickListener {
            var uri = Uri.parse("https://velog.io/") // 웹 브라우저 열기 위한 URI "http://~~" 문자열 형식
            var intent = Intent(Intent.ACTION_VIEW, uri) // ACTION_VIEW:
            startActivity(intent)
        }

        btnGoogle.setOnClickListener {
            // 구글맵 주소와 경위도 형식 사용
            var uri = Uri.parse("https://www.google.co.kr/maps/place/%EC%9D%B8%EC%B2%9C%EA%B4%91%EC%97%AD%EC%8B%9C/@35.737686,123.0633915,7z/data=!3m1!4b1!4m6!3m5!1s0x35796f2596138247:0x7d37fd902cb76142!8m2!3d37.4751578!4d126.6312666!16zL20vMGh2MWI?hl=ko&entry=ttu&g_ep=EgoyMDI1MDYzMC4wIKXMDSoASAFQAw%3D%3D")
            var intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        btnSearch.setOnClickListener {
            var intent = Intent(Intent.ACTION_WEB_SEARCH) // ACTION_WEB_SEARCH:
            // 검색을 위해 putExtra()로 넘기는데, 첫 파라미터로 SearchManager.QUERY를 사용하고
            // 두번째 파라미터에는 검색할 단어를 넣음
            intent.putExtra(SearchManager.QUERY, "안드로이드")
            startActivity(intent)
        }

        btnSms.setOnClickListener {
            var intent = Intent(Intent.ACTION_SENDTO) // ACTION_SENDTO:
            intent.putExtra("sms_body", "안녕하세요") // putExtra()로 첫 파라미터에 "sms_body"를 넣고, 두번째에 보낼 문자를 넣어 넘김
            intent.data = Uri.parse("smsto:" + Uri.encode("010-1234-5678")) // intent.data 속성에 값을 설정해야한다(이유 설명 필요)
            startActivity(intent)
        }

        btnPhoto.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // ACTION_IMAGE_CAPTURE:
            startActivity(intent)
        }
    }
}