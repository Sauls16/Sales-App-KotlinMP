package edu.itvo.kmp1.feature.Product.data.remote


import edu.itvo.kmp1.core.network.ApiResponse
import edu.itvo.kmp1.feature.Product.data.dto.ProductDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ProductApi(
    private val client: HttpClient,
    private val baseUrl: String
) {

    suspend fun  getProducts(): ApiResponse<ProductDto> {
        return client.get(
            "$baseUrl/products"
        ).body()
    }

    suspend fun saveProduct(
        product: ProductDto
    ){
        client.post(
            "$baseUrl/products"
        ){
            contentType(ContentType.Application.Json)
            setBody(product)
        }
    }

    suspend fun deleteProduct(
        id: String
    ){
        client.delete("$baseUrl/products/$id")
    }

    suspend fun updateProduct(
        id: String, product: ProductDto)
    {
        client.put("$baseUrl/products/$id") {
            contentType(ContentType.Application.Json)
            setBody(product)
        }
    }

}