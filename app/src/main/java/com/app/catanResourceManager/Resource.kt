package com.app.catanResourceManager

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.catanResourceManager.R


open class Resource(
    val name: ResourceName,
    var amount: MutableState<Int> = mutableStateOf(0),
    val imageResource: Int = 0
)

class ResourceManager {
    val resourceList = listOf(
        Wool(),
        Brick(),
        Lumber(),
        Grain(),
        Ore(),
        Paper(),
        Cloth(),
        Coin(),
        Gold()
    )

    fun modifySupply(resourceName: ResourceName, amount: Int) {
        resourceList.first { it.name == resourceName }.apply {
            val total = amount + this.amount.value
            if (total >= 0) this.amount.value = total
        }
    }

    fun getResource(resourceName: ResourceName): Resource {
        return resourceList.first { it.name == resourceName }
    }

    fun getResourceAmount(resourceName: ResourceName): Int {
        return resourceList.first { it.name == resourceName }.amount.value
    }
}

enum class ResourceName {
    LUMBER, WOOL, GRAIN, BRICK, ORE, PAPER, CLOTH, COIN, GOLD
}

class Brick(amount: Int = 0): Resource(
    name = ResourceName.BRICK,
    amount = mutableStateOf(amount),
    imageResource = R.drawable.catan_logo_brick
)

class Wool(amount: Int = 0): Resource(
    name = ResourceName.WOOL,
    amount = mutableStateOf(amount),
    imageResource = R.drawable.catan_logo_sheep
)

class Lumber(amount: Int = 0): Resource(
    name = ResourceName.LUMBER,
    amount = mutableStateOf(amount),
    imageResource = R.drawable.catan_logo_wood
)

class Grain(amount: Int = 0): Resource(
    name = ResourceName.GRAIN,
    amount = mutableStateOf(amount),
    imageResource = R.drawable.catan_logo_wheat
)

class Ore(amount: Int = 0): Resource(
    name = ResourceName.ORE,
    amount = mutableStateOf(amount),
    imageResource = R.drawable.catan_logo_rock
)

class Paper(amount: Int = 0): Resource(
    name = ResourceName.PAPER,
    amount = mutableStateOf(amount),
    imageResource = R.drawable.catan_logo_paper
)

class Cloth(amount: Int = 0): Resource(
    name = ResourceName.CLOTH,
    amount = mutableStateOf(amount),
    imageResource = R.drawable.catan_logo_cloth
)

class Coin(amount: Int = 0): Resource(
    name = ResourceName.COIN,
    amount = mutableStateOf(amount),
    imageResource = R.drawable.catan_logo_coin
)

class Gold(amount: Int = 0): Resource(
    name = ResourceName.GOLD,
    amount = mutableStateOf(amount),
    imageResource = R.drawable.catan_logo_coin
)