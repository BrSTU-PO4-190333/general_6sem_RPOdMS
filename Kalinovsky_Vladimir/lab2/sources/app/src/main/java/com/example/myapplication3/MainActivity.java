package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int number;
    boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tvInfo = (TextView)findViewById(R.id.textView);
        etInput = (EditText)findViewById(R.id.editTextNumber);
        bControl = (Button)findViewById(R.id.button);
        check = true;
        number = (int)(1 + Math.random()*101);
    }

    public void onClick(View v){
        if (check){
            if(etInput.length() == 0) {
                tvInfo.setText(getResources().getString(R.string.error));
                check = true;
            } else {
                int inputNum = Integer.parseInt(etInput.getText().toString());
                if(inputNum >= 0 && inputNum <=100) {
                    if (inputNum > number)
                        tvInfo.setText(getResources().getString(R.string.ahead));
                    if (inputNum < number)
                        tvInfo.setText(getResources().getString(R.string.behind));
                    if (inputNum == number) {
                        tvInfo.setText(getResources().getString(R.string.hit));
                        bControl.setText(getResources().getString(R.string.play_more));
                        check = false;
                    }
                } else {
                    tvInfo.setText(getResources().getString(R.string.error));
                    check = true;
                }
            }
        }
        else
        {
            number = (int)(1 + Math.random()*101);
            bControl.setText(getResources().getString(R.string.input_value));
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            check = true;
        }
    }
}