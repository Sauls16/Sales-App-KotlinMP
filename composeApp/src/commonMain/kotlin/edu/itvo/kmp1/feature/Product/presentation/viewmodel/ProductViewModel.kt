package edu.itvo.kmp1.feature.Product.presentation.viewmodel

import edu.itvo.kmp1.feature.Product.domain.model.Product
import edu.itvo.kmp1.feature.Product.domain.usecase.DeleteProductUseCase
import edu.itvo.kmp1.feature.Product.domain.usecase.ObserveProducstUseCase
import edu.itvo.kmp1.feature.Product.domain.usecase.SaveProductUseCase
import edu.itvo.kmp1.feature.Product.domain.usecase.UpdateProductUseCase
import edu.itvo.kmp1.feature.Product.presentation.event.ProductEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

class ProductViewModel @Inject constructor(
    private val observeProductsUseCase: ObserveProducstUseCase,
    private val saveProductUseCase: SaveProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase
) {
    private val scope = CoroutineScope(Dispatchers.Main)

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        observeProducts()
    }

    private fun observeProducts() {
        scope.launch {
            observeProductsUseCase().collect { list ->
                _products.value = list
            }
        }
    }

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.SaveProduct -> {
                scope.launch {
                    saveProductUseCase(event.product)
                }
            }
            is ProductEvent.DeleteProduct -> {
                scope.launch {
                    deleteProductUseCase(event.code)
                }
            }
            is ProductEvent.UpdateProduct -> {
                scope.launch {
                    updateProductUseCase(event.product)
                }
            }
        }
    }
}