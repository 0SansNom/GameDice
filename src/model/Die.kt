package model

import java.util.*

class Die {
    private val generator: Random
    private var faceValue = 0
    var isBlocked: Boolean
        private set

    fun unblock() {
        isBlocked = false
    }

    fun block() {
        isBlocked = true
    }

    fun value(): Int {
        return faceValue
    }

    fun roll() {
        if (isBlocked) return
        faceValue = generator.nextInt(6) + 1
    }

    init {
        generator = Random()
        isBlocked = false
        roll()
    }
}