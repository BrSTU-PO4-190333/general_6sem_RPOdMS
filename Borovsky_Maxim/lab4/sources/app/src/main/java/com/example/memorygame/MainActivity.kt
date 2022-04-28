package com.example.memorygame

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import kotlin.math.round
import com.example.memorygame.R.drawable.*
import com.example.memorygame.R.id.*

class MainActivity : AppCompatActivity() {


    val img: MutableList<Int> = mutableListOf(number1, number2, number3,
        number4, number5, number6, number7, number8, number1, number2,
        number3, number4, number5, number6, number7, number8)

    val btn = listOf(button1, button2, button3, button4, button5, button6,
        button7, button8, button9, button10, button11, button12, button13,
        button14, button15, button16)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img.shuffle()

        var fChose: Boolean = false
        var notMatch: Boolean = false


        var fImg: Int = -1
        var sImg: Int = -1


        for(i in 0..15){
            val button: ImageButton = findViewById(btn[i])

            button.setOnClickListener{
                if(notMatch) {
                    findViewById<ImageButton>(btn[fImg]).setBackgroundResource(nonimage)
                    findViewById<ImageButton>(btn[sImg]).setBackgroundResource(nonimage)
                    notMatch = false
                }

                if(!fChose) {
                    button.setBackgroundResource(img[i])
                    fChose = true
                    fImg = i
                }
                else if(img[i] != img[fImg] && i != fImg) {
                    button.setBackgroundResource(img[i])
                    sImg = i
                    notMatch = true
                    fChose = false
                }
                else if(img[i] == img[fImg] && i != fImg) {
                    button.setBackgroundResource(img[i])
                    button.isClickable = false
                    findViewById<ImageButton>(btn[fImg]).isClickable = false
                    fChose = false
                }
            }
        }
    }
    fun restartGame(view: View) {
        for (i in 0..15) {
            val button: ImageButton = findViewById(btn[i])
            button.setBackgroundResource(nonimage)
            button.isClickable = true
        }
        img.shuffle()
    }
}
