package com.example.lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.lab6.R.string.*



class MainActivity : AppCompatActivity() {

    private lateinit var menuButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menuButton = findViewById(R.id.button)
        menuButton.setOnClickListener {
            val list: Array<String> = arrayOf(getText(drinks).toString(), getText(salads).toString(),
                getText(desserts).toString())
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.message)
            builder.setItems(list) { _, which ->
                val selected = list[which]
                val inet = Intent(this, Dishes::class.java)
                when (selected){
                    "Напитки" -> {
                        inet.putExtra(Dishes.MENU, "Drinks")
                    }
                    "Салаты" -> {
                        inet.putExtra(Dishes.MENU, "Salads")
                    }
                    "Десерты" -> {
                        inet.putExtra(Dishes.MENU, "Desserts")
                    }
                }
                startActivity(inet)
            }
            var dialog = builder.create()
            dialog.show()
        }
    }
}