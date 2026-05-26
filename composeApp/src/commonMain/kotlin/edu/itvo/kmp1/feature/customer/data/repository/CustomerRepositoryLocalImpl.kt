package edu.itvo.kmp1.feature.customer.data.repository


import edu.itvo.kmp1.feature.customer.core.repository.BaseInMemoryRepository
import edu.itvo.kmp1.feature.customer.data.datasource.remote.CustomerRemoteDataSource
import edu.itvo.kmp1.feature.customer.data.mapper.toDomain
import edu.itvo.kmp1.feature.customer.data.mapper.toDto

import edu.itvo.kmp1.feature.customer.domain.model.Customer
import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

class CustomerRepositoryLocalImpl(
    private val remote: CustomerRemoteDataSource
) : BaseInMemoryRepository<Customer, String>(), CustomerRepository {

    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        scope.launch {
            try {
                val remoteCustomers = remote.getCustomers().map { it.toDomain() }
                state.value = remoteCustomers
            }catch (e: Exception){
                println("Error al cargar los clientes iniciales: $e")
            }
        }
    }

    override fun getId(item: Customer): String = item.id

    override suspend fun update(item: Customer) {
        super.save(item)
        try {
            remote.updateCustomer(item.id, item.toDto())
        } catch (e: Exception) {
            println(e)
        }
    }

    override suspend fun save(item: Customer) {
        super.save(item)
        try {
            remote.saveCustomer(item.toDto())
        } catch (e: Exception) {
            println(e)
        }
    }

    override suspend fun deleteById(id: String) {
        super.deleteById(id)
        try {
            remote.deleteCustomer(id)
        } catch (e: Exception) {
            println(e)
        }
    }
}

