package com.legion1900.engineeringcalculator.view.controller.base

import android.text.Editable
import android.util.Log
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

    protected fun replacePrevious(value: CharSequence) {
        if (carriagePosition != 0)
            text.replace(carriagePosition - 1, carriagePosition, value)
    }

    protected fun append(symbol: CharSequence) {
        text.insert(carriagePosition, symbol)
    }

    protected fun append(symbol: CharSequence, carriageShift: Int) {
        val oldPos = carriagePosition
        text.insert(carriagePosition, symbol)
        editText.setSelection(oldPos + carriageShift)
    }
}