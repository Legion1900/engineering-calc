package com.legion1900.engineeringcalculator.domain.model.impl.operators

import com.legion1900.engineeringcalculator.domain.model.base.operators.AbstractOperator
import com.legion1900.engineeringcalculator.domain.model.base.operators.BinAbstractOperator
import com.legion1900.engineeringcalculator.domain.model.base.operators.Operator
import com.legion1900.engineeringcalculator.domain.model.base.operators.Precedence
import java.math.BigDecimal


enum class Operators(operation: AbstractOperator) : Operator by operation {
    Summation(object : BinAbstractOperator("+", Precedence.Additive) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left + right
    }),

    Subtraction(object : BinAbstractOperator("-", Precedence.Additive) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left - right
    }),

    Multiplication(object : BinAbstractOperator("*", Precedence.Multiplicative) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left * right
    }),

    Division(object : BinAbstractOperator("/", Precedence.Multiplicative) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left / right
    }),

    Parentheses(object : AbstractOperator(0, "(", Precedence.Parentheses) {
        override fun execute(vararg args: BigDecimal): BigDecimal =
            throw UnsupportedOperationException()
    }),
}
