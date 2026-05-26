package edu.itvo.kmp1.feature.customer.domain.usecase

import edu.itvo.kmp1.feature.customer.domain.model.Customer
import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository
import me.tatarka.inject.annotations.Inject

class UpdateCustomerUseCase (
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(customer: Customer){
        repository.update(customer)
    }
}