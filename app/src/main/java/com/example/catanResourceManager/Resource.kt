package com.example.catanResourceManager.ui.theme.homepage

import androidx.compose.runtime.*
import com.example.catanResourceManager.R

open class Resource(
    val name: ResourceName,
    var amount: MutableState<Int> = mutableStateOf(0),
    val imageResource: Int = 0
)

class ResourceManager {
    val resourceList = listOf<Resource>(
        Brick().apply { amount.value = 1 }
    )

    fun modifySupply(resourceName: ResourceName, amount: Int) {
        resourceList.first { it.name == resourceName }.apply {
            val total = amount + this.amount.value
            if (total > 0) this.amount.value = total
        }
    }

    fun getResource(resourceName: ResourceName): Resource {
        return resourceList.first { it.name == resourceName }
    }

    fun getResourceAmount(resourceName: ResourceName): Int {
        return resourceList.first { it.name == resourceName }.amount.value
    }
 }

fun MutableMap<ResourceName, Int>.merge(another: MutableMap<ResourceName, Int>) = another.forEach { (name, amount) ->
    val newAmount = amount + (this[name] ?: 0)
    this[name] = if (newAmount >= 0) newAmount else 0
}

class Brick: Resource(
    ResourceName.BRICK,
    imageResource = R.drawable.catan_logo_brick
)

enum class ResourceName {
    LUMBER, WOOL, GRAIN, BRICK, ORE, PAPER, CLOTH, COIN, GOLD
}