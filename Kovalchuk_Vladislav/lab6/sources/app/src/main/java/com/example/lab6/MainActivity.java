package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickKurili(View view)
    {
        Intent intent = new Intent(this, Kurili.class);
        startActivity(intent);
    }
    public void onClickKanari(View view)
    {
        Intent intent = new Intent(this, kanari.class);
        startActivity(intent);
    }
    public void onClickFilipines(View view)
    {
        Intent intent = new Intent(this, Filipines.class);
        startActivity(intent);
    }
    public void onClickMaldives(View view)
    {
        Intent intent = new Intent(this, Maldives.class);
        startActivity(intent);
    }
}