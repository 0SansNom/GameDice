package model

class Yahtzee : Combination {
    private fun exist(hand: DiceHand): Boolean {
        for (faceValue in 1..6) {
            val occurrence = hand.count(faceValue)
            if (occurrence == 6) return true
        }
        return false
    }

    override fun getScore(hand: DiceHand): Int {
        return if (exist(hand)) 50 else 0
    }

    override fun description(): String {
        return "Yahtzee"
    }
}