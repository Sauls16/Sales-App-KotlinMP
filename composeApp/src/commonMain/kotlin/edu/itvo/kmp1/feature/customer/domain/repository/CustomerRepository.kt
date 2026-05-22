package edu.itvo.kmp1.feature.customer.domain.repository

import edu.itvo.kmp1.feature.customer.core.repository.BaseRepository
import edu.itvo.kmp1.feature.customer.domain.model.Customer


interface CustomerRepository :
    BaseRepository<Customer, String>