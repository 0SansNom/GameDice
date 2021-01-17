package view

import javafx.geometry.Point2D
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import sample.Controller

internal class DiceDrawer {
    fun draw(context: GraphicsContext, value: Int, isBlocked: Boolean) {
        val backgroundColor = if (isBlocked) Color.BLACK else Color.WHITE
        val foregroundColor = if (isBlocked) Color.WHITE else Color.BLACK
        context.fill = backgroundColor
        context.fillRect(0.0, 0.0, SIDE_LENGTH, SIDE_LENGTH)
        context.fill = foregroundColor
        for (point in getPoints(value)) {
            context.fillOval(
                    point.x - DIAMETER / 2,
                    point.y - DIAMETER / 2,
                    DIAMETER, DIAMETER
            )
        }
    }

    private fun getPoints(value: Int): List<Point2D> {
        when (value) {
            1 -> return ONE_POINTS
            2 -> return TWO_POINTS
            3 -> return THREE_POINTS
            4 -> return FOUR_POINTS
            5 -> return FIVE_POINTS
            6 -> return SIX_POINTS
            else -> {
            }
        }
        return arrayOf().toList();
    }

    companion object {
        const val SIDE_LENGTH = 80.0
        private const val MIDDLE = 0.5 * SIDE_LENGTH
        private const val WEST_X = 0.25 * SIDE_LENGTH
        private const val EAST_X = 0.75 * SIDE_LENGTH
        private const val NORTH_Y = 0.25 * SIDE_LENGTH
        private const val SOUTH_Y = 0.75 * SIDE_LENGTH
        private val CENTER = Point2D(MIDDLE, MIDDLE)
        private val NORTH_WEST = Point2D(WEST_X, NORTH_Y)
        private val WEST = Point2D(WEST_X, MIDDLE)
        private val SOUTH_WEST = Point2D(WEST_X, SOUTH_Y)
        private val NORTH_EAST = Point2D(EAST_X, NORTH_Y)
        private val EAST = Point2D(EAST_X, MIDDLE)
        private val SOUTH_EAST = Point2D(EAST_X, SOUTH_Y)
        private val ONE_POINTS = java.util.List.of(CENTER)
        private val TWO_POINTS = java.util.List.of(NORTH_EAST, SOUTH_WEST)
        private val THREE_POINTS = java.util.List.of(NORTH_EAST, CENTER, SOUTH_WEST)
        private val FOUR_POINTS = java.util.List.of(NORTH_EAST, NORTH_WEST, SOUTH_WEST, SOUTH_EAST)
        private val FIVE_POINTS = java.util.List.of(NORTH_EAST, NORTH_WEST, SOUTH_WEST, SOUTH_EAST, CENTER)
        private val SIX_POINTS = java.util.List.of(NORTH_EAST, NORTH_WEST, EAST, WEST, SOUTH_EAST, SOUTH_WEST)
        private const val DIAMETER = 8.0
    }
}