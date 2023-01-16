package com.kaleksandra.featuremapelements.presentation

import com.kaleksandra.featuremapelements.presentation.model.Element
import com.kaleksandra.featuremapelements.presentation.model.Elements

val tests = listOf(
    Elements(
        listOf(
            Element(0, "Смешарики"),
            Element(1, "Телепузики"),
            Element(2, "Гравити фолз"),
            Element(3, "Холодное сердце")
        ), listOf(
            Element(0, "Крош"),
            Element(1, "По"),
            Element(2, "Мейбл"),
            Element(3, "Эльза")
        ), listOf(
            0 to 0,
            1 to 1,
            2 to 2,
            3 to 3,
        )
    ),
    Elements(
        listOf(
            Element(0, "Русский"),
            Element(1, "Английский"),
            Element(2, "Испанский"),
            Element(3, "Французский")
        ), listOf(
            Element(0, "Привет"),
            Element(1, "Hello"),
            Element(2, "Oye"),
            Element(3, "Hé")
        ), listOf(
            0 to 0,
            1 to 1,
            2 to 2,
            3 to 3,
        )
    )
)