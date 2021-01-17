package model

interface Combination {
    fun getScore(hand: DiceHand): Int
    fun description(): String
}