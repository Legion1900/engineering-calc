package com.legion1900.engineeringcalculator.logic.calculator.base

import java.math.BigDecimal

interface Evaluator {

    val precision: Int

    fun evaluate(postfixExp: List<String>): BigDecimal

    fun round(num: BigDecimal): BigDecimal
}