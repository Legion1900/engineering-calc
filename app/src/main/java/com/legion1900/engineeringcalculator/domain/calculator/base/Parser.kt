package com.legion1900.engineeringcalculator.domain.calculator.base

interface Parser {
    fun toPostfix(exp: String): List<String>
}