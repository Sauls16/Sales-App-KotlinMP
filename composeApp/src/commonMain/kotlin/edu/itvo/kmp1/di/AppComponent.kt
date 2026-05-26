package edu.itvo.kmp1.di
import edu.itvo.kmp1.core.network.createHttpClient
import edu.itvo.kmp1.feature.Product.data.datasource.remote.ProductRemoteDataSource

import edu.itvo.kmp1.feature.customer.data.datasource.remote.CustomerRemoteDataSource
import edu.itvo.kmp1.feature.customer.data.remote.CustomerApi
import edu.itvo.kmp1.feature.customer.data.repository.CustomerRepositoryImpl
import edu.itvo.kmp1.feature.customer.domain.usecase.DeleteCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.ObserveCustomersUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.SaveCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.UpdateCustomerUseCase
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel

import edu.itvo.kmp1.feature.Product.data.remote.ProductApi
import edu.itvo.kmp1.feature.Product.data.repository.ProductRepositoryImpl
import edu.itvo.kmp1.feature.Product.domain.usecase.DeleteProductUseCase
import edu.itvo.kmp1.feature.Product.domain.usecase.ObserveProductsUseCase

import edu.itvo.kmp1.feature.Product.domain.usecase.SaveProductUseCase
import edu.itvo.kmp1.feature.Product.domain.usecase.UpdateProductUseCase
import edu.itvo.kmp1.feature.Product.presentation.viewmodel.ProductViewModel
import edu.itvo.kmp1.feature.customer.data.repository.CustomerRepositoryLocalImpl

class AppComponent {

    private val httpClient =
        createHttpClient()

    private val api =
        CustomerApi(
            client = httpClient,
            baseUrl = "http://192.168.0.5:3000"

        )

    private val remote =
        CustomerRemoteDataSource(api)

    private val repository =
        CustomerRepositoryLocalImpl(remote)

    private val observeCustomersUseCase =
        ObserveCustomersUseCase(repository)

    private val saveCustomerUseCase =
        SaveCustomerUseCase(repository)

    private val deleteCustomerUseCase =
        DeleteCustomerUseCase(repository)

    private val updateCustomerUseCase =
        UpdateCustomerUseCase(repository)

    val customerViewModel =
        CustomerViewModel(
            observeCustomersUseCase,
            saveCustomerUseCase,
            deleteCustomerUseCase,
            updateCustomerUseCase
        )


    private val productApi = ProductApi(
        client = httpClient,
        baseUrl = "http://192.168.0.5:3000"
    )

                private val productRemote = ProductRemoteDataSource(productApi)
                private val productRepository = ProductRepositoryImpl(productRemote)

                private val observeProductsUseCase = ObserveProductsUseCase(productRepository)
                private val saveProductUseCase = SaveProductUseCase(productRepository)
                private val deleteProductUseCase = DeleteProductUseCase(productRepository)
                private val updateProductUseCase = UpdateProductUseCase(productRepository)

        val productViewModel = ProductViewModel(
            observeProductsUseCase,
    saveProductUseCase,
    deleteProductUseCase,
    updateProductUseCase
    )
}