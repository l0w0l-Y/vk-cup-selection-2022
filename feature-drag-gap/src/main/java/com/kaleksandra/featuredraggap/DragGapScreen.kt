package com.kaleksandra.featuredraggap

import DragTarget
import DropTarget
import LongPressDraggable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.kaleksandra.coretheme.AppTheme
import com.kaleksandra.coretheme.Dimen

@Composable
fun DragGapScreen(
    navController: NavController,
    viewModel: DragGapViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    DragGapScreen(
        uiState = uiState,
        onChangeWord = viewModel::onChangeWord,
        onContinueClick = {
            navController.navigate("drag") {
                popUpTo("drag") {
                    inclusive = true
                }
            }
        },
    )
}

@Composable
fun DragGapScreen(
    uiState: UIState,
    onContinueClick: () -> Unit,
    onChangeWord: (Int, String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimen.padding_16)) {
        LongPressDraggable(modifier = Modifier.fillMaxSize()) {
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
                FlowRow(
                    mainAxisSpacing = Dimen.axis_4,
                    crossAxisSpacing = Dimen.axis_8,
                    crossAxisAlignment = FlowCrossAxisAlignment.Center,
                ) {
                    uiState.options.forEach {
                        WordOption(word = it)
                    }
                }
                AnimatedVisibility(uiState.gaps.all { it.isNotEmpty() }) {
                    Button(onClick = onContinueClick) {
                        Text(text = "Продолжить")
                    }
                }
            }
        }
    }
}

@Composable
fun WordGap(words: String, onChangeWord: (String) -> Unit) {
    DropTarget<String> { isInBound, word ->
        word?.let {
            if (isInBound) {
                onChangeWord(it)
            }
        }
        Column(
            modifier = Modifier
                .background(
                    if (words.isNotEmpty()) AppTheme.colors.primary else AppTheme.colors.secondary,
                    RoundedCornerShape(Dimen.radius_20)
                )
                .padding(vertical = Dimen.padding_8, horizontal = Dimen.padding_12)
                .defaultMinSize(minWidth = Dimen.width_24),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = words,
                color = if (isInBound) AppTheme.colors.disabled else AppTheme.colors.symbolPrimary
            )
        }
    }
}

@Composable
fun WordOption(word: String) {
    DragTarget(dataToDrop = word) {
        Column(
            modifier = Modifier
                .background(AppTheme.colors.secondary, RoundedCornerShape(Dimen.radius_20))
                .padding(vertical = Dimen.padding_8, horizontal = Dimen.padding_12)
                .defaultMinSize(minWidth = Dimen.width_24),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = word)
        }
    }
}
