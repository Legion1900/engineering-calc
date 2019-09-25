package com.legion1900.engineeringcalculator.domain.model.base.operators

abstract class Function(arity: Int, denotation: String, precedence: Int) :
    AbstractOperator(arity, denotation, precedence)