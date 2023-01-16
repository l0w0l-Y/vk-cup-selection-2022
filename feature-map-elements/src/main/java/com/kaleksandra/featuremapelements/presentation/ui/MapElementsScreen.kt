package com.kaleksandra.featuremapelements.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kaleksandra.coretheme.AppTheme
import com.kaleksandra.coretheme.Dimen
import com.kaleksandra.coreui.VKButton
import com.kaleksandra.featuremapelements.R
import com.kaleksandra.featuremapelements.presentation.model.Element
import com.kaleksandra.featuremapelements.presentation.model.UIState
import com.kaleksandra.featuremapelements.presentation.viewmodel.MapElementsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.kaleksandra.corecommon.R as CoreCommonR

private const val defaultDelay: Long = 1500

@Composable
fun MapElementsScreen(
    navController: NavController,
    viewModel: MapElementsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    MapElementsScreen(uiState, viewModel::onSelectBoth,
        onContinueClick = {
            navController.navigate("map") {
                popUpTo("map") {
                    inclusive = true
                }
            }
        }
    )
}

@Composable
fun MapElementsScreen(
    uiState: UIState,
    onCompareAnswer: (Int, Int) -> Unit,
    onContinueClick: () -> Unit,
) {
    var first by remember { mutableStateOf<Int?>(null) }
    var second by remember { mutableStateOf<Int?>(null) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = first, key2 = second) {
        if (first != null && second != null) {
            onCompareAnswer(first!!, second!!)
            scope.launch { delay(defaultDelay) }
            first = null
            second = null
        }
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimen.padding_16),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(Dimen.padding_16),
    ) {
        Text(
            text = stringResource(id = R.string.title_map_elements),
            style = MaterialTheme.typography.headlineSmall,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimen.padding_8)
        ) {
            Elements(
                value = first, list = uiState.elements.firsts,
                modifier = Modifier.weight(Dimen.weight_1)
            ) { first = it }
            Elements(
                value = second, list = uiState.elements.seconds,
                modifier = Modifier.weight(Dimen.weight_1)
            ) { second = it }
        }
        AnimatedVisibility(uiState.elements.firsts.all { it.complete }) {
            VKButton(
                text = stringResource(id = CoreCommonR.string.button_continue),
                onClick = onContinueClick,
            )
        }
    }
}

@Composable
fun Elements(
    value: Int?,
    list: List<Element>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(Dimen.padding_8),
        modifier = modifier
    ) {
        items(list) {
            Button(
                onClick = { onClick(it.id) },
                enabled = !it.complete,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (value == it.id) AppTheme.colors.primary else AppTheme.colors.secondary,
                    disabledContainerColor = AppTheme.colors.tertiary
                )
            ) {
                Text(
                    text = it.value,
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppTheme.colors.symbolPrimary,
                )

            }
        }
    }
}