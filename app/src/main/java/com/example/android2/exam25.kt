package com.example.android2

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Gallery
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

/*
    갤러리와 스피너
    1. 갤러리
        - 사진이나 이미지를 배치하고 좌우로 스크롤 할 수 있는 이미지 목록을 보여주는 뷰
        👉 이미지 목록을 스크롤 하는 기능만 있으므로 이미지를 클릭했을 때 큰 이미지가 나타나게 하려면 코틀린 코드 추가해야함
        📌 현재는 Gallery 위젯이 deprecated됐지만, 학습용으로는 괜찮 -> 실무에서는 RecyclerView + HoriznotalLinearLayoutManager로 구현

    [BaseAdapter]
        - 리스트뷰, 그리드뷰, 갤러리 등에 데이터를 연결해주는 어댑터
        👉 반드시 4개의 메서드를 오버라이드 해야함
            [메서드]
                - getCount() : 항목 개수를 반환(예제 => posterId.size)
                - getItem() : 해당 위치의 데이터를 반환(예제에서는 필요 없음)
                - getItemId() : 해당 항목의 ID를 반환(필요 없어서 x)
                - getView() : 각 항목(셀)에 들어갈 View를 생성해 반환

    📌 RecyclerView
        - Gallery/ListView/GridView의 업그레이드 버전
    [특징]
        - 재사용성
        - 레이아웃 관리
        - 애니메이션 지원
        - 유연한 커스텀
    [필수 메서드]
        - onCreateViewHolder()  : 각 아이템 뷰 생성, ViewHolder 객체로 반환
        - onBindViewHolder()    : 데이터와 뷰를 연결
        - getItemCount()        : 아이템 개수 반환
    [ViewHolder 패턴]
        - 아이템 뷰를 담는 그릇
        👉 findViewById를 반복 호출하는 비효율을 줄이기 위함, View를 미리 찾아놓고 재활용
        ```
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.imgItem)
        }
        ```
    [LinearLayoutManager]
        - RecyclerView의 배치 방식 설정 담당
    [배치 방향]
        - 세로 리스트 : LinearLayoutManager(this)
        - 가로 스크롤 : LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


 */
class exam25 : AppCompatActivity() {
    // 실습 11-2포스터 보기 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam25_main)
        title ="갤러리 포스터"

        var gallery = findViewById<Gallery>(R.id.gallery1)
        // 갤러리 뷰에 어댑터 설정
        var galAdapter = MyGalleryAdapter(this)
        gallery.adapter = galAdapter
    }
    inner class MyGalleryAdapter(var context: Context) : BaseAdapter() {
        // 보여줄 이미지 리소스 ID 배열
        var posterID = arrayOf(
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
        )

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
            var imageview = ImageView(context)
            imageview.layoutParams = Gallery.LayoutParams(200,300)
            imageview.scaleType = ImageView.ScaleType.FIT_CENTER
            imageview.setPadding(5,5,5,5)
            imageview.setImageResource(posterID[p0])

            // 이미지 터치 이벤트
            imageview.setOnTouchListener { v, event ->
                // 이미지 뷰, 크기, 패딩, 이미지 리소스 설정
                var ivPoster = findViewById<ImageView>(R.id.ivPoster)
                ivPoster.scaleType = ImageView.ScaleType.FIT_CENTER
                ivPoster.setImageResource(posterID[p0])
                // 이벤트를 계속 다음으로 넘김
                false
            }

            return imageview
        }
    }

}