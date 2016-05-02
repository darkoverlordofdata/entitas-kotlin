package com.darkoverlordofdata.entitas.demo

import com.badlogic.gdx.Game
import com.uwsoft.editor.renderer.SceneLoader
import com.darkoverlordofdata.entitas.Version
class ShmupWarz() : Game() {

    var menuScene: MenuScene? = null
    var gameScene: GameScene? = null
    var optionScene: MenuScene? = null
    var scoreScene: MenuScene? = null


    override fun create() {
        println("Using entitas v$Version (${Version.description})")
        menuGame()
    }

    fun menuGame() {
        val sceneLoader = SceneLoader()
        menuScene = MenuScene(sceneLoader, MenuUI(this, sceneLoader))
        optionScene = null
        scoreScene = null
        gameScene = null
        setScreen(menuScene)
    }

    fun optionsGame() {
        val sceneLoader = SceneLoader()
        menuScene = null
        optionScene = MenuScene(sceneLoader, OptionUI(this, sceneLoader))
        setScreen(optionScene)
        scoreScene = null
        gameScene = null
    }

    fun scoreGame() {
        val sceneLoader = SceneLoader()
        menuScene = null
        optionScene = null
        scoreScene = MenuScene(sceneLoader, ScoreUI(this, sceneLoader))
        setScreen(scoreScene)
        gameScene = null
    }

    fun playGame() {
        menuScene = null
        optionScene = null
        scoreScene = null
        gameScene = GameScene()
        setScreen(gameScene)
    }

}

