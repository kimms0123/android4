package com.example.android2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

// 동적인 리스트 뷰 항목 추가·삭제
class ListView_add_delete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview_add_delete)

        var midList = ArrayList<String>()
        var list = findViewById<ListView>(R.id.listView1)

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, midList)
        list.adapter = adapter

        var edtItem = findViewById<EditText>(R.id.edtItem) as EditText
        var btnAdd = findViewById<Button>(R.id.btnAdd) as Button

        btnAdd.setOnClickListener {
            midList.add(edtItem.text.toString())
            adapter.notifyDataSetChanged()
        }

        list.setOnItemLongClickListener { parent, view, position, id ->
            midList.removeAt(position)
            adapter.notifyDataSetChanged()
            false
        }
    }
}