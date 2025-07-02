package com.example.android2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// 실습 10-2(명화 선호도 투표 앱)
class practice15 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice15_main)

        // 액션바 타이틀 설정
        title = "명화 선호도 투표"

        // 각 명화에 대한 투표 수를 저장할 배열 (초기값 0)
        var voteCount = IntArray(9)
        for(i in 0..8)
            voteCount[i] = 0

        // 이미지뷰를 담을 배열 선언
        var image = arrayOfNulls<ImageView>(9)

        // 각 이미지뷰의 ID를 배열로 저장
        var imageId = arrayOf(
            R.id.iv1, R.id.iv2, R.id.iv3,
            R.id.iv4, R.id.iv5, R.id.iv6,
            R.id.iv7, R.id.iv8, R.id.iv9
        )

        // 각 명화의 이름을 배열로 저장
        var imgName = arrayOf(
            "독서하는 소녀", "아를의 반 고흐 방", "진주 귀걸이를 단 소녀",
            "Golconda", "그냥 다리", "모나리자",
            "우산을 든 소녀", "별이 빛나는 밤", "나폴레옹"
        )

        // 각 이미지뷰에 클릭 이벤트 설정
        for (i in imageId.indices) {
            image[i] = findViewById(imageId[i])
            image[i]!!.setOnClickListener {
                // 해당 이미지를 클릭하면 투표 수 증가
                voteCount[i]++
                // 현재 투표 수를 토스트 메시지로 출력
                Toast.makeText(
                    applicationContext,
                    imgName[i] + ": 총 " + voteCount[i] + " 표",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // 투표 종료 버튼 찾기
        var btnFinish = findViewById<Button>(R.id.btnResult)
        btnFinish.setOnClickListener {
            // 인텐트를 이용해 ResultActivity로 화면 전환
            var intent = Intent(applicationContext, ResultActivity::class.java)

            // 인텐트에 투표 결과 배열을 넘긴다.
            // putExtra(key, value) 형식으로 저장
            // "VoteCount"라는 키로 voteCount IntArray 데이터를 전달
            intent.putExtra("VoteCount", voteCount)

            // 명화 이름 배열도 "ImageName"이라는 키로 전달
            intent.putExtra("ImageName", imgName)

            /*
             인텐트에 데이터를 실어서 다른 액티비티로 전달할 때는
             putExtra() 메서드를 이용해 key-value 형태로 데이터를 담는다.
             전달된 인텐트는 ResultActivity에서 intent.getXXXExtra("key")로 꺼낼 수 있음.
             이 경우
             - "VoteCount" 키에는 IntArray 형태로 투표 수가 들어있고
             - "ImageName" 키에는 StringArray 형태로 명화 이름이 들어있다.
             */

            // ResultActivity 실행
            startActivity(intent)
        }
    }
}
