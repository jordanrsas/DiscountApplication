package com.cjra.discountapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cjra.discountapp.ui.theme.LightButtonColor
import com.cjra.discountapp.ui.theme.LightTextColor

@Composable
fun TwoCards(
    titleCardOne: String, numberCardOne: Double,
    titleCardTwo: String, numberCardTwo: Double
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MainCard(
            title = titleCardOne,
            number = numberCardOne,
            modifier = Modifier
                .padding(start = 30.dp)
                .weight(1f)
        )
        SpaceWidth()
        MainCard(
            title = titleCardTwo,
            number = numberCardTwo,
            modifier = Modifier
                .padding(end = 30.dp)
                .weight(1f)
        )
    }
}

@Composable
fun MainCard(title: String, number: Double, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = LightButtonColor,
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title, color = LightTextColor, fontSize = 20.sp)
            Text(text = "$$number", color = LightTextColor, fontSize = 20.sp)
        }
    }
}