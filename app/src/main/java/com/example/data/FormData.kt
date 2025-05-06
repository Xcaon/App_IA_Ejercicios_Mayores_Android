package com.example.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class FormData (
    val chronicDiseases: SnapshotStateList<String> = mutableStateListOf(),
    val mobilityProblems: Boolean = false,
    val objectives: SnapshotStateList<String> = mutableStateListOf(),
    val exercisedRecently: Boolean = false,
    val weight: String = "70",
    val age: String = "40",
    val pasos: String = "1000",
    val distancia: String = "1000",
    val calories: String = "1000"
)




