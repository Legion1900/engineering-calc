package com.legion1900.engineeringcalculator.domain.model.impl.operators

import com.legion1900.engineeringcalculator.domain.model.base.operators.*
import java.lang.Math.pow
import java.math.BigDecimal
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

//TODO: for convert BigDecimal to radians for trigonometric functions!
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

    ToPower(object : BinAbstractOperator("^", Precedence.Multiplicative.ordinal) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left / right
    }),

    UnaryMinus(object : UnaryAbstractOperator("unaryMin") {
        override fun execute(arg: BigDecimal): BigDecimal = -arg
    }),

    Sinus(object : UnaryAbstractFunction("sin") {
        override fun execute(arg: BigDecimal): BigDecimal = BigDecimal(sin(arg.toDouble()))
    }),

    Cosine(object : UnaryAbstractFunction("cos") {
        override fun execute(arg: BigDecimal): BigDecimal = BigDecimal(cos(arg.toDouble()))
    }),

    SquareRoot(object : UnaryAbstractFunction("sqrt") {
        override fun execute(arg: BigDecimal): BigDecimal = BigDecimal(sqrt(arg.toDouble()))
    }),

    Power(object : AbstractFunction(2, "pow") {
        override fun execute(vararg args: BigDecimal): BigDecimal =
            BigDecimal(pow(args[0].toDouble(), args[1].toDouble()))
    }),

    CommaSeparator(Special(",")),

    ParenthesesLeft(Special("(")),

    ParenthesesRight(Special(")"));

//    -----------------------------------------------------------------------------------------

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

val String.isOperand: Boolean
    get() = !Operators.map.containsKey(this)

fun String.isOperator(op: Operators) =
    Operators.map[this] == op