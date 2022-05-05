package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityFive extends AppCompatActivity implements View.OnClickListener  {
    Button backbutton;
    TextView textView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        textView = (TextView) findViewById(R.id.textView4);
        textView.setText("\tSwift — открытый мультипарадигмальный компилируемый язык программирования общего назначения. " +
                "Создан компанией Apple в первую очередь для разработчиков iOS и macOS. " +
                "\n\tSwift задумывался как более лёгкий для чтения и устойчивый к ошибкам программиста язык, нежели предшествовавший ему Objective-C. " +
                "Программы на Swift компилируются при помощи LLVM, входящей в интегрированную среду разработки Xcode 6 и выше. " +
                "Swift может использовать рантайм Objective-C, что делает возможным использование обоих языков в рамках одной программы.");
        textView.setTextColor(Color.BLACK);
        backbutton = (Button) findViewById(R.id.back);
        backbutton.setOnClickListener(this);
    }

    public void onClick(View v) {
        intent = new Intent(this, MainActivity.class);
        switch (v.getId()) {
            case R.id.back:
                AlertDialog aboutDialog = new AlertDialog.Builder(
                        ActivityFive.this).setMessage("Вы хотите вернуться на главную страницу?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(intent);
                            }
                        }).create();
                aboutDialog.show();
                break;
            default:
                break;
        }
    }
}