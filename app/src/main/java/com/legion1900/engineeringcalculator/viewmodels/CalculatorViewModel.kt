package com.legion1900.engineeringcalculator.viewmodels

import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.legion1900.engineeringcalculator.domain.model.impl.operators.Operators
import com.legion1900.engineeringcalculator.viewmodels.controller.base.CalculatorPrinter
import com.legion1900.engineeringcalculator.viewmodels.controller.impl.InputController

class CalculatorViewModel(inputField: EditText) : ViewModel() {
    val printer: CalculatorPrinter = InputController(inputField, Operators.map.keys)
}