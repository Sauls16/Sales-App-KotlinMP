package edu.itvo.kmp1.core.network

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T> (
    val success: Boolean,
    val message: String,
    val data: List<T>
)
