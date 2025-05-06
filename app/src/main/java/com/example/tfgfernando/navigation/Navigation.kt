package com.example.tfgfernando.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tfgfernando.activities.DetalleScreen.DetalleListaEjerciciosComposable
import com.example.tfgfernando.activities.DetalleScreen.MostrarActividadDetalle
import com.example.tfgfernando.activities.ExercisesScreen.MostrarEjercicios
import com.example.tfgfernando.activities.ExercisesScreen.ViewModelExercises
import com.example.tfgfernando.activities.FormScreen.FormularioActivityCompose
import com.example.tfgfernando.activities.SaludScreen.SaludComposable
import com.example.tfgfernando.activities.SummaryScreen.MostrarHistorial


@Composable
fun MyApp() {
    val navController = rememberNavController()
    val viewModelDetalle: ViewModelExercises = hiltViewModel<ViewModelExercises>()

    Scaffold(
        bottomBar = { MyBottomNavigation(navController) }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = RutasEnum.FORM.nombre,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(RutasEnum.FORM.nombre) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center
                ) {
                    FormularioActivityCompose(navController)
                }
            }
            composable(
                route = RutasEnum.EXERCISES.nombre
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center
                ) {
                    MostrarEjercicios(navController)
                }
            }
            composable(RutasEnum.SUMMARY.nombre) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center
                ) {
                    MostrarHistorial(navController)
                }
            }

            composable(RutasEnum.SALUD.nombre) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center
                ) {
                    SaludComposable()
                }
            }

            composable(
                route = "${RutasEnum.DETALLE.nombre}/{idEjercicio}/{variable}",
                arguments = listOf(navArgument("idEjercicio") { type = NavType.StringType },
                    navArgument("variable") { type = NavType.StringType })) {
                navBackStackEntry ->

                val idEjercicio = navBackStackEntry.arguments?.getString("idEjercicio")
                val variable = navBackStackEntry.arguments?.getString("variable")

                MostrarActividadDetalle(idEjercicio.toString(), variable)
            }

            composable(
                route = "${RutasEnum.LISTADODETALLE.nombre}/{idEjercicio}",
                arguments = listOf(navArgument("idEjercicio") { type = NavType.StringType }
                    )) {
                    navBackStackEntry ->

                val idEjercicio = navBackStackEntry.arguments?.getString("idEjercicio")

                DetalleListaEjerciciosComposable(idEjercicio, navController)

            }

        }


    }
}