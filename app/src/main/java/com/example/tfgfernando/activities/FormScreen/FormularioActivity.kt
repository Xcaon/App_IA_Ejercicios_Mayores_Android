package com.example.tfgfernando.activities.FormScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.data.classes.FormData
import com.example.tfgfernando.R
import com.example.tfgfernando.activities.ExercisesScreen.ViewModelExercises
import com.example.tfgfernando.navigation.RutasEnum

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FormularioActivityCompose(navController: NavController) {
    val viewModel: ViewModelExercises = hiltViewModel()
    val chronicDiseases = remember { mutableStateListOf<String>() }
    val mobilityProblems = remember { mutableStateOf(false) }
    val objectives = remember { mutableStateListOf<String>() }
    val exercisedRecently = remember { mutableStateOf(false) }
    val weight = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val altura = remember { mutableStateOf("") }
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
            GlideImage(
                model = "https://imgur.com/ZvcdR9p.png",
                contentDescription = "Ejercicio",
                contentScale = ContentScale.Fit,
            )

            Text(fontSize = 24.sp, fontWeight = FontWeight.SemiBold, text = "GENERADOR DE RUTINAS")
            Text(
                fontSize = 22.sp,
                text = "Necesitamos saber un poco más de ti para darte una rutina recomendada de ejercicios, rellena todos los campos para continuar"
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Enfermedades Crónicas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                // Enfermedades Crónicas


                val diseases = listOf(
                    "Ninguno",
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
            }


            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Elige tú objetivo",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                // Objetivos


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
            }
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Estamos terminando...",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                // Más preguntas


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
                    fontSize = 16.sp,
                    text = "Ajustamos la intensidad de los ejercicios en función de los parametros",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Datos personales",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {

                // Peso
                OutlinedTextField(
                    value = weight.value,
                    onValueChange = { newValue ->
                        // Sólo actualiza si cada caracter es dígito (0–9)
                        if (newValue.all { it in '0'..'9' }) {
                            weight.value = newValue
                        } },
                    label = { Text("¿Peso? (kg)") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Edad
                OutlinedTextField(
                    value = age.value,
                    onValueChange = { newValue ->
                        if (newValue.all { it in '0'..'9' }) {
                            age.value = newValue
                        } },
                    label = { Text("¿Edad?") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Altura
                OutlinedTextField(
                    value = altura.value,
                    onValueChange = { newValue ->
                        if (newValue.all { it in '0'..'9' }) {
                            altura.value = newValue
                        } },
                    label = { Text("¿Altura? (cm)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(25.dp))

            formData = FormData(
                chronicDiseases = chronicDiseases,
                mobilityProblems = mobilityProblems.value,
                objectives = objectives,
                exercisedRecently = exercisedRecently.value,
                weight = weight.value,
                age = age.value,
                altura = altura.value
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
                .height(48.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text("Guardar Datos")
        }

    }
    // Botón de enviar

} // Fin de formulario