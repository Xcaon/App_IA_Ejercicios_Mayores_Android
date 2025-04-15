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
import com.example.data.FormData
import com.example.data.Responses.RecomendacionInput
import com.example.data.classes.Exercise
import com.example.data.classes.Exercise.Companion.listadoEjercicios
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ViewModelExercises : ViewModel() {

    val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises : StateFlow<List<Exercise>> = _exercises.asStateFlow()



    // Obtenemos los ejercicios en base a la api de OpenAI
    fun getExercises(formData: FormData) {

        var inputJson = RecomendacionInput(null, listadoEjercicios).getRecomendacion(formData)

        viewModelScope.launch {
            val completion: ChatCompletion = openIAManager().openai.chatCompletion(ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.System,
                        content = "¿Como estas? Estoy usando una api de OpenAI"
                    )
                ),
                maxTokens = 30
            ))

            Log.i("OpenAI", completion.choices[0].message.content.toString())
            Log.i("OpenAI", "Ha gastado en la consulta: " + completion.usage.toString())
        }

    }




}
