package com.legion1900.engineeringcalculator.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.legion1900.engineeringcalculator.R
import com.legion1900.engineeringcalculator.databinding.ActivityMainBinding
import com.legion1900.engineeringcalculator.domain.model.impl.operators.Operators
import com.legion1900.engineeringcalculator.view.adapters.KeyboardPagerAdapter
import com.legion1900.engineeringcalculator.view.controller.base.EditTextCalculatorPrinter
import com.legion1900.engineeringcalculator.view.controller.impl.InputController

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var printer: EditTextCalculatorPrinter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.keyboard.adapter =
            KeyboardPagerAdapter(
                supportFragmentManager
            )
        initInputField()

        printer = InputController(binding.etInput)
    }

    fun onNumClick(view: View) {
        printer.printNumber((view as Button).text)
    }

    fun onOpClick(view: View) {
        printer.printOperator((view as Button).text)
    }

    fun onSpecialClick(view: View) {
        printer.printSpecial((view as Button).text)
    }

    fun onArrowClick(view: View) {
        if (view.id == R.id.move_carriage_right)
            printer.moveCarriageFwd()
        else printer.moveCarriageBkwd()
    }

    fun onFuncClick(view: View) {
        val signature = when (view.id) {
            R.id.abs -> buildFuncSignature(Operators.Absolute)
            R.id.pow -> buildFuncSignature(Operators.Power)
            R.id.sqrt -> buildFuncSignature(Operators.SquareRoot)
            R.id.log -> buildFuncSignature(Operators.Log)
            R.id.ln -> buildFuncSignature(Operators.LogN)
            R.id.sin -> buildFuncSignature(Operators.Sinus)
            R.id.cos -> buildFuncSignature(Operators.Cosine)
            R.id.tan -> buildFuncSignature(Operators.Tangent)
            else -> throw IllegalStateException("Cannot be executed for non-function")
        }
        printer.printFunc(signature)
    }

    fun onConstClick(view: View) {
        val symbol = when (view.id) {
            R.id.e -> Operators.E.denotation
            R.id.pi -> Operators.Pi.denotation
            else -> throw IllegalStateException("Cannot be executed for non-constant")
        }
        printer.printSpecial(symbol)
    }

    fun onBackspaceClick(view: View) {
        printer.backspace()
    }

    private fun buildFuncSignature(func: Operators): String {
        val builder = StringBuilder(func.denotation)
        builder.append('(')
        var i = 0
        /*
        * There is arity - 1 comma separators in function signature
        * */
        while (i < func.arity - 1) {
            builder.append(',')
            i++
        }
        builder.append(')')
        return builder.toString()
    }

    private fun initInputField() {
        with(binding) {
            /*
            * Adjusting font size.
            * */
            etInput.textSize = tvStory.textSize
            etInput.requestFocus()
            etInput.setOnTouchListener { view, MotionEvent -> true }
//            etInput.showSoftInputOnFocus = false
        }
    }
}
