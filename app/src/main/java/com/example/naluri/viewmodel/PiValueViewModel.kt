package com.example.naluri.viewmodel

import androidx.lifecycle.LiveData
import com.example.naluri.model.CircumferenceModel


//Contract for VM
interface PiValueViewModel {
    val uiState: LiveData<UiState>

    fun getPiValue()
    fun createNewCircumference(title: String, radius: String)

    sealed class UiState {
        data class DisplayPiValue(val piValue: String) : UiState()
        data class DisplaySunCircumference(val circumference: String) : UiState()
        data class DisplayNewCircumference(val circumferenceList: ArrayList<CircumferenceModel>?) : UiState()
    }
}