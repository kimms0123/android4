package com.example.android2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileInputStream

/*
    파일 처리 응용
        - SD 카드로 사용

        ** 문제 **
            안드로이드 6.0(API 23)이후부터 런타임 권한 처리를 안 하면
            Caused by: java.io.FileNotFoundException: /storage/emulated/0/sd_test.txt: open failed: EACCES (Permission denied)
            에러가 발생한다. 위 문제는 차차 해결중

            이 부분은 나중에 api와 안드로이드 버전을 변경해서 다시 진행할 에정
 */
class exam16 : AppCompatActivity() {
    /*
    에러 코드

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam16)

        val btnRead: Button = findViewById(R.id.btnRead)
        val edtSD: EditText = findViewById(R.id.edtSD)

        btnRead.setOnClickListener {
            var inFs = FileInputStream("/storage/emulated/0/sd_test.txt")
            var txt = ByteArray(inFs.available())
            inFs.read(txt)
            edtSD.setText(txt.toString(Charsets.UTF_8))
            inFs.close()
        }
    }
     */

    /*
        두 코드의 차이점
        위 코드
        파일 경로: 하드코딩된 절대 경로
        파일 존재 여부: 체크 x
        예외 처리: 없음(예외발생 시 앱 크래시)
        ------------------------------------------
        아래 코드
        파일 경로: 앱 전용 외부 저장소 경로(동적 획득)
        파일 존재 여부: 체크하지 않음
        예외 처리: 있음(에러 발생 시 토스트 메세지 표시
     */

    // 이 코드 사용시 에러 없이 사용 가능
    // 단 getExternalFileDir의 경로가 /storage/emulated/0/Android/data/com.example.프로젝트명/files/ 안에 들어가야한다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam16)

        val btnRead: Button = findViewById(R.id.btnRead)
        val edtSD: EditText = findViewById(R.id.edtSD)

        btnRead.setOnClickListener {
            try {
                val file = File(getExternalFilesDir(null), "sd_test.txt")
                if (file.exists()) {
                    val inFs = FileInputStream(file)
                    val txt = ByteArray(inFs.available())
                    inFs.read(txt)
                    edtSD.setText(txt.toString(Charsets.UTF_8))
                    inFs.close()
                } else {
                    Toast.makeText(this, "파일이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "파일 읽기 실패: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

}
