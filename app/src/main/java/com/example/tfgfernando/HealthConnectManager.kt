package com.example.tfgfernando

import android.content.Context
import androidx.health.connect.client.HealthConnectClient

class HealthConnectManager {

    fun getHealthConnectClient(context: Context): HealthConnectClient {
        var healthConnectClient = HealthConnectClient.getOrCreate(context)
        return healthConnectClient
    }


}