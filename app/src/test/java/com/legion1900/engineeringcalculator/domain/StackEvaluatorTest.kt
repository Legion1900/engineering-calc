package com.legion1900.engineeringcalculator.domain

import com.legion1900.engineeringcalculator.domain.calculator.impl.ShuntingYardParser
import com.legion1900.engineeringcalculator.domain.calculator.impl.StackEvaluator
import org.junit.Assert.assertEquals
import org.junit.Before

import org.junit.Test
import java.math.BigDecimal

class StackEvaluatorTest {

    val testValues = mapOf(
//        Simple operations
        "2 + 5" to BigDecimal("7"),
        "2.001 + 5" to BigDecimal("7.001"),
        "2.001 + 5.001" to BigDecimal("7.002"),
        "2 - 5" to BigDecimal(-3),
        "5.0 - 2" to BigDecimal(3),
        "0.0001 - 2" to BigDecimal("-1.9999"),
        "- 5 + 2" to BigDecimal(-3),
        "2 * 3" to BigDecimal(6),
        "- 2 * 3.0" to BigDecimal(-6),
        "6 / 3" to BigDecimal(2),
        "6 / 3.0" to BigDecimal(2),
        "6.0 / 3.0" to BigDecimal(2),
        "- ( 2 + 5 ) * pow ( 2 , 3 )" to BigDecimal(-56),
        "sqrt ( 4 )" to BigDecimal(2),
        "sin ( 90 )" to BigDecimal(1),
        "sin ( ( pow ( 2 , 3 ) + 1 ) * 10 )" to BigDecimal(1)
    )

    lateinit var postfix: MutableMap<String, List<String>>

    @Before
    fun initPostfix() {
        postfix = mutableMapOf()
        val parser =
            ShuntingYardParser()
        for (pair in testValues)
            postfix[pair.key] = parser.toPostfix(pair.key)
    }

    @Test
    fun evaluate_isCorrect() {
        val eval =
            StackEvaluator()
        for (exp in postfix) {
            assertEquals(
                "${exp.key} => ${exp.value}, result = ${eval.evaluate(exp.value)
                }, expected = ${testValues.getValue(exp.key)}",
                0,
                eval.evaluate(exp.value).compareTo(testValues.getValue(exp.key))
            )
        }
    }
}