package model

class ThreeOfAKind : Combination {
    private fun exist(hand: DiceHand): Boolean {
        for (faceValue in 1..6) if (hand.count(faceValue) >= 3) return true
        return false
    }

    override fun getScore(hand: DiceHand): Int {
        return if (exist(hand)) hand.sum() else 0
    }

    override fun description(): String {
        return "Brelan"
    }
}