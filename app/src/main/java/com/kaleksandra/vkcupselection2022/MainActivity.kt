package com.kaleksandra.vkcupselection2022

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kaleksandra.coretheme.VkCupSelection2022Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkCupSelection2022Theme {

            }
        }
    }
}