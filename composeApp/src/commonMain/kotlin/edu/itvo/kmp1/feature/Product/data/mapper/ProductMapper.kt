package edu.itvo.kmp1.feature.Product.data.mapper

import edu.itvo.kmp1.feature.Product.data.dto.ProductDto
import edu.itvo.kmp1.feature.Product.domain.model.Product

fun ProductDto.toDomain(): Product{

    return Product(
        code = code,
        description = description,
        category = category,
        price = price,
        stock = stock,
        taxable = taxable
    )
}

fun Product.toDto(): ProductDto{

    return ProductDto(
        code = code,
        description = description,
        category = category,
        price = price,
        stock = stock,
        taxable = taxable
    )
}

