package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private TextView txt;
    private GestureDetectorCompat gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.text1);
        gd = new GestureDetectorCompat(this, this);
        gd.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent (MotionEvent event){
        gd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        txt.setText("Вы коснулись: 1 раз");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        txt.setText("Вы коснулись: 2 раза");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        txt.setText("Вы проскролили");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        txt.setText("Долгое нажатие");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}