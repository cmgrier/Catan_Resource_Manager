package com.example.catanResourceManager

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catanResourceManager.ui.theme.Primary
import com.example.catanResourceManager.ui.theme.Secondary
import com.example.catanResourceManager.ui.theme.Shapes
import com.example.catanResourceManager.ui.theme.Typography
import com.example.catanResourceManager.ui.theme.homepage.Resource
import com.example.catanResourceManager.ui.theme.homepage.ResourceManager
import com.example.catanResourceManager.ui.theme.homepage.ResourceName

val cardHeight = 90.dp

@Composable
fun ResourceEditor() {
    val numbers = mutableMapOf<Int, ResourceManager>()
    var addState = true

    for (i in 2..12) {
        if (i != 7) {
            numbers[i] = ResourceManager()
        }
    }
    Box {
        LazyColumn {
            for (entry in numbers) {
                item {
                    NumberCard(entry.key, entry.value, addState)
                }
            }
        }
        FloatingActionButton(onClick = { addState = !addState }) {
            Icon(Icons.Filled.Add, "Add State $addState")
        }
    }
}

@Preview
@Composable
fun NumberCard(number: Int = 0, resourceManager: ResourceManager = ResourceManager(), add: Boolean = false) {
    resourceManager.modifySupply(ResourceName.BRICK, 10)
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
            ResourceListView(resourceManager, add)
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
fun ResourceListView(resourceManager: ResourceManager = ResourceManager(), add: Boolean) {
    var editVisibility by remember { mutableStateOf(false) }
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
            for (resource in resourceManager.map.entries) {
                if (resource.value != 0) {
                    item {
                        ResourceView(Resource(resource.key), resourceManager, add)
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
fun ResourceView(resource: Resource, resourceManager: ResourceManager, add: Boolean) {
    val iconSize = 80.dp
    Box(
        modifier = Modifier.clickable {
            val currentAmount = resourceManager.map[resource.name]
            currentAmount?.let {
                resourceManager.map[resource.name] = if (add) it.inc() else it.dec()
            }
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.brickalpha),
            contentDescription = resource.name.toString(),
            modifier = Modifier.size(iconSize)
        )
        Text(
            text = resourceManager.map[resource.name].toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .width(30.dp)
                .padding(top = 2.dp)
        )
    }
}



