package edu.itvo.kmp1.feature.Product.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import edu.itvo.kmp1.feature.Product.domain.model.Product
import edu.itvo.kmp1.feature.Product.presentation.component.ProductFormCard
import edu.itvo.kmp1.feature.Product.presentation.event.ProductEvent
import edu.itvo.kmp1.feature.Product.presentation.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(
    viewModel: ProductViewModel,
    onBack: () -> Unit
) {
    val selected = viewModel.selectedProduct
    var code by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var isTaxable by remember { mutableStateOf(true) }

    //llenar si hay producto seleccionado
    LaunchedEffect(selected) {
        selected?.let {
            code = it.code
            description = it.description
            category = it.category
            price = it.price.toString()
            stock = it.stock.toString()
            isTaxable = it.taxable
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (selected == null) "Nuevo Producto" else "Editar Producto")
                },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            ProductFormCard(
                code = code,
                description = description,
                category = category,
                price = price,
                stock = stock,
                taxable = isTaxable,
                onCodeChange = { code = it },
                onDescriptionChange = { description = it },
                onCategoryChange = { category = it },
                onPriceChange = { price = it },
                onStockChange = { stock = it },
                onTaxableChange = { isTaxable = it },
                onSaveClick = {
                    val product = Product(
                            code = code,
                            description = description,
                            category = category,
                            price = price.toDoubleOrNull() ?: 0.0,
                            stock = stock.toIntOrNull() ?: 0,
                            taxable = isTaxable
                    )

                    if (selected == null){
                        viewModel.onEvent(ProductEvent.SaveProduct(product))
                    }else{
                        viewModel.onEvent(ProductEvent.UpdateProduct(product))
                    }

                    code = ""
                    description = ""
                    category = ""
                    price = ""
                    stock = ""
                    isTaxable = true

                    onBack()
                }
            )
        }
    }
}
