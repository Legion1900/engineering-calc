package com.legion1900.engineeringcalculator.logic.calculator.base

interface Parser {
    fun toPostfix(exp: CharSequence): List<String>
}