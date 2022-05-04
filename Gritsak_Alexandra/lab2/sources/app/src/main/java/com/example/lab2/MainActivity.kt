package com.example.lab2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View


class MainActivity : AppCompatActivity() {

    private lateinit var tvInfo: TextView //переменные для полей ввода и кнопки
    private lateinit var etInput: EditText
    private lateinit var bControl: Button
    var guess = 0 //загаданное число
    var gameFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInfo = findViewById(R.id.textView);
        etInput = findViewById(R.id.editTextTextPersonName);
        bControl = findViewById(R.id.button);
        guess = (Math.random()*100).toInt(); //рандом числа
        gameFinished = false; //переменная окончания игры


    }
    fun onClick(v: View?){
        if (!gameFinished) { //если игра не закончена

                if (etInput.text.toString().isEmpty()) { //если строка пустая а кнопка нажата
                    tvInfo.text = resources.getString(R.string.error); //ошибка
                    return

                }
            var inp =
                Integer.parseInt(etInput.text.toString()); //считываем ввод в строку и переводим в число
            if (!(inp in 0..100)) {
                tvInfo.text = resources.getString(R.string.error);
            } else {
                if (inp > guess) //если бошльше загаданного
                    tvInfo.text = resources.getString(R.string.ahead);
                if (inp < guess) //если меньше загаданного
                    tvInfo.text = resources.getString(R.string.behind);
                if (inp == guess) //если угадал
                {
                    tvInfo.text = resources.getString(R.string.hit);
                    bControl.text = resources.getString(R.string.play_more);
                    gameFinished = true; //игра окончена
                }

            }
        }
        else //если игра окончена
        {
            guess = ((Math.random()*100).toInt()); //рандом нового числа
            bControl.text = resources.getString(R.string.input_value);
            tvInfo.text = resources.getString(R.string.try_to_guess);
            gameFinished = false;
        }
        etInput.text; //вывод исходного текста в строку ввода
    }
}

