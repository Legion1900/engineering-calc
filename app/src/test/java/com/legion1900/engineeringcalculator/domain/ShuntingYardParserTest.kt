package com.legion1900.engineeringcalculator.domain

import com.legion1900.engineeringcalculator.domain.interactors.calculator.impl.ShuntingYardParser
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.StringBuilder

class ShuntingYardParserTest {

    /*
    * Input values for testing toPostfix
    * */
    val toPostfixValues = mapOf(
//        General input values.
        "A + B" to "A B +",
        "A + B * C" to "A B C * +",
        "A + B * C + D" to "A B C * + D +",
        "( A + B ) * ( C + D )" to "A B + C D + *",
        "A * B + C * D" to "A B * C D * +",
        "A + B + C + D" to "A B + C + D +",
//        Input values for unary minus.
        "- A + B" to "A unaryMin B +",
        "- ( A + B ) * C" to "A B + unaryMin C *",
        "- A * B / ( C + D )" to "A unaryMin B * C D + /",
//        Input values for unary minus
        "sin ( a ) * A" to "a sin A *",
        "- sin ( a + b )" to "a b + sin unaryMin",
//        Testing function with arbitrary args number
        "pow ( cos ( pow ( A , B ) ) , sin ( A + B ) )" to "A B pow cos A B + sin pow",
        "C * pow ( B , P )" to "C B P pow *",
        "sqrt ( pow ( A + B , C * P ) )" to "A B + C P * pow sqrt",
        "123.123 * pow ( sin ( 0.123 ) , 3 )" to "123.123 0.123 sin 3 pow *",
        "2 + 5" to "2 5 +"
    )

    @Test
    fun toPostfix_isCorrect() {
        val parser = ShuntingYardParser()
        for (pair in toPostfixValues)
            assertEquals("Input: ${pair.key}", pair.value, toString(parser.toPostfix(pair.key)))
    }

    fun toString(list: List<String>): String {
        val space = " "
        val builder = StringBuilder()
        for (token in list)
        {
            builder.append(token)
            builder.append(space)
        }
        return builder.dropLast(1).toString()
    }
}