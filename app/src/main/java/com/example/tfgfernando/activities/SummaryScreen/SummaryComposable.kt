package com.example.tfgfernando.activities.SummaryScreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.data.classes.Historial
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun MostrarHistorial(navController: NavController) {

    val viewModel: ViewModelSummary = hiltViewModel<ViewModelSummary>()
    var listado: State<List<Historial>> = viewModel.ejerciciosRecuperados.collectAsState()
    viewModel.getListadoEjercicio()


    Text(
        fontSize = 24.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        text = "Historial de ejercicios"
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        content = {
            items(listado.value.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, top = 8.dp)
                        .clickable {
                            viewModel.navegarAListadoDetalles(listado.value[index].id, navController)
                        }) {
                    Row(modifier = Modifier.padding(12.dp)) {
//                    Text(modifier = Modifier.padding(16.dp), text = "Historial de ejercicios: ${listado.value[index].id}")

                        val formatoFecha = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                        var fechaFormateada =
                            formatoFecha.format(listado.value[index].fecha.toDate())
                        Text(text = "Ejercicios recomendados el: $fechaFormateada")
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Hola"
                        )
                    }
                }
            }

        })


}