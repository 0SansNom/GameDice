package model

import java.util.EnumSet.of

class LargeStraight : Combination {
    private fun combination(hand: DiceHand): ArrayList<Int> {
        val faceValues = ArrayList<Int>()
        for (i in 0..6) {
            faceValues.add(hand[i])
        }
        return faceValues
    }

    private fun IsGoodCombination(hand: DiceHand): Boolean {
        return combination(hand).containsAll(arrayListOf<Int>(1, 2, 3, 4, 5)) ||
                combination(hand).containsAll(arrayListOf<Int>( 2, 3, 4, 5, 6))
    }

    override fun getScore(hand: DiceHand): Int {
        return if (IsGoodCombination(hand)) 40 else 0
    }

    override fun description(): String {
        return "Large Straight"
    }
}