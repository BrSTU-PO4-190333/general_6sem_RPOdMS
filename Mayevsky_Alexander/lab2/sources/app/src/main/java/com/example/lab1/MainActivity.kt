package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private var tvInfo: TextView? = null
    private var etInput: EditText? = null
    private var bControl: Button? = null
    var guess = 0
    var gameFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInfo = findViewById(R.id.textView2)
        etInput = findViewById(R.id.editTextNumber)
        bControl = findViewById(R.id.button)
        guess = ((Math.random()*100).roundToInt())
    }

    fun onClick(view: View) {

        if (!gameFinished) {
            var text = etInput?.text?.toString()

            if(text.equals(""))
            {
                tvInfo?.text = resources.getString(R.string.emptyError)
                return
            }

            var inp = Integer.parseInt(text)
            if (inp < 0 || inp > 100){
                tvInfo?.text = resources.getString(R.string.rangeError)
                return
            }

            if (inp > guess)
                tvInfo?.text = resources.getString(R.string.ahead)

            if (inp < guess)
                tvInfo?.text = resources.getString(R.string.behind)

            if (inp == guess)
            {
                tvInfo?.text = resources.getString(R.string.hit)
                bControl?.text = resources.getString(R.string.play_more)
                gameFinished = true
            }}
        else
        {
            guess = (Math.random()*100).roundToInt()
            bControl?.text = resources.getString(R.string.input_value)
            tvInfo?.text = resources.getString(R.string.try_to_guess)
            gameFinished = false
        }

        etInput?.setText("")
    }
}