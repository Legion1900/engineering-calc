package com.legion1900.engineeringcalculator.domain.model.base.operators

import java.math.BigDecimal

/*
* Interface that represents operations on numbers.
* It should be used to define supported operations.
* */
interface Operator {
    val arity: Int
    val denotation: String
    val precedence: Int

    fun execute(vararg args: BigDecimal): BigDecimal
}