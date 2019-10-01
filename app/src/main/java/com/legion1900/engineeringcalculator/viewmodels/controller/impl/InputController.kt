package com.legion1900.engineeringcalculator.viewmodels.controller.impl

import android.widget.EditText
import com.legion1900.engineeringcalculator.domain.model.impl.operators.Operators
import com.legion1900.engineeringcalculator.domain.model.impl.operators.isOperator
import com.legion1900.engineeringcalculator.viewmodels.controller.base.EditTextCalculatorPrinter

const val ZERO = "0"
const val SCOPE_L = "("
const val SCOPE_R = ")"
const val DOT = "."

class InputController(editText: EditText, private val operators: Set<String>) :
    EditTextCalculatorPrinter(editText) {

    /*
    * State flag that indicates current input type.
    * */
    private var isNumber = false
        set(value) {
            if (!value) isDotPresent = value
            field = value
        }

    /*
    * State flag that indicates dot presence.
    * APPLICABLE ONLY FOR NUMBERS.
    * */
    private var isDotPresent = false

    private var openedScopeCnt = 0

    override fun printNumber(num: String) {
        if (num == ZERO) printZero()
        else if (isNumber) {
            append(num)
            isNumber = true
        }

    }

    /*
    * Operators:
    * 1) if previous op is number or text is empty => append
    * 2) if previous op is left scope and current op is minus => append
    * 3) if previous op is dot or operator => replace
    * */
    override fun printOperator(op: String) {
        if (isNumber || carriagePosition == 0) append(op)
        else if (isPrevious('(') && op.isOperator(Operators.Subtraction)) append(op)
        else if (isPrevious('.') || operators.contains(previous)) replacePrevious(op)
        isNumber = false
    }

    /*
    * func value should contain:
    * 1) function name
    * 2) left scope
    * 3) appropriate number of comma separators
    * 4) right scope
    * */
    override fun printFunc(func: String) {
        if (carriagePosition == 0 || isPrevious('(') || operators.contains(previous)) {
            val shiftCarriageBy = func.indexOf('(') + 1
            append(func, shiftCarriageBy)
        }
        isNumber = false
    }

    override fun printSpecial(symbol: String) {
        /*
        * If symbol is dot and current input is number without dot => append it
        * */
        if (symbol == DOT && isNumber && !isDotPresent) {
            append(symbol)
            isDotPresent = true
        }
        /*
        * If symbol is left scope and previous one is scope, operator, or nothing => append it
        * and increase opened scope counter.
        * */
        else if (symbol == SCOPE_L) {
            /*
            * If text is empty || previous is operator or left scope => append
            * */
            if (carriagePosition == 0 || operators.contains(previous) || previous == SCOPE_L) {
                append(symbol)
                openedScopeCnt++
                isNumber = false
            }
        }
        /*
        * If symbol is right scope and there are opened scope => append symbol
        * and decrease opened scope counter.
        * */
        else if (symbol == SCOPE_R && openedScopeCnt > 0) {
            append(symbol)
            openedScopeCnt--
            isNumber = false
        }
        /*
        * Constants (e, pi, etc.) can be added after operator, left scope, or if test is empty
        * */
        else if (operators.contains(previous)){
            append(symbol)
            isNumber = false
        }
    }

    override fun backspace() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    protected fun printZero() {

    }
}