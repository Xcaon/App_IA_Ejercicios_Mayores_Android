package com.example.data.classes

import androidx.core.net.toUri
import com.google.firebase.Timestamp


// Deben llamarse igual los nombre para que pueda funcionar al solicitarlo a firestore
data class ExerciseResponse (
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val category: String? = null
)
{
    // Esta funcion lo convierte en un objeto de tipo Actividad, basicamente por si queremos cambiarle algun nombre a los atributos
    fun toDomain(): Exercise {
        return Exercise(
            id = id!!,
            title = title!!,
            description = description!!,
            imageUrl = imageUrl!!,
            category = category!!
        )
    }

}