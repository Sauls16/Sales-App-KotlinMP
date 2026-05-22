package edu.itvo.kmp1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.itvo.kmp1.feature.customer.presentation.screen.CustomerFormScreen
import edu.itvo.kmp1.feature.customer.presentation.screen.CustomerListScreen
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel


@Composable
fun AppNavHost(
    customerViewModel: CustomerViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CustomerRoutes.List.route
    ) {

        composable(CustomerRoutes.List.route) {

            CustomerListScreen(
                viewModel = customerViewModel,
                onAddClick = {
                    navController.navigate(CustomerRoutes.Form.route)
                }
            )
        }

        composable(CustomerRoutes.Form.route) {

            CustomerFormScreen(
                viewModel = customerViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}