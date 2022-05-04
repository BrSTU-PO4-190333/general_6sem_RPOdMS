package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.lab2.R.string.*
import java.lang.Exception
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var randNum : Int = Random.nextInt(0, 100)
        var button : Button = findViewById(R.id.button)
        var editText : EditText = findViewById(R.id.editText1)
        var textView : TextView = findViewById(R.id.textView2)

        button.setOnClickListener{
            try{
                var guess : Int = editText.text.toString().toInt()
                if(guess > randNum)
                    textView.setText(ahead)
                else if(guess < randNum)
                    textView.setText(behind)
                else if(guess == randNum)
                {
                    textView.setText(hit)
                    randNum = Random.nextInt(0, 100)
                }
            }
            catch(e: Exception){
                textView.setText(error)
            }
        }
    }
}