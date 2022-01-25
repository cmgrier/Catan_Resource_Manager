package com.app.catanResourceManager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.catanResourceManager.Player.PlayerManager
import com.app.catanResourceManager.ui.theme.AppTypography
import com.app.catanResourceManager.ui.theme.Shapes

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
    val size = 90.dp
    Box(
        modifier = Modifier
            .size(size)
            .padding(2.dp)
            .background(MaterialTheme.colors.primary, shape = CircleShape)
    ) {
        Image(
            painter = painterResource(id = resource.imageResource),
            contentDescription = resource.name.toString(),
            modifier = Modifier
                .padding(start = 3.dp, bottom = 3.dp)
        )
        Text(
            text = resource.amount.value.toString(),
            style = AppTypography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 0.dp, end = 12.dp)
        )
    }
}

@Preview
@Composable
fun TestResourceView() {
    ResourceView(resource = Brick(2))
}