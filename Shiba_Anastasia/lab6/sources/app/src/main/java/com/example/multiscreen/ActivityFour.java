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

public class ActivityFour extends AppCompatActivity implements View.OnClickListener  {
    Button backbutton;
    TextView textView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        textView = (TextView) findViewById(R.id.textView3);
        textView.setText("\tJava — строго типизированный объектно-ориентированный язык программирования общего назначения, разработанный компанией Sun Microsystems (в последующем приобретённой компанией Oracle). " +
                "Разработка ведётся сообществом, организованным через Java Community Process; язык и основные реализующие его технологии распространяются по лицензии GPL.\n" +
                "\tПриложения Java обычно транслируются в специальный байт-код, поэтому они могут работать на любой компьютерной архитектуре, для которой существует реализация виртуальной Java-машины. ");
        textView.setTextColor(Color.BLACK);
        backbutton = (Button) findViewById(R.id.back);
        backbutton.setOnClickListener(this);
    }

    public void onClick(View v) {
        intent = new Intent(this, MainActivity.class);
        switch (v.getId()) {
            case R.id.back:
                AlertDialog aboutDialog = new AlertDialog.Builder(
                        ActivityFour.this).setMessage("Вы хотите вернуться на главную страницу?")
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