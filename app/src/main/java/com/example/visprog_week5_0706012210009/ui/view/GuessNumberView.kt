package com.example.visprog_week5_0706012210009.ui.view

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.visprog_week5_0706012210009.viewModel.GuessNumberViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessNumberGame(
    guessNumberViewModel: GuessNumberViewModel = viewModel()
) {
    var showDialog = remember { mutableStateOf(false) }
    val gameState by guessNumberViewModel.uiState.collectAsState()
    var guessedNumber by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            val padding = 16.dp // You can set your desired padding directly here
            Column(
                verticalArrangement = Arrangement.spacedBy(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(padding)
            ) {
                Text(
                    modifier = Modifier
                        .clip(shapes.medium)
                        .background(color = MaterialTheme.colorScheme.surfaceTint)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                        .align(alignment = Alignment.End),
                    text = "Number of Guesses: ${gameState.numOfGuesses}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "${gameState.numberToGuess}", // isi number to guessed
                    style = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = "From 1 to 10 Guess the Number",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Score: ${gameState.score}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
                OutlinedTextField(
                    value = guessedNumber,
                    onValueChange = { guessedNumber = it },
                    singleLine = true,
                    shape = shapes.large,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.surface),
                    label = { Text("Enter your number") },
                    isError = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = androidx.compose.ui.text.input.ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    )
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    guessNumberViewModel.checkCondition(guessedNumber.toInt())
                    guessedNumber = ""
                },
                enabled = guessedNumber.isNotBlank()
            ) {
                Text(
                    text = "Submit",
                    fontSize = 16.sp
                )
            }
        }
        if (gameState.gameOver) {
            showDialog.value = true
        }
        if (showDialog.value) {
            val activity = (LocalContext.current as Activity)

            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onDismissRequest.
                },
                title = { Text(text = "Welp") },
                text = { Text(text = "You Scored ${gameState.score}") },
                modifier = Modifier,
                dismissButton = {
                    TextButton(
                        onClick = {
                            activity.finish()
//                            showDialog.value = false
                        }
                    ) {
                        Text(text = "Exit")
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            guessNumberViewModel.generateRandomNumber()
                            guessNumberViewModel.resetGame()
                            showDialog.value = false
                        }
                    ) {
                        Text(text = "Play Again")
                    }
                }
            )
        }

    }
}


@Composable
private fun FinalScoreDialog(
    score: Int,
    viewModel: GuessNumberViewModel
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
            TextButton(
                onClick = {
                    viewModel.generateRandomNumber()
                    viewModel.resetGame()
                }
            ) {
                Text(text = "Play Again")
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GuessNumberGamePreview() {
    GuessNumberGame(guessNumberViewModel = GuessNumberViewModel())
}