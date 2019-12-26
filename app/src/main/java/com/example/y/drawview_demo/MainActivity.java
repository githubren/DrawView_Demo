package com.example.y.drawview_demo;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyView myView = findViewById(R.id.my_view);
//        myView.setData(1,1,1,1,1,1);

        PieChartView pieChartView = findViewById(R.id.pie_view);
        pieChartView.setData(1,1,1,1,1,1);

        pieChartView.setPivotX(750);
        pieChartView.setPivotY(200);
//        pieChartView.setPivotX(0);
//        pieChartView.setPivotY(0);
        ObjectAnimator animator = ObjectAnimator.ofFloat(pieChartView,"rotation",360);
        animator.setDuration(1000);
        animator.setRepeatCount(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }
}
