package com.example.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var buttons: List<ImageButton> //список кнопок
    private lateinit var cards: List<CardStates> //список карточек
    private var indexOfSingleSelectedCard: Int? = null //изначально карточек нет
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = mutableListOf( //список картинок
            R.drawable.ic_baseline_grass,
            R.drawable.ic_life_surgeon,
            R.drawable.ic_ocean_danger,
            R.drawable.ic_pufferfish_puffer,
            R.drawable.ic_sea_reptile,
            R.drawable.ic_seafood_animal,
            R.drawable.ic_snail_slug,
            R.drawable.ic_underwater_seaweed
        )
        images.addAll(images) //добавляем копии чтобы создать пары
        images.shuffle()//перемешиваем список
        buttons = listOf(
            imageButton1,
            imageButton2,
            imageButton3,
            imageButton4,
            imageButton5,
            imageButton6,
            imageButton7,
            imageButton8,
            imageButton9,
            imageButton10,
            imageButton11,
            imageButton12,
            imageButton13,
            imageButton14,
            imageButton15,
            imageButton16
        )
        cards = buttons.indices.map { index -> //индексирование кнопок и создания словаря из карточек и индексов соотв. кнопок
            CardStates(images[index]) //создаем карточки на сонове картинок и индексов
        }
        buttons.forEachIndexed { index, button -> //цикл по кнопкам
            button.setOnClickListener { //при нажатии кнопок
                Log.i(TAG, "button clicked!") //логирование нажатия
                updateModels(index) //обновление модели
                updateViews() //обновление интерфейса игры
            }
        }
        button.setOnClickListener {
            images.shuffle()//перемешиваем список
            cards = buttons.indices.map { index ->//индексирование кнопок и создания словаря из карточек и индексов соотв. кнопок
                CardStates(images[index])
            }
            cards.forEachIndexed { index, card -> //цикл по карточкам
                val button = buttons[index] //иниц. кнопки
                button.alpha = 1.0f //придание яркости
                card.faceUp = false //карточка не перевернута
                card.matched = false //карточка не совпала со своей парой
                button.setImageResource(if(card.faceUp)card.id else R.drawable.ic_vector) //если карточка была открыта то назначаем ей картинку иначе рубашка
            }
        }
    }
    private fun updateViews() { //обновление интерфейса
        cards.forEachIndexed { index, card -> //цикл по карточкам
            val button = buttons[index] //иниц кнопки
            if (card.matched){ //если карточка совпала то снизить яркость ее и ее пары
                button.alpha = 0.1f
            }
            button.setImageResource(if(card.faceUp)card.id else R.drawable.ic_vector) //если карточка была открыта то назначаем ей картинку иначе рубашка
        }

    }

    private fun updateModels(position: Int) { //обновление модели
        val card = cards[position] //инициализация карточки
        if (card.faceUp){ //если карточка перевернута в начле игры
            Toast.makeText(this,"Ошибка!", Toast.LENGTH_SHORT).show() //ошибка при нажатии
            return
        }
        if ( indexOfSingleSelectedCard == null){ //если одна карточка
            restoreCards() //перевернуть карточки в случае несовпадения
            indexOfSingleSelectedCard = position //иниц позиции
        }else{
            checkForMactch(indexOfSingleSelectedCard!!,position) //иначе проверить две карточки на совпадение
            indexOfSingleSelectedCard = null
        }
        card.faceUp = !card.faceUp // перевернуть карточки лицом
    }

    private fun restoreCards() {
        for (card in cards){ //цикл по карточкам
            if (!card.matched){ //если карточка не совпала
                card.faceUp = false //то перевернуть ее обратно
            }
        }
    }

    private fun checkForMactch(position1: Int,position2: Int) { //проверка совпадения
        if(cards[position1].id==cards[position2].id){ //если ид двух карточек сопадают
            Toast.makeText(this,"Совпадение найдено!", Toast.LENGTH_SHORT).show() //то пара найдена
            cards[position1].matched = true //переменные совпадения становятся true
            cards[position2].matched = true
        }
    }
}