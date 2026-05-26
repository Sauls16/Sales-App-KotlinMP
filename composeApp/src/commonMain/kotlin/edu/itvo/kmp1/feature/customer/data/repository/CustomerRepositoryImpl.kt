package edu.itvo.kmp1.feature.customer.data.repository

import edu.itvo.kmp1.feature.customer.data.datasource.remote.CustomerRemoteDataSource
import edu.itvo.kmp1.feature.customer.data.mapper.toDomain
import edu.itvo.kmp1.feature.customer.data.mapper.toDto
import edu.itvo.kmp1.feature.customer.domain.model.Customer
import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class CustomerRepositoryImpl(
    private val remote: CustomerRemoteDataSource
) : CustomerRepository {

    private val localCustomers = MutableStateFlow<List<Customer>>(emptyList())
    override fun observeAll(): Flow<List<Customer>> = flow {
        try {
            val customers = remote.getCustomers().map { it.toDomain() }
            localCustomers.value = customers
        } catch (e: Exception) {}

        localCustomers.collect { emit(it) }
    }

    override suspend fun findById(id: String): Customer? {
        return localCustomers.value.find { it.id == id }
    }

    override suspend fun save(item: Customer) {
        localCustomers.value = localCustomers.value.filter { it.id != item.id } + item
        try {
            remote.saveCustomer(item.toDto())
        } catch (e: Exception) {}
    }

    override suspend fun deleteById(id: String) {
        localCustomers.value = localCustomers.value.filter { it.id != id }
        try {
            remote.deleteCustomer(id)
        } catch (e: Exception) {}
    }

    override suspend fun update(item: Customer) {
        localCustomers.value = localCustomers.value.map { if (it.id == item.id) item else it }
        try {
            remote.updateCustomer(item.id, item.toDto())
        } catch (e: Exception) {}
    }

}