package com.example.android2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity

/*
    그래픽 (Canvas와 Paint를 이용한 기본 그래픽 처리)

    [캔버스와 페인트 기본]
    - Canvas : 화면에 도형, 선, 텍스트 등을 그릴 때 사용하는 클래스
    - Paint  : 그릴 때 색상, 스타일, 두께 등을 지정하는 클래스

    [View 클래스 재정의 방법]
    - 그래픽 처리를 위해 View 클래스를 상속받아 onDraw 메소드를 오버라이드하여 사용
    - Activity에서는 setContentView()를 통해 해당 View를 화면에 설정

            [메소드 원형]
            public void drawPoint (float x, float y, Paint paint)
            [색상 지정 속성 및 사용법]
            var paint = Paint()
            paint.color = Color.RED
            [그래픽 표현 시 View 클래스 재정의 형태]
            public override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(재정의한 클래스 이름(this))
            }
            private class 재정의한 클래스 이름(context: Context) : View(context) {
                override fun onDraw(canvas: Canvas) {
                    super.onDraw(canvas)
                    // 화면에 그려질 내용 작성
                }
            }
    [주요 함수]
    - Canvas
        - drawLine(float startX, float startY, float stopX, float stopY, Paint paint): 선을 그림
        - drawRect(Rect rect, Paint paint): 사각형을 그림
        - drawRoundRect(RectF rect, float rx, float ry, Paint paint): 모서리가 둥근 사각형을 그림
        - drawCircle(float cx, float cy, float radius, Paint paint): 원을 그림
        - drawOval(RectF oval, Paint paint): 타원을 그림
        - drawPath(Path path, Paint paint): 여러 선을 연결한 도형을 그림
        - drawText(String text, float x, float y, Paint paint): 텍스트를 화면에 그림
        - drawPoint(float x, float y, Paint paint): 점을 그림
        - drawBitmap(Bitmap bitmap, float left, float top, Paint paint): 비트맵 이미지를 화면에 그림
        - drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint): 호를 그림

    - Paint
        - color: 색상 설정 (ex. paint.color = Color.RED)
        - strokeWidth: 선의 굵기 설정(기본값0)
        - style: 그리기 스타일 설정
            - FILL
            - STROKE
            - FILL_AND_STROKE
        - isAntiAlias: 안티앨리어싱(테두리 부드럽게)을 설정
            - true
            - false
        - textSize: 텍스트 크기 설정
        - setARGB(int a, int r, int g, int b): 색상을 ARGB 값으로 설정
        - setColor(int color): 색상 설정(color 변수 사용 가능)
        - setTextAlign(Paint.Align align): 텍스트 정렬
            - LEFT
            - CENTER
            - RIGHT
        - getTextBounds(Strings text, int start, int en, Rect bounds): 텍스트의 영역(Rect)를 가져옴
        - getTextWidth(String text): 텍스트의 가로 너비 반환(API 마다 다를 수 있어 getTextBounds 와 병행 활동
    [같이 자주 사용하는 클래스]
    - Rect: 사각형의 좌표의 크기 지정(int 값)
    - RectF: 사각형의 좌표와 크기 지정(float 값)
    - Path: 선을 이어 도형이나 경로를 그릴 때 사용

 */
class exam17 : AppCompatActivity() {

    // Activity가 실행될 때 호출되는 메소드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 화면에 우리가 정의한 MyGraphicView를 띄움
        setContentView(MyGraphicView(this))
    }

    // 그래픽을 그릴 View 클래스 정의 (익명 내부 클래스 형식)
    private class MyGraphicView(context: Context) : View(context) {

        // 실제 그래픽 요소를 그리는 메소드 (화면 갱신 시 자동 호출)
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            // 페인트 객체 생성 및 설정
            val paint = Paint()
            paint.isAntiAlias = true  // 안티앨리어싱 활성화 (도형의 경계선이 부드럽게 표현)

            // 선(Line) 그리기
            paint.color = Color.GREEN  // 색상 설정
            canvas.drawLine(10f, 10f, 300f, 10f, paint)  // (시작x, 시작y, 끝x, 끝y, 페인트)

            // 파란색 선 그리기 (굵기 설정)
            paint.color = Color.BLUE
            paint.strokeWidth = 5f  // 선 굵기
            canvas.drawLine(10f, 30f, 300f, 10f, paint)

            // 빨간색 사각형 (채우기 스타일)
            paint.color = Color.RED
            paint.strokeWidth = 0f  // 굵기 0 (채우기 스타일에선 필요없음)
            paint.style = Paint.Style.FILL  // 채우기 스타일
            val rect1 = Rect(10, 50, 10 + 100, 50 + 100)  // 사각형 좌표 (왼쪽, 위, 오른쪽, 아래)
            canvas.drawRect(rect1, paint)

            // 사각형 (테두리만)
            paint.style = Paint.Style.STROKE  // 테두리 스타일
            val rect2 = Rect(130, 50, 130 + 100, 50 + 100)
            canvas.drawRect(rect2, paint)

            // 모서리가 둥근 사각형
            var rect3 = RectF(250f, 50f, 250f + 100f, 50f + 100f)
            canvas.drawRoundRect(rect3, 20f, 20f, paint)  // (사각형 좌표, x반지름, y반지름, 페인트)

            // 원 그리기
            canvas.drawCircle(60f, 220f, 50f, paint)  // (중심x, 중심y, 반지름, 페인트)

            // 선분 연결하여 도형 그리기 (Path)
            paint.strokeWidth = 5f  // 선 굵기
            val path1 = Path()
            path1.moveTo(10f, 290f)  // 시작점
            path1.lineTo(10f + 50f, 290f + 50f)
            path1.lineTo(10f + 100f, 290f)
            path1.lineTo(10f + 150f, 290f + 50f)
            path1.lineTo(10f + 200f, 290f)
            canvas.drawPath(path1, paint)  // Path를 이용해 선 연결하여 도형 그리기

            // 텍스트 그리기
            paint.strokeWidth = 0f  // 굵기 초기화
            paint.textSize = 30f  // 글자 크기
            canvas.drawText("안드로이드", 10f, 290f, paint)  // (문자열, 시작x, 시작y, 페인트)
        }
    }
}