package com.example.gesturelab8;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;

public class MainActivity extends Activity implements OnGesturePerformedListener {
    GestureLibrary gestureLib;
    //GestureOverlayView для распознавания жестов
    GestureOverlayView gestures;
    TextView tv1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView)findViewById(R.id.textView1);
        //Инициалихация gestureLib жестами,
        //загруженными из файла gestures папки res/raw/.
        //Оператор if выполняет проверку, загружены ли жесты, и если нет, то выполняется выход из приложения.
        gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gestureLib.load()) {
            finish();
        }
        gestures = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);
        gestures.addOnGesturePerformedListener(this);
    }

    //вызывается при появлении события, соответствующего какому-либо жесту
    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        //Создаёт ArrayList c загруженными из gestures жестами
        ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
        if (predictions.size() > 0) {
            Prediction prediction = predictions.get(0);
            //если загружен хотя бы один жест из gestures
            if (prediction.score > 1.0) {
                if (prediction.name.equals("check")) {
                    tv1.setText("check");
                } else if (prediction.name.equals("cat")) {
                    tv1.setText("cat");
                } else if (prediction.name.equals("circle")) {
                    tv1.setText("circle");
                }
            } else {
                tv1.setText("Жест неизвестен");
            }
        }
       /* for (Prediction prediction : predictions) {
            if (prediction.score > 1.0) {
                Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
            }
        }*/
    }
}