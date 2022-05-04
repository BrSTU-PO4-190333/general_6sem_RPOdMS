package com.example.lab07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class AllActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private TextView all;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        all = findViewById(R.id.all);
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
    }

    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //GestureDetector.OnDoubleTapListener
    @SuppressLint("SetTextI18n")
    @Override
    public boolean onDoubleTap(MotionEvent event) {
        all.setText("onDoubleTap: " + event.toString());
        return true;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        all.setText("onDoubleTapEvent: " + event.toString());
        return true;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        all.setText("onSingleTapConfirmed: " + event.toString());
        return true;
    }

    //GestureDetector.OnGestureListener
    @SuppressLint("SetTextI18n")
    @Override
    public boolean onDown(MotionEvent event) {
        all.setText("onDown: " + event.toString());
        return false;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        all.setText("onFling: " + event1.toString()+event2.toString());
        return true;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onLongPress(MotionEvent event) {
        all.setText("onLongPress: " + event.toString());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        all.setText("onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onShowPress(MotionEvent event) {
        all.setText("onShowPress: " + event.toString());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        all.setText("onSingleTapUp: " + event.toString());
        return true;
    }
}