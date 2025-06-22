package com.example.android2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class exam20 : Activity() {

    /*
        액티비티와 인텐트의 기본
        1. 안드로이드 4대 컴포넌트
        [액티비티(Activity)]
        - 화면을 구성하는 가장 기본적인 컴포넌트

        [서비스(Service)]
        - 눈에 보이는 화면(Activity)과 상관없이 백그라운드에서 동작하는 컴포넌트
            local에서 동작하는 서비스는 세 단계를 거침
            [ 서비스 생성 → 서비스 시작 → 서비스 종료 ]

        [브로드캐스트 리시버]
        - 문자 메시지 도착, 배터리 방전, SD 카드 탈부착, 네트워크 환경변화 등이 발생하면 전체 응용 프로그램이 들을 수 있도록
        방송 신호를 보냄. 그리고 브로드캐스트 리시버는 이러한 방송메세지가 발생하면 반응함

        [콘텐트 프로바이더]
        - 응용 프로그램 사이에 데이터를 공유하기 위한 컴포넌트
            URL(Uniform Resource Identifier): 콘텐트 프로바이더의 정보를 제공하는 방법
            프로바이더에서 처리된 데이터는 일반적으로 데이터베이스 또는 파일로 저장됨

        2. 액티비티 개요
        [액티비티 추가]
        - activity_main.xml과 MainActivity.kt로 파일이 존재하는데 xml파일은 화면을 구성하는 코드로
        되어있으나 MainActivity.kt가 액티비티에 해당됨

        ```
        class MainActivity : Activity() {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
            }
            ~~~ 생략 ~~~
        }
        ```
        위와 같이 MainActivity.kt 코드는 Activity 클래스를 상속받으므로 MainActivity.kt를 액티비티 라고 부름
            (자동 완성 코드는 Activity 대신 AppCompatActivity로 되어있음. AppCompatActivity는 Activity의 하위 클래스
            이므로 어떤 것을 사용해도 관계없음)

        3. 명시적 인텐트
        [인텐트(Intent)]
        - 안드로이드 4대 컴포넌트가 서로 데이터를 주고 받기 위한 메시지 객체

        [명시적 인텐트와 데이터 전달]
        - 명시적 인텐트(explicit intent): 다른 액티비티의 이름을 명확히 지정할 때 사용하는 방법
        ```
        var intent = Intent(applicationContext, SecondActivity::class.java)
        startActivity(intent)
        ```
        → 일반적인 명식적 인텐트
        다른 액티비티 데이터를 넘기는 방법(한쪽 방향으로 데이터 전달 방법)
               [ main ]                                   [ second_Activity ]
        ⌈       intent      ⌉ (2) startActivity()      ⌈        intent         ⌉
        |   (1) putExtra()  |   →                      |    (3) getExtra()     |
        |                   |  data                    |    (4) finish()       |
        ⌊                   ⌋                          ⌊                       ⌋
        ↳ putExtra()를 이용하여 필요한 만큼 데이터를 인텐트에 넣은 다음 startActivity()로 인텐트를 다른 액티비티에 넘김
          인텐트를 받은 액티비티에서는 getStringExtra(), getIntExtra(), getBooleanExtra()등의 메소드로 넘어온 데이터에 접근 가능하다

        [레이팅바(RatingBar)]
        -
     */

    // 실습 10-1 새로운 액티비티 추가
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam20)

        var btnNewActivity = findViewById<Button>(R.id.btnNewActivity)
        btnNewActivity.setOnClickListener {
            var intent = Intent(applicationContext, exam20_second::class.java)
            startActivity(intent)
        }
    }

}