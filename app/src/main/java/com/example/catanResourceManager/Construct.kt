package com.example.catanResourceManager

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

open class Construct(
    val name: ConstructName,
    val cost: List<Resource>,
    val amountBuilt: MutableState<Int> = mutableStateOf(0),
    val imageResource: Int = 0
) {
    fun canPurchase(resourceManager: ResourceManager) : Boolean {
        for (resource in cost) {
            if (resource.amount.value > resourceManager.getResource(resource.name).amount.value) {
                return false
            }
        }
        return true
    }
}

enum class ConstructName {
    ROAD, SETTLEMENT, CITY
}

class Road: Construct(
    ConstructName.ROAD,
    listOf(Brick(2))
)

class Settlement: Construct(
    ConstructName.SETTLEMENT,
    listOf(Wool(1), Lumber(1), Grain(1), Brick(1)),
    imageResource = R.drawable.catan_logo_settlement
)

class City: Construct(
    ConstructName.CITY,
    listOf(Ore(3), Grain(2)),
    imageResource = R.drawable.catan_logo_city
)