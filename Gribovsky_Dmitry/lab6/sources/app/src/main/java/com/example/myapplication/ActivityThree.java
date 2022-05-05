package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityThree extends AppCompatActivity implements View.OnClickListener {

    Button backbutton;

    TextView textView;

    Intent intent;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_three);

        textView = (TextView) findViewById(R.id.textView2);

        textView.setText("\tC++ — компилируемый, статически типизированный язык программирования общего назначения.\n" +"\n\tПоддерживает такие парадигмы программирования, как процедурное программирование, объектно-ориентированное программирование, обобщённое программирование. " +"\tC++ сочетает свойства как высокоуровневых, " + "так и низкоуровневых языков. В сравнении с его предшественником — языком C — наибольшее внимание уделено поддержке объектно-ориентированного и обобщённого программирования.");

        textView.setTextColor(Color.BLACK);

        backbutton = (Button) findViewById(R.id.back);

        backbutton.setOnClickListener(this);

    }

    public void onClick(View v) {

        intent = new Intent(this, MainActivity.class);

        switch (v.getId()) {

            case R.id.back:

                AlertDialog aboutDialog = new AlertDialog.Builder( ActivityThree.this)

                        .setMessage("Вы хотите вернуться на главную страницу?")

                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                startActivity(intent);

                            }

                        }).create();

                aboutDialog.show();

                break;

            default: break;

        }

    }

}