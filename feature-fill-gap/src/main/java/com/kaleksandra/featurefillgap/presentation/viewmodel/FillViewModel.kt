package com.kaleksandra.featurefillgap.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kaleksandra.featurefillgap.presentation.model.UIState
import com.kaleksandra.featurefillgap.test.tests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FillViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    init {
        getTask()
    }

    fun onWordChange(index: Int, word: String) {
        _uiState.update {
            it.copy(
                gaps = it.gaps.mapIndexed { valueIndex, value -> if (valueIndex == index) word else value }
            )
        }
    }

    private fun getTask() {
        //TODO: Add getting tasks from the network
        _uiState.update { tests.random() }
    }
}