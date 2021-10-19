package com.example.catanResourceManager

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class RollManager(private val listenerList: MutableList<RollListener>) {
    var currentRoll = Roll()

    fun handleNewRoll(newRoll: Roll) {
        for (listener in listenerList) listener.handleNewRoll(newRoll)
        currentRoll = newRoll
    }
}

// add this interface to any class that needs to listen to new rolls
interface RollListener {
    fun handleNewRoll(newRoll: Roll)
}

class Roll(
    val primaryDie: Int = 0,
    val secondaryDie: Int = 0,
    val eventDie: EventDieResult = EventDieResult.BARBARIAN
) {

}

enum class EventDieResult() {
    BARBARIAN, GREEN, BLUE, YELLOW
}

@Composable
fun DiceView(rollManager: RollManager, modifier: Modifier) {
    val diceLength = 18.dp
    val eventTextLength = 90.dp
    Row(
        modifier = modifier
            .padding()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rollManager.currentRoll.primaryDie.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(4.dp)
                .border(BorderStroke(1.dp, Color.Black))
                .padding(4.dp)
                .width(diceLength)
                .height(diceLength)
        )
        Text(
            text = rollManager.currentRoll.secondaryDie.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(4.dp)
                .border(BorderStroke(1.dp, Color.Black))
                .background(Color.Red)
                .padding(4.dp)
                .width(diceLength)
                .height(diceLength)
        )
        Text(
            text = rollManager.currentRoll.eventDie.name,
            modifier = Modifier
                .padding(4.dp)
                .widthIn(eventTextLength)
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = "Roll")
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = "Edit")
        }

    }
}

@Preview
@Composable
fun testView() {
    val rollManager = RollManager(mutableListOf())
    DiceView(rollManager = rollManager, Modifier)
}