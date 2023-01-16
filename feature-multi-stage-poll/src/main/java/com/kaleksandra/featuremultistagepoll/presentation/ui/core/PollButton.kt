package com.kaleksandra.featuremultistagepoll.presentation.ui.core

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kaleksandra.coretheme.AppTheme
import com.kaleksandra.coretheme.Dimen
import com.kaleksandra.coretheme.VkCupSelection2022Theme
import com.kaleksandra.featuremultistagepoll.presentation.model.Type
import com.kaleksandra.corecommon.R as CoreCommonR

@Composable
fun PollResult(answer: String, description: String?, type: Type, percentage: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimen.padding_16),
        modifier = Modifier
            .background(
                color = when (type) {
                    Type.CORRECT -> AppTheme.colors.correctBackground
                    Type.UNCORRECT -> AppTheme.colors.uncorrectBackground
                    Type.DEFAULT -> AppTheme.colors.secondary
                }, shape = RoundedCornerShape(Dimen.radius_8)
            )
            .padding(Dimen.padding_16)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimen.padding_12),
            modifier = Modifier.weight(Dimen.weight_1),
        ) {
            Text(
                text = answer,
                style = MaterialTheme.typography.bodyMedium,
                color = AppTheme.colors.symbolPrimary,
            )
            if (type == Type.CORRECT || type == Type.UNCORRECT) {
                description?.let {
                    Text(
                        text = it,
                        color = if (type == Type.CORRECT) AppTheme.colors.correct else AppTheme.colors.uncorrect
                    )
                }
            }
        }
        if (type == Type.CORRECT || type == Type.UNCORRECT) {
            Icon(
                imageVector = if (type == Type.CORRECT) Icons.Default.Check else Icons.Default.Close,
                contentDescription = stringResource(CoreCommonR.string.icon_desc_check),
                tint = if (type == Type.CORRECT) AppTheme.colors.correct else AppTheme.colors.uncorrect
            )
        }
        Text(
            text = percentage,
            style = MaterialTheme.typography.bodyMedium,
            color = AppTheme.colors.symbolPrimary,
        )
    }
}

@Composable
fun PollButton(answer: String, onButtonClick: () -> Unit, modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    AnimatedVisibility(
        visible = visible,
        enter = expandHorizontally(
            animationSpec = tween(500)
        ) + fadeIn(
            animationSpec = tween(800)
        ),
    ) {
        Button(
            onClick = onButtonClick,
            shape = RoundedCornerShape(Dimen.radius_8),
            colors = ButtonDefaults.buttonColors(containerColor = AppTheme.colors.secondary),
            contentPadding = PaddingValues(Dimen.padding_16),
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = answer,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = AppTheme.colors.symbolPrimary,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun Preview() {
    VkCupSelection2022Theme {
        Column(verticalArrangement = Arrangement.spacedBy(Dimen.padding_12)) {
            PollResult(
                "Первый ответ", "Тут пояснение от автора", Type.CORRECT, "28.4%"
            )
            PollResult(
                "Первый ответ", "Тут пояснение от автора", Type.UNCORRECT, "28.4%"
            )
            PollResult(
                "Первый ответ", "Тут пояснение от автора", Type.DEFAULT, "28.4%"
            )
            PollButton("Первый ответ", {})
            PollButton("Второй ответ", {})
            PollButton("Третий ответ", {})
        }
    }
}