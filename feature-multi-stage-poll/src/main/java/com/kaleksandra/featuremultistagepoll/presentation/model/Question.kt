package com.kaleksandra.featuremultistagepoll.presentation.model

data class Question(
    val number: Int = 0,
    val question: String = "",
    val answer: List<String> = emptyList(),
)