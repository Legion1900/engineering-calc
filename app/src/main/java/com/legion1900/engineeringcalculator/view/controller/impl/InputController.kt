package com.legion1900.engineeringcalculator.view.controller.impl

import android.widget.EditText
import com.legion1900.engineeringcalculator.domain.model.impl.operators.Operators
import com.legion1900.engineeringcalculator.domain.model.impl.operators.isOperand
import com.legion1900.engineeringcalculator.domain.model.impl.operators.isOperator
import com.legion1900.engineeringcalculator.view.controller.base.EditTextCalculatorPrinter

const val ZERO = "0"
const val SCOPE_L = "("
const val SCOPE_R = ")"
const val DOT = "."

class InputController(editText: EditText) :
    EditTextCalculatorPrinter(editText) {

    private var isDotPresent = false

    private val operators = Operators.map

    override fun printNumber(num: CharSequence) {
        if (
            previous?.isOperator(Operators.ParenthesesLeft) == true
            || previous?.isOperand == true
            || text.isEmpty()
        ) append(num)
    }

    override fun printOperator(op: CharSequence) {
        /*
        * If text is empty and our op is subtraction
        * || previous symbol is left scope and op is subtraction
        * || previous symbol is number
        * || previous symbol is right scope
        * => append
        * */
        if (
            (op.isOperator(Operators.Subtraction) && text.isEmpty())
            || (op.isOperator(Operators.Subtraction)
                    && (previous?.isOperator(Operators.ParenthesesLeft) == true))
            || (previous?.isOperand == true)
            || (previous?.isOperator(Operators.ParenthesesRight) == true)
        ) append(op)
        /*
        * If previous symbol is operator or dot => replace
        * */
        else if (previous?.isOperand == false || previous?.equals(DOT) == true) replacePrevious(op)
    }

    override fun printFunc(func: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun printSpecial(symbol: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun backspace() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}