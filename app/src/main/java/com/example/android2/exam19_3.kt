package com.example.android2

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

// (실습 9-2) 미니 포토샵 앱 만들기
class exam19_3 : AppCompatActivity() {

    // 이미지 버튼 선언 (아이콘 버튼)
    lateinit var ibZoomin : ImageButton
    lateinit var ibZoomout : ImageButton
    lateinit var ibRotate : ImageButton
    lateinit var ibBright : ImageButton
    lateinit var ibDark : ImageButton
    lateinit var ibgray : ImageButton

    // 커스텀 뷰 선언 (이미지를 그릴 View)
    lateinit var graphicView : MyGraphicView

    // companion object로 전체 앱에서 공유할 변수 선언
    companion object{
        var sX = 1f       // X축 스케일 (확대/축소)
        var sY = 1f       // Y축 스케일 (확대/축소)
        var angle = 0f    // 회전 각도
        var color = 1f    // 색상(밝기) 조절 값
        var satur = 1f    // 채도 (1f = 컬러, 0f = 흑백)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam19_3)
        title = "미니 포토샵"

        // 사진 표시할 레이아웃에 커스텀 뷰 추가
        var pictureLayout = findViewById<LinearLayout>(R.id.pictureLayout)
        graphicView = MyGraphicView(this)
        pictureLayout.addView(graphicView)

        // 버튼 클릭 이벤트 설정 함수 호출
        clickIcons()
    }

    // 실제 이미지를 그리고 효과 적용하는 커스텀 View 클래스
    class MyGraphicView(context: Context) : View(context) {
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            // 비트맵 이미지 불러오기
            var picture = BitmapFactory.decodeResource(resources, R.drawable.pickchu)

            // 화면 중앙에 이미지 위치 계산
            var picX = (this.width - picture.width) / 2f
            var picY = (this.height - picture.height) / 2f

            // 이미지 중심 좌표 계산
            var cenX = this.width / 2f
            var cenY = this.height / 2f

            // 캔버스에 스케일(확대/축소) 적용
            canvas.scale(sX, sY, cenX, cenY)

            // 캔버스에 회전 적용
            canvas.rotate(angle, cenX, cenY)

            // 색상/채도 조절을 위한 Paint 객체 생성
            val paint = Paint()

            // 색상(밝기) 조절용 ColorMatrix 배열 생성
            val array = floatArrayOf(
                color, 0f, 0f, 0f, 0f,
                0f, color, 0f, 0f, 0f,
                0f, 0f, color, 0f, 0f,
                0f, 0f, 0f, 1f, 0f)

            val cm = ColorMatrix(array)

            // 흑백 변환 버튼을 눌렀을 경우 채도 설정
            if (satur == 0f) cm.setSaturation(satur)

            // Paint에 ColorMatrix 적용
            paint.colorFilter = ColorMatrixColorFilter(cm)

            // 비트맵을 캔버스에 그리기
            canvas.drawBitmap(picture, picX, picY, paint)

            // 비트맵 리소스 메모리 해제
            picture.recycle()
        }
    }

    // 버튼 클릭 이벤트 설정 함수
    private fun clickIcons() {
        // 버튼과 xml id 연결
        ibZoomin = findViewById(R.id.ibZoomin)
        ibZoomout = findViewById(R.id.ibZoomout)
        ibRotate = findViewById(R.id.ibRotate)
        ibBright = findViewById(R.id.ibBright)
        ibDark = findViewById(R.id.ibDark)
        ibgray = findViewById(R.id.ibGray)

        // 확대 버튼 클릭 이벤트
        ibZoomin.setOnClickListener{
            sX += 0.2f
            sY += 0.2f
            graphicView.invalidate() // 화면 다시 그리기
        }

        // 축소 버튼 클릭 이벤트
        ibZoomout.setOnClickListener {
            sX -= 0.2f
            sY -= 0.2f
            graphicView.invalidate()
        }

        // 회전 버튼 클릭 이벤트
        ibRotate.setOnClickListener {
            angle += 20f
            graphicView.invalidate()
        }

        // 밝게 버튼 클릭 이벤트
        ibBright.setOnClickListener {
            color += 0.2f
            graphicView.invalidate()
        }

        // 어둡게 버튼 클릭 이벤트
        ibDark.setOnClickListener {
            color -= 0.2f
            graphicView.invalidate()
        }

        // 흑백 변환 버튼 클릭 이벤트
        ibgray.setOnClickListener {
            // satur 값이 0이면 1로, 1이면 0으로 토글
            if (satur == 0f)
                satur = 1f
            else
                satur = 0f
            graphicView.invalidate()
        }
    }
}
