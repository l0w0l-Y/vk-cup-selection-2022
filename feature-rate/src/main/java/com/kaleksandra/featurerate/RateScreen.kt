package com.kaleksandra.featurerate

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kaleksandra.coretheme.AppTheme.colors
import com.kaleksandra.coretheme.Dimen

@Composable
fun RateScreen(navController: NavController) {
    RateScreen {
        navController.navigate("rate") {
            popUpTo("rate") {
                inclusive = true
            }
        }
    }
}

@Composable
fun RateScreen(onContinueClick: () -> Unit) {
    var rate by remember { mutableStateOf(0F) }
    Column {
        RatingBar(rate) { rate = it }
        AnimatedVisibility(rate != 0F) {
            Button(onContinueClick) {
                Text("Продолжить")
            }
        }
    }
}

@Composable
fun RateScreen(
    width: Int,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {
    val filledStars = rating.toInt() + if (rating % 1 > 0.8F) 1 else 0
    val halfStar = if (rating % 1 > 0.3F && rating % 1 < 0.8F) 1 else 0
    Log.d("rating", rating.toString())
    Log.d("filledStars", filledStars.toString())
    Log.d("halfStar", halfStar.toString())
    val unfilledStars = stars - filledStars - halfStar

    Row(modifier = Modifier.fillMaxWidth()) {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.Blue,
                modifier = Modifier.size((width / stars).dp)
            )
        }

        if (halfStar != 0) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size((width / stars).dp),
            )
        }

        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size((width / stars).dp),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingBar(value: Float, onValueChange: (Float) -> Unit) {
    var rating by remember { mutableStateOf(value) }
    val interactionSource = MutableInteractionSource()
    val width = 240
    Box(modifier = Modifier.width(width.dp), contentAlignment = Alignment.Center) {
        RateScreen(width = width, rating = rating.toDouble())
        Slider(
            value = rating,
            onValueChange = {
                rating = it.coerceIn(1F, 5F)
                onValueChange(rating)
            },
            colors = SliderDefaults.colors(
                Color.Transparent,
                Color.Transparent,
                Color.Transparent,
                Color.Transparent,
            ),
            valueRange = 1f..5f,
            interactionSource = interactionSource,
            thumb = remember(interactionSource, colors, true) { {} },
            modifier = Modifier
                .padding(Dimen.padding_12)
                .fillMaxWidth(),
        )
    }
}