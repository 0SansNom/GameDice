package model

import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class DiceHand(dice: List<Die>) {
    private val faces: MutableList<Int>
    operator fun get(die: Int): Int {
        return faces[die]
    }

    fun sum(): Int {
        val sum = AtomicInteger()
        for (face in faces) sum.addAndGet(face)
        return sum.get()
    }

    fun count(faceValue: Int): Int {
        val counter = AtomicInteger()
        for (face in faces) if (faceValue == face) counter.getAndIncrement()
        return counter.get()
    }

    init {
        faces = ArrayList()
        for (die in dice) faces.add(die.value())
    }
}