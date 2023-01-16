package com.kaleksandra.featuremultistagepoll.presentation.model

data class Question(
    val question: String = "",
    val answer: List<String> = emptyList(),
)