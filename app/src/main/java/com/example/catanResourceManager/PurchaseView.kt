package com.example.catanResourceManager

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catanResourceManager.Player.PlayerManager
import com.example.catanResourceManager.ui.theme.AppTypography

@Composable
fun MarketView(modifier: Modifier = Modifier, playerManager: PlayerManager) {
    Column(
        modifier = modifier
    ){
        PurchaseView(construct = City(), playerManager)
        PurchaseView(construct = Settlement(), playerManager)
    }
}

@Preview
@Composable
fun MarketDemo() {
    MarketView(playerManager = PlayerManager())
}

@Composable
fun PurchaseView(construct: Construct, playerManager: PlayerManager) {
    val iconSize = 80.dp
    Box(
        modifier = Modifier.clickable {
            playerManager.purchaseConstruct(construct)
        }
    ) {
        Image(
            painter = painterResource(id = construct.imageResource),
            contentDescription = construct.name.toString(),
            modifier = Modifier.size(iconSize)
        )
        Text(
            text = construct.amountBuilt.value.toString(),
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = AppTypography.body1,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .width(30.dp)
                .padding(top = 2.dp)
        )
    }
}

@Preview
@Composable
fun PurchaseDemo() {
    PurchaseView(construct = Settlement(), PlayerManager())
}