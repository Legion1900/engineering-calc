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

    private var openScopeCnt = 0

    private val operators = Operators.map

    override fun printNumber(num: CharSequence) {
        if (
            previous?.isOperator(Operators.ParenthesesRight) == false
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
//        TODO: sign do not swap dot
//        TODO: forbid paste operations on input field (any except copy)
        if (
            (op.isOperator(Operators.Subtraction) && text.isEmpty())
            || (op.isOperator(Operators.Subtraction)
                    && (previous?.isOperator(Operators.ParenthesesLeft) == true))
            || (previous?.isOperand == true)
            || (previous?.isOperator(Operators.ParenthesesRight) == true)
        ) append(op)
        /*
        * If previous symbol dot => replace
        * */
        else if (previous?.equals(DOT) == true) replacePrevious(op)
    }

    /*
    * func should contain full signature of function:
    * 1) name
    * 2) left and right scope
    * 3) appropriate number of comma separators
    * */
    override fun printFunc(func: CharSequence) {
        /*
        * Carriage should be set after opening scope
        * */
        val shiftCursorBy = func.indexOf(SCOPE_L) + 1
        append(func, shiftCursorBy)
    }

    /*
    * Specials: dot, (, ), constants (e, pi, etc.)
    * */
//    TODO: constant handling
    override fun printSpecial(symbol: CharSequence) {
        if (symbol == SCOPE_L) printLeftScope()
        else if (symbol == SCOPE_R) printRightScope()
        else if (symbol == DOT) printDot()
    }

    override fun backspace() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*
    * Can be printed if:
    * text is empty
    * or previous symbol is operator (except right parentheses).
    * */
    private fun printLeftScope() {
        if (
            text.isEmpty()
            || (previous?.isOperand == false
                    && previous?.isOperator(Operators.ParenthesesRight) == false)
                ) {
            append(SCOPE_L)
            openScopeCnt++
        }
    }

    private fun printRightScope() {
        if (openScopeCnt == 0) return
        if (
            previous?.isOperand == true
            || previous?.isOperator(Operators.ParenthesesRight) == true
                ) {
            append(SCOPE_R)
            openScopeCnt--
        }
    }

    private fun printDot() {
        /*
        * Searching dot.
        * */
        var isDot = searchForDot(true)
        if (!isDot) isDot = searchForDot(false)
        /*
        * If no dot found and previous symbol is number => append.
        * */
        if (!isDot && (previous?.isOperand == true))
            append(DOT)
    }

    private fun searchForDot(isFwd: Boolean): Boolean {
        var isDot = false
        var i = carriagePosition - 1
        while (i in text.indices && !isDot) {
            val symbol = text[i].toString()
            if (symbol == DOT) isDot = true
            if (operators.containsKey(symbol)) break
            if (isFwd) i++
            else i--
        }
        return isDot
    }
}