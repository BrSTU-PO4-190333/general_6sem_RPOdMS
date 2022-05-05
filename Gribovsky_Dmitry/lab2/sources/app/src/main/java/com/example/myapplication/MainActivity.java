package com.example.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int guess;
    boolean gameFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView)findViewById(R.id.textView1);
        etInput = (EditText)findViewById(R.id.editText1);
        bControl = (Button)findViewById(R.id.button1);
        guess = (int)(Math.random()*100);
        gameFinished = false;
    }

    public void onClick(View v) {
        if (!gameFinished) {
            if (etInput.getText().length() > 0) {
                int inp = Integer.parseInt(etInput.getText().toString());

                if (inp > guess)
                    tvInfo.setText(getResources().getString(R.string.ahead));
                if (inp < guess)
                    tvInfo.setText(getResources().getString(R.string.behind));
                if (inp == guess) {
                    tvInfo.setText(getResources().getString(R.string.hit));
                    bControl.setText(getResources().getString(R.string.play_more));
                    gameFinished = true;
                }
                if (inp == 0) {
                    tvInfo.setText(getResources().getString(R.string.error_noinput));
                }

                if (inp < 1 && inp != 0)
                    tvInfo.setText(getResources().getString(R.string.error_less_1));
                if (inp > 100)
                    tvInfo.setText(getResources().getString(R.string.error_over_100));
            } else {
                tvInfo.setText(getResources().getString(R.string.error_noinput));
            }
        } else {
            guess = (int) (Math.random() * 100);
            bControl.setText(getResources().getString(R.string.input_value));
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            gameFinished = false;
        }
        etInput.setText("");

    }
}