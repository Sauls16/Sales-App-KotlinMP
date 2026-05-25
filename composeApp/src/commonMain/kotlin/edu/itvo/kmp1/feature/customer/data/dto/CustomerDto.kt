package edu.itvo.kmp1.feature.customer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomerDto(
    @SerialName("code")
    val id: String,
    val name: String,
    val email: String
)