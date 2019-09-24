package com.legion1900.engineeringcalculator.domain.model.base.operators

import java.math.BigDecimal

/*
* Interface that represents unary operator.
* */
abstract class UnaryOperator(denotation: String, precedence: Precedence) :
    Operator(1, denotation, precedence) {

    override fun execute(vararg args: BigDecimal): BigDecimal = execute(args[0])

    abstract fun execute(arg: BigDecimal): BigDecimal
}