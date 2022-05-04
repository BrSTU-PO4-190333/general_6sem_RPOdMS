package com.example.lab99;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}
//

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
    public void toVideo(View view){
        Intent intent = new Intent(this, video.class);
        startActivity(intent);
    }
    public void toAudio(View view){
        Intent intent = new Intent(this, Audio.class);
        startActivity(intent);
    }
    public void toCamera(View view){
        Intent intent = new Intent(this, Camera.class);
        startActivity(intent);
    }
    public void toGallery(View view){
        Intent intent = new Intent(this, gallery.class);
        startActivity(intent);
    }

}