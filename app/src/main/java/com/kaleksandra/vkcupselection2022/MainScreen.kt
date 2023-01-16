package com.kaleksandra.vkcupselection2022

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.kaleksandra.coretheme.Dimen

@Composable
fun MainScreen(navController: NavController) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimen.padding_12)) {
        Button({ navController.navigate("poll") }) {
            Text("Многоступенчатый опрос")
        }
        Button({ navController.navigate("map") }) {
            Text("Сопоставление элементов")
        }
        Button({ navController.navigate("drag") }) {
            Text("Перетаскивание вариантов")
        }
        Button({ navController.navigate("fill") }) {
            Text(" Заполнение пропуска")
        }
        Button({ navController.navigate("rate") }) {
            Text("Оценка прочитанной статьи")
        }
    }
}