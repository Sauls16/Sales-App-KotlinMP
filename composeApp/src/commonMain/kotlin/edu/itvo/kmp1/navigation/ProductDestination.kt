package edu.itvo.kmp1.navigation

sealed class ProductDestination {
    data object List : ProductDestination()
    data object Form : ProductDestination()
}