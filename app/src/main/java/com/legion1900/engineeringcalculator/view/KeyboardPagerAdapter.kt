package com.legion1900.engineeringcalculator.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.legion1900.engineeringcalculator.view.fragments.BaseKeyboardFragment
import com.legion1900.engineeringcalculator.view.fragments.ExtendedKeyboardFragment

class KeyboardPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> BaseKeyboardFragment()
            else -> ExtendedKeyboardFragment()
        }
    }

    override fun getCount(): Int = 2
}