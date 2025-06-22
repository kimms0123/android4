package com.example.android2

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar

class RatingBarExam : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ratingbar)
        var rating1 : RatingBar
        var rating2 : RatingBar
        var rating3 : RatingBar
        var btnInc : Button
        var btnDec : Button

        rating1 = findViewById(R.id.ratingBar1)
        rating2 = findViewById(R.id.ratingBar2)
        rating3 = findViewById(R.id.ratingBar3)
        btnInc = findViewById(R.id.btnInc)
        btnDec = findViewById(R.id.btnDec)

        btnInc.setOnClickListener {
            rating1.rating = rating1.rating + rating1.stepSize
            rating2.rating = rating2.rating + rating2.stepSize
            rating3.rating = rating3.rating + rating3.stepSize
        }
        btnDec.setOnClickListener {
            rating1.rating = rating1.rating - rating1.stepSize
            rating2.rating = rating2.rating - rating2.stepSize
            rating3.rating = rating3.rating - rating3.stepSize
        }
    }
}