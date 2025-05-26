package com.example.android2

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class exam10 : AppCompatActivity() {
    lateinit var baseLayout : LinearLayout
    lateinit var button1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        title = "배경색 바꾸기 ver.2"

        baseLayout = findViewById(R.id.baseLayout)
        button1 = findViewById(R.id.button1)

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

        menu!!.add(0, 1, 0, "배경색 (빨강)")
        menu!!.add(0, 2, 0, "배경색 (초록)")
        menu!!.add(0, 3, 0, "배경색 (파랑)")

        var sMenu : SubMenu = menu.addSubMenu("버튼 변경 >>")
        sMenu.add(0, 4, 0,"버튼 45도 회전")
        sMenu.add(0, 5, 0,"버튼 2배 확대")

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            1 -> {
                baseLayout.setBackgroundColor(Color.RED)
                return true
            }
            2 -> {
                baseLayout.setBackgroundColor(Color.GREEN)
                return true
            }
            3 -> {
                baseLayout.setBackgroundColor(Color.BLUE)
                return true
            }
            4 -> {
                button1.rotation = 45f
                return true
            }
            5 -> {
                button1.scaleX = 2f
                return true
            }
        }
        return false
    }
}