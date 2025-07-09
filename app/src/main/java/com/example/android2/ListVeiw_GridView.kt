package com.example.android2

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/*
    1. 어댑터 뷰
    [어댑터]
        - 데이터를 가져와서 각 항목을 View로 변환해주는 객체

        [어댑터 클래스]
            - ArrayAdapter  : 배열, 리스트 같은 간단한 데이터 연결용
            - SimpleAdapter : Map 형태의 복잡한 데이터 연결용
            - CursorAdapter : 데이터베이스 Cursor 결과 연결용
            - BaseAdapter   : 직접 커스텀해서 어댑터를 만들 때 사용

    [어댑터 뷰]
        - 데이터를 **목록 형태로 화면에 표시**해주는 뷰
        - 데이터와 화면 요소를 연결해주는 중간 역할
        👉 데이터 -> Adapter -> AdapterView(화면)

    [종류]
        - ListView      : 세로로 스크롤되는 목록 형태의 뷰
        - GridView      : 격자 형태로 항목을 배치하는 뷰
        - Spinner       : 드롭다운 형태로 목록을 보여주는 뷰
        - Gallery       : 가로로 스크롤되는 이미지 목록(구버전)
        - RecyclerView  : ListView를 개선한 고급 어댑터 뷰(많이 사용)

    2. ListView(리스트뷰)
        - 데이터를 리스트 모양으로 보여주고 그중 하나를 선택

    3. GridView(그리드뷰)
        - 사진이나 그림을 격자 모양으로 배치(버튼, 텍스트도 가능)
 */
class ListVeiw_GridView : AppCompatActivity() {
    // ListView 기본 예제
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)
        title ="리스트뷰 테스트"

        var mid = arrayOf("히어로즈", "24시", "로스트",
            "로스트룸", "스몰빌", "탐정몽크", "빅뱅이론" ,
            "프렌즈", "덱스터", "글리", "가쉽걸", "테이큰", "슈퍼내추럴", "브이")

        var list = findViewById<View>(R.id.listView1) as ListView

//        var adapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, mid)
//        list.adapter = adapter

        // 리스트뷰의 다양한 모양 설정
        val adapter : ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_list_item_multiple_choice, mid)
        list.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        list.adapter = adapter


        list.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(applicationContext, mid[position], Toast.LENGTH_SHORT).show()
        }
    }
}