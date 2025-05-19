package com.example.tfgfernando.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

// Este fichero define el diseño del menu inferior
@Composable
fun MyBottomNavigation(navController: NavHostController) {

    NavigationBar(contentColor = MaterialTheme.colorScheme.primary) {
        var index by remember { mutableIntStateOf(0) }
        NavigationBarItem(
            selected = index == 0,
            onClick = {
                index = 0
                navController.navigate(RutasEnum.FORM.nombre) {

                    // launchSingleTop = true indica al NavController que si la pantalla Home ya está en la
                    // parte superior del back stack, no se creará una nueva instancia. En su lugar, se reutilizará la instancia existente.
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            },
            label = { Text(text = "Mis datos") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Dataset,
                    contentDescription = "Inicio de la app"
                )
            })

        NavigationBarItem(
            selected = index == 1,
            onClick = {
                index = 1
                navController.navigate(RutasEnum.EXERCISES.nombre) {

                    // launchSingleTop = true indica al NavController que si la pantalla Home ya está en la
                    // parte superior del back stack, no se creará una nueva instancia. En su lugar, se reutilizará la instancia existente.
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            },
            label = { Text(text = "Ejercicios") },
            icon = {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = "Reservas de la app"
                )
            })

        NavigationBarItem(
            selected = index == 2,
            onClick = {
                index = 2
                navController.navigate(RutasEnum.SUMMARY.nombre) {

                    // launchSingleTop = true indica al NavController que si la pantalla Home ya está en la
                    // parte superior del back stack, no se creará una nueva instancia. En su lugar, se reutilizará la instancia existente.
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            },
            label = { Text(text = "Rutinas") },
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "seguidos"
                )
            })

        NavigationBarItem(
            selected = index == 3,
            onClick = {
                index = 3
                navController.navigate(RutasEnum.SALUD.nombre) {

                    // launchSingleTop = true indica al NavController que si la pantalla Home ya está en la
                    // parte superior del back stack, no se creará una nueva instancia. En su lugar, se reutilizará la instancia existente.
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            },
            label = { Text(text = "Salud") },
            icon = {
                Icon(
                    imageVector = Icons.Default.MonitorHeart,
                    contentDescription = "Salud de la app"
                )
            })
    }
}