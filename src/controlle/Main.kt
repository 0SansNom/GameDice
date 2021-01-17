package view

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.jvm.JvmStatic
import kotlin.reflect.KClass

class Main : Application() {
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("view.fxml"))
        primaryStage.title = "Amuhtzee"
        primaryStage.scene = Scene(root, Controller.WIDTH, Controller.HEIGHT)
        primaryStage.show()
    }

    annotation class Throws(val value: KClass<Exception>)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(*args)
        }
    }
}