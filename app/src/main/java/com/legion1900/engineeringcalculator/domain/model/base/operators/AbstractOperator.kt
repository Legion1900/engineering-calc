package com.legion1900.engineeringcalculator.domain.model.base.operators

/*
* The less value it holds, the higher precedence it is.
* */
enum class Precedence(value: Int) {
    Parentheses(0),
    /*
    * Complex functions such as sin, cos, sqrt, pow, etc.
    * */
    Functions(1),
    /*
    * For such ops as *, /, remainder.
    * */
    Multiplicative(2),
    /*
    * For such ops as +, -
    * */
    Additive(3)
}

/*
* Interface that represents any kind of operation.
* It is used for parsing expressions.
* */
abstract class AbstractOperator(
    override val arity: Int,
    override val denotation: String,
    override val precedence: Precedence
) : Operator