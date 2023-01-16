package com.kaleksandra.featurefillgap.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.kaleksandra.coretheme.Dimen
import com.kaleksandra.coreui.VKButton
import com.kaleksandra.featurefillgap.R
import com.kaleksandra.featurefillgap.presentation.model.UIState
import com.kaleksandra.featurefillgap.presentation.viewmodel.FillViewModel
import com.kaleksandra.corecommon.R as CoreCommonR

@Composable
fun FillGapScreen(
    navController: NavController,
    viewModel: FillViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    FillGapScreen(
        uiState = uiState,
        onChangeWord = viewModel::onWordChange,
        onContinueClick = {
            navController.navigate("fill") {
                popUpTo("fill") {
                    inclusive = true
                }
            }
        },
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillGapScreen(
    uiState: UIState,
    onContinueClick: () -> Unit,
    onChangeWord: (Int, String) -> Unit,
) {
    Scaffold {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimen.padding_16),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimen.padding_16),
        ) {
            Text(
                text = stringResource(id = R.string.title_fill_gap),
                style = MaterialTheme.typography.headlineSmall
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
                        text.forEach { Text(text = it, style = MaterialTheme.typography.bodySmall) }
                    }
                    if (gapsIterator.hasNext()) {
                        val index = gapsIterator.nextIndex()
                        val word = gapsIterator.next()
                        WordGap(word) { onChangeWord(index, it) }
                    }
                }
            }
            AnimatedVisibility(uiState.gaps.all { it.isNotEmpty() }) {
                VKButton(
                    text = stringResource(id = CoreCommonR.string.button_continue),
                    onClick = onContinueClick,
                )
            }
        }
    }
}