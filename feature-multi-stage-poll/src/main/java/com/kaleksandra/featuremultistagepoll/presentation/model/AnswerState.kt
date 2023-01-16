package com.kaleksandra.featuremultistagepoll.presentation.model

data class AnswerState(
    val answer: String,
    val description: String?,
    val type: Type,
    val percentage: String,
)