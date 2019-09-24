package com.legion1900.engineeringcalculator.domain.model.base.operators

import com.legion1900.engineeringcalculator.domain.model.base.operands.Operand

/*
* Interface that represents any kind of operation.
* It is used for parsing expressions.
* */
interface Operator<T> where T : Number {
    val precedence: Int

    fun execute(vararg args: Operand<T>): Operand<T>
}