package com.legion1900.engineeringcalculator.domain.model.base.operators

import java.math.BigDecimal

interface Operator {
    val arity: Int
    val denotation: String
    val precedence: Precedence

    fun execute(vararg args: BigDecimal): BigDecimal
}