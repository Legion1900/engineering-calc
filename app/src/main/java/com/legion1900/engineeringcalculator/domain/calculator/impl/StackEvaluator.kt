package com.legion1900.engineeringcalculator.domain.calculator.impl

import com.legion1900.engineeringcalculator.domain.calculator.base.Evaluator
import com.legion1900.engineeringcalculator.domain.model.impl.operators.Operators
import com.legion1900.engineeringcalculator.domain.model.impl.operators.isOperand
import java.math.BigDecimal
import java.math.MathContext
import java.util.*

// TODO: create intermediate class that will hold postfix expression if form of two stacks (op&operand)
// (because otherwise I`m doing same work of 'reading' string tokens!)
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