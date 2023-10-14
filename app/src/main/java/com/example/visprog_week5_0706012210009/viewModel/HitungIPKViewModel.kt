package com.example.visprog_week5_0706012210009.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.visprog_week5_0706012210009.model.CourseUIState
import kotlinx.coroutines.flow.MutableStateFlow

class HitungIPKViewModel : ViewModel() {

    private var _uiState = MutableStateFlow<List<CourseUIState>>(emptyList())

    fun addCourse(sks:String, score: String,name: String) {
        val sksValue = sks.toIntOrNull() ?: 0
        val scoreValue = formatDouble(score)
        val data = CourseUIState(sksValue, scoreValue.toDouble(), name)
        _uiState.value = _uiState.value + data
    }

    fun getCourseList(): List<CourseUIState> {
        return _uiState.value
    }

    fun sumSKS(): Int {
        return _uiState.value.sumOf { it.sks }
    }

    fun sumIPK(): String{
        val sumSKS = _uiState.value.sumOf { it.sks }

        if (sumSKS == 0) {
            return "0" // Return a default value (or handle it differently)
        }

        val sumScore = _uiState.value.sumOf { it.score * it.sks}
        val sumIPK = sumScore/ sumSKS
        return formatDouble(sumIPK.toString()).toString()
    }

    private fun formatDouble(num:String):Double {
        val value = String.format("%.2f", num.toDoubleOrNull() ?: 0.0)

        return value.toDouble()
    }

    fun deleteCourse(course:CourseUIState) {
        _uiState.value = _uiState.value.filter { it != course }
    }

    fun isSksValid(sks: String): Boolean {
        if(sks.isEmpty()) return true
        val sksValue = sks.toIntOrNull()
        return sksValue != null && sksValue in 1..10
    }

    fun isScoreValid(score: String): Boolean {
        if(score.isEmpty()) return true
        val scoreValue = score.toDoubleOrNull()
        return scoreValue != null && scoreValue in 0.0..4.0
    }
}