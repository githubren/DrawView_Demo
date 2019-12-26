package com.example.y.drawview_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PieChartView extends View {
    private Paint mPaint;
    private int[] values;
    private int sum = 0;
    private int curSum = 0;
    private List<Integer> colors = new ArrayList<>();

    public PieChartView(Context context) {
        super(context);
        init();
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PieChartView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.color_black));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float sweepAngle = 360*1.0f/sum;
        for (int i = 0; i < values.length; i++) {
            if (i == 0) {
                curSum = values[0];
                drawArc(canvas, colors.get(0), 0, sweepAngle);
            } else if (i == values.length - 1) {
                curSum = sum - curSum;
                drawArc(canvas, colors.get(i), 0, -sweepAngle);
            } else{
                curSum += values[i];
                drawArc(canvas, colors.get(i), ((curSum-values[i]) * 1.0f / sum) * 360, sweepAngle);
            }
        }
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
