package io.github.Pavel_Innokentevich_Galanin.gpi_rpodms6_lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    TextView tvOutput;
    GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOutput = (TextView)findViewById(R.id.textView);
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
    }

    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    // отслеживает появление жеста одиночного нажатия (клик).
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        tvOutput.setText("onSingleTapConfirmed: " + motionEvent.toString());
        return false;
    }

    // отслеживает появление жеста двойного нажатия ("двойной клик");
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        tvOutput.setText("onDoubleTap: " + motionEvent.toString());
        return false;
    }

    // отслеживает появление события во время выполнения жеста двойного нажатия, включая касание, перемещение, подъем пальца.
    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        tvOutput.setText("onDoubleTapEvent: " + motionEvent.toString());
        return false;
    }

    // отслеживает появление касания, т. е. палец прижат к экрану;
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        tvOutput.setText("onDown: " + motionEvent.toString());
        return false;
    }

    // отслеживает, что произошло событие касания и больше никаких событий не происходит короткое время;
    @Override
    public void onShowPress(MotionEvent motionEvent) {
        tvOutput.setText("onShowPress: " + motionEvent.toString());
    }

    // отслеживает появление жеста одиночного нажатия (клик).
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        tvOutput.setText("onSingleTapUp: " + motionEvent.toString());
        return false;
    }

    // отслеживает появление жеста прокрутки (пролистывания);
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        tvOutput.setText("onScroll: " + motionEvent.toString() + motionEvent1.toString());
        return false;
    }

    // отслеживает удерживание пальца прижатым к экрану длительное время
    @Override
    public void onLongPress(MotionEvent motionEvent) {
        tvOutput.setText("onLongPress: " + motionEvent.toString());
    }

    // отслеживает появление жеста смахивания
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        tvOutput.setText("onFling: " + motionEvent.toString() + motionEvent1.toString());
        return false;
    }
}