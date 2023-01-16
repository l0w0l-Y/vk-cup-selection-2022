package com.kaleksandra.featuredraggap.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.kaleksandra.coretheme.AppTheme
import com.kaleksandra.coretheme.Dimen
import com.kaleksandra.coreui.VKButton
import com.kaleksandra.featuredraggap.R
import com.kaleksandra.featuredraggap.presentation.ext.DragTarget
import com.kaleksandra.featuredraggap.presentation.ext.DropTarget
import com.kaleksandra.featuredraggap.presentation.ext.LongPressDraggable
import com.kaleksandra.featuredraggap.presentation.model.UIState
import com.kaleksandra.featuredraggap.presentation.viewmodel.DragGapViewModel
import com.kaleksandra.corecommon.R as CoreCommonR

@Composable
fun DragGapScreen(
    navController: NavController,
    viewModel: DragGapViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    DragGapScreen(
        uiState = uiState,
        onWordChange = viewModel::onWordChange,
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
    onWordChange: (Int, String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimen.padding_16),
    ) {
        LongPressDraggable(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimen.padding_16),
                verticalArrangement = Arrangement.spacedBy(Dimen.padding_16),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.title_drag_gap),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                )
                FlowRow(
                    mainAxisSpacing = Dimen.axis_4,
                    crossAxisSpacing = Dimen.axis_4,
                    crossAxisAlignment = FlowCrossAxisAlignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val textIterator = uiState.text.iterator()
                    val gapsIterator = uiState.gaps.listIterator()
                    while (textIterator.hasNext() || gapsIterator.hasNext()) {
                        if (textIterator.hasNext()) {
                            val text = textIterator.next().split(' ')
                            text.forEach {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                        if (gapsIterator.hasNext()) {
                            val index = gapsIterator.nextIndex()
                            val word = gapsIterator.next()
                            WordGap(word) { onWordChange(index, it) }
                        }
                    }
                }
                FlowRow(
                    mainAxisSpacing = Dimen.axis_8,
                    crossAxisSpacing = Dimen.axis_8,
                    crossAxisAlignment = FlowCrossAxisAlignment.Center,
                ) {
                    uiState.options.forEach {
                        WordOption(word = it)
                    }
                }
                AnimatedVisibility(uiState.gaps.all { it.isNotEmpty() }) {
                    VKButton(
                        text = stringResource(id = CoreCommonR.string.button_continue),
                        onClick = onContinueClick
                    )
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
                    color = if (words.isNotEmpty()) AppTheme.colors.primary else AppTheme.colors.secondary,
                    shape = RoundedCornerShape(Dimen.radius_20)
                )
                .padding(vertical = Dimen.padding_8, horizontal = Dimen.padding_12)
                .defaultMinSize(minWidth = Dimen.width_24),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = words,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
fun WordOption(word: String) {
    DragTarget(dataToDrop = word) {
        Column(
            modifier = Modifier
                .background(
                    color = AppTheme.colors.secondary,
                    shape = RoundedCornerShape(Dimen.radius_20)
                )
                .padding(vertical = Dimen.padding_8, horizontal = Dimen.padding_12)
                .defaultMinSize(minWidth = Dimen.width_24),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = word, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
