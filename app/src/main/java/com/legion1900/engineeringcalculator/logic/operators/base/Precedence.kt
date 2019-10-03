package com.legion1900.engineeringcalculator.logic.operators.base

/*
* The less value it holds, the higher precedence it is.
* */
enum class Precedence(value: Int) {
    /*
    * Comma, parentheses, etc.
    * */
    SPECIAL(-1),
    /*
    * Functions
    * */
    FUNCTION(0),
    /*
    * UNARY operators
    * */
    UNARY(1),
    /*
    * For such ops as *, /, remainder.
    * */
    MULTIPLICATIVE(2),
    /*
    * For such ops as +, -
    * */
    ADDITIVE(3)
}