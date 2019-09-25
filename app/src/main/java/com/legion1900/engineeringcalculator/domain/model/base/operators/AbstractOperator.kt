package com.legion1900.engineeringcalculator.domain.model.base.operators

abstract class AbstractOperator(
    override val arity: Int,
    override val denotation: String,
    override val precedence: Int
) : Operator