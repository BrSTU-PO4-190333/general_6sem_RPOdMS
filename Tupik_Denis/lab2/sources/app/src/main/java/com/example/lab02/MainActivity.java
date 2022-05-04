package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
    private TextView textRool;
    private EditText userNumberFromEditText;
    private Button guessBtn;
    private Button exitBtn;
    private int guessNumber;
    private int userNumber;
    private boolean gameOver;
    private int numberOfAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textRool = (TextView)findViewById(R.id.textRool);
        this.userNumberFromEditText = (EditText)findViewById(R.id.userNumber);
        this.guessBtn = (Button)findViewById(R.id.guessBtn);

        this.guessNumber =  (int)(Math.random()*100);
        this.gameOver = false;
        this.numberOfAttempts = 0;
    }

    public void onClick(View v){
        if(!isGameOver()){
            if(isEmpty()){
                textRool.setText(getResources().getString(R.string.empty));
                return;
            }

            String userNumberString = this.userNumberFromEditText.getText().toString().trim();
            char[] chars = userNumberString.toCharArray();

            for (char character : chars) {
                if (Character.isDigit(character)){
                    this.userNumber = Integer.parseInt(userNumberString);
                } else if (character == '-'){
                    continue;
                } else {
                    this.textRool.setText(getResources().getString(R.string.letters));
                    this.userNumberFromEditText.setText("");
                    return;
                }
            }

            if (isUserNumberInBorders()) {
                if (isUserNumberMore()) {
                    this.numberOfAttempts++;
                    this.textRool.setText(getResources().getString(R.string.more));
                }

                if (isUserNumberLess()) {
                    this.numberOfAttempts++;
                    this.textRool.setText(getResources().getString(R.string.less));
                }

                if (isUserNumberRight()) {
                    this.numberOfAttempts++;
                    this.textRool.setText(getResources().getString(R.string.right, this.numberOfAttempts));
                    this.guessBtn.setText(getResources().getString(R.string.again));
                    this.gameOver = true;
                }
            } else {
                this.textRool.setText(getResources().getString(R.string.border));
            }

        } else {
            this.textRool.setText(getResources().getString(R.string.enter_text));
            this.guessBtn.setText(getResources().getString(R.string.enter));

            this.guessNumber =  (int)(Math.random()*100);
            this.gameOver = false;
            this.numberOfAttempts = 0;
        }

        this.userNumberFromEditText.setText("");
    }

    private boolean isGameOver(){
        return this.gameOver;
    }

    private boolean isEmpty(){
        return this.userNumberFromEditText.getText().toString().trim().isEmpty();
    }

    private boolean isUserNumberInBorders(){
        return this.userNumber >= 1 && this.userNumber <= 100;
    }

    private boolean isUserNumberMore(){
        return this.userNumber > this.guessNumber;
    }

    private boolean isUserNumberLess(){
        return this.userNumber < this.guessNumber;
    }

    private boolean isUserNumberRight(){
        return this.userNumber == this.guessNumber;
    }
}