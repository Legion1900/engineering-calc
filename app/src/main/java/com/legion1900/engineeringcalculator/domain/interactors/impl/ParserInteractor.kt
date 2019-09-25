package com.legion1900.engineeringcalculator.domain.interactors.impl

import com.legion1900.engineeringcalculator.domain.interactors.base.Interactor
import com.legion1900.engineeringcalculator.domain.model.impl.operators.Operators
import java.util.*

const val SEPARATOR = " "

class ParserInteractor(exp: String) : Interactor {

    val expression: List<String> by lazy { exp.split(SEPARATOR) }

    override fun execute() {
    }

    fun toPostfix(): List<String> {
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
            * If token is a right parentheses opStack.pop => postfix until ')' is met.
            * */
            else if (isRightParentheses(token)) {
                while(opStack.isNotEmpty()) {
                    val op = opStack.pop()
                    if (isLeftParentheses(op)) break
                    else postfix += op
                }
            }
            /*
            * If token is an operator:
            * 1) move any op`s with less or equal precedence from opStack to postfix and then
            * 2) push token to opStack
            * */
            else {
                /*
                * Move operators from opStack.
                * */
                while(opStack.isNotEmpty()) {
                    val op = opStack.peek()
                    /*
                    * Parentheses are special operators and should not be appended to postfix
                    * */
                    if (isLeftParentheses(op) || isRightParentheses(op)) break
                    val tokenPrecedence = operators.getValue(token).precedence
                    val opPrecedence = operators.getValue(op).precedence
                    if (opPrecedence <= tokenPrecedence) postfix += opStack.pop()
                    else break
                }
                /*
                * Add token.
                * */
                opStack.push(token)
            }
        }

        /*
        * Move all operators that left in opStack to postfix
        * */
        while (opStack.isNotEmpty())
            postfix += opStack.pop()
        return postfix
    }

    fun isOperand(token: String) = !Operators.map.containsKey(token)

    fun isLeftParentheses(token: String) = Operators.map[token] == Operators.ParenthesesLeft

    fun isRightParentheses(token: String) = Operators.map[token] == Operators.ParenthesesRight
}