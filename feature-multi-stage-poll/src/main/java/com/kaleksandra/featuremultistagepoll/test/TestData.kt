package com.kaleksandra.featuremultistagepoll.test

import com.kaleksandra.featuremultistagepoll.presentation.model.Question
import com.kaleksandra.featuremultistagepoll.presentation.model.UIState


val test = UIState(
    question = Question(
        "Ах, крокодилы, бегемоты!\n" +
                "Ах, обезьяны, кашалоты!\n" +
                "Ах, и зеленый...",
        listOf("Оранжевый жираф", "Синий слон", "Зеленый попугай", "Синий попугай")
    ),
)