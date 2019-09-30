package com.legion1900.engineeringcalculator.view

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.legion1900.engineeringcalculator.R
import com.legion1900.engineeringcalculator.databinding.ActivityMainBinding
import com.legion1900.engineeringcalculator.view.adapters.KeyboardPagerAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.keyboard.adapter =
            KeyboardPagerAdapter(
                supportFragmentManager
            )
        initInputField()
    }

    fun onNumberClick(view: View) {
        with(binding) {
            etInput.text.append((view as Button).text)
        }
    }

    private fun initInputField() {
        with(binding) {
            /*
            * Adjusting font size.
            * */
            etInput.textSize = tvStory.textSize
            etInput.requestFocus()
//            etInput.setOnTouchListener { view, MotionEvent -> true }
            etInput.showSoftInputOnFocus = false
        }
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }
}
