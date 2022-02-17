package com.app.catanResourceManager

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

interface Construct {
    val name: ConstructName
    val cost: List<Resource>
    val amountBuilt: MutableState<Int>
    val imageResource: Int

    fun canPurchase(resourceManager: ResourceManager) : Boolean {
        for (resource in cost) {
            if (resource.amount.value > resourceManager.getResource(resource.name).amount.value) {
                return false
            }
        }
        return true
    }
}

open class ConstantConstruct (
    override val name: ConstructName,
    override val cost: List<Resource>,
    override val amountBuilt: MutableState<Int> = mutableStateOf(0),
    override val imageResource: Int = 0
) : Construct {

}

enum class ConstructName (val title: String) {
    ROAD("Road"),
    SETTLEMENT("Settlement"),
    CITY("City"),
    CITY_WALL("City Wall"),
    DEV_CARD("Dev Card"),
    CLOTH_CI("Cloth C.I."),
    PAPER_CI("Paper C.I."),
    COIN_CI("Coin C.I.")
}

class Road: ConstantConstruct(
    ConstructName.ROAD,
    listOf(Brick(1), Lumber(1))
)

class CityWall: ConstantConstruct(
    ConstructName.CITY_WALL,
    listOf(Brick(2))
)

class DevCard: ConstantConstruct(
    ConstructName.DEV_CARD,
    listOf(Wool(1), Grain(1), Ore(1))
)

class Settlement: ConstantConstruct(
    ConstructName.SETTLEMENT,
    listOf(Wool(1), Lumber(1), Grain(1), Brick(1)),
    imageResource = R.drawable.catan_logo_settlement
)

class City: ConstantConstruct(
    ConstructName.CITY,
    listOf(Ore(3), Grain(2)),
    imageResource = R.drawable.catan_logo_city
)

class ClothImprovement: ConstantConstruct(
    ConstructName.CLOTH_CI,
    listOf(Cloth(1))
)

class PaperImprovement: ConstantConstruct(
    ConstructName.PAPER_CI,
    listOf(Paper(1))
)

class CoinImprovement: ConstantConstruct(
    ConstructName.COIN_CI,
    listOf(Coin(1))
)