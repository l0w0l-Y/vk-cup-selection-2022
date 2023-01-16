package com.kaleksandra.featurerate.presentation.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kaleksandra.coretheme.AppTheme
import com.kaleksandra.coretheme.Dimen

private const val defaultStartValue: Float = 1F
private const val defaultEndValue: Float = 5F
private const val defaultWidth: Int = 240

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingBar(
    value: Float,
    onValueChange: (Float) -> Unit,
    width: Int = defaultWidth,
    startValue: Float = defaultStartValue,
    endValue: Float = defaultEndValue,
) {
    var rating by remember { mutableStateOf(value) }
    val interactionSource = MutableInteractionSource()
    Box(modifier = Modifier.width(width.dp)) {
        RatingBar(width = width, rating = rating.toDouble())
        Slider(
            value = rating,
            onValueChange = {
                rating = it.coerceIn(startValue, endValue)
                onValueChange(rating)
            },
            colors = SliderDefaults.colors(
                Color.Transparent,
                Color.Transparent,
                Color.Transparent,
                Color.Transparent,
            ),
            valueRange = startValue..endValue,
            interactionSource = interactionSource,
            thumb = remember(interactionSource, AppTheme.colors, true) { {} },
            modifier = Modifier
                .padding(Dimen.padding_12)
                .fillMaxWidth(),
        )
    }
}