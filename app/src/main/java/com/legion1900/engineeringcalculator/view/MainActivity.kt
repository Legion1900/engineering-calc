package com.legion1900.engineeringcalculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.legion1900.engineeringcalculator.R
import com.legion1900.engineeringcalculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.keyboard.adapter = KeyboardPagerAdapter(supportFragmentManager)
        adjustEditTextSize()
    }

    private fun adjustEditTextSize() {
        with(binding) {
            etInput.textSize = tvStory.textSize
        }
    }
}
