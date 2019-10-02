package com.legion1900.engineeringcalculator.logic.calculator.impl

import com.legion1900.engineeringcalculator.logic.calculator.base.Evaluator
import com.legion1900.engineeringcalculator.logic.operators.impl.Operators
import com.legion1900.engineeringcalculator.logic.operators.impl.isOperand
import java.math.BigDecimal
import java.math.MathContext
import java.util.*
//TODO: add Calculator class for handling both parsing and evaluating
// and Formatter for preparing string input

class StackEvaluator(override val precision: Int = 32) :
    Evaluator {

    private val context: MathContext = MathContext(precision)

    override fun evaluate(postfixExp: List<String>): BigDecimal {
        val operands = Stack<BigDecimal>()
        for (token in postfixExp) {
            /*
            * If token is an operand: add it to operands stack.
            * */
            if (token.isOperand) operands.push(BigDecimal(token))
            /*
            * If token is an operator:
            * 1) pop enough arguments from operands;
            * 2) reverse order of received arguments;
            * 3) perform operation
            * 4) push result back to operands stack
            * */
            else {
                val op = Operators.map.getValue(token)
                val args = Array(op.arity) { operands.pop() }
                args.reverse()
                val result = op.execute(*args)
                operands.push(result)
            }
        }
        val result = operands.pop()
        return round(result)
    }

    override fun round(num: BigDecimal): BigDecimal = num.round(context)
}