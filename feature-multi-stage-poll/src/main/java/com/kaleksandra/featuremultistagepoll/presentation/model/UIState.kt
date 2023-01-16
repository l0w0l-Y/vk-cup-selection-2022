package com.kaleksandra.featuremultistagepoll.presentation.model

data class UIState(
    val question: Question = Question(),
    val answer: Answer? = null,
    val pollSize: Int = 0,
)