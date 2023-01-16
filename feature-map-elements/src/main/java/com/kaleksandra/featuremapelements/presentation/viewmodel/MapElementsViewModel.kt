package com.kaleksandra.featuremapelements.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.kaleksandra.featuremapelements.presentation.model.Elements
import com.kaleksandra.featuremapelements.presentation.model.UIState
import com.kaleksandra.featuremapelements.presentation.tests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MapElementsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    init {
        getTask()
    }

    fun onSelectBoth(first: Int, second: Int) {
        val pair = _uiState.value.elements.pairs.first { it.first == first }
        if (pair.second == second) {
            completeElement(first, second)
        }
    }

    private fun getTask() {
        //TODO: Add getting tasks from the network
        val elements = tests.random()
        _uiState.update {
            UIState(
                elements = Elements(
                    firsts = elements.firsts.shuffled(),
                    seconds = elements.seconds.shuffled(),
                    pairs = elements.pairs
                )
            )
        }
    }

    private fun completeElement(first: Int, second: Int) {
        _uiState.update {
            it.copy(
                elements = _uiState.value.elements.copy(
                    firsts = _uiState.value.elements.firsts.map { elements ->
                        if (elements.id == first) {
                            elements.copy(complete = true)
                        } else elements
                    },
                    seconds = _uiState.value.elements.seconds.map { elements ->
                        if (elements.id == second) {
                            elements.copy(complete = true)
                        } else elements
                    })
            )
        }
    }
}