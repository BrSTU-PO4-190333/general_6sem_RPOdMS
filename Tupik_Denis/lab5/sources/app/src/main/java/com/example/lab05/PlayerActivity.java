package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {
    private TextView playerInfo;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playerInfo = findViewById(R.id.playerInfo);
        Bundle arguments = getIntent().getExtras();

        if(isArgumentsNotEmpty(arguments)){
            String rank = arguments.get("rank").toString();
            String username = arguments.get("username").toString();
            String rating = arguments.get("rating").toString();
            String score = arguments.get("score").toString();

            playerInfo.setText("Rank: " + rank + "\nUsername: " + username + "\nRating: " + rating + "\nScore: " + score);
        }
    }

    private boolean isArgumentsNotEmpty(Bundle arguments){
        return arguments != null;
    }
}