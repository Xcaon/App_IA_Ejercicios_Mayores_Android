package com.example.tfgfernando

import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.Model
import com.aallam.openai.client.OpenAI
import kotlin.time.Duration.Companion.seconds
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

class openIAManager {

    val client = HttpClient(OkHttp)


        var openai = OpenAI(
            token = "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",
            timeout = Timeout(socket = 60.seconds),
            // additional configurations...
        )



    suspend fun getOpenAIModelsList(): List<Model> {
        val models: List<Model> = openai.models()
        return models
    }
}