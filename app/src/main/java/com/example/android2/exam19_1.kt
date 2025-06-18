package com.example.android2

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/*
    [이미지 기하학적 변환]
        - 비트맵 이미지를 캔버스에 출력하기 전에
          Canvas의 기하학적 변환 메소드를 이용해 변환된 상태로 이미지를 출력
        - 도화지를 움직이고 그림을 그리는 원리 (그림이 변한 것처럼 보임)

    [Canvas의 주요 기하학적 변환 메소드]
        - rotate(degrees, px, py) : (px, py)를 중심으로 degrees만큼 회전
        - scale(sx, sy, px, py)   : (px, py)를 중심으로 sx, sy만큼 확대/축소
        - translate(dx, dy)       : 좌표계 자체를 (dx, dy)만큼 이동
        - skew(kx, ky)            : x축, y축으로 기울이기
 */
class exam19_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyGraphicView(this))
    }

    private class MyGraphicView(context: Context) : View(context) {
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            // 비트맵 이미지 디코딩 (drawable의 pickchu.png 사용)
            var picture = BitmapFactory.decodeResource(resources, R.drawable.pickchu)

            // 화면 중앙 좌표 계산
            var cenX = this.width / 2f
            var cenY = this.height / 2f

            // 비트맵을 화면 중앙에 그리기 위한 좌표 계산
            var picX = (this.width - picture.width) / 2f
            var picY = (this.height - picture.height) / 2f

            // 1. 캔버스 회전 (중앙을 기준으로 45도 회전)
            canvas.rotate(45f, cenX, cenY)
            // 회전된 상태에서 이미지 그리기
            canvas.drawBitmap(picture, picX, picY, null)

            // 2. 캔버스 이동 (x: -150, y: 200 이동)
            canvas.translate(-150f, 200f)
            // 이동된 상태에서 이미지 그리기
            canvas.drawBitmap(picture, picX, picY, null)

            // 3. 캔버스 확대 (중앙을 기준으로 x 2배, y 2배 확대)
            canvas.scale(2f, 2f, cenX, cenY)
            // 확대된 상태에서 이미지 그리기
            canvas.drawBitmap(picture, picX, picY, null)

            // 4. 캔버스 기울이기 (x축 0.3f, y축 0.3f 만큼 skew)
            canvas.skew(0.3f, 0.3f)
            // 기울어진 상태에서 이미지 그리기
            canvas.drawBitmap(picture, picX, picY, null)

            // 비트맵 객체 메모리 해제
            picture.recycle()
        }
    }
}