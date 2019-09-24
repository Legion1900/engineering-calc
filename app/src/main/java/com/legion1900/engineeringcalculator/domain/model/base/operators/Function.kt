package com.legion1900.engineeringcalculator.domain.model.base.operators

abstract class Function(arity: Int, denotion: String, precedence: Precedence) :
    Operator(arity, denotion, precedence)