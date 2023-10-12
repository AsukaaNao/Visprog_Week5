package com.example.visprog_week5_0706012210009

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.visprog_week5_0706012210009.ui.theme.VisProg_Week5_0706012210009Theme
import com.example.visprog_week5_0706012210009.ui.view.GuessNumberGame
import com.example.visprog_week5_0706012210009.viewModel.GuessNumberViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VisProg_Week5_0706012210009Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GuessNumberGame(GuessNumberViewModel())
                }
            }
        }
    }
}