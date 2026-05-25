package edu.itvo.kmp1.feature.Product.presentation.state


sealed class ProductUiState {

    data object List: ProductUiState()

    data object Form: ProductUiState()
}