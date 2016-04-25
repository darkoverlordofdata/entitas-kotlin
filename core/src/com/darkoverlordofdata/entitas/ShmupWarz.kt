package com.darkoverlordofdata.entitas
import com.badlogic.gdx.Game
import com.uwsoft.editor.renderer.SceneLoader

class ShmupWarz() : Game() {

    var menuScene: MenuScene? = null
    var gameScene: GameScene? = null
    var optionScene: MenuScene? = null
    var scoreScene: MenuScene? = null

    override fun create() {
        menuGame()
//        this.setScreen(MainScreen())
    }

    fun menuGame() {
        val sceneLoader = SceneLoader()
        menuScene = MenuScene(sceneLoader, MenuUI(this, sceneLoader))
        optionScene = null
        scoreScene = null
        gameScene = null
        this.setScreen(menuScene)
    }

    fun optionsGame() {
        val sceneLoader = SceneLoader()
        menuScene = null
        optionScene = MenuScene(sceneLoader, OptionUI(this, sceneLoader))
        this.setScreen(optionScene)
        scoreScene = null
        gameScene = null
    }

    fun scoreGame() {
        val sceneLoader = SceneLoader()
        menuScene = null
        optionScene = null
        scoreScene = MenuScene(sceneLoader, ScoreUI(this, sceneLoader))
        this.setScreen(scoreScene)
        gameScene = null
    }

    fun playGame() {
        menuScene = null
        optionScene = null
        scoreScene = null
        gameScene = GameScene()
        this.setScreen(gameScene)
    }


}

