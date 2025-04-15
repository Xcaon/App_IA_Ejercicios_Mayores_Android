package com.example.tfgfernando.activities.ExercisesScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tfgfernando.ui.theme.TfgFernandoTheme



class ExercisesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TfgFernandoTheme {

            }
        }
    }
}

