package edu.itvo.kmp1.feature.customer.data.repository

import edu.itvo.kmp1.feature.customer.data.datasource.remote.CustomerRemoteDataSource
import edu.itvo.kmp1.feature.customer.data.mapper.toDomain
import edu.itvo.kmp1.feature.customer.data.mapper.toDto
import edu.itvo.kmp1.feature.customer.domain.model.Customer
import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CustomerRepositoryImpl(
    private val remote: CustomerRemoteDataSource
) : CustomerRepository {

    override fun observeAll(): Flow<List<Customer>> = flow {

        val customers =
            remote.getCustomers()

        emit(
            customers.map {
                it.toDomain()
            }
        )
    }

    override suspend fun findById(
        id: String
    ): Customer? {

        return observeAll()
            .let { flow ->

                var result: Customer? = null

                flow.collect { customers ->

                    result = customers.find {
                        it.id == id
                    }
                }

                result
            }
    }

    override suspend fun save(
        item: Customer
    ) {

        remote.saveCustomer(
            item.toDto()
        )
    }

    override suspend fun deleteById(
        id: String
    ) {

        remote.deleteCustomer(id)
    }
}