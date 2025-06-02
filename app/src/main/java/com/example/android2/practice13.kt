package com.example.android2

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class practice13 : AppCompatActivity() {
    lateinit var tvName : TextView
    lateinit var tvEmail : TextView
    lateinit var button1 : Button
    lateinit var dlgEdtName : EditText
    lateinit var dlgEdtEmail : EditText
    lateinit var toastText : TextView
    lateinit var dialogView : View
    lateinit var toastView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice13)
        title = "사용자 정보 입력"

        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        button1 = findViewById(R.id.button1)

        button1.setOnClickListener {
            dialogView = View.inflate(this@practice13, R.layout.dialog1, null)
            var dlg = AlertDialog.Builder(this@practice13)
            dlg.setTitle("사용자 정보 입력")
            dlg.setIcon(R.drawable.ic_menu_allfriends)
            dlg.setView(dialogView)
            dlg.setPositiveButton("확인",null)
            dlg.setNegativeButton("취소", null)


            dlg.setPositiveButton("확인") {dialog, which ->
                dlgEdtName = dialogView.findViewById(R.id.dlgEdit1)
                dlgEdtEmail = dialogView.findViewById(R.id.dlgEdt2)
                tvName.text = dlgEdtName.text.toString()
                tvEmail.text = dlgEdtEmail.text.toString()
            }
            dlg.setNegativeButton("취소") {dialog, which ->
                toastView = View.inflate(this@practice13, R.layout.toast1, null)
                toastText = toastView.findViewById(R.id.toastText1)
                toastText.text = "취소했습니다"

                val toast = Toast(this@practice13)
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