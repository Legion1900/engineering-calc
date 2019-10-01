package com.legion1900.engineeringcalculator.viewmodels.controller.base

interface CalculatorPrinter {
    fun printNumber(num: String)

    fun printOperator(op: String)

    fun printFunc(func: String)

    fun printSpecial(symbol: String)

    fun backspace()

    fun clear()
}