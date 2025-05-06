package com.example.tfgfernando.activities.SaludScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tfgfernando.activities.ExercisesScreen.ViewModelExercises

@Composable
fun SaludComposable() {

    var viewModel : ViewModelExercises = hiltViewModel<ViewModelExercises>()

    var pasos: State<String> = viewModel.pasos.collectAsState()
    var distancia: State<String> = viewModel.distancia.collectAsState()
    var calories: State<String> = viewModel.calories.collectAsState()

    // Recuperamos los datos
    viewModel.getDatosHealthConnect()

    Row (modifier = Modifier.fillMaxWidth().padding(24.dp)) {
        Text(fontWeight = FontWeight.Bold, fontSize = 24.sp ,text = "Bienvenido a tu salud")
    }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {

        Row (modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(fontWeight = FontWeight.Bold ,textAlign = TextAlign.Center, text = "Pasos")
                Spacer(modifier = Modifier.height(8.dp))
                Card (modifier = Modifier.height(100.dp).fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
                    Text(text = "Cantidad de pasos")
                    Text(text = "Has dado " + pasos.value + " pasos hoy")
                }
                Spacer(modifier = Modifier.height(24.dp))

                Text(fontWeight = FontWeight.Bold ,textAlign = TextAlign.Center,text = "Distancia")
                Spacer(modifier = Modifier.height(8.dp))
                Card (modifier = Modifier.height(100.dp).fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
                    Text(text = "Distancia recorrida \n ${distancia.value} metros")
                }
            }
            Spacer(modifier = Modifier.width(32.dp))
            Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
                Text(fontWeight = FontWeight.Bold ,textAlign = TextAlign.Center,text = "Calorias quemadas")
                Spacer(modifier = Modifier.height(8.dp))
                Card (modifier = Modifier.height(100.dp).fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
                    Text(text = "Cantidad")
                    Text(text = "Has quemado ${calories.value} Calorias")
                }

            }
        }
    }


}