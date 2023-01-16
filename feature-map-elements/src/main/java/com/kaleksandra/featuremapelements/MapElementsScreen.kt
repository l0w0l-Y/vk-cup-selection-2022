package com.kaleksandra.featuremapelements

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kaleksandra.coretheme.AppTheme
import com.kaleksandra.coretheme.Dimen

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
    LaunchedEffect(key1 = first, key2 = second) {
        if (first != null && second != null) {
            onCompareAnswer(first!!, second!!)
            first = null
            second = null
        }
    }
    Column() {
        Row(
            modifier = Modifier.padding(horizontal = Dimen.padding_16),
            horizontalArrangement = Arrangement.spacedBy(Dimen.padding_8)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(Dimen.padding_8),
                modifier = Modifier.weight(1f)
            ) {
                items(uiState.elements.firsts) {
                    Button(
                        onClick = { first = it.id },
                        enabled = !it.complete,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = if (first == it.id) AppTheme.colors.secondary else AppTheme.colors.primary)
                    ) {
                        Text(text = it.value)
                    }
                }
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(Dimen.padding_8),
                modifier = Modifier.weight(1f)
            ) {
                items(uiState.elements.seconds) {
                    Button(
                        { second = it.id },
                        enabled = !it.complete,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = if (second == it.id) AppTheme.colors.secondary else AppTheme.colors.primary)
                    ) {
                        Text(text = it.value)
                    }
                }
            }
        }
        AnimatedVisibility(uiState.elements.firsts.all { it.complete }) {
            Button(onClick = onContinueClick) {
                Text(text = "Продолжить")
            }
        }
    }
}