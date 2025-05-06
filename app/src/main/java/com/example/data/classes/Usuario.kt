package com.example.data.classes

data class Usuario(
    val id: Int = 1,
    var nombre: String,
    var edad: String,
    var genero: String,
    var objetivos: List<String>,
    var problemasSalud: List<String>,
    var ejerciciosRecientes: Boolean,
    var problemasMovilidad: Boolean,
    var peso: Int,
    var pasosDiarios: String,
    var caloriasQuemadas: String,
    var distanciaRecorrida: String
)