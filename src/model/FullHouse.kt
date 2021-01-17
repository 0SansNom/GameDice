package model

class FullHouse : Combination {
    private fun exist3SameFaces(hand: DiceHand): Boolean {
        for (faceValue in 1..6) {
            if (hand.count(faceValue) == 3) return true
        }
        return false
    }

    private fun exist2SameFaces(hand: DiceHand): Boolean {
        for (faceValue in 1..6) {
            if (hand.count(faceValue) == 2) return true
        }
        return false
    }

    override fun getScore(hand: DiceHand): Int {
        return if (exist2SameFaces(hand) && exist3SameFaces(hand)) 25 else 0
    }

    override fun description(): String {
        return "Full House"
    }
}