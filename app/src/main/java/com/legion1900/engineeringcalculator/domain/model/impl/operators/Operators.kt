package com.legion1900.engineeringcalculator.domain.model.impl.operators

import com.legion1900.engineeringcalculator.domain.model.base.operators.BinOperator
import com.legion1900.engineeringcalculator.domain.model.base.operators.Operator
import com.legion1900.engineeringcalculator.domain.model.base.operators.Precedence
import java.math.BigDecimal



enum class Operators(value: Operator) {
    Summation(object : BinOperator("+", Precedence.Additive) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left + right
    }),

    Subtraction(object : BinOperator("-", Precedence.Additive) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left - right
    }),

    Multiplication(object : BinOperator("*", Precedence.Multiplicative) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left * right
    }),

    Division(object : BinOperator("/", Precedence.Multiplicative) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left / right
    })
}
