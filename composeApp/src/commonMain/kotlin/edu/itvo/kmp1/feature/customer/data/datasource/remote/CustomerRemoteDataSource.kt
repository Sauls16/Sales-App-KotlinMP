package edu.itvo.kmp1.feature.customer.data.datasource.remote

import edu.itvo.kmp1.feature.customer.data.dto.CustomerDto
import edu.itvo.kmp1.feature.customer.data.remote.CustomerApi

class CustomerRemoteDataSource(
    private val api: CustomerApi
) {

    suspend fun getCustomers(): List<CustomerDto> {

        return api.getCustomers().data
    }

    suspend fun saveCustomer(
        customer: CustomerDto
    ) {

        api.saveCustomer(customer)
    }

    suspend fun deleteCustomer(
        id: String
    ) {

        api.deleteCustomer(id)
    }

    suspend fun updateCustomer(
        id: String, customer: CustomerDto
    ){
         api.updateCustomer(id,customer)
    }
}