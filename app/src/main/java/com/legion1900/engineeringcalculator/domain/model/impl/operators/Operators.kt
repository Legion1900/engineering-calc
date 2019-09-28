package com.legion1900.engineeringcalculator.domain.model.impl.operators

import com.legion1900.engineeringcalculator.domain.model.base.operators.*
import java.lang.Math.pow
import java.math.BigDecimal
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

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

    UnaryMinus(object : UnaryAbstractOperator("unaryMin") {
        override fun execute(arg: BigDecimal): BigDecimal = -arg
    }),

    Sinus(object : UnaryAbstractFunction("sin") {
        override fun execute(arg: BigDecimal): BigDecimal =
            BigDecimal(sin(arg.degreeToRadians().toDouble()))
    }),

    Cosine(object : UnaryAbstractFunction("cos") {
        override fun execute(arg: BigDecimal): BigDecimal =
            BigDecimal(cos(arg.degreeToRadians().toDouble()))
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

fun isUnaryMinus(token: String, prevToken: String?): Boolean {
    /*
    * It`s not unary minus if it isn`t a minus operator.
    * */
    if (!token.isOperator(Operators.Subtraction)) return false
    /*
    * If previous operator exists check if it`s left scope else return false
    * */
    return prevToken?.isOperator(Operators.ParenthesesLeft) ?: true
}

fun BigDecimal.degreeToRadians(): BigDecimal = this * BigDecimal(PI) / BigDecimal(180)