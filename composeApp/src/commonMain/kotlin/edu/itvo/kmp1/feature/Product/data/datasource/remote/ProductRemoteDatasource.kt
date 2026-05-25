package edu.itvo.kmp1.feature.Product.data.datasource.remote

import edu.itvo.kmp1.feature.Product.data.dto.ProductDto
import edu.itvo.kmp1.feature.Product.data.remote.ProductApi


class ProductRemoteDataSource(
    private val api: ProductApi
) {
    suspend fun getProducts(): List<ProductDto> {

        return api.getProducts().data
    }

    suspend fun saveProduct(
        product: ProductDto
    ){
        api.saveProduct(product)
    }

    suspend fun deleteProduct(
        code: String
    ){
        api.deleteProduct(code)
    }

    suspend fun updateProduct(
        code: String, product: ProductDto
    ){
        api.updateProduct(code,product)
    }

}