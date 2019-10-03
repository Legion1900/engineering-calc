package com.legion1900.engineeringcalculator.logic.operators.impl

import com.legion1900.engineeringcalculator.logic.operators.base.*
import java.lang.Math.pow
import java.math.BigDecimal
import kotlin.math.*

/*
* Enum of supported operations and their denotations
* */
enum class Operators(operation: AbstractOperator) : Operator by operation {

    SUMMATION(object : BinAbstractOperator("+", Precedence.ADDITIVE.ordinal) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left + right
    }),

    SUBTRACTION(object : BinAbstractOperator("-", Precedence.ADDITIVE.ordinal) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left - right
    }),

    MULTIPLICATION(object : BinAbstractOperator("*", Precedence.MULTIPLICATIVE.ordinal) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left * right
    }),

    DIVISION(object : BinAbstractOperator("/", Precedence.MULTIPLICATIVE.ordinal) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left / right
    }),

    REMAINDER(object : BinAbstractOperator("%", Precedence.MULTIPLICATIVE.ordinal) {
        override fun execute(left: BigDecimal, right: BigDecimal): BigDecimal = left.rem(right)
    }),

    UNARY_MINUS(object : UnaryAbstractOperator("unaryMin") {
        override fun execute(arg: BigDecimal): BigDecimal = -arg
    }),

    SINUS(object : UnaryAbstractFunction("sin") {
        override fun execute(arg: BigDecimal): BigDecimal =
            BigDecimal(sin(arg.degreeToRadians().toDouble()))
    }),

    COSINE(object : UnaryAbstractFunction("cos") {
        override fun execute(arg: BigDecimal): BigDecimal =
            BigDecimal(cos(arg.degreeToRadians().toDouble()))
    }),

    TANGENT(object : UnaryAbstractFunction("tan") {
        override fun execute(arg: BigDecimal): BigDecimal =
            BigDecimal(tan(arg.degreeToRadians().toDouble()))
    }),

    LOG(object : UnaryAbstractFunction("log") {
        override fun execute(arg: BigDecimal): BigDecimal =
            BigDecimal(log10(arg.toDouble()))
    }),

    LOG_N(object : UnaryAbstractFunction("ln") {
        override fun execute(arg: BigDecimal): BigDecimal =
            BigDecimal(log(arg.toDouble(), kotlin.math.E))
    }),

    SQUARE_ROOT(object : UnaryAbstractFunction("sqrt") {
        override fun execute(arg: BigDecimal): BigDecimal = BigDecimal(sqrt(arg.toDouble()))
    }),

    POWER(object : AbstractFunction(2, "pow") {
        override fun execute(vararg args: BigDecimal): BigDecimal =
            BigDecimal(pow(args[0].toDouble(), args[1].toDouble()))
    }),

    ABSOLUTE(object : UnaryAbstractFunction("abs") {
        override fun execute(arg: BigDecimal): BigDecimal = arg.abs()
    }),

    PI(Constant(BigDecimal(kotlin.math.PI), "pi")),

    E(
        Constant(
            BigDecimal(kotlin.math.E),
            "e"
        )
    ),

    COMMA_SEPARATOR(Special(",")),

    PARENTHESES_LEFT(Special("(")),

    PARENTHESES_RIGHT(Special(")"));

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
    if (!token.isOperator(Operators.SUBTRACTION)) return false
    /*
    * If previous operator exists check if it`s left scope else return false
    * */
    return prevToken?.isOperator(Operators.PARENTHESES_LEFT) ?: true
}

fun BigDecimal.degreeToRadians(): BigDecimal = this * BigDecimal(PI) / BigDecimal(180)