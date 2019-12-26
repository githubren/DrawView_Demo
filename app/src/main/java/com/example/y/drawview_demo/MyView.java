package com.example.y.drawview_demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MyView extends View {
    private Paint mPaint;
    private Path mPath;
    private Canvas mCanvas;
    private Bitmap mBitmap;

    private int mLastX;
    private int mLastY;

    private int[] values;
    private int sum = 0;
    private int curSum = 0;
    private List<Integer> colors = new ArrayList<>();

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setColor(getResources().getColor(R.color.color_black));
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);//结合处为圆角
//        mPaint.setStrokeCap(Paint.Cap.ROUND);//转弯处为圆角
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.color_yellow));
        canvas.drawCircle(150,200,100,mPaint);

        canvas.drawRect(300,100,600,300,mPaint);

//        drawArc(canvas, R.color.color_red, 0, 120);
//        drawArc(canvas, R.color.color_green, 120, 240);
//        drawArc(canvas, R.color.color_blue, 0, -120);

//        float sweepAngle = 360*1.0f/sum;
//        for (int i = 0; i < values.length; i++) {
//            if (i == 0) {
//                curSum = values[0];
//                drawArc(canvas, colors.get(0), 0, sweepAngle);
//            } else if (i == values.length - 1) {
//                curSum = sum - curSum;
//                drawArc(canvas, colors.get(i), 0, -sweepAngle);
//            } else{
//                curSum += values[i];
//                drawArc(canvas, colors.get(i), ((curSum-values[i]) * 1.0f / sum) * 360, sweepAngle);
//            }
//        }

        drawPath();
        canvas.drawBitmap(mBitmap,0,0,null);
    }

    private void drawPath() {
        mCanvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX,mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);
                if (dx > 3 || dy > 3)
                    mPath.lineTo(x,y);
                mLastX = x;
                mLastY = y;
                break;
        }
        postInvalidate();
        return true;
    }

    private void drawArc(Canvas canvas, int paintColor, float startAngle, float sweepAngle) {
        mPaint.setColor(getResources().getColor(paintColor));
        canvas.drawArc(650, 100, 850, 300, startAngle, sweepAngle, true, mPaint);
    }


    public void setData(int... values){
        this.values = values;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        colors.add(R.color.color_red);
        colors.add(R.color.color_green);
        colors.add(R.color.color_blue);
        colors.add(R.color.color_pink);
        colors.add(R.color.color_orange);
        colors.add(R.color.color_gray);
        colors.add(R.color.color_purple);
        colors.add(R.color.color_light_blue);
        colors.add(R.color.color_white);
        colors.add(R.color.color_black);
        postInvalidate();
    }
}
