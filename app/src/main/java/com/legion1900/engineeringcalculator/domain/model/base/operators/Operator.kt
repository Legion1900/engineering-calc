package com.legion1900.engineeringcalculator.domain.model.base.operators

import java.math.BigDecimal

/*
* Interface that represents any kind of operation.
* It is used for parsing expressions.
* */
interface Operator {
    val arity: Int
    val denotation: String
    val precedence: Int

    fun execute(vararg args: BigDecimal): BigDecimal
}