package com.example.catanResourceManager

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catanResourceManager.Player.PlayerManager
import com.example.catanResourceManager.ui.theme.AppTypography

@Composable
fun HandView(modifier: Modifier, playerManager: PlayerManager) {

    Box(modifier = modifier) {
        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 0..4) {
                    ResourceView(resource = playerManager.hand.resourceList[i])
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 5..8) {
                    ResourceView(resource = playerManager.hand.resourceList[i])
                }
            }
        }
    }
}

@Preview
@Composable
fun TestHandView() {
    HandView(modifier = Modifier, PlayerManager())
}

@Composable
fun ResourceView(resource: Resource) {
    val size = 120.dp
    val width = 48.dp
    Box(
        modifier = Modifier
            .size(size)
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = resource.imageResource),
            contentDescription = resource.name.toString()
        )
        Text(
            text = resource.amount.value.toString(),
            style = AppTypography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding()
                .align(Alignment.TopEnd)
                .width(width)
                .padding(top = 3.dp)
        )
    }
}

@Preview
@Composable
fun TestResourceView() {
    ResourceView(resource = Brick(2))
}