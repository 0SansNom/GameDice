package model

class FourOfAKind : Combination {
    private fun exist3SameValue(hand: DiceHand): Boolean {
        for (faceValue in 1..6) {
            if (hand.count(faceValue) == 3) return true
        }
        return false
    }

    private fun exist2SameValue(hand: DiceHand): Boolean {
        for (faceValue in 1..6) {
            if (hand.count(faceValue) == 2) return true
        }
        return false
    }

    override fun getScore(hand: DiceHand): Int {
        return if (exist3SameValue(hand)) hand.sum() else 0
    }

    override fun description(): String {
        return "Four-of-kind"
    }
}