package com.example.data.Responses

import com.example.data.FormData
import com.example.data.classes.Exercise
import com.example.data.classes.Usuario
import com.google.gson.Gson
import kotlinx.serialization.json.Json

data class RecomendacionInput(
    val usuario: Usuario? = null,
    val ejerciciosDisponibles: List<Exercise>
){
    fun getRecomendacion(input: FormData): String {
        val gson = Gson()

        val usuario = Usuario(
            nombre = "fer",
            edad = 52,
            genero = "masculino",
            objetivos = listOf("mejorar movilidad", "perder peso"),
            problemasSalud = listOf("dolor de rodilla")
        )

        val ejerciciosResumidos = ejerciciosDisponibles.map {
            ExerciseResumen(it.id, it.title, it.category)
        }

        val input = RecomendacionInput(usuario, ejerciciosResumidos)

        val json = gson.toJson(input)

        return json
    }
}

data class ExerciseResumen(
    val id: Int,
    val title: String,
    val category: String
)



