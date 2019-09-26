package com.legion1900.engineeringcalculator.domain.model.base.operators

/*
* The less value it holds, the higher precedence it is.
* */
enum class Precedence(value: Int) {
    /*
    * Comma, parentheses, etc.
    * */
    Special(-1),
    /*
    * Functions
    * */
    Function(0),
    /*
    * Unary operators
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