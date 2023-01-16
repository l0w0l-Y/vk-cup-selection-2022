package com.kaleksandra.featuremultistagepoll.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.kaleksandra.featuremultistagepoll.presentation.model.Answer
import com.kaleksandra.featuremultistagepoll.presentation.model.AnswerState
import com.kaleksandra.featuremultistagepoll.presentation.model.Question
import com.kaleksandra.featuremultistagepoll.presentation.model.Type
import com.kaleksandra.featuremultistagepoll.presentation.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MultiStagePollViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    init {
        getTask()
    }

    fun getAnswer(index: Int) {
        //TODO: Add getting answer from the network
        _uiState.update {
            it.copy(
                answer = Answer(
                    correct = 2,
                    your = index,
                    listOf(
                        AnswerState(
                            "Оранжевый жираф",
                            "Тут же даже рифмы нет :(",
                            getType(0, index, 2),
                            "13.4%"
                        ),
                        AnswerState(
                            "Синий слон",
                            "А слоны точно синие?",
                            getType(1, index, 2),
                            "25.0%"
                        ),
                        AnswerState(
                            "Зеленый попугай",
                            "И зеленый попугааай!",
                            getType(2, index, 2),
                            "1.6%"
                        ),
                        AnswerState(
                            "Синий попугай",
                            "Почти верно, только попугай был зеленый",
                            getType(3, index, 2),
                            "60.0%"
                        )
                    )
                )
            )
        }
    }

    private fun getTask() {
        //TODO: Add getting tasks from the network
        _uiState.update {
            it.copy(
                question = Question(
                    "Ах, крокодилы, бегемоты!\n" +
                            "Ах, обезьяны, кашалоты!\n" +
                            "Ах, и зеленый...",
                    listOf("Оранжевый жираф", "Синий слон", "Зеленый попугай", "Синий попугай")
                ),
            )
        }
    }

    //TODO: Only for preview
    private fun getType(count: Int, your: Int, correct: Int): Type {
        return if (correct == count) Type.CORRECT else if (your != count) Type.DEFAULT else Type.UNCORRECT
    }
}