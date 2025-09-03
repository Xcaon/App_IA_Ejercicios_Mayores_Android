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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tfgfernando.activities.ExercisesScreen.ViewModelExercises
import com.example.tfgfernando.ui.theme.colorCalorias
import com.example.tfgfernando.ui.theme.colorDistancia
import com.example.tfgfernando.ui.theme.colorRecorrido

@Composable
fun SaludComposable() {

    var viewModel: ViewModelExercises = hiltViewModel<ViewModelExercises>()

    var pasos: State<String> = viewModel.pasos.collectAsState()
    var distancia: State<String> = viewModel.distancia.collectAsState()
    var calories: State<String> = viewModel.calories.collectAsState()

    // Recuperamos los datos
    viewModel.getDatosHealthConnect()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding( horizontal = 24.dp, vertical = 12.dp)
    ) {
        Text(textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, lineHeight = 32.sp, fontSize = 32.sp, text = "Bienvenido a tu salud")
        Spacer(modifier = Modifier.height(8.dp))
        Text( fontSize = 22.sp, text = "Aquí encontrarás un resumen diario de tu actividad física y tus datos de salud.")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, text = "RECORRIDO")
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = colorRecorrido)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    lineHeight = 18.sp,
                    fontSize = 16.sp,
                    text = "Hoy has caminado ${pasos.value} pasos",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, text = "DISTANCIA")
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = colorDistancia)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    lineHeight = 14.sp,
                    fontSize = 16.sp,
                    text = "Hoy has recorrido ${distancia.value} metros",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, text = "CALORIAS QUEMADAS")
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = colorCalorias)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    lineHeight = 14.sp,
                    fontSize = 16.sp,
                    text = "Hoy has quemado ${calories.value} kCal",
                    style = MaterialTheme.typography.bodyLarge
                )

            }
        }

    }
}