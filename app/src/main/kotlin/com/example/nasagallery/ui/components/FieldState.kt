package com.example.nasagallery.ui.components

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf

class FieldState<T>(
    private val defaultValue: T,
    private val getErrorMessage: (T) -> String = { "" },
) {
    private val _valueState = mutableStateOf(defaultValue)
    val valueState: State<T> = _valueState

    val errorState = derivedStateOf {
        if (valueState.value == defaultValue) {
            ""
        } else {
            getErrorMessage(valueState.value)
        }
    }

    val isModified = derivedStateOf {
        defaultValue != valueState.value
    }

    val isValid: Boolean
        get() = errorState.value.isBlank()

    fun updateValue(value: T) {
        _valueState.value = value
    }

    override fun toString(): String {
        return "value: ${valueState.value},isModified: ${isModified.value} ,error: ${errorState.value}"
    }

}