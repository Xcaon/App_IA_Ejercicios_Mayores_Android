package com.example.tfgfernando.activities.DetalleScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.data.classes.Exercise
import com.example.data.classes.Exercise.Companion.listadoEjercicios

@Composable
fun MostrarActividadDetalle(idEjercicio: String? = null, variable: String?) {

    var viewModelDetalle: viewModelDetalle = hiltViewModel<viewModelDetalle>()

    when (variable) {
        "documento" -> {
            // Recuperamos el documento por su ID de firebase

        }

        else -> {
            // Pintamos de la lista de Ejercicios
            Column(modifier = Modifier.fillMaxSize()) {
                Card(idEjercicio.toString())
            }
        }
    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Card(idEjercicio: String) {

    var ejercicio: Exercise = listadoEjercicios[idEjercicio.toInt() - 1]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Color.White)
    ) {
        GlideImage(
            model = ejercicio.imageUrl,
            contentDescription = ejercicio.title,
            contentScale = ContentScale.Crop,
        )
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .background(Color.Black)
                .padding(6.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Text(
                fontSize = 24.sp,
                text = ejercicio.category, // Opcional: separación del borde
                color = Color.White, // Opcional: para que se vea sobre la imagen
                fontWeight = FontWeight.Bold
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            lineHeight = 18.sp,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = ejercicio.title
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            lineHeight = 24.sp, fontSize = 16.sp, maxLines = 3,
            text = ejercicio.description
        )
    }


}