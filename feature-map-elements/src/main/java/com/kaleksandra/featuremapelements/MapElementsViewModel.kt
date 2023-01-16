package com.kaleksandra.featuremapelements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapElementsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    init {
        viewModelScope.launch {
            delay(20000)
        }
        //TODO: Only for preview
        _uiState.update {
            it.copy(
                elements = Elements(
                    listOf(
                        Element(0, "first"),
                        Element(1, "second"),
                        Element(2, "third"),
                        Element(3, "fourth")
                    ), listOf(
                        Element(0, "first"),
                        Element(1, "second"),
                        Element(2, "third"),
                        Element(3, "fourth")
                    ), listOf(
                        0 to 0,
                        1 to 1,
                        2 to 2,
                        3 to 3,
                    )
                )
            )
        }
        _uiState.update {
            it.copy(
                elements = it.elements.copy(
                    firsts = it.elements.firsts.shuffled(),
                    seconds = it.elements.seconds.shuffled(),
                )
            )
        }
    }

    fun onSelectBoth(first: Int, second: Int) {
        val pair = _uiState.value.elements.pairs.first { it.first == first }
        if (pair.second == second ) {
            completeElement(first, second)
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