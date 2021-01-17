package model

class Sum : Combination {
    override fun getScore(hand: DiceHand): Int {
        return hand.sum()
    }

    override fun description(): String {
        return "Sum"
    }
}