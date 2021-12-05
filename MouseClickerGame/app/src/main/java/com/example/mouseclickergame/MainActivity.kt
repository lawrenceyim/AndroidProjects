package com.example.mouseclickergame

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var clicker = Clicker(0)

        var clicksText: TextView = findViewById(R.id.clicked_counter_text)
        var mouseButton: ImageView = findViewById(R.id.imageView)

        mouseButton.setOnClickListener {
            clicker.clicks++
            clicksText.text = clicker.clicks.toString()
        }
    }


}