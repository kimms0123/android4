package com.example.android2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
/*
    2. 스피너
        - PC의 드롭다운 박스와 비슷한 기능을 하므로 화면이 작은 스마트폰에서 여러개 중 하나를 선택할 수 있도록 확장하는 용도로 사용
    [ArrayAdapter]
        - 스피너 같은 UI 컴포넌트와 데이터를 연결해주는 역할
        👉 스피너는 리스트형 데이터를 어댑터로 받아 드롭다운 형태로 표시)
    [동작 순서]
        1. 데이터 배열 생성
        2. 스피너 위젯 찾기
        3. 어댑터 생성해서 데이터-UI 연결
        4. 스피너에 어댑터 적용
 */
class exam25_2 : AppCompatActivity() {
    // 스피너 기본 예제
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam25_2_main)
        title ="스피너 테스트"

        var movie = arrayOf("쿵푸팬더", "짱구는 못 말려", " 아저씨",
            "아바타", "대부", "국가대표", "토이스토리", "마당을 나온 암탉",
            "죽은 시인의 사회", "서유기")

        var spinner = findViewById<Spinner>(R.id.spinner1)

        var adapter : ArrayAdapter<String>
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, movie)
        spinner.adapter = adapter
    }
}