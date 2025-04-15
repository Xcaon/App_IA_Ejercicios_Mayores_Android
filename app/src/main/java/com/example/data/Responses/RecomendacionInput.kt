package com.example.data.Responses

import com.example.data.FormData
import com.example.data.classes.Exercise.Companion.listadoEjercicios
import com.example.data.classes.Usuario
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class RecomendacionInput(
    val usuario: Usuario? = null,
    val ejerciciosDisponibles: List<ExerciseResumen> = emptyList<ExerciseResumen>()
){
    fun getRecomendacion(input: FormData): String {
        val gson = Gson()

        val usuario = Usuario(
            nombre = "fer",
            edad = input.age.toString(),
            genero = "masculino",
            objetivos = listOf(input.objectives.toString()),
            problemasSalud = listOf(input.chronicDiseases.toString()),
            problemasMovilidad = input.mobilityProblems,
            peso = input.weight.toInt(),
            ejerciciosRecientes = input.exercisedRecently
        )

        // Le pasamos solo los datos que le interesa al chatgpt
        val listaEjerciciosResumidos = listadoEjercicios.map {ejercicio ->
            ExerciseResumen(ejercicio.id, ejercicio.title, ejercicio.category)
        }

        // Guardamos toodo en una variable
        val input = RecomendacionInput(usuario, listaEjerciciosResumidos)

        // Lo pasamos a json
        val json = gson.toJson(input)

        return json
    }
}

data class ExerciseResumen(
    val id: Int,
    val title: String,
    val category: String
)

data class ExerciseRecommendationResponse(
    @SerializedName("ejerciciosRecomendados")
    val ejerciciosRecomendados: List<ExerciseResumen>
)

