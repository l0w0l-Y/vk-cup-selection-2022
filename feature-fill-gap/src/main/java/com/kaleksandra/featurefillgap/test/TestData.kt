package com.kaleksandra.featurefillgap.test

import com.kaleksandra.featurefillgap.presentation.model.UIState

val tests = listOf(
    UIState(
        text = listOf("Величие не в", ", а в"),
        gaps = listOf("", ""),
    ),
    UIState(
        text = listOf("", "не может принести благодать, если", "его не заслуживает."),
        gaps = listOf("", ""),
    ),
    UIState(
        text = listOf("Жизнь - это", ", которую мы живем, и", "которую мы создаем."),
        gaps = listOf("", ""),
    ),
    UIState(
        text = listOf("Никакой", "не может быть слишком высокой, как для того, чтобы достичь ее с"),
        gaps = listOf("", ""),
    ),
    UIState(
        text = listOf(
            "",
            "не означает, что ты всегда будешь прав, но это значит, что ты никогда не должен бояться"
        ),
        gaps = listOf("", ""),
    ),
)