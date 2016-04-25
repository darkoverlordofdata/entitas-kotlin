package com.darkoverlordofdata.entitas
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.uwsoft.editor.renderer.SceneLoader
import com.uwsoft.editor.renderer.scene2d.CompositeActor

class ScoreUI(game:ShmupWarz, sceneLoader: SceneLoader) : Stage() {

    val game = game
    val sceneLoader = sceneLoader
    val backButtonVo = sceneLoader.loadVoFromLibrary("backButton")
    val backButtonActor = CompositeActor(backButtonVo, sceneLoader.rm)
    val pixelFactor = if (Gdx.graphics.density > 1f) 2f else 1f
    val col = (width-backButtonActor.width*pixelFactor)/2f
    val row = (pixelFactor-1f)*100f-200f*pixelFactor

    init {
        sceneLoader.loadScene("LeaderboardScene", FitViewport(320f, 480f))
        Gdx.input.inputProcessor = this

        addActor(backButtonActor)
        backButtonActor.setX(col)
        backButtonActor.setY(row+110f*2f*pixelFactor)
        backButtonActor.setScale(pixelFactor)
        backButtonActor.addListener(object: ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                game.menuGame()
            }
        })
    }
}