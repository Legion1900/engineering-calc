package com.legion1900.engineeringcalculator.domain.model.base.operators

import java.math.BigDecimal

/*
* Interface that represents unary operator.
* */
interface BinOperator: Operator {

    override val arity: Int
        get() = 2

    override fun execute(vararg args: BigDecimal): BigDecimal = execute(args[0], args[1])

    fun execute(arg1: BigDecimal, arg2: BigDecimal): BigDecimal
}