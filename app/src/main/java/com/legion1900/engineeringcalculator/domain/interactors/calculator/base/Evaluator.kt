package com.legion1900.engineeringcalculator.domain.interactors.calculator.base

import java.math.BigDecimal

interface Evaluator {
    fun evaluate(postfixExp: List<String>): BigDecimal
}