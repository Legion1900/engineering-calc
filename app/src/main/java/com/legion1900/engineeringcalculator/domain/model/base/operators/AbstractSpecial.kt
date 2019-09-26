package com.legion1900.engineeringcalculator.domain.model.base.operators

import java.math.BigDecimal

/*
* Abstract class for representing special operators (parentheses, comma separators, etc.)
* */
class AbstractSpecial(symbol: String) :
    AbstractOperator(0, symbol, Precedence.Special.ordinal) {
    override fun execute(vararg args: BigDecimal): BigDecimal =
        throw UnsupportedOperationException()
}