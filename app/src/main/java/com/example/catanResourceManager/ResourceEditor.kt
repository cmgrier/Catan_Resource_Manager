package com.example.catanResourceManager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catanResourceManager.ui.theme.*

val cardHeight = 90.dp

class NumberManager {
    val numberList = mutableMapOf<Int, ResourceManager>()

    init {
        for (i in 2..12) {
            if (i != 7) {
                numberList[i] = ResourceManager()
            }
        }
    }

    fun getRollResult(roll: Roll): List<Resource> {
        val number = roll.primaryDie.value + roll.secondaryDie.value
        val manager = numberList[number]
        val resourceList = mutableListOf<Resource>()
        manager?.let {
            for (resource in it.resourceList) {
                if (resource.amount.value > 0) resourceList.add(resource)
            }
        }
        return resourceList.toList()
    }
}

@Preview
@Composable
fun ResourceEditor(numberManager: NumberManager = NumberManager()) {
    val addState: MutableState<Boolean> = remember { mutableStateOf(false) }
    Box(Modifier.background(Color.Transparent)) {
        LazyColumn(Modifier.background(Color.Transparent)) {
            for (entry in numberManager.numberList) {
                item { NumberCard(entry.key, remember { entry.value }, remember { addState }) }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .border(2.dp, MaterialTheme.colors.onSurface, CircleShape)
                .align(alignment = Alignment.BottomEnd),
            onClick = { addState.value = !addState.value },
            backgroundColor = MaterialTheme.colors.background
        ) {
            Text(
                text = if (addState.value) "+" else "-",
                style = AppTypography.h3
            )
        }
    }
}

@Preview
@Composable
fun NumberCard(number: Int = 0, resourceManager: ResourceManager = remember { ResourceManager() }, add: MutableState<Boolean> = remember { mutableStateOf(false) }) {
    Card(
        shape = Shapes.medium,
        backgroundColor = MaterialTheme.colors.primarySurface,
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
            ResourceListView(remember { resourceManager }, remember { add })
        }
    }
}

@Composable
fun NumberHeader(number: Int) {
    Text(
        text = number.toString(),
        style = AppTypography.h2,
        modifier = Modifier
            .heightIn(max = cardHeight)
            .padding(12.dp)
    )

}

@Composable
fun ResourceListView(resourceManager: ResourceManager = ResourceManager(), add: MutableState<Boolean>) {
    var editVisibility by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                editVisibility = !editVisibility
            }
    ) {
        LazyRow (
            modifier = Modifier
                .wrapContentWidth(align = Alignment.Start)
                .weight(1f)
        ) {
            for (resource in resourceManager.resourceList) {
                item {
                    ResourceView(resource, remember { resourceManager }, remember { add })
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
                    .background(MaterialTheme.colors.primary)
            ) {
                Text(text = "Edit")
            }
        }
    }
}

@Composable
fun ResourceView(resource: Resource, resourceManager: ResourceManager, add: MutableState<Boolean>) {
    val iconSize = 80.dp
    Box(
        modifier = Modifier.clickable {
            resourceManager.modifySupply(resource.name, if (add.value) 1 else -1)
        }
    ) {
        Image(
            painter = painterResource(id = resource.imageResource),
            contentDescription = resource.name.toString(),
            modifier = Modifier.size(iconSize)
        )
        Text(
            text = resourceManager.getResourceAmount(resource.name).toString(),
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



