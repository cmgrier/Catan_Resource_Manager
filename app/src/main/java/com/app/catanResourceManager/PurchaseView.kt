package com.app.catanResourceManager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.catanResourceManager.Player.PlayerManager
import com.app.catanResourceManager.ui.theme.AppTypography

@Composable
fun MarketView(modifier: Modifier = Modifier, playerManager: PlayerManager) {
    Column(modifier = modifier) {
        CityImprovements(playerManager = playerManager)
        Row() {
            PurchaseView(construct = Settlement(), playerManager = playerManager)
            PurchaseView(construct = City(), playerManager = playerManager)
        }
        Row() {
            PurchaseView(construct = Road(), playerManager = playerManager)
            PurchaseView(construct = DevCard(), playerManager = playerManager)
        }
    }
}

@Preview
@Composable
fun MarketDemo() {
    MarketView(playerManager = PlayerManager())
}

@Composable
fun CityImprovements(playerManager: PlayerManager) {
    Column() {
        PurchaseView(modifier = Modifier.align(Alignment.CenterHorizontally), construct = PaperImprovement(), playerManager = playerManager)
        Row() {
            PurchaseView(construct = ClothImprovement(), playerManager = playerManager)
            PurchaseView(construct = CoinImprovement(), playerManager = playerManager)
        }
    }
}

@Composable
fun PurchaseView(modifier: Modifier = Modifier, construct: Construct, playerManager: PlayerManager) {
    val iconSize = 80.dp
    Box(
        modifier = modifier
            .clickable { playerManager.purchaseConstruct(construct) }
            .padding(2.dp)
            .size(iconSize)
    ) {
        if (construct.imageResource != 0) {
            Image(
                painter = painterResource(id = construct.imageResource),
                contentDescription = construct.name.toString(),
                modifier = Modifier.size(iconSize)
            )
        } else {
            Box {
                Box(modifier = Modifier
                    .background(color = Color.White, shape = CircleShape)
                    .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                    .size(iconSize)
                )
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = construct.name.title,
                    style = AppTypography.h6,
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .background(color = Color.Green, shape = CircleShape)
                        .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                        .align(Alignment.TopEnd)
                        .size(23.dp)
                )
            }
        }
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
    PurchaseView(construct = Settlement(), playerManager = PlayerManager())
}