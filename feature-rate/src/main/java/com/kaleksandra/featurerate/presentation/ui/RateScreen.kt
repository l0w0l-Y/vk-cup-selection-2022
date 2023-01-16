package com.kaleksandra.featurerate.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kaleksandra.coretheme.AppTheme
import com.kaleksandra.coretheme.Dimen
import com.kaleksandra.coreui.VKButton
import com.kaleksandra.featurerate.R
import com.kaleksandra.corecommon.R as CoreCommonR

private const val initialRating: Float = 0F
private const val defaultStarsCount: Int = 5

@Composable
fun RatingBar(navController: NavController) {
    RatingBar {
        // TODO: Add rating processing in the viewmodel
        navController.navigate("rate") {
            popUpTo("rate") {
                inclusive = true
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingBar(onContinueClick: (Float) -> Unit) {
    var rating by remember { mutableStateOf(initialRating) }
    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimen.padding_16)
        ) {
            Text(
                text = stringResource(id = R.string.title_article_rating),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = Dimen.padding_12)
            )
            RatingBar(value = rating, onValueChange = { rating = it })
            AnimatedVisibility(rating != initialRating) {
                VKButton(
                    text = stringResource(id = CoreCommonR.string.button_continue),
                    onClick = { onContinueClick(rating) },
                )
            }
        }
    }
}

@Composable
fun RatingBar(
    width: Int,
    rating: Double = initialRating.toDouble(),
    stars: Int = defaultStarsCount,
) {
    val filledStars = rating.toInt() + if (rating % 1 > 0.8F) 1 else 0
    val halfStar = if (rating % 1 > 0.3F && rating % 1 < 0.8F) 1 else 0
    val unfilledStars = stars - filledStars - halfStar

    Row(modifier = Modifier.fillMaxWidth()) {
        repeat(filledStars) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = stringResource(id = R.string.icon_desc_rating_star),
                tint = AppTheme.colors.primary,
                modifier = Modifier.size((width / stars).dp)
            )
        }

        if (halfStar != 0) {
            Icon(
                painter = painterResource(id = R.drawable.ic_outlined_star),
                contentDescription = stringResource(id = R.string.icon_desc_rating_star),
                tint = AppTheme.colors.primary,
                modifier = Modifier.size((width / stars).dp),
            )
        }

        repeat(unfilledStars) {
            Icon(
                painter = painterResource(id = R.drawable.ic_outlined_star),
                contentDescription = stringResource(id = R.string.icon_desc_rating_star),
                tint = AppTheme.colors.disabled,
                modifier = Modifier.size((width / stars).dp),
            )
        }
    }
}