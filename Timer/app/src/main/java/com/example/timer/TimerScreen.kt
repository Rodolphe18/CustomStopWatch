package com.example.timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale


@Composable
fun TimerScreen(
    timerValue: Long,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = timerValue.formatTimerValue(),
            fontSize = 16.sp
        )
        Button(modifier = Modifier.padding(bottom = 16.dp), onClick = { onStart() }) {
            Text(text = "START")
        }
        Button(modifier = Modifier.padding(bottom = 16.dp), onClick = { onPause() }) {
            Text(text = "PAUSE")
        }
        Button(modifier = Modifier.padding(bottom = 16.dp), onClick = { onReset() }) {
            Text(text = "RESET")
        }
    }
}


private fun Long.formatTimerValue(): String {
    val seconds = (this / 1000) % 60
    val minutes = (this / 1000) / 60
    val milliseconds = (this % 1000) / 10
    return String.format(Locale.FRANCE, "%02d:%02d:%02d", minutes, seconds, milliseconds)
}