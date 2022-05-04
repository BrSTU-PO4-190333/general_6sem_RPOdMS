package com.example.lab7;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.widget.*;
import androidx.core.view.GestureDetectorCompat;

public class MainActivity extends Activity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener
{
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    GestureDetectorCompat mDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView)findViewById(R.id.textView1);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);
        tv4 = (TextView)findViewById(R.id.textView4);
        tv5 = (TextView)findViewById(R.id.textView5);
        tv6 = (TextView)findViewById(R.id.textView6);
        tv7 = (TextView)findViewById(R.id.textView7);
        tv8 = (TextView)findViewById(R.id.textView8);
        tv9 = (TextView)findViewById(R.id.textView9);

        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
    }
    public boolean onTouchEvent(MotionEvent event){
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
        tv5.setText("");
        tv6.setText("");
        tv7.setText("");
        tv8.setText("");
        tv9.setText("");
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    @Override
    public boolean onDown(MotionEvent event) {
        tv1.setText("onDown: " + " -- отслеживает появление касания, т. е. палец прижат к экрану.");
        return false;
    }
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        tv2.setText("onFling: " + " -- отслеживает появление жеста смахивания.");
        return true;
    }
    @Override
    public void onLongPress(MotionEvent event) {
        tv3.setText("onLongPress: " + " -- отслеживает удерживание пальца прижатым к экрану длительное время.");
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        tv4.setText("onScroll: " + " -- отслеживает появление жеста прокрутки (пролистывания).");
        return true;
    }
    @Override
    public void onShowPress(MotionEvent event) {
        tv5.setText("onShowPress: " + " -- отслеживает, что произошло событие касания и больше никаких событий не происходит короткое время.");
    }
    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        tv6.setText("onSingleTapUp: " + " -- отслеживает появление жеста одиночного нажатия (клик).");
        return true;
    }
    @Override
    public boolean onDoubleTap(MotionEvent event) {
        tv7.setText("onDoubleTap: " + " -- отслеживает появление жеста двойного нажатия.");
        return true;
    }
    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        tv8.setText("onDoubleTapEvent: " + " -- отслеживает появление события во время выполнения жеста двойного нажатия.");
        return true;
    }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        tv9.setText("onSingleTapConfirmed: " + " -- отслеживает появление жеста одиночного нажатия (клик).");
        return true;
    }}