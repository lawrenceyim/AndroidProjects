package com.example.fizzbuzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeText(view: View) {
        var editText: EditText = findViewById(R.id.editTextNumber)
        var description: TextView = findViewById(R.id.description_text)
        var image: ImageView = findViewById(R.id.imageView2)
        var num = editText.text.toString().toInt()
        var result = fizzBuzz(num)
        description.text = result
        when(result) {
            "Fizz" -> {
                image.setImageResource(R.drawable.fizz)
            }
            "Buzz" -> {
                image.setImageResource(R.drawable.bees)
            }
            "FizzBuzz" -> {
                image.setImageResource(R.drawable.fizzbuzz)
            }
            else -> image.setImageResource(R.drawable.sad)
        }
    }

    private fun fizzBuzz(num: Int): String {
        when {
            num % 15 == 0 -> return "FizzBuzz"
            num % 5 == 0 -> return "Buzz"
            num % 3 == 0 -> return "Fizz"
            else -> return num.toString()
        }
    }
}