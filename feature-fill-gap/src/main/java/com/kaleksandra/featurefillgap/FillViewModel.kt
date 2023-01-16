package com.kaleksandra.featurefillgap

import android.util.Log
import androidx.lifecycle.ViewModel
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
        _uiState.update {
            it.copy(
                text = listOf("Текст", "несколькими пропусками", "разными вариантами"),
                options = listOf("c", "через", "за", "и"),
                gaps = listOf("", "", ""),
            )
        }
    }

    fun onChangeWord(index: Int, word: String) {
        Log.d("INDEX", index.toString())
        _uiState.update {
            it.copy(
                gaps = it.gaps.mapIndexed { indexs, value -> if (indexs == index) word else value }
            )
        }
    }
}