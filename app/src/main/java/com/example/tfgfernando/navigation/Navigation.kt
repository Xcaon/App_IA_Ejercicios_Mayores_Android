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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tfgfernando.activities.ExercisesScreen.MostrarEjercicios
import com.example.tfgfernando.activities.FormScreen.FormularioActivityCompose


@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { MyBottomNavigation(navController) }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = RutasEnum.FORM.nombre,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(RutasEnum.FORM.nombre) { Column(Modifier.fillMaxSize().background(Color.White), verticalArrangement =  Arrangement.Center) {
                FormularioActivityCompose(navController)
            } }
            composable(RutasEnum.EXERCISES.nombre) { Column(Modifier.fillMaxSize().background(Color.Green), verticalArrangement =  Arrangement.Center) {
                MostrarEjercicios()
            } }
            composable(RutasEnum.SUMMARY.nombre) { Column(Modifier.fillMaxSize().background(Color.Green), verticalArrangement =  Arrangement.Center) {
                Text("Esto son las summary")
            } }
        }


    }
}