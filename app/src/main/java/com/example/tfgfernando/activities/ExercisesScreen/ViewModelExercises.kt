package com.example.tfgfernando.activities.ExercisesScreen

import android.util.Log
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.response.ReadRecordsResponse
import androidx.lifecycle.ViewModel
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.example.tfgfernando.openIAManager
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.data.classes.FormData
import com.example.data.Responses.ExerciseRecommendationResponse
import com.example.data.Responses.RecomendacionInput
import com.example.data.classes.Exercise
import com.example.data.classes.Exercise.Companion.listadoEjercicios
import com.example.model.firebase.firestore.firestoreManager
import com.example.tfgfernando.activities.FormScreen.MainActivity
import com.example.tfgfernando.activities.FormScreen.MainActivity.Companion.healthConnectClient
import com.example.tfgfernando.activities.FormScreen.readCaloriesByTimeRange
import com.example.tfgfernando.activities.FormScreen.readDistanceByTimeRange
import com.example.tfgfernando.activities.FormScreen.readStepsByTimeRange
import com.example.tfgfernando.navigation.RutasEnum
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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


    val _exito = MutableStateFlow<Boolean>(false)
    val exito: StateFlow<Boolean> = _exito.asStateFlow()

    val _alerta = MutableStateFlow<Boolean>(false)
    val alerta: StateFlow<Boolean> = _alerta.asStateFlow()

    // Lista de ejercicio que nos devuelve chatGpt
    val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    // Lista de ejercicio que nos devuelve chatGpt
    val _error = MutableStateFlow<Boolean>(false)
    val error: StateFlow<Boolean> = _error.asStateFlow()

    val _pasos = MutableStateFlow<String>("")
    val pasos: StateFlow<String> = _pasos.asStateFlow()

    val _distancia = MutableStateFlow<String>("")
    val distancia: StateFlow<String> = _distancia.asStateFlow()

    val _calories = MutableStateFlow<String>("")
    val calories: StateFlow<String> = _calories.asStateFlow()

    var formData: FormData = FormData()

    fun setPersonalizadoFormData(formData: FormData) {
        this.formData = formData
    }

    fun switchAlertValue() {
        _alerta.value = !_alerta.value
    }

    fun getDatosHealthConnect() {

        viewModelScope.launch {

            var pasosLeidos: ReadRecordsResponse<StepsRecord> = readStepsByTimeRange(
                healthConnectClient,
                Instant.now().minus(Duration.ofHours(24)),
                Instant.now()
            )

            var distancia = readDistanceByTimeRange(
                healthConnectClient, Instant.now().minus(Duration.ofHours(24)),
                Instant.now()
            )

            var calories = readCaloriesByTimeRange(
                healthConnectClient,
                Instant.now().minus(Duration.ofHours(24)),
                Instant.now()
            )


            _pasos.value = pasosLeidos.records.sumOf { it.count }.toString()
            _distancia.value = distancia.records.sumOf { it.distance.inMeters }.toString()
            _calories.value = calories.records.sumOf { it.energy.inKilocalories }.toString()

        }
    }

    // Obtenemos los ejercicios en base a la api de OpenAI
    fun getExercises() {
        _error.value = false
        viewModelScope.launch {

            getDatosHealthConnect()
            var inputJson = RecomendacionInput().getRecomendacion(formData, pasos.value, distancia.value, calories.value)

            var completion: ChatCompletion = openIAManager().openai.chatCompletion(
                ChatCompletionRequest(
                    model = ModelId("gpt-3.5-turbo"),
                    messages = listOf(
                        ChatMessage(
                            role = ChatRole.System,
                            content = "Eres un entrenador personal. A continuación tienes los datos de un usuario y una lista de ejercicios disponibles. \n" +
                                    "\n" +
                                    "Toma los valores en unidades europeas, kg, cm y km\n" +
                                    "Devuélveme un JSON que contenga solo los ejercicios más recomendados para este usuario. \n" +
                                    "Devuélveme solo un JSON válido, sin explicaciones, sin texto antes o después. \n" +
                                    "Cada objeto debe tener `id`, `title` y `reason`. No inventes ejercicios nuevos, usa solo los de la lista. Dame 6 ejercicios minimo"
                                    + inputJson
                        )
                    ),
                    maxTokens = 600
                )
            )

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

            } catch (e: Exception) {
                Log.wtf("OpenAI", "Error al parsear el JSON ${e.message}")
                getExercises()
            }
        }
    }

    fun guardarEjercicios() {
        if (_exercises.value.isNotEmpty()) {
            viewModelScope.launch {
               var isSaved = firestore.guardarEjercicios(_exercises.value)
                if (isSaved) {
                    _exito.value = true
                } else {
                    _exito.value = false
                }
                switchAlertValue() // Cambiamos el valor de la alerta para que lo quite
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
