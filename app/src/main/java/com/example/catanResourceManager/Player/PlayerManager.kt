package com.example.catanResourceManager.Player

import com.example.catanResourceManager.*

class PlayerManager(val numberManager: NumberManager = NumberManager()): RollListener {
    val hand = ResourceManager()
    val purchases = mutableListOf<Construct>()

    fun purchaseConstruct(construct: Construct) {
        if (construct.canPurchase(hand)) {
            for (resourceCost in construct.cost) {
                hand.modifySupply(resourceCost.name, -resourceCost.amount.value)
            }

            for (purchase in purchases) {
                if (purchase.name == construct.name) {
                    purchase.amountBuilt.value += 1
                    return
                }
            }
            purchases.add(construct.apply { amountBuilt.value = 1 })
        }
    }

    private fun hasBuiltConstruct(constructName: ConstructName): Boolean {
        for (purchase in purchases) {
            if (constructName == purchase.name) {
                return true
            }
        }
        return false
    }

    override fun handleNewRoll(newRoll: Roll) {
        for (resource in numberManager.getRollResult(newRoll)) {
            hand.modifySupply(resource.name, resource.amount.value)
        }
    }
}