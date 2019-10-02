package com.legion1900.engineeringcalculator.logic.operators.base

import java.math.BigDecimal

abstract class AbstractFunction(arity: Int, denotation: String) :
    AbstractOperator(arity, denotation, Precedence.Function.ordinal)

abstract class UnaryAbstractFunction(denotation: String) :
    AbstractFunction(1, denotation) {
    override fun execute(vararg args: BigDecimal): BigDecimal = execute(args[0])

    abstract fun execute(arg: BigDecimal): BigDecimal
}