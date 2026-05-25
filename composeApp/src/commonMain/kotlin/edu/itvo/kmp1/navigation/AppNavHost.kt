package edu.itvo.kmp1.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.itvo.kmp1.feature.Product.presentation.screen.ProductFormScreen
import edu.itvo.kmp1.feature.Product.presentation.screen.ProductListScreen
import edu.itvo.kmp1.feature.Product.presentation.viewmodel.ProductViewModel
import edu.itvo.kmp1.feature.customer.presentation.screen.CustomerFormScreen
import edu.itvo.kmp1.feature.customer.presentation.screen.CustomerListScreen
import edu.itvo.kmp1.feature.customer.presentation.viewmodel.CustomerViewModel


@Composable
fun AppNavHost(
    customerViewModel: CustomerViewModel,
    productViewModel: ProductViewModel
) {


    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == ProductRoutes.List.route || currentRoute == CustomerRoutes.List.route) {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Productos") },
                        label = { Text("Productos") },
                        selected = currentRoute == ProductRoutes.List.route,
                        onClick = {
                            navController.navigate(ProductRoutes.List.route) {
                                popUpTo(navController.graph.findStartDestination().route ?: "") { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Clientes") },
                        label = { Text("Clientes") },
                        selected = currentRoute == CustomerRoutes.List.route,
                        onClick = {
                            navController.navigate(CustomerRoutes.List.route) {
                                popUpTo(navController.graph.findStartDestination().route ?: "") { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
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

        composable(ProductRoutes.List.route) {
            ProductListScreen(
                viewModel = productViewModel,
                onAddClick = {
                    navController.navigate(ProductRoutes.Form.route)
                }
            )
        }

        composable(ProductRoutes.Form.route) {
            ProductFormScreen(
                viewModel = productViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

    }

    }
}

