package view

import model.Game

internal class ViewModel(private val controller: Controller) {
    private val game: Game = Game()
    fun click(dieIndex: Int) {
        val die = game.dice[dieIndex]
        if (die.isBlocked) {
            die.unblock()
        } else {
            die.block()
        }
        controller.redrawDices()
    }

    fun choose(row: Int) {
        val scoreRow = game.rows[row]
        val isOver = game.choose(scoreRow)
        controller.update(row)
        if (isOver) {
            controller.stopGame()
        } else {
            controller.redrawDices()
        }
    }

    fun reRoll() {
        game.reRoll()
        controller.redrawDices()
    }

    fun restart() {
        for (row in game.rows.indices) {
            game.rows[row].reset()
            controller.update(row)
        }
        game.initialRoll()
        controller.redrawDices()
    }

    val totalScore: Int
        get() = game.totalScore

    fun nbDice(): Int {
        return game.dice.size
    }

    fun nbRows(): Int {
        return game.rows.size
    }

    fun getRowDescription(row: Int): String {
        val scoreRow = game.rows[row]
        return scoreRow.description().toString()
    }

    fun getDieValue(die: Int): Int {
        return game.dice[die].value()
    }

    fun isDieBlocked(die: Int): Boolean {
        return game.dice[die].isBlocked
    }

    fun isChoosableRow(row: Int): Boolean {
        return game.rows[row].isAvailable
    }

    fun getScoreDescription(row: Int): String {
        val scoreRow = game.rows[row]
        return if (scoreRow.isAvailable) "???" else scoreRow.score.toString()
    }

}