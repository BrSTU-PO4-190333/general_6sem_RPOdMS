package io.github.Pavel_Innokentevich_Galanin.gpi_rpodms6_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int guess;
    boolean gameFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView) findViewById(R.id.textView1);
        etInput = (EditText) findViewById(R.id.editTextNumber1);
        bControl = (Button) findViewById(R.id.button1);
        guess = (int) (Math.random()*100);
        gameFinished = false;
        etInput.setText("50");
    }

    public void onClick(View v) {
        if(!gameFinished) {
            int inp = Integer.parseInt(etInput.getText().toString());
            if (inp > guess) {
                tvInfo.setText(getResources().getString(R.string.ahead));
            }
            if (inp < guess) {
                tvInfo.setText(getResources().getString(R.string.behind));
            }
            if (inp == guess) {
                tvInfo.setText(getResources().getString(R.string.hit));
                bControl.setText(getResources().getString(R.string.play_more));
                gameFinished = true;
            }
        }
        else
        {
            guess = (int) (Math.random() * 100);
            bControl.setText(getResources().getString(R.string.input_value));
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            gameFinished = false;
            etInput.setText("50");
        }
    }
}