package com.example.timer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.timer.ui.theme.TimerTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimerTheme {
                    val viewModel by viewModels<TimerViewModel>()
                    val timer by viewModel.timer.collectAsState()
                    TimerScreen(timer, { viewModel.startTimer() }, {viewModel.pauseTimer()}, {viewModel.resetTimer()})
                }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerPreview() {
    TimerTheme {
        TimerScreen(0L, {} , {}, { })
    }
}