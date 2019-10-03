package com.legion1900.engineeringcalculator.view

import android.util.Log
import com.legion1900.engineeringcalculator.logic.calculator.base.Formatter
import com.legion1900.engineeringcalculator.logic.operators.impl.Operators

const val SEPARATOR = " "
const val ESCAPE = "\\"

class InputFormatter : Formatter {
    private val operators = Operators.map

    override fun format(input: CharSequence): String {
        val builder = StringBuilder(input)
        val replacement = StringBuilder()
        val tmp = StringBuilder()
        for (op in operators.keys) {
            /*
            * Add spacing around operators
            * */
            tmp.clear().append(ESCAPE)
            replacement.clear().append(SEPARATOR).append(op).append(SEPARATOR)
            val opRegex = if (op.length > 1) Regex(op) else Regex(tmp.append(op).toString())
            val newString = builder.replace(opRegex, replacement.toString())
            builder.clear().append(newString)
        }
        /*
        * Replace double space by single one
        * */
        val extraSpaceRegex = Regex("\\s{2}")
        val trimmed = builder.replace(extraSpaceRegex, SEPARATOR)
        builder.clear().append(trimmed)
        /*
        * Delete space occurrence at the beginning and the end
        * */
        builder.deleteSpaceAt(0)
        builder.deleteSpaceAt(builder.length - 1)
        return builder.toString()
    }

    private fun StringBuilder.deleteSpaceAt(i: Int) {
        if (this[i] == ' ')
            deleteCharAt(i)
    }
}