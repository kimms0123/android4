package com.example.android2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/*
    [raw 폴더 파일 처리]
    - res/raw 폴더에 넣은 파일은 앱이 빌드될 때 **읽기 전용 리소스**로 포함됨
    - 오디오, 텍스트, 동영상 등 **변경되지 않는 정적 파일**을 포함할 때 유용

    주요 특징
    - 읽기 전용 (파일을 수정하려면 내부 저장소 또는 외부 저장소 사용)
    - 리소스 ID (R.raw.파일명)를 통해 직접 접근 가능
    - 파일 이름은 소문자, 숫자, 밑줄(_)만 사용 가능

    파일 인코딩 유의사항
    - 텍스트 파일을 PC에서 만들 때 반드시 **UTF-8 인코딩**으로 저장해야 한글이 깨지지 않음
    - 메모장에서 저장 시 [파일] → [다른 이름으로 저장] → 인코딩을 UTF-8로 지정

    주요 함수
    - resource.openRawResource(R.raw.파일명)   : raw 폴더에 있는 파일 일기용 InputStream으로 열어줌
    - InputStream.read(ByteArray)             : 파일의 내용을 바이트 배열에 저장
    - InputStream.available()                 : InputStream에서 읽을 수 있는 바이트 수(파일 크기) 반환
    - InputStream.close()                     :파일 스트림을 닫아 메모리 누수 방지
    - ByteArray.toString(Charsets.UTF_8)      : 바이트 배열을 UTF_8 문자열로 변환
    - BufferedReader(InputStreamReader(...))  : 텍스트 파일을 줄 단위로 읽기 편하게 해주는 보조 클래스

    처리 순서
        1. res/raw 폴더에 파일 추가
            raw 파일이 없다면 Resource type: raw 파일도 추가
        2. InputStream 객체로 파일 열기
        3. 파일의 내용 읽기 및 읽은 내용을 문자열로 변환
        4. InputStream 닫기
*/

class exam15 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam15)

        // 레이아웃에 정의된 버튼과 에디트텍스트를 변수에 연결
        var btnRead: Button = findViewById(R.id.btnRead)  // 텍스트 읽기 버튼
        var edtRaw: EditText = findViewById(R.id.edtRaw)  // 텍스트 출력용 에디트박스

        // 버튼 클릭 시 raw 폴더에 있는 텍스트 파일을 읽어 에디트텍스트에 표시
        btnRead.setOnClickListener {
            // 1. raw 폴더의 'raw_test.txt' 파일을 InputStream 형태로 가져옴
            val inputS = resources.openRawResource(R.raw.raw_test)
            // 2. available(): 파일의 전체 크기(바이트 수)를 가져옴
            val txt = ByteArray(inputS.available())

            // 3. 파일에서 바이트 배열(txt)에 데이터 읽어옴
            inputS.read(txt)

            // 4. 읽은 바이트 배열을 문자열로 변환 (UTF-8로 인코딩)
            edtRaw.setText(txt.toString(Charsets.UTF_8))

            // 5. InputStream 닫기 (메모리 누수 방지)
            inputS.close()
        }
    }
}
