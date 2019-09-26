package com.legion1900.engineeringcalculator.domain.interactors.calculator.impl

import com.legion1900.engineeringcalculator.domain.interactors.calculator.base.Parser
import com.legion1900.engineeringcalculator.domain.model.impl.operators.Operators
import java.util.*

const val SEPARATOR = " "

//TODO: add separate class for formatting string string (adding spaces after opening and before closing parentheses)

/*
* Partial implementation of shunting-yard algorithm.
* */
class ShuntingYardParser : Parser {
    private val expression = mutableListOf<String>()
    private val opStack = Stack<String>()
    private val postfix = mutableListOf<String>()

    //    TODO: add support for comma operator
    override fun toPostfix(exp: String): List<String> {
        expression.clear()
        expression.addAll(exp.split(SEPARATOR))
        val operators = Operators.map
        opStack.clear()
        postfix.clear()

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
                popUntil(Operators.ParenthesesLeft)
                if (opStack.isNotEmpty()) opStack.pop()
            }
            /*
            * Comma separator case: opStack.pop => postfix until left parentheses is met.
            * */
            else if (token.isOperator(Operators.CommaSeparator)) popUntil(Operators.ParenthesesLeft)
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
                * */
                if (isUnaryMinus(expression.indexOf(token)))
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
    * Removes operators from opStack and appends them to postfix until target operator i met.
    * Target operator is not removed from opStack.
    * */
    fun popUntil(target: Operators) {
        while (opStack.isNotEmpty()) {
            val op = opStack.peek()
            if (op.isOperator(target)) break
            else postfix += opStack.pop()
        }
    }

    private val String.isOperand: Boolean
        get() = !Operators.map.containsKey(this)

    private fun String.isOperator(op: Operators) =
        Operators.map[this] == op

    private fun isUnaryMinus(tokenInd: Int): Boolean {
        /*
        * It`s not unary minus if it isn`t a minus operator.
        * */
        if (Operators.map[expression[tokenInd]] != Operators.Subtraction) return false
        val previousTokenInd = tokenInd - 1
        return if (previousTokenInd > 0) {
            val prevToken = expression[previousTokenInd]
            /*
            * It`s unary minus if it`s minus & first token after parentheses
            * */
            prevToken.isOperator(Operators.ParenthesesLeft)
        }
        /*
        * It`s unary minus if it`s minus & first token in expression
        * */
        else true
    }
}