package com.legion1900.engineeringcalculator.domain.interactors.impl

import com.legion1900.engineeringcalculator.domain.interactors.base.Interactor
import com.legion1900.engineeringcalculator.domain.model.base.operators.Operator
import com.legion1900.engineeringcalculator.domain.model.impl.operators.Operators

const val SEPARATOR = " "

class ParserInteractor(val exp: String) : Interactor {

    companion object {
        val operators: Map<String, Operator> by lazy {
            val opList = enumValues<Operators>()
            val map = mutableMapOf<String, Operator>()
            for (op in opList) {
                val tmp = op.ordinal as Operator
                map[tmp.denotation] = tmp
            }
            map.toMap()
        }
    }

    override fun execute() {
    }

//    fun toPostfix(): String {
//        val infix = exp.splitToSequence(SEPARATOR)
//        val opStack = Stack<String>()
//        val postfix = mutableListOf<String>()
//
//        for (token in infix) {
//
//        }
//    }

}