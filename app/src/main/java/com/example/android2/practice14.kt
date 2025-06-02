package com.example.android2

import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class practice14 : AppCompatActivity() {
    lateinit var edtName: EditText
    lateinit var edtEmail: EditText
    lateinit var button1: Button
    lateinit var dlgEdtName: EditText
    lateinit var dlgEdtEmail: EditText
    lateinit var toastText: TextView
    lateinit var dialogView: View
    lateinit var toastView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice14)
        title = "사용자 정보 입력"

        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        button1 = findViewById(R.id.button1)

        button1.setOnClickListener {
            dialogView = layoutInflater.inflate(R.layout.dialog1, null)

            // 다이얼로그 뷰 안의 editText에 접근해 main.xml의 editText 내용 미리 설정
            dlgEdtName = dialogView.findViewById(R.id.dlgEdit1)
            dlgEdtEmail = dialogView.findViewById(R.id.dlgEdt2)

            dlgEdtName.setText(edtName.text.toString())
            dlgEdtEmail.setText(edtEmail.text.toString())

            val dlg = AlertDialog.Builder(this)
            dlg.setTitle("사용자 정보 입력")
            dlg.setIcon(R.drawable.ic_menu_allfriends)
            dlg.setView(dialogView)

            dlg.setPositiveButton("확인") { dialog, which ->
                edtName.setText(dlgEdtName.text.toString())
                edtEmail.setText(dlgEdtEmail.text.toString())
            }

            dlg.setNegativeButton("취소") { dialog, which ->
                toastView = layoutInflater.inflate(R.layout.toast1, null)
                toastText = toastView.findViewById(R.id.toastText1)
                toastText.text = "취소했습니다"

                val toast = Toast(this)
                toast.view = toastView
                // 무작위 위치 (예: x:100, y:500 위치에 표시)
                toast.setGravity(Gravity.TOP or Gravity.START, 100, 500)
                toast.duration = Toast.LENGTH_SHORT
                toast.show()
            }

            dlg.show()
        }
    }
}