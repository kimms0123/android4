package com.example.android2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class exam14 : AppCompatActivity() {
    /*
        [내장 메모리 파일 처리]
            - 안드로이드 앱의 **내부 저장소(Internal Storage)**에 파일을 저장하거나 읽는 작업
            - 사용자가 아닌 **앱만 접근 가능한 안전한 공간**에서 데이터를 보관할 수 있음

        📁 저장 경로: /data/data/앱 패키지명/files
        🔐 다른 앱은 접근 불가능 (보안에 강함)

        - 사용 목적: 간단한 설정값, 텍스트 데이터, 캐시 등을 앱 내부에 안전하게 저장할 때 사용

        - 주요 함수 및 처리 흐름:
        1. openFileOutput(fileName, MODE)
            → 내부 저장소에 **쓰기 모드**로 파일 열기 (FileOutputStream 반환)
                - MODE_PRIVATE는 기존 내용 덮어씀
        2. openFileInput(fileName)
            → 내부 저장소에 **읽기 모드**로 파일 열기 (FileInputStream 반환)
        3. write(), read()
            → 바이트 단위로 데이터 저장 또는 읽기(실 문자열로 쓰려면 toStirng(Charsets.UTF.8) 사용)
        4. close()
            → 스트림 종료 (리소스 누수 방지)
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam14)

        // 버튼 참조: 레이아웃에서 버튼 연결
        val btnRead: Button = findViewById(R.id.btnRead)
        val btnWrite: Button = findViewById(R.id.btnWrite)

        // [쓰기 기능] - 버튼 클릭 시 file.txt 파일에 문자열 저장
        btnWrite.setOnClickListener {
            // 🔧 내부 저장소에 file.txt 파일을 생성(또는 덮어쓰기)
            // MODE_PRIVATE: 같은 파일명 있을 경우 덮어쓰기 (기본값)
            val outFs: FileOutputStream = openFileOutput("file.txt", Context.MODE_PRIVATE)

            // 저장할 문자열
            val str = "쿡북 안드로이드"

            // 문자열을 바이트 배열로 변환 후 파일에 쓰기
            outFs.write(str.toByteArray())

            // 스트림 닫기 (꼭 필요!)
            outFs.close()

            // 사용자에게 저장 완료 메시지 표시
            Toast.makeText(applicationContext, "file.txt 가 저장됨", Toast.LENGTH_SHORT).show()
        }

        // [읽기 기능] - 버튼 클릭 시 file.txt 파일의 내용 읽어오기
        btnRead.setOnClickListener {
            try {
                // 🔧 내부 저장소에서 file.txt 파일 열기 (읽기 전용)
                val inFs: FileInputStream = openFileInput("file.txt")

                // 읽은 데이터를 저장할 바이트 배열 생성 (최대 30바이트로 가정)
                val txt = ByteArray(30)

                // 파일에서 데이터 읽어 txt 배열에 저장
                inFs.read(txt)

                // 바이트 배열을 문자열로 변환 (UTF-8 인코딩)
                val str = txt.toString(Charsets.UTF_8)

                // 사용자에게 읽어온 문자열을 토스트로 표시
                Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()

                // 스트림 닫기
                inFs.close()
            } catch (e: IOException) {
                // 파일이 존재하지 않거나 읽는 도중 오류 발생 시
                Toast.makeText(applicationContext, "파일 없음", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
