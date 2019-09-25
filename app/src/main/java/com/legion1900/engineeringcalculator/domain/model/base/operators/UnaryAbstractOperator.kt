package com.legion1900.engineeringcalculator.domain.model.base.operators

import com.legion1900.engineeringcalculator.domain.model.impl.operators.Precedence
import java.math.BigDecimal

/*
* Interface that represents unary operator.
* */
abstract class UnaryAbstractOperator(denotation: String, precedence: Int) :
    AbstractOperator(1, denotation, precedence) {

    override fun execute(vararg args: BigDecimal): BigDecimal = execute(args[0])

    abstract fun execute(arg: BigDecimal): BigDecimal
}