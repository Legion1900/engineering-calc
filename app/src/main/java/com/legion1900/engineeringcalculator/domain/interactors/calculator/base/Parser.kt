package com.legion1900.engineeringcalculator.domain.interactors.calculator.base

interface Parser {
    fun toPostfix(): List<String>
}