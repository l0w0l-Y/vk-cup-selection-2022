package com.kaleksandra.featuremultistagepoll.presentation.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kaleksandra.corecommon.R
import com.kaleksandra.coretheme.Dimen
import com.kaleksandra.coretheme.VkCupSelection2022Theme
import com.kaleksandra.coreui.VKButton
import com.kaleksandra.featuremultistagepoll.presentation.model.Question
import com.kaleksandra.featuremultistagepoll.presentation.model.UIState
import com.kaleksandra.featuremultistagepoll.presentation.ui.core.PollButton
import com.kaleksandra.featuremultistagepoll.presentation.ui.core.PollResult
import com.kaleksandra.featuremultistagepoll.presentation.viewmodel.MultiStagePollViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MultiStagePollScreen(
    navController: NavController,
    viewModel: MultiStagePollViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    MultiStagePollScreen(
        uiState = uiState,
        onOptionClick = viewModel::getAnswer,
        onContinueClick = {
            navController.navigate("poll") {
                popUpTo("poll") {
                    inclusive = true
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MultiStagePollScreen(
    uiState: UIState,
    onOptionClick: (Int) -> Unit,
    onContinueClick: () -> Unit,
) {
    Scaffold {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimen.padding_16),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(Dimen.padding_16)
                .fillMaxWidth(),
        ) {
            with(uiState) {
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth()
                )

                LazyColumn(verticalArrangement = Arrangement.spacedBy(Dimen.padding_8)) {
                    if (answer == null) {
                        itemsIndexed(question.answer) { index, value ->
                            AnimatedContent(
                                targetState = question,
                                transitionSpec = {
                                    fadeIn() with fadeOut()
                                },
                            ) {
                                PollButton(
                                    answer = value,
                                    onButtonClick = { onOptionClick(index) },
                                )
                            }
                        }
                    } else {
                        items(answer.state) { value ->
                            PollResult(
                                answer = value.answer,
                                description = value.description,
                                type = value.type,
                                percentage = value.percentage
                            )
                        }
                    }
                }
                AnimatedVisibility(answer != null) {
                    VKButton(
                        text = stringResource(id = R.string.button_continue),
                        onClick = onContinueClick,
                    )
                }
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun Preview() {
    VkCupSelection2022Theme {
        MultiStagePollScreen(
            UIState(
                question = Question("Первый вопрос", listOf("один", "два", "три", "четыре")),
            ), {}, {}
        )
    }
}