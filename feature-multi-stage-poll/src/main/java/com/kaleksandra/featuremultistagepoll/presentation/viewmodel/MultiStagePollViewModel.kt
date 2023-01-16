package com.kaleksandra.featuremultistagepoll.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaleksandra.featuremultistagepoll.presentation.model.Answer
import com.kaleksandra.featuremultistagepoll.presentation.model.AnswerState
import com.kaleksandra.featuremultistagepoll.presentation.model.Question
import com.kaleksandra.featuremultistagepoll.presentation.model.UIState
import com.kaleksandra.featuremultistagepoll.presentation.ui.core.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MultiStagePollViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    init {
        viewModelScope.launch {
            delay(20000)
        }
        //TODO: Only for preview
        _uiState.update {
            it.copy(
                question = Question(
                    Random.nextInt(1, Int.MAX_VALUE),
                    "Первый вопрос",
                    listOf("один", "два", "три", "четыре")
                ),
                pollSize = Int.MAX_VALUE,
            )
        }
    }

    fun getAnswer(index: Int) {
        //TODO: Only for preview
        _uiState.update {
            it.copy(
                answer = Answer(
                    correct = 2,
                    your = index,
                    listOf(
                        AnswerState(
                            "first",
                            "Good answer, but not correct",
                            getType(0, index, 2),
                            "13.4%"
                        ),
                        AnswerState(
                            "first",
                            "Good answer, but not correct",
                            getType(1, index, 2),
                            "25.0%"
                        ),
                        AnswerState(
                            "first",
                            "Good answer, but not correct",
                            getType(2, index, 2),
                            "1.6%"
                        ),
                        AnswerState(
                            "first",
                            "Good answer, but not correct",
                            getType(3, index, 2),
                            "60.0%"
                        )
                    )
                )
            )
        }
    }

    //TODO: Only for preview
    private fun getType(count: Int, your: Int, correct: Int): Type {
        return if (correct == count) Type.CORRECT else if (your != count) Type.DEFAULT else Type.UNCORRECT
    }
}