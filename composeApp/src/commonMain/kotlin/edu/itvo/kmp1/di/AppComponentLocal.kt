package edu.itvo.kmp1.di



import edu.itvo.kmp1.feature.customer.data.repository.CustomerRepositoryLocalImpl
import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel
import edu.itvo.kmp1.feature.customer.domain.usecase.ObserveCustomersUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.SaveCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.DeleteCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.UpdateCustomerUseCase
/*
class AppComponentLocal {

    private val repository: CustomerRepository by lazy {
        CustomerRepositoryLocalImpl()
    }

    private val observeCustomersUseCase by lazy {
        ObserveCustomersUseCase(repository)
    }

    private val saveCustomerUseCase by lazy {
        SaveCustomerUseCase(repository)
    }

    private val deleteCustomerUseCase by lazy {
        DeleteCustomerUseCase(repository)
    }

    private val updateCustomerUseCase by lazy {
        UpdateCustomerUseCase(repository)
    }

    val customerViewModel: CustomerViewModel by lazy {
        CustomerViewModel(
            observeCustomersUseCase,
            saveCustomerUseCase,
            deleteCustomerUseCase,
            updateCustomerUseCase
        )
    }
}
*/
