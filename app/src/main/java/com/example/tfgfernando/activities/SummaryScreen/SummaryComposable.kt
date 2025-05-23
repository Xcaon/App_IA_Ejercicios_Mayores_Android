package com.example.tfgfernando.activities.SummaryScreen


import android.R
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.data.classes.Historial
import java.text.SimpleDateFormat
import java.util.Locale

var listadoImagenesSemana : Map<String, String> = mapOf(
    "lunes" to "https://imgur.com/BZq6qjo.png",
    "martes" to "https://imgur.com/s5kEO4i.png",
    "miércoles" to "https://imgur.com/y5dx2f0.png",
    "jueves" to "https://imgur.com/LLyHHKC.png",
    "viernes" to "https://imgur.com/OhyUTib.png",
    "sábado" to "https://imgur.com/Y10K9LS.png",
    "domingo" to "https://imgur.com/Ngta5Mj.png"
)

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MostrarHistorial(navController: NavController) {

    val viewModel: ViewModelSummary = hiltViewModel<ViewModelSummary>()
    var listado: State<List<Historial>> = viewModel.ejerciciosRecuperados.collectAsState()
    var cargado: State<Boolean> = viewModel.cargado.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getListadoEjercicio()
        viewModel.switchValueCargado()
    }

    Column (modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            text = "Historial de ejercicios"
        )
        Text( modifier = Modifier.padding(horizontal = 12.dp), textAlign = TextAlign.Center, fontSize = 22.sp, text = "Consulta los ejercicios que has realizado en tu historial.")

        if (cargado.value){
            CircularProgressIndicator()
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 12.dp),
            content = {
                items(listado.value.size) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp, top = 8.dp)
                            .clickable {
                                viewModel.navegarAListadoDetalles(
                                    listado.value[index].id,
                                    navController
                                )
                            }
                            .height(200.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(modifier = Modifier) {
                            val formatoFecha = SimpleDateFormat("HH:mm", Locale("es", "ES"))
                            var fechaFormateada = formatoFecha.format(listado.value[index].fecha.toDate())

                            val formatoDiaSemana = SimpleDateFormat("EEEE", Locale("es", "ES"))
                            val diaSemana = formatoDiaSemana.format(listado.value[index].fecha.toDate())

                            val formatoDiaSemanaNumero = SimpleDateFormat("d", Locale("es", "ES"))
                            val diaSemanaNumero = formatoDiaSemanaNumero.format(listado.value[index].fecha.toDate())


                            val formatoMes = SimpleDateFormat("MMMM", Locale("es", "ES"))
                            val mes = formatoMes.format(listado.value[index].fecha.toDate())

                            Row (modifier = Modifier.weight(1f)) {
                                GlideImage(
                                    model = listadoImagenesSemana[diaSemana],
                                    contentDescription = "Dia de la semana",
                                    contentScale = ContentScale.Fit,
                                )
                            }

                            Row (modifier = Modifier.weight(1.8f).padding(12.dp)) {
                                Column {
                                    Text(fontSize = 22.sp, fontWeight = FontWeight.Bold, text = "Rutina de ejercicios del $diaSemana $diaSemanaNumero a las $fechaFormateada")
                                    Text(fontSize = 18.sp, text = "Forma parte del progreso de este mes de $mes")
                                }
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = "Hola"
                                )
                            }
                            Log.i("dia", "El dia de la semana es $diaSemana")
                        }
                    }
                }

            })
    }


}