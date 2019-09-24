package com.legion1900.engineeringcalculator.domain.model.base.operators

import java.math.BigDecimal

/*
* Interface that represents unary operator.
* */
abstract class BinOperator(denotation: String, precedence: Precedence):
    Operator(2, denotation, precedence) {

    override fun execute(vararg args: BigDecimal): BigDecimal = execute(args[0], args[1])

    abstract fun execute(left: BigDecimal, right: BigDecimal): BigDecimal
}