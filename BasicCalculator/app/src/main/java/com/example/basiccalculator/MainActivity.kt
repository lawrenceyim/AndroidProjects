package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var input: TextView? = null
    private var output: TextView? = null
    private var a: Int? = null
    private var last: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.expression_text)
        output = findViewById(R.id.result_text)
    }

    fun onDigit(view: View) {
        input?.append((view as Button).text)
    }

    fun onClear(view: View) {
        input?.text = null
        output?.text = null
        a = null
    }

    fun onSolve(view: View) {
        output?.text = calculateResults(view)
    }

    fun calculateResults(view: View): String {
        val digitOperators = digitsOperators()
        if (digitOperators.isEmpty()) return ""
        val timesDivision = timesDivisionsCalculate(digitOperators)
        if (timesDivision.isEmpty()) return ""
        val result = addSubstractCalculate(timesDivision)
        return result.toString()
    }

    private fun addSubstractCalculate(passedList: MutableList<Any>): Float {
        var result = passedList[0] as Float
        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex) {
                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Float
                if (operator == '+') {
                    result += nextDigit
                }
                if (operator == '-') {
                    result -= nextDigit
                }
            }
        }
        return result
    }

    private fun timesDivisionsCalculate(passedList: MutableList<Any>): MutableList<Any> {
          var list = passedList
        while (list.contains('*') || list.contains('/')) {
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size
        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Float
                val nextDigit = passedList[i + 1] as Float
                when(operator) {
                    '*' -> {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' -> {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else -> {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }
            if (i > restartIndex) {
                newList.add(passedList[i])
            }
        }
        return newList
    }

    private fun digitsOperators(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""
        input?.text?.forEach {
            if (it.isDigit()) {
                currentDigit += it
            } else {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(it)
            }
        }
        if (currentDigit != "") {
            list.add(currentDigit.toFloat())
        }
        return list
    }
}