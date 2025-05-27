package com.example.android2

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
    context menu: 레이아웃 또는 버튼, 에디트텍스트 등의 위젯을 롱클릭 했을 시 화면 중앙에 나타나는 Window 팝업창과 유사한 것
    생성 방법
        1. 메뉴 폴더 생성 및 위젯의 메뉴 xml 파일 생성 · 편집 (메뉴 코딩)
        2. Kotlin 코딩
            onCreate()안에 registerForContextMenu()로 등록 (메뉴를 사용할 위젯 등록)
        3. Kotlin 코딩
            onCreateContextMenu() 메소드 오버라이딩 (메뉴 파일 등록)
        4. Kotlin 코딩
            onContextItemSelected() 메소드 오버라이딩 (메뉴 선택 시 동작할 내용 코딩)
 */
class exam11 : AppCompatActivity() {
    lateinit var baseLayout: LinearLayout
    lateinit var button1 : Button
    lateinit var button2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exam11)

        title = "배경색 바꾸기(컨텍스트 메뉴)"
        baseLayout = findViewById(R.id.baseLayout)as LinearLayout
        button1 = findViewById(R.id.button1)as Button
        registerForContextMenu(button1)
        button2 = findViewById(R.id.button2)as Button
        registerForContextMenu(button2)

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

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        var mInflater = this.menuInflater
        if (v === button1){
            menu!!.setHeaderTitle("배경색 변경")
            mInflater.inflate(R.menu.menu_exam11_1, menu)
        }
        if (v === button2) {
            mInflater.inflate(R.menu.menu_exam11_2, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemRed -> {
                baseLayout.setBackgroundColor(Color.RED)
                return true
            }
            R.id.itemGreen -> {
                baseLayout.setBackgroundColor(Color.GREEN)
                return true
            }
            R.id.itemBlue -> {
                baseLayout.setBackgroundColor(Color.BLUE)
                return true
            }
            R.id.subRotate -> {
                button2.rotation = 45f
                return true
            }
            R.id.subSize -> {
                button2.scaleX = 2f
                return true
            }
        }
        return false
    }
}