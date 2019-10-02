package com.legion1900.engineeringcalculator.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.legion1900.engineeringcalculator.R
import com.legion1900.engineeringcalculator.databinding.ActivityMainBinding
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

    fun moveCarriage(view: View) {
        if (view.id == R.id.move_carriage_right)
            printer.moveCarriageFwd()
        else printer.moveCarriageBkwd()
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
