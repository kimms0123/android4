package com.example.android2

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

// 실습 (11-1) 영화 포스터 보기 1
class exam24 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam24)
        title ="그리드뷰 영화 포스터"

        // 레이아웃에서 ID가 gridView1인 GridView를 찾아 gv 변수에 저장
        var gv = findViewById<View>(R.id.gridView1) as GridView

        // 커스텀 어댑터 객체 생성 (MyGridAdapter 클래스 사용)
        var gAdapter = MyGridAdapter(this)

        // 그리드뷰에 어댑터 연결 → 이걸 해야 화면에 그리드뷰가 표시됨
        gv.adapter = gAdapter
    }

    // BaseAdapter를 상속받아 커스텀 어댑터 클래스 정의
    inner class MyGridAdapter(var context: Context) : BaseAdapter() {

        // 그리드뷰에 표시할 이미지 리소스 ID 배열
        var posterID = arrayOf(
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,  // 9개 이미지
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,  // 반복
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
        )

        // 그리드뷰에 표시할 총 아이템 개수 반환
        override fun getCount(): Int {
            return posterID.size
        }

        // 해당 위치의 아이템 반환 (여기선 딱히 쓸 일이 없어서 0 반환)
        override fun getItem(p0: Int): Any {
            return 0
        }

        // 해당 위치의 아이템 ID 반환 (여기선 딱히 쓸 일 없어 0 반환)
        override fun getItemId(p0: Int): Long {
            return 0
        }

        // 각 그리드 셀에 들어갈 뷰를 생성하고 반환하는 메서드
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            // 새로운 이미지뷰 객체 생성
            var imageview = ImageView(context)

            // 이미지뷰 크기 설정 (가로 200, 세로 300)
            imageview.layoutParams = ViewGroup.LayoutParams(200, 300)

            // 이미지뷰의 이미지 배치 방식 설정 (중앙 정렬 + 비율 유지)
            imageview.scaleType = ImageView.ScaleType.FIT_CENTER

            // 이미지뷰 테두리 패딩 설정 (좌우상하 5픽셀)
            imageview.setPadding(5, 5, 5, 5)

            // 이미지뷰에 해당 위치의 이미지 리소스 설정
            imageview.setImageResource(posterID[p0])

            // 이미지뷰 클릭 이벤트 설정
            imageview.setOnClickListener {
                // 커스텀 다이얼로그 뷰 생성 (activity_dialog.xml을 inflate)
                var dialogView = View.inflate(this@exam24, R.layout.activity_dialog, null)

                // AlertDialog.Builder 객체 생성
                var dlg = AlertDialog.Builder(this@exam24)

                // 다이얼로그 뷰에서 ID가 ivPoster인 이미지뷰 찾아오기
                var ivPoster = dialogView.findViewById<ImageView>(R.id.ivPoster)

                // 다이얼로그의 이미지뷰에 클릭한 이미지 설정
                ivPoster.setImageResource(posterID[p0])

                // 다이얼로그 타이틀 설정
                dlg.setTitle("큰포스터")

                // 다이얼로그 아이콘 설정
                dlg.setIcon(R.drawable.ic_launcher_foreground)

                // 다이얼로그에 커스텀 뷰 설정
                dlg.setView(dialogView)

                // 다이얼로그 '닫기' 버튼 설정
                dlg.setNegativeButton("닫기", null)

                // 다이얼로그 화면에 표시
                dlg.show()
            }

            // 완성된 이미지뷰 반환
            return imageview
        }
    }
}