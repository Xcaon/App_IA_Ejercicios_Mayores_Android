package com.example.tfgfernando.activities.DetalleScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.classes.Exercise
import com.example.model.firebase.firestore.firestoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class viewModelDetalle @Inject constructor(val firestore: firestoreManager) : ViewModel() {


    val _Exercise: MutableStateFlow<List<Exercise>> = MutableStateFlow<List<Exercise>>(emptyList())
    var Exercise: StateFlow<List<Exercise>> = _Exercise.asStateFlow()

    // Aqui recuperamos un conjunto de ejercicios
    fun getEjercicios(idEjercicio: String) {
        viewModelScope.launch {
            var listado: List<Exercise> = firestore.recuperarEjercicio(idEjercicio)
            _Exercise.value = listado
        }
    }

}