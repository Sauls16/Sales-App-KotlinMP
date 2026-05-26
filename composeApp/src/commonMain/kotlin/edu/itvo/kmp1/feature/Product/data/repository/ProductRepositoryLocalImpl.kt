package edu.itvo.kmp1.feature.Product.data.repository

import edu.itvo.kmp1.feature.Product.core.repository.BaseInMemoryRepository
import edu.itvo.kmp1.feature.Product.data.datasource.remote.ProductRemoteDataSource
import edu.itvo.kmp1.feature.Product.data.mapper.toDomain
import edu.itvo.kmp1.feature.Product.data.mapper.toDto
import edu.itvo.kmp1.feature.Product.domain.model.Product
import edu.itvo.kmp1.feature.Product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryLocalImpl(
    private val remote: ProductRemoteDataSource
) : BaseInMemoryRepository<Product, String>(), ProductRepository {

    override fun getId(item: Product): String = item.code

    override suspend fun update(item: Product) {
        save(item)
        try {
            remote.updateProduct(item.code, item.toDto())
        } catch (e: Exception) {
            println(e)
        }
    }

    override suspend fun save(item: Product) {
        super.save(item)
        try {
            remote.saveProduct(item.toDto())
        } catch (e: Exception) {
            println(e)
        }
    }

    override suspend fun deleteById(id: String) {
        super.deleteById(id)
        try {
            remote.deleteProduct(id)
        } catch (e: Exception) {
            println(e)
        }
    }
}