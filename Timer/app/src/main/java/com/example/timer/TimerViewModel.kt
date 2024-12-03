package com.example.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private var startTime = 0L
    private var isRunning = false

    private var job: Job? = null

    private var timePaused = 0L

    fun startTimer() {
        job = viewModelScope.launch {
            if (!isRunning) {
                isRunning = true
                startTime = System.currentTimeMillis()
                while (isRunning) {
                    if (timePaused > 0L) {
                        _timer.update { System.currentTimeMillis() + timePaused - startTime }
                        delay(1L)
                    } else {
                        _timer.update { System.currentTimeMillis() - startTime }
                        delay(1L)
                    }
                }
            }
        }
    }

    fun pauseTimer() {
        isRunning = false
        timePaused = _timer.value
    }

    fun resetTimer() {
        isRunning = false
        _timer.update { 0L }
        timePaused = 0L
        job?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}