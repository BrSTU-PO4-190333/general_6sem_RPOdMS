package com.example.lab8;

import android.os.Bundle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnGesturePerformedListener {

    private TextView mResults;
    private GestureLibrary mGestureLibrary;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResults = (TextView) findViewById(R.id.results);

        mGestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!mGestureLibrary.load()) finish();

        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.draw_gestures);
        gestures.addOnGesturePerformedListener(this);
    }

    public void onGesturePerformed(GestureOverlayView overlay,Gesture gesture) {
        ArrayList <Prediction> predictions = mGestureLibrary.recognize(gesture);
        String predictList = "";
        NumberFormat formatter = new DecimalFormat("#0.00");
        for(int i=0; i<predictions.size(); i++) {
            Prediction prediction = predictions.get(i);
            predictList = predictList + prediction.name + " "
                    + formatter.format(prediction.score) + "\n";
        }
        mResults.setText(predictList);
    }
}