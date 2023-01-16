package com.kaleksandra.featuremapelements

data class Elements(
    val firsts: List<Element> = listOf(),
    val seconds: List<Element> = listOf(),
    val pairs: List<Pair<Int, Int>> = listOf(),
)