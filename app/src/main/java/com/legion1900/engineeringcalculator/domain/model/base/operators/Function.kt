package com.legion1900.engineeringcalculator.domain.model.base.operators

abstract class UnaryFunction(denotation: String, precedence: Int) :
    UnaryAbstractOperator(denotation, precedence)