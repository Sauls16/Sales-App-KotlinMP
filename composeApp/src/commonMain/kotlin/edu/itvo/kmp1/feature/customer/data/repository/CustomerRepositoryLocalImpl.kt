package edu.itvo.kmp1.feature.customer.data.repository


import edu.itvo.kmp1.feature.customer.core.repository.BaseInMemoryRepository

import edu.itvo.kmp1.feature.customer.domain.model.Customer
import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository

class CustomerRepositoryLocalImpl : BaseInMemoryRepository<Customer, String>(), CustomerRepository{

    override fun getId(item: Customer): String {
        return item.id
    }

    override suspend fun update(item: Customer) {
        save(item)
    }
}