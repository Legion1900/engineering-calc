package com.legion1900.engineeringcalculator.domain.model.base.operators

import com.legion1900.engineeringcalculator.domain.model.impl.operators.Precedence
import java.math.BigDecimal

/*
* Interface that represents unary operator.
* */
abstract class BinAbstractOperator(denotation: String, precedence: Int):
    AbstractOperator(2, denotation, precedence) {

    override fun execute(vararg args: BigDecimal): BigDecimal = execute(args[0], args[1])

    abstract fun execute(left: BigDecimal, right: BigDecimal): BigDecimal
}