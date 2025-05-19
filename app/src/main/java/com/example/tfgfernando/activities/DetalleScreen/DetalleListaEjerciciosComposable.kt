package com.example.tfgfernando.activities.DetalleScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.data.classes.Exercise
import com.example.tfgfernando.activities.ExercisesScreen.EjercicioCard
import com.example.tfgfernando.activities.ExercisesScreen.ViewModelExercises

@Composable
fun DetalleListaEjerciciosComposable(idEjercicio: String?, navController: NavHostController) {


    val viewModel : viewModelDetalle = hiltViewModel<viewModelDetalle>()

    val viewModelExercise : ViewModelExercises = hiltViewModel<ViewModelExercises>()

    val listado: List<Exercise> by viewModel.Exercise.collectAsState()

    viewModel.getEjercicios(idEjercicio.toString())

    if ( !listado.isEmpty()) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                fontSize = 32.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                text = "Rutina seleccionada"
            )
            Text(fontSize = 18.sp,textAlign = TextAlign.Center,text = "¡Hora de activarse!")
            LazyVerticalGrid(
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp),
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                content = {
                    items(listado.size) { item ->
                        EjercicioCard(listado[item], navController, viewModelExercise)
                    }
                })
        }
    }

}