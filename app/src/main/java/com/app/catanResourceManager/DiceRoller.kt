package com.app.catanResourceManager

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.catanResourceManager.ui.theme.AppTypography
import kotlin.random.Random

class RollManager(private val listenerList: MutableList<RollListener>) {
    var currentRoll = Roll()

    fun handleNewRoll(newRoll: Roll) {
        for (listener in listenerList) listener.handleNewRoll(newRoll)
        currentRoll.primaryDie.value = newRoll.primaryDie.value
        currentRoll.secondaryDie.value = newRoll.secondaryDie.value
        currentRoll.eventDie.value = newRoll.eventDie.value
    }
}

// add this interface to any class that needs to listen to new rolls
interface RollListener {
    fun handleNewRoll(newRoll: Roll)
}

class Roll(
    val primaryDie: MutableState<Int> = mutableStateOf(0),
    val secondaryDie: MutableState<Int> = mutableStateOf(0),
    val eventDie: MutableState<EventDieResult> = mutableStateOf(EventDieResult.BARBARIAN)
) {
    fun randomize(): Roll {
        primaryDie.value = Random.nextInt(1, 7)
        secondaryDie.value = Random.nextInt(1, 7)
        eventDie.value = when (Random.nextInt(1, 7)) {
            1 -> EventDieResult.BLUE
            2 -> EventDieResult.GREEN
            3 -> EventDieResult.YELLOW
            else -> EventDieResult.BARBARIAN
        }
        return this
    }
}

enum class EventDieResult() {
    BARBARIAN, GREEN, BLUE, YELLOW
}

@Composable
fun DiceView(rollManager: RollManager, modifier: Modifier) {
    val diceLength = 18.dp
    val eventTextLength = 70.dp
    Row(
        modifier = modifier
            .padding(10.dp)
            .background(MaterialTheme.colors.background, shape = RoundedCornerShape(10)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rollManager.currentRoll.primaryDie.value.toString(),
            textAlign = TextAlign.Center,
            style = AppTypography.body1,
            modifier = Modifier
                .padding(4.dp)
                .border(BorderStroke(1.dp, Color.Black))
                .background(Color.White)
                .padding(4.dp)
                .width(diceLength)
                .height(diceLength)

        )
        Text(
            text = rollManager.currentRoll.secondaryDie.value.toString(),
            textAlign = TextAlign.Center,
            style = AppTypography.body1,
            modifier = Modifier
                .padding(4.dp)
                .border(BorderStroke(1.dp, Color.Black))
                .background(Color.Red)
                .padding(4.dp)
                .width(diceLength)
                .height(diceLength)
        )
        Text(
            text = rollManager.currentRoll.eventDie.value.name,
            style = AppTypography.body1,
            modifier = Modifier
                .padding(4.dp)
                .widthIn(eventTextLength)
        )
        Button(
            onClick = { rollManager.handleNewRoll(Roll().randomize()) },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = "Roll",
                style = AppTypography.body1
            )
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = "Edit",
                style = AppTypography.body1
            )
        }

    }
}

@Preview
@Composable
fun testView() {
    val rollManager = RollManager(mutableListOf())
    DiceView(rollManager = rollManager, Modifier)
}
