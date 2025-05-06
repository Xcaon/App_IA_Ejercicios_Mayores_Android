package com.example.tfgfernando.activities.SummaryScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.data.classes.Historial
import com.example.model.firebase.firestore.firestoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSummary @Inject constructor(val firestore : firestoreManager) : ViewModel() {

    // Lista de ejercicio que nos devuelve chatGpt
    val _ejerciciosRecuperados = MutableStateFlow<List<Historial>>(emptyList())
    val ejerciciosRecuperados : StateFlow<List<Historial>> = _ejerciciosRecuperados.asStateFlow()

    fun getListadoEjercicio(){
        viewModelScope.launch {

            var listadoDocumentos: List<Historial>? = firestore.recuperarListadoEjercicio()
            Log.i("Firestore", "El listado de documentos es $listadoDocumentos")

            listadoDocumentos.let {
                _ejerciciosRecuperados.value = listadoDocumentos!!
            }



        }
    }

    fun navegarAListadoDetalles(idEjercicio: String, navController: NavController) {
        navController.navigate("listadoDetalle/$idEjercicio")
    }

    fun navegarDetalle(idDocumento: String, navController: NavController) {
        navController.navigate("detalle/$idDocumento/documento")
    }

}