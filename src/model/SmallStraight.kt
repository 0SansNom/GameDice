package model

import java.util.*

class SmallStraight : Combination {
    private fun combination(hand: DiceHand): ArrayList<Int> {
        val faceValues = ArrayList<Int>()
        for (i in 0..6) {
            faceValues.add(hand[i])
        }
        return faceValues
    }

    private fun IsGoodCombination(hand: DiceHand): Boolean {
        return combination(hand).containsAll(arrayOf(1, 2, 3, 4).toList()) ||
                combination(hand).containsAll(arrayListOf(2, 3, 4, 5)) ||
                combination(hand).containsAll(arrayListOf(3, 4, 5, 6))
    }

    override fun getScore(hand: DiceHand): Int {
        return if (IsGoodCombination(hand)) 30 else 0
    }

    override fun description(): String {
        return "Small Straight"
    }
}