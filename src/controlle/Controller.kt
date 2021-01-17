package view

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import java.net.URL
import java.util.*

class Controller : Initializable {
    private var viewModel: ViewModel? = null

    @FXML
    private val diceBox: HBox? = null

    @FXML
    private val scoreBox: HBox? = null

    @FXML
    private val scoreGrid: GridPane? = null

    @FXML
    private val rerollButton: Button? = null

    @FXML
    private val scoreLabel: Label? = null
    private val diceCanvas: MutableMap<Int, Canvas> = HashMap()
    private val rowButtons: MutableMap<Int, Button> = HashMap()
    private val rowLabels: MutableMap<Int, Label> = HashMap()
    override fun initialize(url: URL, resourceBundle: ResourceBundle) {
        viewModel = ViewModel(this)
        for (die in 0 until viewModel!!.nbDice()) {
            val canvas = Canvas(DiceDrawer.SIDE_LENGTH, DiceDrawer.SIDE_LENGTH)
            diceCanvas[die] = canvas
            diceBox!!.children.add(canvas)
            canvas.onMouseReleased = EventHandler { event: MouseEvent? -> viewModel!!.click(die) }
        }
        redrawDices()
        scoreGrid!!.prefWidth(WIDTH)
        val colConstr = ColumnConstraints()
        colConstr.hgrow = Priority.ALWAYS
        scoreGrid.columnConstraints.add(ColumnConstraints())
        scoreGrid.columnConstraints.add(colConstr)
        scoreGrid.columnConstraints.add(ColumnConstraints())
        var lineCount = 0
        for (row in 0 until viewModel!!.nbRows()) {
            val button = Button("Choisir")
            button.onMouseClicked = EventHandler { event: MouseEvent? -> clicked(button, row) }
            val name = Label(viewModel!!.getRowDescription(row))
            val score = Label(viewModel!!.getScoreDescription(row))
            rowButtons[row] = button
            rowLabels[row] = score
            scoreGrid.add(button, 0, lineCount)
            scoreGrid.add(name, 1, lineCount)
            scoreGrid.add(score, 2, lineCount)
            lineCount++
        }
        rerollButton!!.onMouseClicked = EventHandler { event: MouseEvent? -> viewModel!!.reRoll() }
        scoreBox!!.prefWidth(WIDTH)
    }

    private fun clicked(button: Button, row: Int) {
        button.disarm()
        button.isVisible = false
        viewModel!!.choose(row)
    }

    fun redrawDices() {
        for (die in 0 until viewModel!!.nbDice()) {
            val canvas = diceCanvas[die]
            diceDrawer.draw(
                    canvas!!.graphicsContext2D,
                    viewModel!!.getDieValue(die),
                    viewModel!!.isDieBlocked(die))
        }
    }

    fun update(row: Int) {
        val score = rowLabels[row]
        score!!.text = viewModel!!.getScoreDescription(row)
        val button = rowButtons[row]
        button!!.isVisible = viewModel!!.isChoosableRow(row)
        redrawScore()
    }

    private fun redrawScore() {
        scoreLabel!!.text = Integer.toString(viewModel!!.totalScore)
    }

    fun stopGame() {
        rerollButton!!.text = "Recommencer"
        rerollButton.onMouseClicked = EventHandler { mouseEvent: MouseEvent -> restart(mouseEvent) }
    }

    private fun restart(mouseEvent: MouseEvent) {
        rerollButton!!.text = "Relancer !"
        viewModel!!.restart()
        rerollButton.onMouseClicked = EventHandler { event: MouseEvent? -> viewModel!!.reRoll() }
    }

    companion object {
        const val WIDTH = 450.0
        const val HEIGHT = 650.0
        private val diceDrawer = DiceDrawer()
    }
}