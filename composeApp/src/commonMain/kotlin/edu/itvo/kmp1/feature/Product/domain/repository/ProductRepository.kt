package edu.itvo.kmp1.feature.Product.domain.repository

import edu.itvo.kmp1.feature.Product.domain.model.Product
import edu.itvo.kmp1.feature.customer.core.repository.BaseRepository


interface ProductRepository :
        BaseRepository<Product, String>