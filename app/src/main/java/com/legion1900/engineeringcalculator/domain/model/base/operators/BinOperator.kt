package com.legion1900.engineeringcalculator.domain.model.base.operators

import com.legion1900.engineeringcalculator.domain.model.base.operands.Operand

/*
* Interface that represents unary operator.
* */
interface BinOperator<T> : Operator<T> where T : Number {
    override fun execute(vararg args: Operand<T>): Operand<T> = execute(args[0], args[1])

    fun execute(arg1: Operand<T>, arg2: Operand<T>): Operand<T>
}