package com.example.visprog_week5_0706012210009.model

data class GuessNumberUIState(
    var numberToGuess: Int = 0,
    var numOfGuesses: Int = 0,
    var score: Int = 0,
    var gameOver: Boolean = false
    )