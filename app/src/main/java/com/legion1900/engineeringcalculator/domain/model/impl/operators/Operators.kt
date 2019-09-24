package com.legion1900.engineeringcalculator.domain.model.impl.operators

import com.legion1900.engineeringcalculator.domain.model.base.operators.BinOperator
import com.legion1900.engineeringcalculator.domain.model.base.operators.Precedence
import java.math.BigDecimal

object Summation : BinOperator("+", Precedence.Additive) {
    override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left + right
}

object Subtraction : BinOperator("-", Precedence.Additive) {
    override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left - right
}

object Multiplication : BinOperator("*", Precedence.Multiplicative) {
    override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left * right
}

object Division : BinOperator("/", Precedence.Multiplicative) {
    override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left / right
}
