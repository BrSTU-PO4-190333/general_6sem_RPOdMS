package com.example.lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    companion object {
        var EMAIL = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        EMAIL = intent.getStringExtra(EMAIL)!!
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = EMAIL
    }
}