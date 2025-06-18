package com.example.android2

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/*
    /*
    [이미지]

    [비트맵(Bitmap)]
        - 캔버스에 이미지를 그릴 때 사용하는 객체
        - 주로 res/drawable 폴더에 있는 이미지나
          SD카드에 있는 이미지 파일을 화면에 출력할 때 사용

    [res/drawable 폴더의 이미지 파일을 그릴 때]
        - onDraw() 메소드 안에서 다음과 같이 처리
        ```
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            // 비트맵 이미지 디코딩 (R.drawable.그림id)
            var picture = BitmapFactory.decodeResource(resources, R.drawable.그림id)

            // 이미지 그리기 (시작 좌표 x, y 설정)
            canvas.drawBitmap(picture, 시작x, 시작y, null)

            // 비트맵 객체 메모리 해제 (권장)
            picture.recycle()
        }
        ```

    [SD 카드의 이미지 파일을 그릴 때]
        - onDraw() 메소드 안에서 다음과 같이 처리
        ```
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            // SD카드 경로에 있는 이미지 파일 디코딩
            var picture = BitmapFactory.decodeFile("파일 경로 및 파일 이름")

            // 이미지 그리기 (시작 좌표 x, y 설정)
            canvas.drawBitmap(picture, 시작x, 시작y, null)

            // 비트맵 객체 메모리 해제 (권장)
            picture.recycle()
        }
        ```
*/


 */
class exam19 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyGraphicView(this))
    }

    // 커스텀 뷰 클래스 (View 클래스를 상속받아 onDraw 오버라이드)
    private class MyGraphicView(context: Context) : View(context) {
        // 화면에 그릴 내용 정의
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            // res/drawable 폴더에 있는 han.png (혹은 han 이미지)를 비트맵으로 디코딩
            var picture = BitmapFactory.decodeResource(resources, R.drawable.han)

            // 화면 중앙에 이미지를 배치하기 위한 좌표 계산
            var picX = (this.width - picture.width) / 2f
            var picY = (this.height - picture.height) / 2f

            // 비트맵 이미지를 계산한 좌표에 그리기
            canvas.drawBitmap(picture, picX, picY, null)

            // 비트맵 객체 메모리 해제 (권장사항)
            picture.recycle()
        }
    }

}