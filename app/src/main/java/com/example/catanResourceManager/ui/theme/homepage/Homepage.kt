package com.example.catanResourceManager.ui.theme.homepage

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.example.catanResourceManager.R
import com.example.catanResourceManager.ui.theme.*

@Composable
fun NumberList() {
    LazyColumn {
        for (number in numberList) {
            item {
                NumberCard(number = number)
            }
        }
    }
}

val cardHeight = 90.dp

@Preview
@Composable
fun NumberCard(number: Int = 0) {
    val resourceManager = ResourceManager()
    resourceManager.modifySupply(ResourceName.BRICK, 10)
    var editState = false
    Card(
        shape = Shapes.medium,
        backgroundColor = Secondary,
        elevation = 4.dp,
        modifier = Modifier
            .heightIn(min = cardHeight)
            .padding(4.dp),
    ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentHeight(Alignment.CenterVertically)
            ) {
                NumberHeader(number = number)
                ResourceListView(resourceManager)
            }
        }
}

@Composable
fun NumberHeader(number: Int) {
    Text(
        text = number.toString(),
        style = Typography.h2,
        modifier = Modifier
            .heightIn(max = cardHeight)
    )

}

@Composable
fun ResourceListView(resourceManager: ResourceManager = ResourceManager()) {
    var editVisibility by remember { mutableStateOf(false) }
    resourceManager.resourceMap[ResourceName.WOOL] = 2
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                editVisibility = !editVisibility
                Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show()
            }
    ) {
        LazyRow (
            modifier = Modifier
                .wrapContentWidth(align = Alignment.Start)
                .weight(1f)
                ) {
            for (resource in resourceManager.resourceMap.entries) {
                if (resource.value != 0) {
                    item {
                        ResourceView(resource = Resource(resource.key), number = resource.value)
                    }
                }
            }
        }
        if (editVisibility) {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .wrapContentWidth(Alignment.End)
                    .height(cardHeight)
                    .background(Primary)
            ) {
                Text(text = "Edit")
            }
        }
    }
}

@Composable
fun ResourceView(resource: Resource, number: Int) {
    val valueString = if (number > 1) " x $number" else ""
    val iconSize = 80.dp
    Box(

    ) {
        Image(
            painter = painterResource(id = R.drawable.brickalpha),
            contentDescription = resource.name.toString(),
            modifier = Modifier.size(iconSize)
        )
        Text(
            text = number.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.TopEnd).width(30.dp).padding(top = 2.dp)
        )
    }
}

val numberList = listOf(
    2,
    3,
    4,
    5,
    6,
    8,
    9,
    10,
    11,
    12
)