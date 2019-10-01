package com.legion1900.engineeringcalculator.viewmodels.controller.base

import android.text.Editable
import android.widget.EditText

abstract class EditTextCalculatorPrinter(private val editText: EditText) : CalculatorPrinter {
    protected val carriagePosition: Int
        get() = editText.selectionStart

    protected val text: Editable
        get() = editText.text

    protected val previous: String?
        get() = text.getOrNull(carriagePosition - 1)?.toString()

    /*
    * Move carriage forward.
    * */
    fun moveCarriageFwd() {
        if (carriagePosition < text.length)
            editText.setSelection(carriagePosition + 1)
    }

    fun moveCarriageBkwd() {
        if (carriagePosition > 0)
            editText.setSelection(carriagePosition - 1)
    }

    override fun clear() = text.clear()

    protected fun isPrevious(symbol: Char) : Boolean {
        if (carriagePosition == 0) return false
        return text[carriagePosition - 1] == symbol
    }

    protected fun replacePrevious(value: String) {
        text.replace(carriagePosition - 1, carriagePosition, value)
    }

    protected fun append(symbol: String) {
        text.append(symbol)
        editText.setSelection(carriagePosition + symbol.length)
    }

    protected fun append(symbol: String, carriageShift: Int) {
        text.append(symbol)
        editText.setSelection(carriagePosition + carriageShift)
    }
}