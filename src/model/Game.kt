package model

import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class Game {
    internal val dice: MutableList<Die>
    val rows: List<ScoreRow>
    private var nbReRolls = 0
    fun getDice(): List<Die> {
        return dice
    }

    private fun unBlockDice() {
        for (die in dice) {
            if (die.isBlocked) die.unblock()
        }
    }

    fun initialRoll() {
        unBlockDice()
        nbReRolls = 3
        reRoll()
    }

    fun choose(row: ScoreRow): Boolean {
        if (!row.isAvailable) row.record(hand)
        initialRoll()
        return !isOver
    }

    fun reRoll() {
        if (nbReRolls < 1) return
        for (die in dice) die.roll()
        nbReRolls--
    }

    val totalScore: Int
        get() {
            val score = AtomicInteger()
            for (row in rows) {
                score.addAndGet(row.score)
            }
            return score.get()
        }

    private val hand: DiceHand
        private get() = DiceHand(getDice())

    private val isOver: Boolean
        private get() {
            for (row in rows) {
                if (row.isAvailable) return false
            }
            return true
        }

    init {
        dice = ArrayList()
        for (i in 0..5) {
            dice.add(Die())
        }
        initialRoll()
        rows = java.util.List.of(
                ScoreRow(Sum()),
                ScoreRow(ThreeOfAKind()),
                ScoreRow(FourOfAKind()),
                ScoreRow(Yahtzee()),
                ScoreRow(SmallStraight()),
                ScoreRow(LargeStraight()),
                ScoreRow(new Chance()),
                new ScoreRow(new FullHouse())
        )
    }
}