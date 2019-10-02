package com.legion1900.engineeringcalculator.view.controller.impl

import android.widget.EditText
import com.legion1900.engineeringcalculator.logic.operators.impl.Operators
import com.legion1900.engineeringcalculator.logic.operators.impl.isOperand
import com.legion1900.engineeringcalculator.logic.operators.impl.isOperator
import com.legion1900.engineeringcalculator.view.controller.base.EditTextCalculatorPrinter

const val SCOPE_L = "("
const val SCOPE_R = ")"
const val DOT = "."

class InputController(editText: EditText) :
    EditTextCalculatorPrinter(editText) {

    private val isPreviousLetter: Boolean
        get() = isLetter(previous?.get(0))

    private val isPrevOperand: Boolean
        get() = previous?.isOperand == true

    private val isPrevRightScope: Boolean
        get() = previous?.isOperator(Operators.ParenthesesRight) == true

    private var openScopeCnt = 0

    private val operators = Operators.map

    override fun printNumber(num: CharSequence) {
        if (isPreviousLetter) return
        val isPrevNotRightScope: Boolean = previous?.isOperator(Operators.ParenthesesRight) == false
        if (isPrevNotRightScope || isPrevOperand || carriagePosition == 0) append(num)
    }

    override fun printOperator(op: CharSequence) {
        /*
        * If text is empty and our op is subtraction
        * || previous symbol is left scope and op is subtraction
        * || previous symbol is number
        * || previous symbol is right scope
        * => append
        * */
        if (isPreviousLetter) {
            append(op)
            return
        }
        val isUnaryMinus: Boolean = (op.isOperator(Operators.Subtraction) && text.isEmpty())
                || (op.isOperator(Operators.Subtraction)
                && (previous?.isOperator(Operators.ParenthesesLeft) == true))
        if (isUnaryMinus || isPrevOperand || isPrevRightScope) append(op)
        /*
        * If previous symbol dot => replace
        * */
//        else if (previous?.equals(DOT) == true) replacePrevious(op)
    }

    /*
    * func should contain full signature of function:
    * 1) name
    * 2) left and right scope
    * 3) appropriate number of comma separators
    * */
    override fun printFunc(func: CharSequence) {
        if (isPreviousLetter) return
        /*
        * Functions can only be printed if:
        * carriage at 0
        * or previous symbol was operator (but not right scope)
        * */
        val isPrevOperator = !isPrevOperand
        val isPrevNotRightScope = !isPrevRightScope
        val prevCondition: Boolean = isPrevOperator && isPrevNotRightScope
        if (carriagePosition == 0 || prevCondition) {
            /*
            * Carriage should be set after opening scope
            * */
            val shiftCursorBy = func.indexOf(SCOPE_L) + 1
            append(func, shiftCursorBy)
        }
    }

    /*
    * Specials: dot, (, ), constants (e, pi, etc.)
    * */
    override fun printSpecial(symbol: CharSequence) {
        when (symbol) {
            SCOPE_L -> printLeftScope()
            SCOPE_R -> printRightScope()
            DOT -> printDot()
            /*
            * Constants
            * */
            else -> printConst(symbol)
        }
    }

    override fun backspace() {
        /*
        * If previous is number, dot or operator
        * */
        if (!isPreviousLetter) {
            val isPrevLeftScope: Boolean = previous?.isOperator(Operators.ParenthesesLeft) == true
            if (isPrevRightScope) {
                openScopeCnt++
                super.backspace()
            } else if (isPrevLeftScope) {
                /*
                * If previous is usual scope
                * */
                if (!isLetter(text[carriagePosition - 2])) {
                    openScopeCnt--
                    super.backspace()
                }
                /*
                * If scope is a part of function call
                * */
                else {
                    var start = carriagePosition - 2
                    while (start > 0 && isLetter(text[start])) start--
                    var end = carriagePosition + 1
                    while (end != text.length && text[end - 1] != SCOPE_R[0]) end++
                    text.delete(start, end)
                }
            } else super.backspace()
        }
        /*
        * If constant is met
        * */
        else {
            var start = carriagePosition - 2
            while (start > 0 && isLetter(text[start])) start--
            text.delete(start, carriagePosition)
        }
    }

    /*
    * Can be printed if:
    * carriage at 0
    * or previous symbol is operator (except right parentheses).
    * */
    private fun printLeftScope() {
        val prevCondition: Boolean = !isPrevOperand && !isPrevRightScope
        if (carriagePosition == 0 || prevCondition) {
            append(SCOPE_L)
            openScopeCnt++
        }
    }

    private fun printRightScope() {
        if (openScopeCnt == 0) return
        val prevCondition: Boolean = isPrevOperand || isPrevRightScope
        if (prevCondition) {
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

    private fun printConst(symbol: CharSequence) {
        if (isPreviousLetter || isPrevRightScope || isPrevOperand) return
        append(symbol)
    }

    private fun isLetter(symbol: Char?) = symbol in 'a'..'z'
}