package com.example.visprog_week5_0706012210009.viewModel

import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.visprog_week5_0706012210009.model.GuessNumberUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GuessNumberViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GuessNumberUIState())
    val uiState: StateFlow<GuessNumberUIState> = _uiState.asStateFlow()

    init {
        generateRandomNumber()
    }

    fun generateRandomNumber() {
        _uiState.value.numberToGuess = (1..10).random()
        _uiState.update { currentData ->
            currentData.copy(
                numberToGuess = _uiState.value.numberToGuess,
                numOfGuesses = 0,
                gameOver = false
            )
        }
    }

    fun resetGame() {
        _uiState.update { currentData ->
            currentData.copy(numOfGuesses = 0, gameOver = false, score = 0)
        }
    }

    fun addScore() {
        val temp = _uiState.value.score + 1
        _uiState.update { currentData ->
            currentData.copy(score = temp)
        }
    }

    fun addGuess() {
        val temp = _uiState.value.numOfGuesses + 1
        _uiState.update { currentData ->
            currentData.copy(numOfGuesses = temp)
        }
    }

    //    fun checkUserGuess() {
//
//        if (userGuess.equals(currentWord, ignoreCase = true)) {
//        } else {
//        }
//        // Reset user guess
//        updateUserGuess("")
//    }
    @Composable
    private fun FinalScoreDialog(
        score: Int,
        onPlayAgain: () -> Unit
    ) {
        val activity = (LocalContext.current as Activity)

        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
            },
            title = { Text(text = "Welp") },
            text = { Text(text = "You Scored $score") },
            modifier = Modifier,
            dismissButton = {
                TextButton(
                    onClick = {
                        activity.finish()
                    }
                ) {
                    Text(text = "Exit")
                }
            },
            confirmButton = {
                TextButton(onClick = onPlayAgain) {
                    Text(text = "Play Again")
                }
            }
        )
    }

    fun checkCondition(guess: Int){
        if(guess == _uiState.value.numberToGuess) {
            generateRandomNumber()
            addScore()
        } else {
            addGuess()
        }
        if (_uiState.value.numOfGuesses == 3 || _uiState.value.score == 3) {
            _uiState.value = _uiState.value.copy(gameOver = true)
        }
    }
}