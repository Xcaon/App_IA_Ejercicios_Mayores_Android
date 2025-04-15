package com.example.tfgfernando.activities.FormScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.health.connect.client.HealthConnectClient
import com.example.tfgfernando.ui.theme.TfgFernandoTheme
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.records.metadata.Device
import androidx.health.connect.client.records.metadata.Metadata
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import androidx.lifecycle.lifecycleScope
import com.example.tfgfernando.navigation.MyApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import java.time.ZoneOffset

const val providerPackageName = "com.google.android.apps.healthdata"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    val PERMISSIONS =
        setOf(
            HealthPermission.getReadPermission(HeartRateRecord::class),
            HealthPermission.getWritePermission(HeartRateRecord::class),
            HealthPermission.getReadPermission(StepsRecord::class),
            HealthPermission.getWritePermission(StepsRecord::class)
        )


    val requestPermissionActivityContract =
        PermissionController.createRequestPermissionResultContract()

    // Launcher para solicitar permisos
    private val requestPermissions: ActivityResultLauncher<Set<String>> =
        registerForActivityResult(PermissionController.createRequestPermissionResultContract()) { granted ->
            if (granted.containsAll(PERMISSIONS)) {
                // Permisos concedidos
                Log.i("HealthConnect", "Permisos concedidos")
            } else {
                // Permisos denegados
                Log.e("HealthConnect", "Permisos denegados")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Comprobamos que la app de Health Connect este instalada
        var healthConnectClient = healthCheckStatus(context = this)

        if (healthConnectClient != null) {
            checkPermissionsAndRun(healthConnectClient)
        }

        // Leemos los datos
        readStepsByTimeRange(
            healthConnectClient!!,
            Instant.now().minus(Duration.ofHours(1)),
            Instant.now()
        )





        // Compose Vista
        setContent {
            TfgFernandoTheme {
                // Iniciamos el menu inferior
                MyApp()
            }
        }
    }

    private fun readStepsByTimeRange(
        healthConnectClient: HealthConnectClient,
        startTime: Instant,
        endTime: Instant
    ) {
        lifecycleScope.launch {
            try {
                val response =
                    healthConnectClient.readRecords(
                        ReadRecordsRequest(
                            StepsRecord::class,
                            timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                        )
                    )
                for (stepRecord: StepsRecord in response.records) {
                    // Process each step record
                    Log.i("HealthConnect", stepRecord.count.toString())

                }
            } catch (e: Exception) {
                // Run error handling here.
                Log.e("HealthConnect", "Error reading steps", e)
            }
        }
    }

    fun checkPermissionsAndRun(healthConnectClient: HealthConnectClient) {
        lifecycleScope.launch {
            val granted = healthConnectClient.permissionController.getGrantedPermissions()
            if (granted.containsAll(PERMISSIONS)) {
                Log.i("HealthConnect", "Permisos concedidos")
                // Insertamos datos sabiendo que los permisos estan okey
                insertSteps(healthConnectClient)
            } else {
                requestPermissions.launch(PERMISSIONS)
            }
        }
    }


    // Comprobar que la app existe en el dispositivo para acceder al almacenamiento
    fun healthCheckStatus(context: Activity): HealthConnectClient? {
        val availabilityStatus = HealthConnectClient.getSdkStatus(context, providerPackageName)
        val healthConnectClient = HealthConnectClient.getOrCreate(context)

        if (availabilityStatus == HealthConnectClient.SDK_UNAVAILABLE) {
            Log.e("HealthConnect", "Health Connect is not available")
        } else if (availabilityStatus == HealthConnectClient.SDK_AVAILABLE) {
            Log.i("HealthConnect", "Health Connect is available")
        }

        if (availabilityStatus == HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED) {
            Log.e("HealthConnect", "Health Connect App is not available")
            // Optionally redirect to package installer to find a provider, for example:
            val uriString =
                "market://details?id=$providerPackageName&url=healthconnect%3A%2F%2Fonboarding"
            context.startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    setPackage("com.android.vending")
//                    Intent.setData = uriString.toUri() TODO() Esta mierda ha dejado de funcionar por la cara
                    putExtra("overlay", true)
                    putExtra("callerId", context.packageName)
                }
            )
            return null
        }
        return healthConnectClient

    }

    // Introducimos datos de prueba
    fun insertSteps(healthConnectClient: HealthConnectClient?) {
        lifecycleScope.launch {
            val endTime = Instant.now()
            val startTime = endTime.minus(Duration.ofMinutes(15))


            val device = Device(
                manufacturer = "Ejemplo", // Puedes poner la información que desees
                model = "Ejemplo Watch",   // Puedes poner la información que desees
                type = Device.TYPE_WATCH,  // Puedes poner la información que desees
            )

            try {
                val stepsRecord = StepsRecord(
                    count = 120,
                    startTime = startTime,
                    endTime = endTime,
                    startZoneOffset = ZoneOffset.UTC,
                    endZoneOffset = ZoneOffset.UTC,
                    metadata = Metadata.autoRecorded(
                        device
                    ),
                )
                healthConnectClient!!.insertRecords(listOf(stepsRecord))
            } catch (e: Exception) {
                // Run error handling here
                Log.e("HealthConnect", "Error inserting steps", e)
            }
        }

    }
}



