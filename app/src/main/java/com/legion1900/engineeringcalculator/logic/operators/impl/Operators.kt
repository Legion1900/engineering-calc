package com.legion1900.engineeringcalculator.logic.operators.impl

import com.legion1900.engineeringcalculator.logic.operators.base.*
import java.lang.Math.pow
import java.math.BigDecimal
import kotlin.math.*

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

    Remainder(object : BinAbstractOperator("%", Precedence.Multiplicative.ordinal) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left.rem(right)
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

    Tangent(object : UnaryAbstractFunction("tan") {
        override fun execute(arg: BigDecimal): BigDecimal =
            BigDecimal(tan(arg.degreeToRadians().toDouble()))
    }),

    Log(object : UnaryAbstractFunction("log") {
        override fun execute(arg: BigDecimal): BigDecimal =
            BigDecimal(log10(arg.toDouble()))
    }),

    LogN(object : UnaryAbstractFunction("ln") {
        override fun execute(arg: BigDecimal): BigDecimal =
            BigDecimal(log(arg.toDouble(), kotlin.math.E))
    }),

    SquareRoot(object : UnaryAbstractFunction("sqrt") {
        override fun execute(arg: BigDecimal): BigDecimal = BigDecimal(sqrt(arg.toDouble()))
    }),

    Power(object : AbstractFunction(2, "pow") {
        override fun execute(vararg args: BigDecimal): BigDecimal =
            BigDecimal(pow(args[0].toDouble(), args[1].toDouble()))
    }),

    Absolute(object : UnaryAbstractFunction("abs") {
        override fun execute(arg: BigDecimal): BigDecimal = arg.abs()
    }),

    Pi(Constant(BigDecimal(PI), "pi")),

    E(
        Constant(
            BigDecimal(kotlin.math.E),
            "e"
        )
    ),

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

val CharSequence.isOperand: Boolean
    get() = !Operators.map.containsKey(this)

fun CharSequence.isOperator(op: Operators) =
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