package edu.itvo.kmp1.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T> (
    @SerialName("success")
    val success: Boolean,
    val message: String? = null,
    val data: List<T>
)
