package io.github.Pavel_Innokentevich_Galanin.gpi_rpodms_lab8;

//import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends Activity implements OnGesturePerformedListener {
    private TextView tvOut;
    private GestureLibrary gLib;
    private GestureOverlayView gestures;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = (TextView) findViewById(R.id.textView_result);

        // Загрузка жестов из app/res/raw/gestures.txt
        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);

        // Если жесты не загружены, то выход из приложения
        if (!gLib.load()) finish();

        gestures = findViewById(R.id.gestureOverlayView);
        gestures.addOnGesturePerformedListener((GestureOverlayView.OnGesturePerformedListener) this);
    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        //Создаёт ArrayList c загруженными из gestures жестами
        ArrayList<Prediction> predictions = gLib.recognize(gesture);

        if (predictions.size() == 0) {
            tvOut.setText("Нет жестов");
            return;
        }

        // Берём первый элемент жеста (у него будет максимальный коэффициент предсказания)
        Prediction prediction = predictions.get(0);

        // Если предсказание больше 1
        if (prediction.score > 2.0) {

            String result = "Предсказания:\n";
            NumberFormat formatter = new DecimalFormat("#0.00");
            for(int i = 0; i < predictions.size(); ++i) {
                Prediction prediction_item = predictions.get(i);
                result += prediction_item.name + " ~ ";
                result += formatter.format(prediction_item.score) + "\n";
            }
            tvOut.setText(result);
            return;
        }

        String result = "Малый процент предсказания!!!\n\nПредсказания:\n";
        NumberFormat formatter = new DecimalFormat("#0.00");
        for(int i = 0; i < predictions.size(); ++i) {
            Prediction prediction_item = predictions.get(i);
            result += prediction_item.name + " ~ ";
            result += formatter.format(prediction_item.score) + "\n";
        }
        tvOut.setText(result);
    }
}