package com.darkoverlordofdata.entitas
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.uwsoft.editor.renderer.SceneLoader
import com.uwsoft.editor.renderer.scene2d.CompositeActor

class MenuUI(game:ShmupWarz, sceneLoader: SceneLoader) : Stage() {

    val playButtonVo = sceneLoader.loadVoFromLibrary("playButton")
    val playButtonActor = CompositeActor(playButtonVo, sceneLoader.rm)
    val scoreButtonVo  = sceneLoader.loadVoFromLibrary("scoreButton")
    val scoreButtonActor = CompositeActor(scoreButtonVo, sceneLoader.rm)
    val optionButtonVo  = sceneLoader.loadVoFromLibrary("optionButton")
    val optionButtonActor = CompositeActor(optionButtonVo, sceneLoader.rm)
    val pixelFactor = if (Gdx.graphics.density > 1f) 2f else 1f
    val col = (width-playButtonActor.width*pixelFactor)/2f
    val row = (pixelFactor-1f)*100f-200f*pixelFactor

    init {
        sceneLoader.loadScene("MenuScene", FitViewport(320f, 480f))
        Gdx.input.inputProcessor = this

        addActor(playButtonActor)
        playButtonActor.x = col
        playButtonActor.y = row+220f*2f*pixelFactor
        playButtonActor.setScale(pixelFactor)
        playButtonActor.addListener(object: ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                game.playGame()
            }
        })

        addActor(scoreButtonActor)
        scoreButtonActor.x = col
        scoreButtonActor.y = row+180f*2f*pixelFactor
        scoreButtonActor.setScale(pixelFactor)
        scoreButtonActor.addListener(object: ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                game.scoreGame()
            }
        })

        addActor(optionButtonActor)
        optionButtonActor.x = col
        optionButtonActor.y = row+140f*2f*pixelFactor
        optionButtonActor.setScale(pixelFactor)
        optionButtonActor.addListener(object: ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                game.optionsGame()
            }
        })

    }

}