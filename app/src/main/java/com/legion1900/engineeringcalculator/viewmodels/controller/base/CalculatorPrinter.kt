package com.legion1900.engineeringcalculator.viewmodels.controller.base

interface CalculatorPrinter {
    fun printNumber(symbol: String)

    fun printOperator(symbol: String)

    fun printFunc(symbol: String)

    fun printSpecial(symbol: String)

    fun backspace()

    fun clear()
}