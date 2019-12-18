package com.ck.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentResult: Double = 0.0
    var chosenOperator = 0
    var operandOne: String = ""
    var operandTwo: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display.text = "0"
        setClickListeners()

    }

    private fun onDigit(number: String) {
        if(chosenOperator == 0) {
            operandOne += number
            showResult(operandOne)
        } else {
            operandTwo += number
            showResult(operandTwo)
        }
    }

    fun add(a: Double, b: Double): Double {
        return a + b
    }

    fun minus(a: Double, b: Double): Double {
        return a - b
    }

    fun divide(a: Double, b: Double): Double {
        return a / b
    }

    fun multiply(a: Double, b: Double): Double {
        return a * b
    }

    fun appendToDisplay(numberStr: String) {
        display.append(numberStr)
    }

    fun showResult(number: String){
        if(number == "0.0"){ display.text = "0" } else { display.text = number }
    }


    fun resetDisplay(){
        display.text = "0"
    }



    private fun setClickListeners(){
        listOf(button1, button2, button3,button4,button5,button6,button7,button8,button9,button0 ).forEach {button ->
            button.setOnClickListener { onDigit(button.text.toString()) }


        }
        listOf(buttonADD, buttonDIV, buttonSUB, buttonMUL).forEach {button ->
            button.setOnClickListener {
                chosenOperator = button.id
                appendToDisplay(button.text.toString())
            }
        }
        buttonC.setOnClickListener{
            chosenOperator = 0
            operandOne = ""
            operandTwo = ""
            resetDisplay()
        }

        buttonEQ.setOnClickListener {
            try {
                currentResult = when (chosenOperator) {
                    R.id.buttonADD -> add(operandOne.toDouble(), operandTwo.toDouble())
                    R.id.buttonSUB -> minus(operandOne.toDouble(), operandTwo.toDouble())
                    R.id.buttonMUL -> multiply(operandOne.toDouble(), operandTwo.toDouble())
                    R.id.buttonDIV -> divide(operandOne.toDouble(), operandTwo.toDouble())
                    else -> 0.0
                }
                operandOne = currentResult.toString()
                operandTwo = ""
                showResult(currentResult.toString())
            } catch (e: Exception){
                Log.d("Exception", " " + e.message)
            }
        }
    }


}
