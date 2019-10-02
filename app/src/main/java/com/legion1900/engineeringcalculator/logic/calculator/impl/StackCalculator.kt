package com.legion1900.engineeringcalculator.logic.calculator.impl

import com.legion1900.engineeringcalculator.logic.calculator.base.Calculator
import com.legion1900.engineeringcalculator.logic.calculator.base.Formatter

class StackCalculator(formatter: Formatter) :
    Calculator(formatter, ShuntingYardParser(), StackEvaluator())