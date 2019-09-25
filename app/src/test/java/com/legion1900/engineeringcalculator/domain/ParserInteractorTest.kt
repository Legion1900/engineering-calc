package com.legion1900.engineeringcalculator.domain

import com.legion1900.engineeringcalculator.domain.interactors.impl.ParserInteractor
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.StringBuilder

class ParserInteractorTest {

    val inputOutput = mapOf(
        "A + B" to "A B +",
        "A + B * C" to "A B C * +",
        "A + B * C + D" to "A B C * + D +",
        "( A + B ) * ( C + D )" to "A B + C D + *",
        "A * B + C * D" to "A B * C D * +",
        "A + B + C + D" to "A B + C + D +"
    )

    @Test
    fun toPostfix_isCorrect() {
        for (pair in inputOutput)
            assertEquals("Input: ${pair.key}", pair.value, toString(ParserInteractor(pair.key).toPostfix()))
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