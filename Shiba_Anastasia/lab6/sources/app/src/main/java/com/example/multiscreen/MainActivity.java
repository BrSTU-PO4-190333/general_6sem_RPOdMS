package com.example.multiscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener  {
    ImageButton btnActTwo;
    ImageButton btnActThree;
    ImageButton btnActFour;
    ImageButton btnActFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActTwo = (ImageButton) findViewById(R.id.btnActTwo);
        btnActTwo.setOnClickListener(this);

        btnActThree = (ImageButton) findViewById(R.id.btnActThree);
        btnActThree.setOnClickListener(this);

        btnActFour = (ImageButton) findViewById(R.id.btnActFour);
        btnActFour.setOnClickListener(this);

        btnActFive = (ImageButton) findViewById(R.id.btnActFive);
        btnActFive.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnActTwo:
                Toast.makeText(MainActivity.this, R.string.read, Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ActivityTwo.class);
                startActivity(intent);
                break;
            case R.id.btnActThree:
                Toast.makeText(MainActivity.this, R.string.read, Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ActivityThree.class);
                startActivity(intent);
                break;
            case R.id.btnActFour:
                Toast.makeText(MainActivity.this, R.string.read, Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ActivityFour.class);
                startActivity(intent);
                break;
            case R.id.btnActFive:
                Toast.makeText(MainActivity.this, R.string.read, Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ActivityFive.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}