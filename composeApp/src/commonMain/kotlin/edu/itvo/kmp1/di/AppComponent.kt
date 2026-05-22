package edu.itvo.kmp1.di
import edu.itvo.kmp1.core.network.createHttpClient
import edu.itvo.kmp1.feature.customer.data.datasource.remote.CustomerRemoteDataSource
import edu.itvo.kmp1.feature.customer.data.remote.CustomerApi
import edu.itvo.kmp1.feature.customer.data.repository.CustomerRepositoryImpl
import edu.itvo.kmp1.feature.customer.domain.usecase.DeleteCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.ObserveCustomersUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.SaveCustomerUseCase
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel

class AppComponent {

    private val httpClient =
        createHttpClient()

    private val api =
        CustomerApi(
            client = httpClient,
            baseUrl = "http://10.1.6.138:3000"
        )

    private val remote =
        CustomerRemoteDataSource(api)

    private val repository =
        CustomerRepositoryImpl(remote)

    private val observeCustomersUseCase =
        ObserveCustomersUseCase(repository)

    private val saveCustomerUseCase =
        SaveCustomerUseCase(repository)

    private val deleteCustomerUseCase =
        DeleteCustomerUseCase(repository)

    val customerViewModel =
        CustomerViewModel(
            observeCustomersUseCase,
            saveCustomerUseCase,
            deleteCustomerUseCase
        )
}