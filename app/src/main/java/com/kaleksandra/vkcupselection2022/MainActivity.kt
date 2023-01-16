package com.kaleksandra.vkcupselection2022

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kaleksandra.coretheme.VkCupSelection2022Theme
import com.kaleksandra.featuredraggap.presentation.ui.DragGapScreen
import com.kaleksandra.featurefillgap.presentation.ui.FillGapScreen
import com.kaleksandra.featuremapelements.MapElementsScreen
import com.kaleksandra.featuremultistagepoll.presentation.ui.MultiStagePollScreen
import com.kaleksandra.featurerate.presentation.ui.RatingBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkCupSelection2022Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {
                    composable("main") { MainScreen(navController) }
                    composable("poll") { MultiStagePollScreen(navController) }
                    composable("map") { MapElementsScreen(navController) }
                    composable("rate") { RatingBar(navController) }
                    composable("drag") { DragGapScreen(navController) }
                    composable("fill") { FillGapScreen(navController) }
                }
            }
        }
    }
}