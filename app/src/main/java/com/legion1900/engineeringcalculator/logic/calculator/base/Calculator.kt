package com.legion1900.engineeringcalculator.logic.calculator.base

import java.math.BigDecimal

abstract class Calculator(
    protected val formatter: Formatter,
    protected val parser: Parser,
    protected val evaluator: Evaluator
) {
    fun calculate(input: CharSequence): BigDecimal {
        val exp = formatter.format(input)
        val postfix = parser.toPostfix(exp)
        return evaluator.evaluate(postfix)
    }
}