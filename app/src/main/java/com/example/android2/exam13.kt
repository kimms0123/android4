package com.example.android2

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class exam13 : AppCompatActivity() {

    /*
        기본 대화 상자: 사용자에게 간단한 메세지를 보여 주고, 확인 또는 취소 등의 버튼을 통해 반응을 받을 수 있는 창
            - setTitle() : 제목 설정
            - setMessage() : 내용 입력
            - setIcon() : 아이콘 설정
            - setPositiveButton : <OK>버튼
            - setNegativeButton : <Cancel>버튼
            - setItems() : 목록 출력
            - setSingleChoiceItems() : 라디오 버튼 목록 출력
            - setMultiChoiceItems() : 체크 박스 목록 출력
     */
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_exam13)
//
//        var button1 = findViewById<Button>(R.id.button1)
//
//        button1.setOnClickListener {
//            var dlg = AlertDialog.Builder(this@exam13)
//            dlg.setTitle("제목입니다")
//            dlg.setMessage("이곳이 내용입니다")
//            dlg.setIcon(R.mipmap.ic_launcher)
//            dlg.setPositiveButton("확인") {dialog, which ->
//                Toast.makeText(this@exam13, "확인을 눌렀네요", Toast.LENGTH_SHORT).show()
//            } // setPositiveButton("문자열" , 람다식)
//            dlg.show()
//        }
//
//    }

    // 목록 대화상자
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam13)

        var button1 = findViewById<Button>(R.id.button1)

        button1.setOnClickListener {
            var versionArray = arrayOf("오레오", "파이", "큐(10)")
            var checkArray = booleanArrayOf(true, false, false)

            var dlg = AlertDialog.Builder(this@exam13)
            dlg.setTitle("좋아하는 버전은?")
            dlg.setIcon(R.mipmap.ic_launcher)

            // dlg.setItems(versionArray) {dialog, which ->
            //     button1.text = versionArray[which]
            // }
            // setItems -> 항목의 선택과 동시에 대화상자 닫힘
            // setSingleChoiceItems() -> 선택해도 대화상자 닫히지 않음 (라디오버튼 형태로 출력) 아래 예제 참고

            // dlg.setSingleChoiceItems(versionArray, 0) {dialog, which ->
            //     button1.text = versionArray[which]
            // }

            dlg.setMultiChoiceItems(versionArray,checkArray) {dialog, which, isCheckd ->
                button1.text = versionArray[which]
            }
            // 두 번째 파라미터는 첫 파라미터인 문자열 배열과 개수가 같은 boolean 배열이어야 함
            // -> 처음 화면 나올 때 항목의 체크 여부 표시 가능

            dlg.setPositiveButton("닫기", null)
            dlg.show()
        }
    }
}