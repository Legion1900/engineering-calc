package com.legion1900.engineeringcalculator.domain.calculator.impl

import com.legion1900.engineeringcalculator.domain.calculator.base.Parser
import com.legion1900.engineeringcalculator.domain.model.impl.operators.isUnaryMinus
import com.legion1900.engineeringcalculator.domain.model.impl.operators.Operators
import com.legion1900.engineeringcalculator.domain.model.impl.operators.isOperand
import com.legion1900.engineeringcalculator.domain.model.impl.operators.isOperator
import java.util.*

const val SEPARATOR = " "

//TODO: add separate class for formatting string string (adding spaces after opening and before closing parentheses)

/*
* Partial implementation of shunting-yard algorithm.
* */
class ShuntingYardParser :
    Parser {

    override fun toPostfix(exp: String): List<String> {
        val expression = exp.split(SEPARATOR)
        val operators = Operators.map
        val opStack = Stack<String>()
        val postfix = mutableListOf<String>()

        for (token in expression) {
            /*
            * If token is an operand append it to the end of postfix
            * */
            if (token.isOperand) postfix.add(token)
            /*
            * If token is a left parentheses push it to opStack
            * */
            else if (token.isOperator(Operators.ParenthesesLeft)) opStack.push(token)
            /*
            * If token is a right parentheses OR a comma separator:
            * opStack.pop => postfix until ')' is met.
            * */
            else if (token.isOperator(Operators.ParenthesesRight)) {
//                while (opStack.isNotEmpty()) {
//                    val op = opStack.pop()
//                    if (op.isOperator(Operators.ParenthesesLeft)) break
//                    else postfix += op
//                }
                popUntilLeftScope(opStack, postfix)
                if (opStack.isNotEmpty()) opStack.pop()
            }
            /*
            * Comma separator case: opStack.pop => postfix until left parentheses is met.
            * */
            else if (token.isOperator(Operators.CommaSeparator)) popUntilLeftScope(
                opStack,
                postfix
            )
            /*
            * If token is an operator:
            * 0) check if it`s unary minus:
            * 1) move any op`s with less or equal precedence from opStack to postfix and then
            * 2) push token to opStack
            * */
            else {
                /*
                * Move operators from opStack.
                * */
                while (opStack.isNotEmpty()) {
                    val op = opStack.peek()
                    /*
                    * In this implementation parentheses are Special but still Operators.
                    * Special operators should not be present in postfix.
                    * */
                    if (op.isOperator(Operators.ParenthesesLeft)) break
                    val tokenPrecedence = operators.getValue(token).precedence
                    val opPrecedence = operators.getValue(op).precedence
                    if (opPrecedence <= tokenPrecedence) postfix += opStack.pop()
                    else break
                }
                /*
                * Add token.
                * If it`s unary minus symbol must be replaced to appropriate.
                * */
                val prevToken = expression.getOrNull(expression.indexOf(token) - 1)
                if (isUnaryMinus(
                        token,
                        prevToken
                    )
                )
                    opStack.push(Operators.UnaryMinus.denotation)
                else opStack.push(token)
            }
        }

        /*
        * Move all operators that left in opStack to postfix
        * */
        while (opStack.isNotEmpty())
            postfix += opStack.pop()
        return postfix
    }


    /*
    * Removes operators from opStack and appends them to postfix until targetOp operator i met.
    * Target operator is not removed from opStack.
    * */
    private fun popUntilLeftScope(from: Stack<String>, to: MutableList<String>) {
        while (from.isNotEmpty()) {
            val op = from.peek()
            if (op.isOperator(Operators.ParenthesesLeft)) break
            else to += from.pop()
        }
    }
}