package edu.itvo.kmp1.feature.Product.presentation.event

import edu.itvo.kmp1.feature.Product.domain.model.Product

sealed interface ProductEvent {

    data class SaveProduct(
        val product: Product
    ): ProductEvent

    data class DeleteProduct(
        val code: String
    ): ProductEvent

    data class UpdateProduct(
        val product: Product
    ): ProductEvent

}