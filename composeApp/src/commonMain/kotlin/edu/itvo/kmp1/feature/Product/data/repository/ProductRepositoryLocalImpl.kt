package edu.itvo.kmp1.feature.Product.data.repository

import edu.itvo.kmp1.feature.Product.data.datasource.remote.ProductRemoteDatasource
import edu.itvo.kmp1.feature.Product.data.mapper.toDomain
import edu.itvo.kmp1.feature.Product.data.mapper.toDto
import edu.itvo.kmp1.feature.Product.domain.model.Product
import edu.itvo.kmp1.feature.Product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryLocalImpl(
    private val remote: ProductRemoteDatasource
): ProductRepository {


    override fun observeAll(): Flow<List<Product>> = flow {

        val products =
            remote.getProducts()

        emit(
            products.map {
                it.toDomain()
            }
        )
    }

    override suspend fun findById(
        id: String
    ): Product? {

        return observeAll()
            .let{flow ->

                var result: Product? = null

                flow.collect { products ->

                    result = products.find {
                        it.code == id
                    }
                }
                result
            }
    }


    override suspend fun save(
        item: Product
    ){
        remote.saveProduct(
            item.toDto()
        )

    }

    override suspend fun deleteById(
        id: String
    ){
        remote.deleteProduct(id)

    }

    override suspend fun update(item: Product) {
        remote.updateProduct(item.code, item.toDto())
    }

}