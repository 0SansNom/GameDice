package model

class ScoreRow(private val combination: Combination) {
    var score = 0
        private set
    var isAvailable = false
        private set

    fun reset() {
        score = 0
        isAvailable = false
    }

    fun description(): String? {
        return combination.description()
    }

    fun record(hand: DiceHand) {
        if (!isAvailable) score = combination.getScore(hand)
    }

}