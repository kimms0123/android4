package com.example.android2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class practice10 : AppCompatActivity() {
    lateinit var baseLayout : RelativeLayout
    lateinit var angle1 : EditText
    lateinit var img : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_practice10)

        baseLayout = findViewById(R.id.baseLayout)
        angle1 = findViewById(R.id.angle1)
        img = findViewById(R.id.img1)

        ViewCompat.setOnApplyWindowInsetsListener(baseLayout) { view, insets ->
            val systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemInsets.left,
                systemInsets.top,
                systemInsets.right,
                systemInsets.bottom
            )
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        var mInflater = menuInflater
        menuInflater.inflate(R.menu.practice_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.imgRotate -> {
                // 입력된 문자열 가져옴
                val angleStr = angle1.text.toString()
                // 입력값이 비어있지 않은지 확인
                if(angleStr.isNotEmpty()) {
                    // 문자열을 실수로 변환
                    val angle = angleStr.toFloatOrNull()
                    // 변환 성공 시 (숫자가 맞는 경우)
                    if(angle != null) {
                        // 이미지 입력된 각도만큼 회전
                        img.rotation = angle
                    }else{
                        // 변환 실패 시 사용자에게 알림
                        Toast.makeText(this,"Error: 숫자를 입력해주세요",Toast.LENGTH_SHORT).show()
                    }
                    return true
                }
            }
            R.id.Han -> {
                img.setImageResource(R.drawable.han)
                return true
            }
            R.id.Chu -> {
                img.setImageResource(R.drawable.chujado)
                return true
            }
            R.id.Tiger -> {
                img.setImageResource(R.drawable.bum)
                return true
            }
        }
        return false
    }
}