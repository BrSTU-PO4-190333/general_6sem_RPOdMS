package com.example.myapplication;

import android.app.AlertDialog;

import android.content.DialogInterface;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.graphics.Color;

import android.view.View;

import android.widget.Button;

import android.widget.TextView;

public class ActivityFour extends AppCompatActivity implements View.OnClickListener {

    Button backbutton;

    TextView textView;

    Intent intent;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_two);

        backbutton = (Button) findViewById(R.id.back);

        backbutton.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.textView1);

        textView.setText("\tActivity Four.");

        textView.setTextColor(Color.BLACK);

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

            default: break;

        }

    }

}