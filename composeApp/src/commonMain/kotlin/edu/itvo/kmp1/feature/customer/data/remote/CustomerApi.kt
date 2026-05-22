package edu.itvo.kmp1.feature.customer.data.remote

import edu.itvo.kmp1.core.network.ApiResponse
import edu.itvo.kmp1.feature.customer.data.dto.CustomerDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*

class CustomerApi(
    private val client: HttpClient,
    private val baseUrl: String
) {

    suspend fun getCustomers(): ApiResponse<CustomerDto>{

        return client.get(
            "$baseUrl/customers"
        ).body()
    }

    suspend fun saveCustomer(
        customer: CustomerDto
    ) {

        client.post(
            "$baseUrl/customers"
        ) {

            contentType(ContentType.Application.Json)

            setBody(customer)
        }
    }

    suspend fun deleteCustomer(
        id: String
    ) {

        client.delete(
            "$baseUrl/customers/$id"
        )
    }
}