package com.example.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.tfgfernando.activities.ExercisesScreen.ViewModelExercises

data class FormData (
    val chronicDiseases: SnapshotStateList<String> = mutableStateListOf(),
    val mobilityProblems: Boolean = false,
    val objectives: SnapshotStateList<String> = mutableStateListOf(),
    val exercisedRecently: Boolean = false,
    val weight: String = "",
    val age: String = ""
)

// Esta clase nos sirve para gestionar los datos enviados a la IA
object DataRepository {

    // Guardamos la informacion del formulario
    fun addSubmission(formData: FormData) {

        // Ejecuto el viewModel
        ViewModelExercises().getExercises(formData)
    }



}


