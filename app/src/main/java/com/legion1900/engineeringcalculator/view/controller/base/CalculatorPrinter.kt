package com.legion1900.engineeringcalculator.view.controller.base

interface CalculatorPrinter {
    fun printNumber(num: CharSequence)

    fun printOperator(op: CharSequence)

    fun printFunc(func: String)

    fun printSpecial(symbol: String)

    fun backspace()

    fun clear()
}