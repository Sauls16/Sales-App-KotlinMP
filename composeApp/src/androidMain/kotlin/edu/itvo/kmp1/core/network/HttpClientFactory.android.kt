package edu.itvo.kmp1.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.plugins.logging.*
import kotlinx.serialization.json.Json

actual fun createHttpClient(): HttpClient {

    return HttpClient(OkHttp) {

        install(ContentNegotiation) {

            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Logging) {
            level = LogLevel.ALL
        }
    }
}