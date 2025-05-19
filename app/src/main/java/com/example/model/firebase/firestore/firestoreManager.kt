package com.example.model.firebase.firestore

import android.util.Log
import com.example.data.classes.Exercise
import com.example.data.classes.ExerciseResponse
import com.example.data.classes.Historial
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject


class firestoreManager @Inject constructor(var firestore: FirebaseFirestore) {


    suspend fun guardarEjercicios(ejercicios: List<Exercise>) : Boolean {

        val fechaActual = Date()
        val timestamp = Timestamp(fechaActual)
        var resultado : Boolean = false

        val ejercicioRef = firestore.collection("listadoHistorial").document()

        return try {
            ejercicioRef.set(mapOf("fecha" to timestamp), SetOptions.merge()).await()
            for (ejercicio in ejercicios) {
                ejercicioRef.collection("ejercicios").add(ejercicio).await()
            }
            true
        } catch (e: Exception) {
            false
        }

    }

    // Recuperamos los documentos de la coleccion de ejercicios
    suspend fun recuperarListadoEjercicio(): List<Historial>? {
        return try {
            firestore.collection("listadoHistorial").orderBy("fecha", Query.Direction.DESCENDING)
                .get(Source.DEFAULT).await().map { document ->
                    var id = document.id
                    var fecha = document.data["fecha"] as Timestamp
                    Historial(id, fecha)
                }
        } catch (e: Exception) {
            Log.i("Firestore", "Error al recuperar los documentos ${e.message}")
            null
        }
    }

    suspend fun recuperarEjercicio(idEjercicio: String): List<Exercise> {
        return try {
            firestore.collection("listadoHistorial").document(idEjercicio).collection("ejercicios").get().await().map {
                document ->
                val response = document.toObject(ExerciseResponse::class.java)
                var ejercicios = response.toDomain()

                ejercicios
            }
        } catch (e: Exception) {
            Log.i("Firestore", "Error al recuperar los ejercicios del documento ${e.message}")
            emptyList()
        }

    }


}