package com.bitmavrick.asopilot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bitmavrick.asopilot.ui.theme.ASOPilotTheme
import com.bitmavrick.feature.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ASOPilotTheme {
                HomeScreen()
            }
        }
    }
}