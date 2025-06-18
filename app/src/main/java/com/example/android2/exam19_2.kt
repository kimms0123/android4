package com.example.android2

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.EmbossMaskFilter
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlin.io.path.Path

/*
    [이미지 활용]
        - 포토샵 등의 이미지 처리 응용 프로그램으로 이미지에 다양한 효과를 줄 수 있음
    [효과]
        - 블러링: 이미지를 뿌옇게 만듬
            BlurMaskFilter(반지름, 스타일)
        - 엠보싱: 이미지가 볼록하게 튀어나와 보이는 효과
            EmbossMaskFilter(빛의 xyz 방향 1차 배열, 빛의 밝기, 반사 계수, 블러링 크기)
        - 컬러매트릭스: 색상이나 밝기를 조절하기 위함
            var paint = Paint()
            var array = floatArrayOf(4 x 5 배열)
            var cm = ColorMatrix(array)
            paint.colorFilter = ColorMatrixColorFilter(cm)
            canvas.drawBitmap(···)
 */
class exam19_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyGraphicView(this))
    }

    /*
    [블러링]
    [BlurMaskFilter를 이용한 블러(번짐) 효과 예제]
        - BlurMaskFilter : 비트맵이나 도형의 외곽선을 흐리게 번지게 만들어주는 필터
        - 번짐의 강도와 스타일(어디를 번지게 할지)을 설정할 수 있음

    [BlurMaskFilter 생성자]
        BlurMaskFilter(radius, style)

        - radius : 블러의 반경 (값이 클수록 번짐 효과가 강해짐)
        - style  : 블러의 적용 방식
            → BlurMaskFilter.Blur.NORMAL : 외곽 전체 블러
            → BlurMaskFilter.Blur.INNER  : 안쪽만 블러
            → BlurMaskFilter.Blur.OUTER  : 바깥쪽만 블러
            → BlurMaskFilter.Blur.SOLID  : 안쪽은 선명, 외곽만 블러

    [주의]
        - BlurMaskFilter도 하드웨어 가속이 활성화되어 있으면 적용 안 될 수 있어,
          필요한 경우 setLayerType(LAYER_TYPE_SOFTWARE, paint)로 소프트웨어 레이어로 변경 필요
    */
//    private class MyGraphicView(context: Context) : View(context) {
//        override fun onDraw(canvas: Canvas) {
//            super.onDraw(canvas)
//
//            // 비트맵 이미지 디코딩 (drawable의 han.png 사용)
//            var picture = BitmapFactory.decodeResource(resources, R.drawable.han)
//
//            // 비트맵을 화면 중앙에 배치하기 위한 좌표 계산
//            var picX = (this.width - picture.width) / 2f
//            var picY = (this.height - picture.height) / 2f
//
//            // 페인트 객체 생성
//            var paint = Paint()
//
//            // BlurMaskFilter 객체 생성
//            var bMask : BlurMaskFilter
//            bMask = BlurMaskFilter(
//                30f,                        // 블러 반경 (값이 클수록 번짐 효과 강함)
//                BlurMaskFilter.Blur.NORMAL  // 블러 스타일: 전체 블러
//            )
//
//            // 페인트에 블러 효과 필터 적용
//            paint.maskFilter = bMask
//
//            // 이미지에 블러 효과를 적용하며 그림
//            canvas.drawBitmap(picture, picX, picY, paint)
//
//            // 비트맵 객체 메모리 해제
//            picture.recycle()
//        }
//    }

    /*
    [엠보싱]
    [EmbossMaskFilter를 이용한 입체감(양각) 효과 예제]
        - EmbossMaskFilter : 그림에 빛의 방향과 입체감을 줘서
          양각(돌출) 효과를 표현하는 필터
        - 빛의 방향과 강도, 주변 밝기 등을 조절해 입체 효과 조절 가능

    [EmbossMaskFilter 생성자]
        EmbossMaskFilter(direction, ambient, specular, blurRadius)

        - direction : floatArrayOf(x, y, z)
                      → 빛의 방향 벡터
        - ambient   : 0~1 사이 값, 주변 밝기 (값이 클수록 전체적으로 밝아짐)
        - specular  : 반사광 강도 (값이 클수록 더 뚜렷해짐)
        - blurRadius: 블러의 반경 (값이 클수록 부드럽게 퍼짐)

    [빛 방향 예시]
        - {10, 3, 3} : x축으로 강하게 빛이 비춤
        - {3, 10, 3} : y축으로 강하게 빛이 비춤
        - {3, 3, 10} : z축(앞쪽)에서 비추는 효과

    [주의]
        - EmbossMaskFilter는 하드웨어 가속을 끄지 않으면 적용 안 되는 경우가 있음
*/
//    private class MyGraphicView(context: Context) : View(context) {
//        override fun onDraw(canvas: Canvas) {
//            super.onDraw(canvas)
//
//            // 화면 중앙 좌표 계산
//            var cenX = this.width / 2f
//            var cenY = this.height / 2f
//
//            // 페인트 객체 생성 및 색상 설정
//            var paint = Paint()
//            paint.color = Color.GRAY
//
//            // EmbossMaskFilter 객체 생성
//            var eMask : EmbossMaskFilter
//            eMask = EmbossMaskFilter(
//                floatArrayOf(3f, 3f, 3f), // 빛의 방향 벡터 (x, y, z)
//                0.5f,  // 주변 밝기 (ambient)
//                5f,    // 반사광 강도 (specular)
//                10f    // 블러 반경 (blurRadius)
//            )
//
//            // 페인트에 입체감 효과 필터 적용
//            paint.maskFilter = eMask
//
//            // 원 그리기 (입체감 효과 적용)
//            canvas.drawCircle(cenX, cenY, 150f, paint)
//        }
//    }

    /*
    [컬러매트릭스]
    [ColorMatrixColorFilter를 이용한 색상 효과 예제]
        - ColorMatrixColorFilter : ColorMatrix를 이용해 이미지의 색상을 조정하는 필터
        - 색상 변환 행렬을 통해 RGB값, 투명도, 채도, 명암 등을 변경 가능

    [ColorMatrix 구조]
        4x5 행렬 형태 (20개 값)
        [ R, G, B, A, Offset ]
        총 4줄 (R, G, B, A) 각각에 대해 변환값 적용

        ┌                                             ┐
        │ a, b, c, d, e  → Red 변환 (R, G, B, A, 상수)│
        │ f, g, h, i, j  → Green 변환                 │
        │ k, l, m, n, o  → Blue 변환                  │
        │ p, q, r, s, t  → Alpha 변환                 │
        └                                             ┘

    [예제 행렬 설명]
        var array = floatArrayOf(
            2f, 0f, 0f, 0f, -25f,   → Red 채널 값 * 2 + (-25)
            0f, 2f, 0f, 0f, -25f,   → Green 채널 값 * 2 + (-25)
            0f, 0f, 2f, 0f, -25f,   → Blue 채널 값 * 2 + (-25)
            0f, 0f, 0f, 1f, 0f      → Alpha 값 그대로 유지
        )

        → 전체적으로 RGB 채널의 값을 2배로 키우고 -25를 빼서 대비와 밝기 조절 효과

    [ColorMatrixColorFilter 생성자]
        ColorMatrixColorFilter(ColorMatrix)

        → ColorMatrix를 전달받아 페인트의 colorFilter에 적용 가능
*/
    // 커스텀 그래픽 뷰
    private class MyGraphicView(context: Context) : View(context) {
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            // 비트맵 이미지 디코딩 (drawable의 han.png 사용)
            var picture = BitmapFactory.decodeResource(resources, R.drawable.han)

            // 비트맵을 화면 중앙에 배치하기 위한 좌표 계산
            var picX = (this.width - picture.width) / 2f
            var picY = (this.height - picture.height) / 2f

            // 페인트 객체 생성
            var paint = Paint()

            // 색상 변환 행렬 정의 (대비 증가 + 밝기 감소 효과)
            var array = floatArrayOf(
                2f, 0f, 0f, 0f, -25f,  // R 채널 조정
                0f, 2f, 0f, 0f, -25f,  // G 채널 조정
                0f, 0f, 2f, 0f, -25f,  // B 채널 조정
                0f, 0f, 0f, 1f, 0f     // Alpha 값 유지
            )

            // ColorMatrix 객체 생성
            var cm = ColorMatrix(array)

            // 페인트에 컬러 매트릭스 필터 적용
            paint.colorFilter = ColorMatrixColorFilter(cm)

            // 필터 적용한 비트맵 출력
            canvas.drawBitmap(picture, picX, picY, paint)

            // 비트맵 객체 메모리 해제
            picture.recycle()
        }
    }
}