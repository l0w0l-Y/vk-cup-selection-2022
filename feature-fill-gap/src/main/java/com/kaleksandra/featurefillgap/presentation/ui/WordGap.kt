package com.kaleksandra.featurefillgap.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.kaleksandra.coretheme.AppTheme
import com.kaleksandra.coretheme.Dimen

@Composable
fun WordGap(words: String, onWordChange: (String) -> Unit) {
    BasicTextField(
        value = words,
        onValueChange = onWordChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .background(
                AppTheme.colors.secondary, RoundedCornerShape(Dimen.radius_20)
            )
            .padding(vertical = Dimen.padding_8, horizontal = Dimen.padding_12)
            .width(IntrinsicSize.Min)
            .widthIn(Dimen.width_40, Dimen.width_120),
    )
}