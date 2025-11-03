package com.example.caculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    
    private lateinit var tvDisplay: TextView
    private var currentNumber = ""
    private var operator = ""
    private var firstNumber = 0
    private var isNewOperation = true
    private var displayText = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        tvDisplay = findViewById(R.id.tvDisplay)
        currentNumber = "0"
        tvDisplay.text = currentNumber
        
        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)
        
        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnMul).setOnClickListener(this)
        findViewById<Button>(R.id.btnDiv).setOnClickListener(this)
        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)
        
        findViewById<Button>(R.id.btnCE).setOnClickListener(this)
        findViewById<Button>(R.id.btnC).setOnClickListener(this)
        findViewById<Button>(R.id.btnBS).setOnClickListener(this)
        findViewById<Button>(R.id.btnSign).setOnClickListener(this)
    }
    
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn0 -> addDigit("0")
            R.id.btn1 -> addDigit("1")
            R.id.btn2 -> addDigit("2")
            R.id.btn3 -> addDigit("3")
            R.id.btn4 -> addDigit("4")
            R.id.btn5 -> addDigit("5")
            R.id.btn6 -> addDigit("6")
            R.id.btn7 -> addDigit("7")
            R.id.btn8 -> addDigit("8")
            R.id.btn9 -> addDigit("9")
            
            R.id.btnAdd -> setOperator("+")
            R.id.btnSub -> setOperator("-")
            R.id.btnMul -> setOperator("x")
            R.id.btnDiv -> setOperator("/")
            
            R.id.btnEqual -> calculate()
            R.id.btnCE -> clearEntry()
            R.id.btnC -> clearAll()
            R.id.btnBS -> backspace()
            R.id.btnSign -> changeSign()
        }
    }
    
    private fun addDigit(digit: String) {
        if (isNewOperation) {
            currentNumber = digit
            isNewOperation = false
        } else {
            if (currentNumber == "0") {
                currentNumber = digit
            } else {
                currentNumber += digit
            }
        }
        if (operator.isNotEmpty() && displayText.isNotEmpty()) {
            tvDisplay.text = displayText + currentNumber
        } else {
            tvDisplay.text = currentNumber
        }
    }
    
    private fun setOperator(op: String) {
        if (currentNumber.isNotEmpty() && currentNumber != "") {
            if (operator.isNotEmpty() && !isNewOperation) {
                calculate()
                firstNumber = currentNumber.toInt()
            } else {
                firstNumber = currentNumber.toInt()
            }
            operator = op
            displayText = "$firstNumber $operator "
            tvDisplay.text = displayText
            isNewOperation = true
        }
    }
    
    private fun calculate() {
        if (operator.isNotEmpty() && currentNumber.isNotEmpty() && !isNewOperation) {
            val secondNumber = currentNumber.toInt()
            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "x" -> firstNumber * secondNumber
                "/" -> if (secondNumber != 0) firstNumber / secondNumber else 0
                else -> 0
            }
            currentNumber = result.toString()
            tvDisplay.text = currentNumber
            operator = ""
            displayText = ""
            firstNumber = 0
            isNewOperation = true
        }
    }
    
    private fun clearEntry() {
        currentNumber = "0"
        if (operator.isNotEmpty()) {
            tvDisplay.text = displayText + currentNumber
        } else {
            tvDisplay.text = currentNumber
        }
        isNewOperation = false
    }
    
    private fun clearAll() {
        currentNumber = "0"
        firstNumber = 0
        operator = ""
        displayText = ""
        tvDisplay.text = currentNumber
        isNewOperation = true
    }
    
    private fun backspace() {
        if (currentNumber.isNotEmpty() && currentNumber != "0") {
            currentNumber = currentNumber.dropLast(1)
            if (currentNumber.isEmpty() || currentNumber == "-") {
                currentNumber = "0"
            }
            if (operator.isNotEmpty() && displayText.isNotEmpty()) {
                tvDisplay.text = displayText + currentNumber
            } else {
                tvDisplay.text = currentNumber
            }
        }
    }
    
    private fun changeSign() {
        if (currentNumber.isNotEmpty() && currentNumber != "0") {
            if (currentNumber.startsWith("-")) {
                currentNumber = currentNumber.substring(1)
            } else {
                currentNumber = "-$currentNumber"
            }
            if (operator.isNotEmpty() && displayText.isNotEmpty()) {
                tvDisplay.text = displayText + currentNumber
            } else {
                tvDisplay.text = currentNumber
            }
        }
    }
}