package edu.itvo.kmp1.feature.Product.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val code: String,
    val description: String,
    val category: String,
    val price: Double,
    val stock: Int,
    val taxable: Boolean
)
