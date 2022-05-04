package com.example.lab07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class SomeActivity extends AppCompatActivity {
    private GestureDetectorCompat mDetector;
    private TextView some;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some);

        some = findViewById(R.id.some);
        mDetector=new GestureDetectorCompat(this, new MyGestListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestListener extends GestureDetector.SimpleOnGestureListener {
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            some.setText("onFling: " + event1.toString()+event2.toString());
            return true;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onLongPress(MotionEvent event) {
            some.setText("onLongPress: " + event.toString());
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onShowPress(MotionEvent event) {
            some.setText("onShowPress: " + event.toString());
        }
    }
}