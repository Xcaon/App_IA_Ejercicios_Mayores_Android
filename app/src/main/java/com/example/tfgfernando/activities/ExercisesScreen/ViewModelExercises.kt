package com.example.tfgfernando.activities.ExercisesScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.example.tfgfernando.openIAManager
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.data.FormData
import com.example.data.Responses.ExerciseRecommendationResponse
import com.example.data.Responses.RecomendacionInput
import com.example.data.classes.Exercise
import com.example.data.classes.Exercise.Companion.listadoEjercicios
import com.example.model.firebase.firestore.firestoreManager
import com.example.tfgfernando.activities.FormScreen.MainActivity
import com.example.tfgfernando.activities.FormScreen.MainActivity.Companion.healthConnectClient
import com.example.tfgfernando.activities.FormScreen.readStepsByTimeRange
import com.example.tfgfernando.navigation.RutasEnum
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.time.Duration
import java.time.Instant
import java.time.ZoneOffset

@HiltViewModel
class ViewModelExercises @Inject constructor(val firestore: firestoreManager) : ViewModel() {

    // Lista de ejercicio que nos devuelve chatGpt
    val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    var formData: FormData = FormData()

    fun setPersonalizadoFormData(formData: FormData) {
        this.formData = formData
    }

    // Obtenemos los ejercicios en base a la api de OpenAI
    fun getExercises() {



        viewModelScope.launch {


            var pasosLeidos = readStepsByTimeRange(
                healthConnectClient,
                Instant.now().minus(Duration.ofHours(24)),
                Instant.now()
            )

            Log.i("HealthConnect", "Se han leido ${pasosLeidos.records.size} pasos")

            var inputJson = RecomendacionInput().getRecomendacion(formData)

            var completion: ChatCompletion = openIAManager().openai.chatCompletion(
                ChatCompletionRequest(
                    model = ModelId("gpt-3.5-turbo"),
                    messages = listOf(
                        ChatMessage(
                            role = ChatRole.System,
                            content = "Eres un entrenador personal. A continuación tienes los datos de un usuario y una lista de ejercicios disponibles. \n" +
                                    "\n" +
                                    "Devuélveme un JSON que contenga solo los ejercicios más recomendados para este usuario. \n" +
                                    "Devuélveme solo un JSON válido, sin explicaciones, sin texto antes o después. \n" +
                                    "Cada objeto debe tener `id`, `title` y `reason`. No inventes ejercicios nuevos, usa solo los de la lista."
                                    + inputJson
                        )
                    ),
                    maxTokens = 200
                )
            )
//
            Log.i("OpenAI", completion.choices[0].message.content.toString())
            Log.i("OpenAI", "Ha gastado en la consulta: " + completion.usage.toString())
            var respuesta = completion.choices[0].message.content.toString()

            val gson = Gson()
            try {
                val response = gson.fromJson(respuesta, ExerciseRecommendationResponse::class.java)

                // Aqui tenemos los ejercicios filtrados
                val titulosRecomendados = response.ejerciciosRecomendados.map { it.title }

                var ejerciciosFiltrados: List<Exercise> =
                    listadoEjercicios.filter { it.title in titulosRecomendados }

                Log.i("OpenAI", "Se han filtrado los ejercicios $ejerciciosFiltrados")
                _exercises.value = ejerciciosFiltrados

                if (_exercises.value.isNotEmpty()) {
                    firestore.guardarEjercicios(_exercises.value)
                }


            } catch (e: Exception) {
                Log.i("OpenAI", "Error al parsear el JSON ${e.message}")
            }

        }

    }

    // Funcion para navegar al detalle
    fun navegarDetalle(ejercicio: Exercise, navController: NavController) {

        try {
            var idEjercicio = ejercicio.id
            navController.navigate("${RutasEnum.DETALLE.nombre}/$idEjercicio/listado")

        } catch (e: Exception) {
            Log.i("OpenAI", "Error al navegar al detalle ${e.message}")
        }

    }


}
