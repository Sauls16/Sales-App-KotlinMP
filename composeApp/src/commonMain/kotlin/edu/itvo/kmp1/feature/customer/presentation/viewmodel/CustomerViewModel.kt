package edu.itvo.kmp1.feature.customer.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import edu.itvo.kmp1.feature.customer.domain.model.Customer
import edu.itvo.kmp1.feature.customer.domain.usecase.DeleteCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.ObserveCustomersUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.SaveCustomerUseCase
import edu.itvo.kmp1.feature.customer.domain.usecase.UpdateCustomerUseCase
import edu.itvo.kmp1.feature.customer.presentation.event.CustomerEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

class CustomerViewModel @Inject constructor(
    private val observeCustomersUseCase: ObserveCustomersUseCase,
    private val saveCustomerUseCase: SaveCustomerUseCase,
    private val deleteCustomerUseCase: DeleteCustomerUseCase,
    private val updateCustomerUseCase: UpdateCustomerUseCase
){

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _customers =
        MutableStateFlow<List<Customer>>(emptyList())

    val customers: StateFlow<List<Customer>> = _customers

    init {
        observeCustomers()
    }

    var selectedCustomer by mutableStateOf<Customer?>(null)

    fun selectCustomer(customer: Customer?){
        selectedCustomer = customer
    }

    private fun observeCustomers() {
        scope.launch {
            observeCustomersUseCase()
                .collect { list ->
                    _customers.value = list
                }
        }
    }

    fun onEvent(event: CustomerEvent) {
        when (event) {
            is CustomerEvent.SaveCustomer -> {
                scope.launch {
                    saveCustomerUseCase(event.customer)
                }
            }
            is CustomerEvent.DeleteCustomer -> {
                scope.launch {
                    deleteCustomerUseCase(event.id)
                }
            }
            is CustomerEvent.UpdateCustomer -> {
                scope.launch {
                    updateCustomerUseCase(event.customer)
                }
            }
        }
    }
}