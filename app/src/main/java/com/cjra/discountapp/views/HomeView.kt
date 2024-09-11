package com.cjra.discountapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cjra.discountapp.components.Alert
import com.cjra.discountapp.components.MainButton
import com.cjra.discountapp.components.MainTextField
import com.cjra.discountapp.components.SpaceHeight
import com.cjra.discountapp.components.TwoCards
import com.cjra.discountapp.ui.theme.LightTertiaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Discount App", color = Color.White) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }) {
        ContentHomeView(it)
    }
}

@Composable
fun ContentHomeView(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var price by remember {
            mutableStateOf("")
        }
        var discount by remember {
            mutableStateOf("")
        }
        var priceDiscount by remember {
            mutableDoubleStateOf(0.0)
        }
        var totalDiscount by remember {
            mutableDoubleStateOf(0.0)
        }

        var showAlert by remember {
            mutableStateOf(false)
        }

        TwoCards(
            titleCardOne = "Total", numberCardOne = totalDiscount,
            titleCardTwo = "Discount", numberCardTwo = priceDiscount
        )

        MainTextField(value = price, onValueChange = { price = it }, label = "Price")
        SpaceHeight()
        MainTextField(value = discount, onValueChange = { discount = it }, label = "Discount")
        SpaceHeight(12.dp)
        MainButton(text = "Apply discount") {
            if (price.isNotBlank() && discount.isNotBlank()) {
                priceDiscount = calculatePrice(price.toDouble(), discount.toDouble())
                totalDiscount = calculateDiscount(price.toDouble(), discount.toDouble())
            } else {
                showAlert = true
            }
        }
        SpaceHeight()
        MainButton(text = "Clear", color = LightTertiaryColor) {
            price = ""
            discount = ""
            priceDiscount = 0.0
            totalDiscount = 0.0
        }

        if (showAlert) {
            Alert(title = "Alert",
                message = "Type Price and/or Discount",
                confirmText = "Accept",
                onConfirmClick = { showAlert = false }) {}
        }
    }
}

fun calculatePrice(price: Double, discount: Double): Double {
    val result = price - calculateDiscount(price, discount)
    return kotlin.math.round(result * 100) / 100.0
}

fun calculateDiscount(price: Double, discount: Double): Double {
    val result = price * (1 - discount / 100)
    return kotlin.math.round(result * 100) / 100.0
}