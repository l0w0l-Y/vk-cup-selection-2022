package com.kaleksandra.featuremultistagepoll.presentation.model

import com.kaleksandra.featuremultistagepoll.presentation.ui.core.Type

data class AnswerState(
    val answer: String,
    val description: String?,
    val type: Type,
    val percentage: String,
)