package com.example.android2

import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// 투표 결과를 보여주는 액티비티
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice15_result)

        // 액션바 타이틀 설정
        title = "투표 결과"

        // practice15에서 전달받은 투표 수 배열과 명화 이름 배열 가져오기
        val voteResult = intent.getIntArrayExtra("VoteCount")
        val imageName = intent.getStringArrayExtra("ImageName")

        // 값이 없으면 액티비티 종료
        if (voteResult == null || imageName == null) {
            finish()
            return
        }

        // TextView와 RatingBar 배열 선언 (투표 결과 표시용)
        val tv = arrayOfNulls<TextView>(voteResult.size)
        val rbar = arrayOfNulls<RatingBar>(voteResult.size)

        // TextView와 RatingBar의 id를 배열로 저장
        val tvID = arrayOf(
            R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5,
            R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9
        )
        val rbarID = arrayOf(
            R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5,
            R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9
        )

        // 각 TextView와 RatingBar를 findViewById로 연결
        for (i in voteResult.indices) {
            tv[i] = findViewById(tvID[i])
            rbar[i] = findViewById(rbarID[i])
        }

        // 가져온 값으로 TextView와 RatingBar 값 세팅
        for (i in voteResult.indices) {
            tv[i]?.text = imageName[i]
            rbar[i]?.rating = voteResult[i].toFloat()
        }

        // 돌아가기 버튼 클릭 시 액티비티 종료
        val btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            finish()
        }
    }
}
