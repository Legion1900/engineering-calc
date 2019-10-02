package com.legion1900.engineeringcalculator.logic.operators.impl

import com.legion1900.engineeringcalculator.logic.operators.base.AbstractOperator
import com.legion1900.engineeringcalculator.logic.operators.base.Precedence
import java.math.BigDecimal

/*
* Abstract class for representing special operators (parentheses, comma separators, etc.)
* */
class Special(symbol: String) :
    AbstractOperator(0, symbol, Precedence.Special.ordinal) {
    override fun execute(vararg args: BigDecimal): BigDecimal =
        throw UnsupportedOperationException()
}

class Constant(private val value: BigDecimal, symbol: String) :
    AbstractOperator(0, symbol, Precedence.Function.ordinal) {
    override fun execute(vararg args: BigDecimal): BigDecimal = value

}