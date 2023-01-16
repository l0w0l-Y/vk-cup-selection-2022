package com.kaleksandra.featuredraggap.presentation.model

data class UIState(
    val text: List<String> = listOf(),
    val options: List<String> = listOf(),
    val gaps: List<String> = listOf(),
)