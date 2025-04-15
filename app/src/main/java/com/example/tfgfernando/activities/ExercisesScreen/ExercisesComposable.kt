package com.example.tfgfernando.activities.ExercisesScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.data.classes.Exercise
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.aallam.openai.api.edits.editsRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage



@Composable
fun MostrarEjercicios() {

    var viewModel: ViewModelExercises = ViewModelExercises()

    var ejercicios = viewModel.exercises.collectAsState()


    LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(2), content = {
        items(ejercicios.value.size) { item ->
            EjercicioCard(ejercicios.value[item])
        }
    })

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EjercicioCard(ejercicio: Exercise) {

    Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
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
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White)
        ) {
            Text(modifier = Modifier.align(androidx.compose.ui.Alignment.Center) , text = ejercicio.title)
        }

    }
}
