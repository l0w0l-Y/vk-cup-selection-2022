package com.kaleksandra.featurefillgap

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.kaleksandra.coretheme.AppTheme
import com.kaleksandra.coretheme.Dimen

@Composable

fun FillGapScreen(
    navController: NavController,
    viewModel: FillViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    FillGapScreen(
        uiState = uiState,
        onChangeWord = viewModel::onChangeWord,
        onContinueClick = {
            navController.navigate("fill") {
                popUpTo("fill") {
                    inclusive = true
                }
            }
        },
    )
}

@Composable
fun FillGapScreen(
    uiState: UIState,
    onContinueClick: () -> Unit,
    onChangeWord: (Int, String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(Dimen.padding_16),
        verticalArrangement = Arrangement.spacedBy(Dimen.padding_16)
    ) {
        FlowRow(
            mainAxisSpacing = Dimen.axis_4,
            crossAxisSpacing = Dimen.axis_4,
            crossAxisAlignment = FlowCrossAxisAlignment.Center,
        ) {
            val textIterator = uiState.text.iterator()
            val gapsIterator = uiState.gaps.listIterator()
            while (textIterator.hasNext() || gapsIterator.hasNext()) {
                if (textIterator.hasNext()) {
                    Text(textIterator.next())
                }
                if (gapsIterator.hasNext()) {
                    val index = gapsIterator.nextIndex()
                    val word = gapsIterator.next()
                    WordGap(word) { onChangeWord(index, it) }
                }
            }
        }
        AnimatedVisibility(uiState.gaps.all { it.isNotEmpty() }) {
            Button(onClick = onContinueClick) {
                Text(text = "Продолжить")
            }
        }
    }
}

@Composable
fun WordGap(words: String, onWordChange: (String) -> Unit) {
    BasicTextField(
        value = words,
        onValueChange = onWordChange,
        singleLine = true,
        modifier = Modifier
            .background(AppTheme.colors.secondary, RoundedCornerShape(Dimen.radius_20))
            .padding(vertical = Dimen.padding_8, horizontal = Dimen.padding_12)
            .width(IntrinsicSize.Min)
            .widthIn(Dimen.width_40, Dimen.width_120),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    )
}