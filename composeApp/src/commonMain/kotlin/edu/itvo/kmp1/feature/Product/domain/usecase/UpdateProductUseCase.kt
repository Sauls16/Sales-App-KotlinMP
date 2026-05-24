package edu.itvo.kmp1.feature.Product.domain.usecase

import edu.itvo.kmp1.feature.Product.domain.model.Product
import edu.itvo.kmp1.feature.Product.domain.repository.ProductRepository

class UpdateProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product){
        repository.update(product)
    }
}