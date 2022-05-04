package com.example.lab6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.lab6.ui.main.PlaceholderFragment

class Dishes : AppCompatActivity() {
    companion object {
        var MENU = "1"
    }

    private lateinit var arrayAdapter : ArrayAdapter<String>
    //private var continent = "None"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val listview = findViewById<ListView>(R.id.listView)
        val names1 = arrayOf("Кофе", "Чай", "Сок")
        val names2 = arrayOf("Цезарь", "Венеция", "Подсолнух")
        val names3 = arrayOf("Чизкейк", "Штрудель", "Брауни")
        MENU = intent.getStringExtra(MENU)!!

        when(MENU) {

            "Drinks" -> {
                arrayAdapter = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, names1
                )
            }
            "Salads" -> {
                arrayAdapter = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, names2
                )
            }
            "Desserts" -> {
                arrayAdapter = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1,
                )
            }
        }

        listview.adapter = arrayAdapter
        listview.setOnItemClickListener { adapterView, view, i, l ->

            val inet = Intent(this, DishPhotos::class.java)
            when(i) {
                0 -> {
                    inet.putExtra(PlaceholderFragment.CASE, "0")
                }
                1 -> {
                    inet.putExtra(PlaceholderFragment.CASE, "1")
                }
                2 -> {
                    inet.putExtra(PlaceholderFragment.CASE, "2")
                }
            }
            inet.putExtra(PlaceholderFragment.DISH, MENU)
            startActivity(inet)
        }
    }
}