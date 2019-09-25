package com.legion1900.engineeringcalculator.domain.model.base.operators

import com.legion1900.engineeringcalculator.domain.model.impl.operators.Precedence

abstract class Function(arity: Int, denotion: String, precedence: Precedence) :
    AbstractOperator(arity, denotion, precedence)