package edu.itvo.kmp1.feature.Product.data.repository

import edu.itvo.kmp1.feature.Product.data.datasource.remote.ProductRemoteDataSource
import edu.itvo.kmp1.feature.Product.data.mapper.toDomain
import edu.itvo.kmp1.feature.Product.data.mapper.toDto
import edu.itvo.kmp1.feature.Product.domain.model.Product
import edu.itvo.kmp1.feature.Product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl(
    private val remote: ProductRemoteDataSource
): ProductRepository {

    private val localProducts = MutableStateFlow<List<Product>>(emptyList())

    override fun observeAll(): Flow<List<Product>> = flow {
        try {
            val remoteData = remote.getProducts()
                .map {
                    it.toDomain()
                }
            localProducts.value = remoteData
        } catch (e: Exception){

        }
        localProducts.collect { emit(it) }
    }

    override suspend fun findById(id: String): Product? {
        return localProducts.value.find { it.code == id }
    }

    override suspend fun save(item: Product) {
        localProducts.value = localProducts.value.filter { it.code != item.code } + item

        try {
            remote.saveProduct(item.toDto())
        } catch (e: Exception) {}
    }

    override suspend fun deleteById(id: String) {
        localProducts.value = localProducts.value.filter { it.code != id }

        try {
            remote.deleteProduct(id)
        } catch (e: Exception) {}
    }

    override suspend fun update(item: Product) {
        localProducts.value = localProducts.value.map { if (it.code == item.code) item else it }

        try {
            remote.updateProduct(item.code, item.toDto())
        } catch (e: Exception) {}
    }

}