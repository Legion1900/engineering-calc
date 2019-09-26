package com.legion1900.engineeringcalculator.domain.model.impl.operators

import com.legion1900.engineeringcalculator.domain.model.base.operators.*
import java.math.BigDecimal

/*
* The less value it holds, the higher precedence it is.
* */
enum class Precedence(value: Int) {
    Parentheses(0),
    /*
    * Unary functions and operators
    * */
    Unary(1),
    /*
    * For such ops as *, /, remainder.
    * */
    Multiplicative(2),
    /*
    * For such ops as +, -
    * */
    Additive(3)
}

class Parentheses(val symbol: String) :
    AbstractOperator(0, symbol, Precedence.Parentheses.ordinal) {
    override fun execute(vararg args: BigDecimal): BigDecimal =
        throw UnsupportedOperationException()
}

/*
* Enum of supported operations and their denotations
* */
enum class Operators(operation: AbstractOperator) : Operator by operation {

    Summation(object : BinAbstractOperator("+", Precedence.Additive.ordinal) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left + right
    }),

    Subtraction(object : BinAbstractOperator("-", Precedence.Additive.ordinal) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left - right
    }),

    Multiplication(object : BinAbstractOperator("*", Precedence.Multiplicative.ordinal) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left * right
    }),

    Division(object : BinAbstractOperator("/", Precedence.Multiplicative.ordinal) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left / right
    }),

    UnaryMinus(object : UnaryAbstractOperator("unaryMin", Precedence.Unary.ordinal) {
        override fun execute(arg: BigDecimal): BigDecimal = -arg
    }),

    Sinus(object : UnaryFunction("sin", Precedence.Unary.ordinal) {
        override fun execute(arg: BigDecimal): BigDecimal {
            TODO("BigDecimal.double -> sin()")
        }
    }),

    ParenthesesLeft(Parentheses("(")),

    ParenthesesRight(Parentheses(")"));

    companion object {
        val map: Map<String, Operator> by lazy {
            val opList = enumValues<Operators>()
            val map = mutableMapOf<String, Operator>()
            for (op in opList) {
                map[op.denotation] = op
            }
            map.toMap()
        }
    }
}