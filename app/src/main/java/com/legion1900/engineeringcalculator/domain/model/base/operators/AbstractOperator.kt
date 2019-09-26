package com.legion1900.engineeringcalculator.domain.model.base.operators

import java.math.BigDecimal

abstract class AbstractOperator(
    override val arity: Int,
    override val denotation: String,
    override val precedence: Int
) : Operator {
    override fun toString(): String = denotation
}

/*
* Interface that represents unary operator.
* */
abstract class BinAbstractOperator(denotation: String, precedence: Int):
    AbstractOperator(2, denotation, precedence) {

    override fun execute(vararg args: BigDecimal): BigDecimal = execute(args[0], args[1])

    abstract fun execute(left: BigDecimal, right: BigDecimal): BigDecimal
}

/*
* Interface that represents unary operator.
* */
abstract class UnaryAbstractOperator(denotation: String) :
    AbstractOperator(1, denotation, Precedence.Unary.ordinal) {

    override fun execute(vararg args: BigDecimal): BigDecimal = execute(args[0])

    abstract fun execute(arg: BigDecimal): BigDecimal
}