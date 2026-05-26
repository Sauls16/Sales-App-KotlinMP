package edu.itvo.kmp1.di



import edu.itvo.kmp1.core.network.createHttpClient
import edu.itvo.kmp1.feature.Product.data.datasource.remote.ProductRemoteDataSource
import edu.itvo.kmp1.feature.Product.data.remote.ProductApi
import edu.itvo.kmp1.feature.Product.data.repository.ProductRepositoryLocalImpl
import edu.itvo.kmp1.feature.Product.domain.repository.ProductRepository
import edu.itvo.kmp1.feature.Product.domain.usecase.DeleteProductUseCase
import edu.itvo.kmp1.feature.Product.domain.usecase.ObserveProductsUseCase
import edu.itvo.kmp1.feature.Product.domain.usecase.SaveProductUseCase
import edu.itvo.kmp1.feature.Product.domain.usecase.UpdateProductUseCase
import edu.itvo.kmp1.feature.Product.presentation.viewmodel.ProductViewModel
import edu.itvo.kmp1.feature.customer.data.datasource.remote.CustomerRemoteDataSource
import edu.itvo.kmp1.feature.customer.data.remote.CustomerApi
import edu.itvo.kmp1.feature.customer.data.repository.CustomerRepositoryLocalImpl
import edu.itvo.kmp1.feature.customer.domain.repository.CustomerRepository
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel
import edu.itvo.kmp1.feature.customer.domain.usecase.ObserveCustomersUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.SaveCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.DeleteCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.UpdateCustomerUseCase

class AppComponentLocal {

    private val httpClient = createHttpClient()

    private val customerApi = CustomerApi(
        client = httpClient,
        baseUrl = "http://192.168.0.5:3000"
    )
    private val customerRemote = CustomerRemoteDataSource(customerApi)
    private val customerRepository: CustomerRepository by lazy {
        CustomerRepositoryLocalImpl(customerRemote)
    }

    val customerViewModel: CustomerViewModel by lazy {
        CustomerViewModel(
            ObserveCustomersUseCase(customerRepository),
            SaveCustomerUseCase(customerRepository),
            DeleteCustomerUseCase(customerRepository),
            UpdateCustomerUseCase(customerRepository)
        )
    }

    private val productApi = ProductApi(
        client = httpClient,
        baseUrl = "http://192.168.0.5:3000"
    )

    private val productRemote = ProductRemoteDataSource(productApi)

    private val productRepository: ProductRepository by lazy {
        ProductRepositoryLocalImpl(productRemote)
    }

    val productViewModel: ProductViewModel by lazy {
        ProductViewModel(
            ObserveProductsUseCase(productRepository),
            SaveProductUseCase(productRepository),
            DeleteProductUseCase(productRepository),
            UpdateProductUseCase(productRepository)
        )
    }

}




