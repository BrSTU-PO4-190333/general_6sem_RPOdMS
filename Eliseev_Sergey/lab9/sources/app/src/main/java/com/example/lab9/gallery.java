package com.example.lab9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class gallery extends AppCompatActivity {

    private Integer[] pictures = {R.drawable.one, R.drawable.two, R.drawable.three};
    private int curIndex = 0;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        iv = (ImageView)findViewById(R.id.imageView1);
        iv.setImageResource(pictures[curIndex]);
    }
    public void previous(View view){
        if(curIndex == 0){
            curIndex = pictures.length - 1;
            iv.setImageResource(pictures[curIndex]);
        }
        else{
            curIndex--;
            iv.setImageResource(pictures[curIndex]);
        }
    }
    public void next(View view){
        if(curIndex == pictures.length-1){
            curIndex = 0;
            iv.setImageResource(pictures[curIndex]);
        }
        else{
            curIndex++;
            iv.setImageResource(pictures[curIndex]);
        }
    }
}