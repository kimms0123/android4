package com.example.android2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

// exam21_second.kt : exam21에서 넘어온 두 숫자를 더해서 다시 돌려주는 액티비티
class exam21_second : AppCompatActivity() {

//    // 액티비티가 생성될 때 호출되는 함수
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // 해당 액티비티 화면으로 activity_exam21_second.xml을 표시
//        setContentView(R.layout.activity_exam21_second)
//        title = "Second 액티비티"
//
//        // exam21 액티비티에서 전달한 인텐트 가져오기
//        var inIntent = intent
//
//        // 전달받은 "Num1"과 "Num2" 값을 꺼내서 더한 값 저장
//        var hapValue = inIntent.getIntExtra("Num1", 0) + inIntent.getIntExtra("Num2", 0) // exam21에서 전달한 인텐트에서 숫자 2개를 꺼내서 더한 값 저장
//
//        // '돌아가기' 버튼 객체 연결 (id: btnReturn)
//        var btnReturn = findViewById<Button>(R.id.btnReturn)
//
//        // 버튼 클릭 이벤트 리스너 설정
//        btnReturn.setOnClickListener {
//            // exam21 액티비티로 돌아가기 위한 인텐트 생성
//            var outIntent = Intent(applicationContext, exam21::class.java)
//
//            // 인텐트에 "Hap" 이라는 이름으로 합계값 담기
//            outIntent.putExtra("Hap", hapValue)
//
//            // 결과값과 함께 액티비티 종료 준비
//            setResult(Activity.RESULT_OK, outIntent)
//
//            // 현재 액티비티 종료 (exam21으로 돌아감)
//            finish()
//        }
//    }

    // 직접 풀어보기 10-3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam21_second)
        title = "Second 액티비티"

        val inIntent = intent
        val num1 = intent.getIntExtra("Num1", 0)
        val num2 = intent.getIntExtra("Num2", 0)
        val rbtnID = inIntent.getIntExtra("rbtnID", 0)

        var result = 0

        // rbtnID 값으로 연산 분기 처리
        when (rbtnID) {
            R.id.rbtnHap -> result = (num1+num2).toInt()
            R.id.rbtnMinus -> result = (num1-num2).toInt()
            R.id.rbtnMul -> result = (num1*num2).toInt()
            R.id.rbtnDiv ->
                result = if (num2 == 0) 0 else num1.toInt()/num2.toInt()
        }
        val btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            val outIntent = Intent(applicationContext, exam21::class.java)
            outIntent.putExtra("Result", result)
            setResult(Activity.RESULT_OK, outIntent)
            finish()
        }
    }

}
