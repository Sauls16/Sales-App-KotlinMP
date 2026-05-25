package edu.itvo.kmp1.feature.Product.domain.usecase

import edu.itvo.kmp1.feature.Product.domain.repository.ProductRepository

class ObserveProducstUseCase(
    private val repository: ProductRepository
){
    operator fun invoke() = repository.observeAll()
}