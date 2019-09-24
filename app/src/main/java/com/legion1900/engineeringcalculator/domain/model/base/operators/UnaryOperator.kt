package com.legion1900.engineeringcalculator.domain.model.base.operators

import com.legion1900.engineeringcalculator.domain.model.base.operands.Operand

/*
* Interface that represents unary operator.
* */
interface UnaryOperator<T> :
    Operator<T> where T : Number {
    override fun execute(vararg args: Operand<T>): Operand<T> = execute(args[0])

    fun execute(arg: Operand<T>): Operand<T>
}