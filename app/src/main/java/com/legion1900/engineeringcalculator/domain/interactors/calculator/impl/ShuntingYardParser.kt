package com.legion1900.engineeringcalculator.domain.interactors.calculator.impl

import com.legion1900.engineeringcalculator.domain.interactors.calculator.base.Parser
import com.legion1900.engineeringcalculator.domain.model.impl.operators.Operators
import java.util.*

const val SEPARATOR = " "

//TODO: add support for unary operators (unary function)

//TODO: add separate class for formatting string string (adding spaces after opening and before closing parentheses)

/*
* Partial implementation of shunting-yard algorithm.
* Does not support not unary functions;
* Does not support unary minus;
* */
class ShuntingYardParser(exp: String) : Parser {
    private val expression: List<String> by lazy { exp.split(SEPARATOR) }

    //    TODO: add support for comma operator
    override fun toPostfix(): List<String> {
        val operators = Operators.map
        val opStack = Stack<String>()
        val postfix = mutableListOf<String>()

        for (token in expression) {
            /*
            * If token is an operand append it to the end of postfix
            * */
            if (isOperand(token)) postfix.add(token)
            /*
            * If token is a left parentheses push it to opStack
            * */
            else if (isLeftParentheses(token)) opStack.push(token)
            /*
            * If token is a right parentheses OR a comma separator:
            * opStack.pop => postfix until ')' is met.
            * */
            else if (isRightParentheses(token) || isCommaSeparator(token)) {
                while (opStack.isNotEmpty()) {
                    val op = opStack.pop()
                    if (isLeftParentheses(op)) break
                    else postfix += op
                }
            }
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
                    if (isLeftParentheses(op)) break
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

    private fun isOperand(token: String) = !Operators.map.containsKey(token)

    private fun isLeftParentheses(token: String) =
        Operators.map[token] == Operators.ParenthesesLeft

    private fun isRightParentheses(token: String) =
        Operators.map[token] == Operators.ParenthesesRight

    private fun isCommaSeparator(token: String) =
        Operators.map[token] == Operators.CommaSeparator

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
            isLeftParentheses(prevToken)
        }
        /*
        * It`s unary minus if it`s minus & first token in expression
        * */
        else true
    }

}