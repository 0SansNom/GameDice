package model

class Chance : Combination {
    override fun getScore(hand: DiceHand): Int {
        return hand.sum()
    }

    override fun description(): String {
        return "Chance"
    }
}