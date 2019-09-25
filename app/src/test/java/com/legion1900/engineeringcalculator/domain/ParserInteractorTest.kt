package com.legion1900.engineeringcalculator.domain

import com.legion1900.engineeringcalculator.domain.interactors.impl.ParserInteractor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ParserInteractorTest {

    val inputOutput = mapOf(
        "A + B" to listOf("A", "B", "+"),
        "A + B * C" to listOf("A", "B", "C", "*", "+")
    )

    @Test
    fun toPostfix_isCorrect() {
        for (pair in inputOutput)
            assertEquals(pair.value, ParserInteractor(pair.key).toPostfix())
    }
}