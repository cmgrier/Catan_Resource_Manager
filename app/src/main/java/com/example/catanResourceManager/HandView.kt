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
import androidx.compose.ui.unit.sp
import com.example.catanResourceManager.ui.theme.AppTypography
import com.example.catanResourceManager.ui.theme.homepage.Brick
import com.example.catanResourceManager.ui.theme.homepage.Resource

@Composable
fun HandView(modifier: Modifier) {
    Box(modifier = modifier) {
        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ResourceView(resource = Brick(), 1)
                ResourceView(resource = Brick(), 2)
                ResourceView(resource = Brick(), 5)
                ResourceView(resource = Brick(), 10)
                ResourceView(resource = Brick(), 15)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ResourceView(resource = Brick(), 12)
                ResourceView(resource = Brick(), 1)
                ResourceView(resource = Brick(), 0)
            }
        }
    }
}

@Preview
@Composable
fun TestHandView() {
    HandView(modifier = Modifier)
}

@Composable
fun ResourceView(resource: Resource, amount: Int) {
    val size = 120.dp
    val width = 48.dp
    Box(
        modifier = Modifier.size(size).padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.brickalpha),
            contentDescription = resource.name.toString()
        )
        Text(
            text = amount.toString(),
            style = AppTypography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding().align(Alignment.TopEnd).width(width).padding(top = 3.dp)
        )
    }
}

@Preview
@Composable
fun TestResourceView() {
    ResourceView(resource = Brick(), 2)
}