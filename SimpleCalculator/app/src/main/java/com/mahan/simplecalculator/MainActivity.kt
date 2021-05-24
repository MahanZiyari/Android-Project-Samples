package com.mahan.simplecalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mahan.simplecalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    var firstNumber: Double? = null
    var secondNumber: Double? = null
    var operator: String? = null
    var result = ""

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backspaceButton.setOnLongClickListener { backSpaceButtonOnLongClick(it) }
    }

    private fun backSpaceButtonOnLongClick(view: View): Boolean {
        binding.expressionEdittext.text?.clear()
        return false
    }

    fun numberButtonOnclick(view: View) {
        val button = view as Button
        addNumberToExpression(button.text as String)
    }

    private fun addNumberToExpression(text: String) {
        val numberToApeand = text.toIntOrNull()
        binding.expressionEdittext.text?.append((numberToApeand ?: ".").toString())
    }

    fun equalButtonOnClick(view: View){
        secondNumber = binding.expressionEdittext.text.toString().toDoubleOrNull()
        println("$firstNumber  $operator  $secondNumber")
        if (firstNumber != null && secondNumber != null && operator != null){
            result = calculate()
            setValuesToNull()
            // set numbers and operator to null
            binding.outlinedTextField.helperText = result
            binding.expressionEdittext.text?.clear()
        }
    }

    fun setValuesToNull(){
        firstNumber = null
        secondNumber = null
        operator = null
    }

    fun backSpaceOnClick(view: View): Unit {
        val text = binding.expressionEdittext.editableText ?: null
        if (!text.isNullOrEmpty() || !text.isNullOrBlank()) {
            binding.expressionEdittext.setText(text?.substring(0 until text.lastIndex))
        }
    }

    private fun calculate(): String {
        return when(operator){
            "+" -> (firstNumber!! + secondNumber!!).toString()
            "-" -> (firstNumber!! - secondNumber!!).toString()
            "/" -> (firstNumber!! / secondNumber!!).toString()
            "*" -> (firstNumber!! * secondNumber!!).toString()
            else -> ""
        }
    }

    fun operatorButtonOnClick(view: View) {
        // getting user input
        val inputedNumber = getUserInput(binding.expressionEdittext.text.toString())

        // assigning entered number if it was valid
        assignOperands(inputedNumber)

        // choosing correct operation
        operator = getOperator(view as Button).takeIf { it != null }

        // clear edittext
        binding.expressionEdittext.setText("")

    }

    private fun assignOperands(inputedNumber: Double?) {
        if (firstNumber == null)
            firstNumber = inputedNumber.takeIf { it != null }
        else if (secondNumber == null)
            secondNumber = inputedNumber.takeIf { it != null }
    }

    fun getOperator(button: Button): String? {
        return when(button.id){
            binding.plusButton.id -> "+"
            binding.minusButton.id -> "-"
            binding.multiplyButton.id -> "*"
            binding.divideButton.id -> "/"
            else -> null
        }
    }

    fun getUserInput(exp: String): Double? {
        if (exp.isNullOrBlank() || exp.isNullOrEmpty())
            return null

        return exp.toDoubleOrNull()
    }
}