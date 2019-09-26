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
        "- sin ( a + b )" to "a b + sin unaryMin"
    )

    @Test
    fun toPostfix_isCorrect() {
        for (pair in toPostfixValues)
            assertEquals("Input: ${pair.key}", pair.value, toString(ShuntingYardParser(pair.key).toPostfix()))
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