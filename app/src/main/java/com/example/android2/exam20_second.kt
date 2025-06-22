package com.example.android2

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class exam20_second : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam20_second)

        var btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            finish()
        }
    }
}