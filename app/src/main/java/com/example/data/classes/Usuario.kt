package com.example.data.classes

data class Usuario(val id: String = "",var nombre: String, var edad: Int, var genero: String, var objetivos: List<String>, var problemasSalud: List<String>)