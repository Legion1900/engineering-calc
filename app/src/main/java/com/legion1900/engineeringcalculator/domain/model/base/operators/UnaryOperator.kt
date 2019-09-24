package com.legion1900.engineeringcalculator.domain.model.base.operators

import java.math.BigDecimal

/*
* Interface that represents unary operator.
* */
interface UnaryOperator :
    Operator {
    override val arity: Int
        get() = 1

    override fun execute(vararg args: BigDecimal): BigDecimal = execute(args[0])

    fun execute(arg: BigDecimal): BigDecimal
}