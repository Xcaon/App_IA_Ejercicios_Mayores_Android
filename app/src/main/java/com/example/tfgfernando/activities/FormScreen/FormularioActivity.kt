package com.example.tfgfernando.activities.FormScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.data.FormData
import com.example.tfgfernando.activities.ExercisesScreen.ViewModelExercises
import com.example.tfgfernando.navigation.RutasEnum

@Composable
fun FormularioActivityCompose(navController: NavController) {
    val viewModel: ViewModelExercises = hiltViewModel()
    val chronicDiseases = remember { mutableStateListOf<String>() }
    val mobilityProblems = remember { mutableStateOf(false) }
    val objectives = remember { mutableStateListOf<String>() }
    val exercisedRecently = remember { mutableStateOf(false) }
    val weight = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val camposCompletos = remember { mutableStateOf(false) }
    var formData: FormData = FormData()
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, bottom = 80.dp, top = 12.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Queremos conocerte un poco más",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            // Enfermedades Crónicas
            Text(
                text = "Enfermedades Crónicas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            val diseases = listOf(
                "Hipertensión", "Diabetes", "Problemas cardíacos",
                "Artritis", "Osteoporosis", "Dolor crónico"
            )

            diseases.forEach { disease ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .toggleable(
                            value = chronicDiseases.contains(disease),
                            role = Role.Checkbox,
                            onValueChange = {
                                if (it) chronicDiseases.add(disease)
                                else chronicDiseases.remove(disease)
                            }
                        )
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = chronicDiseases.contains(disease),
                        onCheckedChange = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = disease)
                }
            }


            // Objetivos
            Text(
                text = "Elige tú objetivo",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            val objectivesList = listOf(
                "Mantener movilidad",
                "Mejorar equilibrio",
                "Perder peso",
                "Fortalecer músculos",
                "Reducir dolores o rigidez",
                "Actividad social (hacer ejercicio en grupo)"
            )

            objectivesList.forEach { objective ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .toggleable(
                            value = objectives.contains(objective),
                            role = Role.Checkbox,
                            onValueChange = {
                                if (it) objectives.add(objective)
                                else objectives.remove(objective)
                            }
                        )
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = objectives.contains(objective),
                        onCheckedChange = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = objective)
                }
            }

            // Más preguntas
            Text(
                text = "Más preguntas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                "¿Tienes problemas de movilidad o equilibrio?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            // Problemas de movilidad
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .toggleable(
                        value = mobilityProblems.value,
                        role = Role.Checkbox,
                        onValueChange = { mobilityProblems.value = it }
                    )
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = mobilityProblems.value,
                    onCheckedChange = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Problemas de movilidad o equilibrio")
            }

            // Ejercicio reciente
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .toggleable(
                        value = exercisedRecently.value,
                        role = Role.Checkbox,
                        onValueChange = { exercisedRecently.value = it }
                    )
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = exercisedRecently.value,
                    onCheckedChange = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "¿Has hecho ejercicio en las últimas dos semanas?")
            }

            Text(
                text = "Ajustamos la intensidad de los ejercicios en función de los parametros",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 32.dp)
            )

            // Peso
            OutlinedTextField(
                value = weight.value,
                onValueChange = { weight.value = it },
                label = { Text("¿Peso? (kg)") },
                modifier = Modifier.fillMaxWidth()
            )

            // Edad
            OutlinedTextField(
                value = age.value,
                onValueChange = { age.value = it },
                label = { Text("¿Edad?") },
                modifier = Modifier.fillMaxWidth()
            )

            formData = FormData(
                chronicDiseases = chronicDiseases,
                mobilityProblems = mobilityProblems.value,
                objectives = objectives,
                exercisedRecently = exercisedRecently.value,
                weight = weight.value,
                age = age.value
            )

            Log.d("datos", "Tiene los datos vacios: " + formData.tieneCamposVacio().toString())
            // Si los campos no estan vacios, activamos el boton
            if (!formData.tieneCamposVacio()) {
                camposCompletos.value = true
            } else {
                camposCompletos.value = false
            }
        }


            Button(
                enabled = camposCompletos.value,
                onClick = {
                    // Si nos devuelve toodo false entonces es que esta toodo correcto
                    if (!formData.tieneCamposVacio()) {
                        viewModel.setPersonalizadoFormData(formData)
                        navController.navigate(RutasEnum.EXERCISES.nombre)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 24.dp)
                    .height(48.dp).align(Alignment.BottomCenter)
            ) {
                Text("Guardar Datos")
            }

    }
    // Botón de enviar

} // Fin de formulario