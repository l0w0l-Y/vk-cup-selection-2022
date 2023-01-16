package com.kaleksandra.featuremultistagepoll.presentation.model

data class Answer(
    val correct: Int = 0,
    val your: Int = 0,
    val state: List<AnswerState>,
)