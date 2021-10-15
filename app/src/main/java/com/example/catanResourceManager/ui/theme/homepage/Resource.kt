package com.example.catanResourceManager.ui.theme.homepage

import com.example.catanResourceManager.R

class ResourceManager() {
    val resourceMap: MutableMap<ResourceName, Int> = mutableMapOf()
    init {
        for (resource in ResourceName.values()) {
            resourceMap[resource] = 0
        }
    }

    fun modifySupply(resource: ResourceName, amount: Int) {
        resourceMap[resource]?.let {
            if (it + amount >= 0) {
                resourceMap[resource] = it + amount
            } else {
                resourceMap[resource] = 0
            }
        }
    }
}

open class Resource(
    val name: ResourceName,
    val imageResource: Int = 0
)

class Brick: Resource(
    ResourceName.BRICK,
    R.drawable.brickalpha
)

enum class ResourceName {
    LUMBER, WOOL, GRAIN, BRICK, ORE, PAPER, CLOTH, COIN, GOLD
}