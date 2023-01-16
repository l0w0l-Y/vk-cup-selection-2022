package com.kaleksandra.featuredraggap.presentation

import com.kaleksandra.featuredraggap.presentation.model.UIState

val tests = listOf(
    UIState(
        text = listOf("Величие не в том, что ", ", а в кем"),
        options = listOf("имеешь", "являешься", "создаешь", "делаешь", "надеешься", "родишься"),
        gaps = listOf("", ""),
    ),
    UIState(
        text = listOf("", "не может принести благодать, если", "его не заслуживает."),
        options = listOf("Жизнь", "Мир", "Деньги", "создатель", "человек", "явец"),
        gaps = listOf("", ""),
    ),
    UIState(
        text = listOf("Жизнь - это", ", которую мы живем, и", "которую мы создаем."),
        options = listOf("история", "картина", "любовь"),
        gaps = listOf("", ""),
    ),
    UIState(
        text = listOf("Никакой", "не может быть слишком высокой, как для того, чтобы достичь ее с"),
        options = listOf("награды", "цели", "цены", "семьей", "друзьями", "честностью"),
        gaps = listOf("", ""),
    ),
    UIState(
        text = listOf(
            "",
            "не означает, что ты всегда будешь прав, но это значит, что ты никогда не должен бояться"
        ),
        options = listOf("Жизнь", "Деньги", "Связи", "Уверенность"),
        gaps = listOf("", ""),
    ),
)