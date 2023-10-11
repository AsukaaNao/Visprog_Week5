package com.example.visprog_week5_0706012210009.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.visprog_week5_0706012210009.model.GuessNumberUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed interface GuessNumberUiState{

    data class Success(val data: List<GuessNumberUIState>): GuessNumberUiState

    object Error: GuessNumberUiState

    object Loading: GuessNumberUiState
}

class GuessNumberViewModel : ViewModel(){
    var model: GuessNumberUIState? = null

    fun generateRandomNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            val numberToGuess = (1..10).random()
            model = GuessNumberUIState(numberToGuess)
        }
    }
}