package com.example.tfgfernando.activities.ExercisesScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.data.classes.Exercise
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun MostrarEjercicios(navController: NavController) {

    var viewModel: ViewModelExercises = hiltViewModel<ViewModelExercises>()

    LaunchedEffect(Unit) {
        viewModel.getExercises()
    }

    val ejercicios: List<Exercise> by viewModel.exercises.collectAsState()

//    Log.i("OpenAIFernando", "Los ejercicios son" + ejercicios.toString())
    Text(
        fontSize = 24.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 8.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        text = "Ejercicios Recomendados"
    )

        when {
            ejercicios.isEmpty() -> {
                Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "Cargando ejercicios...",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    CircularProgressIndicator()
                }
            }

            else -> {
                LazyVerticalGrid(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp),
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    content = {
                        items(ejercicios.size) { item ->
                            EjercicioCard(ejercicios[item], navController, viewModel)
                        }
                    })
            }
        }





}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EjercicioCard(ejercicio: Exercise, navController: NavController, viewModel: ViewModelExercises) {

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(Color.White).clickable {
                viewModel.navegarDetalle(ejercicio, navController)

            },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White)
        ) {
            GlideImage(
                model = ejercicio.imageUrl,
                contentDescription = ejercicio.title,
                contentScale = ContentScale.Crop,
            )
            Row(modifier = Modifier.align(Alignment.TopStart).background(Color.Black).padding(6.dp).clip(RoundedCornerShape(16.dp))) {
                Text(
                    fontSize = 12.sp,
                    text = ejercicio.category, // Opcional: separación del borde
                    color = Color.White, // Opcional: para que se vea sobre la imagen
                    fontWeight = FontWeight.Bold
                )
            }
            // Texto encima, en la esquina superior izquierda
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                lineHeight = 14.sp,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = ejercicio.title
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                lineHeight = 16.sp, fontSize = 11.sp, maxLines = 3,
                overflow = TextOverflow.Ellipsis, text = ejercicio.description
            )
        }

    }
}
