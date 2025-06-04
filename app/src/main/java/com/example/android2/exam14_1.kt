package com.example.android2

import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.IOException

class exam14_1 : AppCompatActivity() {
    // 실습 8-1 : 간단 일기장 앱 만들기
    lateinit var dp: DatePicker
    lateinit var edtDiary: EditText
    lateinit var btnWrite: Button
    lateinit var fileName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam14_1)
        title = "간단한 일기장"

        // 레이아웃 요소 연결
        dp = findViewById(R.id.datePicker1)
        edtDiary = findViewById(R.id.edtDiary)
        btnWrite = findViewById(R.id.btnWrite)

        // 현재 날짜 가져오기
        val cal = Calendar.getInstance()
        val cYear = cal.get(Calendar.YEAR)
        val cMonth = cal.get(Calendar.MONTH)
        val cDay = cal.get(Calendar.DAY_OF_MONTH)

        // 문자열 더하기 방식으로 파일 이름 생성
        // fileName = (Integer.toString(cYear) + "_" + Integer.toString(cMonth + 1) + "_" + Integer.toString(cDay) + ".txt") // 🔸 기존 코드

        // kotlin 문자열 보간을 사용해 파일 이름 생성
        fileName = "${cYear}_${cMonth + 1}_${cDay}.txt"

        // 앱이 처음 실행될 때, 오늘 날짜의 일기 파일을 먼저 읽어오기
        val str = readDiary(fileName) // 파일에서 일기 내용 읽기
        edtDiary.setText(str) // editText에 내용 표시
        btnWrite.isEnabled = true // 버튼 활성화(처음 실행 시에도 사용 가능하게)

        // datePicker가 선택된 날짜가 바뀔 때마다 실행되는 콜백 설정
        dp.init(cYear, cMonth, cDay) { view, year, monthOfYear, dayOfMonth ->
            // 문자열 더하기 방식 -> 자동 날짜별 일기 관리 가능
            // fileName = (Integer.toString(year) + "_" + Integer.toString(monthOfYear + 1) + "_" + Integer.toString(dayOfMonth) + ".txt") // 🔸 기존 코드

            // 선택된 날짜를 기반으로 파일 이름 다실 설정
            fileName = "${year}_${monthOfYear + 1}_${dayOfMonth}.txt"
            // 해당 날짜의 일기 파일 읽어서 EditText 표시
            val str = readDiary(fileName)
            edtDiary.setText(str)

            // 버튼 활성화
            btnWrite.isEnabled = true
        }

        // 저장 버튼 클릭 시 실행
        btnWrite.setOnClickListener {
            // openFileOutput: 내부 저장소에 파일 열기(쓰기 모드)
            val outFs = openFileOutput(fileName, Context.MODE_PRIVATE)
            // EditText 입력된 문자열 가져옴
            val str = edtDiary.text.toString()
            // 문자열을 바이트 배열로 변환해 파일 저장
            outFs.write(str.toByteArray())
            outFs.close()
            // 사용자에게 저장 완료 메세지 표시
            Toast.makeText(applicationContext, "$fileName 이 저장됨", Toast.LENGTH_SHORT).show()
        }
    }

    // 파일을 읽어서 문자열로 반환하는 함수
    fun readDiary(fName: String): String? {
        var diaryStr: String? = null
        try {
            // 파일 입력 스트림 열기
            val inFs = openFileInput(fName)
            // 최대 500바이트 읽기(일기 내용이 적다는 가정)
            val txt = ByteArray(500)
            inFs.read(txt)
            inFs.close()
            // 바이트 배열을 문자열로 변환
            diaryStr = txt.toString(Charsets.UTF_8).trim()
            btnWrite.text = "수정하기"
            // 파일이 없을 경우(일기가 없는 경우)
        } catch (e: IOException) {
            // 표시
            edtDiary.hint = "일기 없음"
            btnWrite.text = "새로 저장"
        }
        // 읽어온 일기 문자열 반환(없으면 null)
        return diaryStr
    }
}
