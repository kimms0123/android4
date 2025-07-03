package com.example.android2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity

/*
    액티비티와 인텐트의 응용
    1. 양방향 액티비티
        - 다른 액티비티를 실행하고, 거기서 결과값을 다시 전달받는 방법
        (양방향으로 데이터를 전달하는 방법 그림)

 */
// exam21.kt : 양방향 액티비티 예제 (메인 액티비티)
class exam21 : AppCompatActivity() {

//    // onCreate() : 액티비티가 생성될 때 처음 호출되는 함수
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_exam21)  // 액티비티에 표시할 레이아웃 설정
//        title ="메인 액티비티"                      // 상단 타이틀바 제목 설정
//
//        // '새 액티비티 호출' 버튼 객체 연결 (id: btnNewActivity)
//        var btnNewActivity = findViewById<Button>(R.id.btnNewActivity)
//
//        // 버튼 클릭 이벤트 리스너 설정
//        btnNewActivity.setOnClickListener {
//            // 첫 번째 숫자 입력 필드(EditText) 객체 연결
//            var edtNum1 = findViewById<EditText>(R.id.edtNum1)
//            // 두 번째 숫자 입력 필드(EditText) 객체 연결
//            var edtNum2 = findViewById<EditText>(R.id.edtNum2)
//
//            // 새로운 인텐트 생성 (현재 액티비티 → exam21_second 액티비티로 이동)
//            var intent = Intent(applicationContext, exam21_second::class.java)
//
//            // 입력된 숫자 2개를 문자열로 받아 정수로 변환하여 인텐트에 담음
//            intent.putExtra("Num1", Integer.parseInt(edtNum1.text.toString()))
//            intent.putExtra("Num2", Integer.parseInt(edtNum2.text.toString()))
//
//            // exam21_second 액티비티 실행 (결과를 받기 위해 startActivityForResult() 사용)
//            startActivityForResult(intent, 0)  // 두 번째 매개변수 0은 요청코드 (requestCode)
//        }
//    }
//
//    // startActivityForResult로 실행한 액티비티가 종료되면 호출되는 함수
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // resultCode가 RESULT_OK인 경우 (정상적으로 값이 돌아왔을 때)
//        if (resultCode == Activity.RESULT_OK) {
//            // 되돌아온 인텐트에서 "Hap" 값 꺼내기 (합계 값)
//            var hap = data!!.getIntExtra("Hap", 0)
//            // 토스트 메시지로 합계값 화면에 출력
//            Toast.makeText(applicationContext, "합계: $hap", Toast.LENGTH_SHORT).show()
//        }
//    }

    // 직접풀어보기(10-3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam21)
        title = "계산기 앱"

        var btnNewActivity = findViewById<Button>(R.id.btnNewActivity)
        val radioGroup = findViewById<RadioGroup>(R.id.rbtnGroup)

        btnNewActivity.setOnClickListener {
            val edtNum1 = findViewById<EditText>(R.id.edtNum1)
            val edtNum2 = findViewById<EditText>(R.id.edtNum2)

            // 라디오 버튼 값 가져오기
            val rbtnHap = findViewById<RadioButton>(R.id.rbtnHap)
            val rbtnMinus = findViewById<RadioButton>(R.id.rbtnMinus)
            val rbtnMul = findViewById<RadioButton>(R.id.rbtnMul)
            val rbtnDiv = findViewById<RadioButton>(R.id.rbtnDiv)

            // 인텐트 설정
            intent = Intent(applicationContext, exam21_second::class.java)

            intent.putExtra("Num1", Integer.parseInt(edtNum1.text.toString()))
            intent.putExtra("Num2", Integer.parseInt(edtNum2.text.toString()))
            intent.putExtra("rbtnID", radioGroup.checkedRadioButtonId)

            // second 액티비티 호출
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val result = data!!.getIntExtra("Result", 0)
            Toast.makeText(applicationContext, "계산결과: $result", Toast.LENGTH_SHORT).show()
        }
    }

}
