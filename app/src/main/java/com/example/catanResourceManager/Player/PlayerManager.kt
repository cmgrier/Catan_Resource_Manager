package com.example.catanResourceManager.Player

import com.example.catanResourceManager.NumberManager
import com.example.catanResourceManager.ResourceManager
import com.example.catanResourceManager.Roll
import com.example.catanResourceManager.RollListener

class PlayerManager(val numberManager: NumberManager = NumberManager()): RollListener {
    val hand = ResourceManager()

    override fun handleNewRoll(newRoll: Roll) {
        for (resource in numberManager.getRollResult(newRoll)) {
            hand.modifySupply(resource.name, resource.amount.value)
        }
    }
}