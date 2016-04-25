package com.darkoverlordofdata.entitas
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.uwsoft.editor.renderer.SceneLoader
import com.uwsoft.editor.renderer.components.label.LabelComponent
import com.uwsoft.editor.renderer.scene2d.CompositeActor
import com.uwsoft.editor.renderer.utils.ItemWrapper

class GameUI(game:ShmupWarz, sceneLoader: SceneLoader) : Stage() {
    val game = game
    val sceneLoader = sceneLoader

    val hudVo = sceneLoader.loadVoFromLibrary("hud")
    val hudActor = CompositeActor(hudVo, sceneLoader.rm)
    val pixelFactor = if (Gdx.graphics.density > 1f) 2f else 1f
    val root = ItemWrapper(sceneLoader.root)
    val score = root.getChild("score").getComponent<LabelComponent>(LabelComponent::class.java)
    val power = hudActor.getItem("power")
    val life1 = hudActor.getItem("life1")
    val life2 = hudActor.getItem("life2")
    val life3 = hudActor.getItem("life3")


    init {
        sceneLoader.loadScene("MainScene", FitViewport(320f * .8f, 480f * .8f))
        setScore(0)
        addActor(hudActor)
        hudActor.x = 10f
        hudActor.y = (height-hudActor.height/2)-10*pixelFactor
        hudActor.setScale(pixelFactor*.5f)
    }

    fun gameOver(score:Int) {
        println("GameOver: $score")
        game.menuGame()
    }

    fun setScore(points:Int) {
        score.setText("$points")
    }

    fun setPower(level:Float) {
        power.setScaleY(level)
    }

    fun setLives(lives:Int) {
        when (lives) {
            0 -> {
                life1.setVisible(false)
                life2.setVisible(false)
                life3.setVisible(false)
            }
            1 -> {
                life1.setVisible(true)
                life2.setVisible(false)
                life3.setVisible(false)
            }
            2 -> {
                life1.setVisible(true)
                life2.setVisible(true)
                life3.setVisible(false)
            }
            3 -> {
                life1.setVisible(true)
                life2.setVisible(true)
                life3.setVisible(true)
            }
            else -> {
                throw Exception("GameUI.setLives - Invalid param: $lives")
            }
        }
    }
}