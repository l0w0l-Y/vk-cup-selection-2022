package com.kaleksandra.vkcupselection2022

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.kaleksandra.coretheme.Dimen
import com.kaleksandra.coreui.VKButton
import com.kaleksandra.corecommon.R as CoreCommonR

@Composable
fun MainScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimen.padding_8),
        modifier = Modifier
            .padding(Dimen.padding_16)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        VKButton(
            text = stringResource(CoreCommonR.string.button_poll),
            onClick = { navController.navigate("poll") },
            modifier = Modifier.fillMaxWidth(),
        )
        VKButton(
            text = stringResource(CoreCommonR.string.button_map_elements),
            onClick = { navController.navigate("map") },
            modifier = Modifier.fillMaxWidth(),
        )
        VKButton(
            text = stringResource(CoreCommonR.string.button_drag_gap),
            onClick = { navController.navigate("drag") },
            modifier = Modifier.fillMaxWidth(),
        )
        VKButton(
            text = stringResource(CoreCommonR.string.button_fill_gap),
            onClick = { navController.navigate("fill") },
            modifier = Modifier.fillMaxWidth(),
        )
        VKButton(
            text = stringResource(CoreCommonR.string.button_rate),
            onClick = { navController.navigate("rate") },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}