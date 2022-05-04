package com.example.lab3

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import kotlin.math.round
import com.example.lab3.R.drawable.*
import com.example.lab3.R.id.*

class MainActivity : AppCompatActivity() {

    var score: Double = 0.0
    var rounds: Double = 0.0

    val images: MutableList<Int> = mutableListOf(first,second,third, fourth,five, six, seven, eigth,
        first,second,third, fourth,five, six, seven, eigth)

    val buttons = listOf(button1, button2, button3, button4, button5, button6, button7, button8,
        button9, button10, button11, button12, button13, button14, button15, button16)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        images.shuffle()

        var firstChosen: Boolean = false
        var firstImage: Int = -1
        var secondImage: Int = -1
        var notMatch: Boolean = false

        for(i in 0..15){
            val button: ImageButton = findViewById(buttons[i])

            button.setOnClickListener{
                if(notMatch) {
                    findViewById<ImageButton>(buttons[firstImage]).setBackgroundResource(ximage)
                    findViewById<ImageButton>(buttons[secondImage]).setBackgroundResource(ximage)
                    notMatch = false
                    rounds += 0.05
                }

                if(!firstChosen) {
                    button.setBackgroundResource(images[i])
                    firstChosen = true
                    firstImage = i
                }
                else if(images[i] != images[firstImage] && i != firstImage) {
                    button.setBackgroundResource(images[i])
                    secondImage = i
                    notMatch = true
                    firstChosen = false
                }
                else if(images[i] == images[firstImage] && i != firstImage) {
                    button.setBackgroundResource(images[i])
                    button.isClickable = false
                    findViewById<ImageButton>(buttons[firstImage]).isClickable = false
                    firstChosen = false
                    changeScore(1 - rounds)
                }
            }
        }
    }
    fun changeScore(_score: Double, is_absolute:Boolean = false) {
        var ScoreView: TextView = findViewById(textView2)
        if (!is_absolute) {
            score += _score
            if(score > 0)
                ScoreView.setText("Score: " + round(score * 100)/100)
            else
                ScoreView.setText("Score: 0.0")
        }
        else{
            score = _score
            ScoreView.setText("Score: " + score)
        }
    }
    fun restartGame(view: View) {
        for (i in 0..15) {
            val button: ImageButton = findViewById(buttons[i])
            button.setBackgroundResource(ximage)
            button.isClickable = true
        }
        rounds = 0.0
        changeScore(0.0, true)
        images.shuffle()
    }
}
