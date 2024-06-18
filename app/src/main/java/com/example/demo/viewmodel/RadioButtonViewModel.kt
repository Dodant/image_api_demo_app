package com.example.demo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RadioButtonViewModel : ViewModel() {
    private val _selectedOption = MutableStateFlow(0)
    val selectedOption: StateFlow<Int> get() = _selectedOption

    fun selectOption(option: Int) {
        _selectedOption.value = option
    }
}