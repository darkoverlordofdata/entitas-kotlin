package com.darkoverlordofdata.entitas.demo
import com.badlogic.gdx.Game
import com.darkoverlordofdata.entitas.demo.GameController
import com.uwsoft.editor.renderer.SceneLoader

class ShmupWarz() : com.badlogic.gdx.Game() {

    var menuScene: com.darkoverlordofdata.entitas.demo.MenuScene? = null
    //var gameScene: GameScene? = null
    var gameScene: com.darkoverlordofdata.entitas.demo.GameController? = null
    var optionScene: com.darkoverlordofdata.entitas.demo.MenuScene? = null
    var scoreScene: com.darkoverlordofdata.entitas.demo.MenuScene? = null

    override fun create() {
        menuGame()
//        this.setScreen(MainScreen())
    }

    fun menuGame() {
        val sceneLoader = com.uwsoft.editor.renderer.SceneLoader()
        menuScene = com.darkoverlordofdata.entitas.demo.MenuScene(sceneLoader, com.darkoverlordofdata.entitas.demo.MenuUI(this, sceneLoader))
        optionScene = null
        scoreScene = null
        gameScene = null
        this.setScreen(menuScene)
    }

    fun optionsGame() {
        val sceneLoader = com.uwsoft.editor.renderer.SceneLoader()
        menuScene = null
        optionScene = com.darkoverlordofdata.entitas.demo.MenuScene(sceneLoader, com.darkoverlordofdata.entitas.demo.OptionUI(this, sceneLoader))
        this.setScreen(optionScene)
        scoreScene = null
        gameScene = null
    }

    fun scoreGame() {
        val sceneLoader = com.uwsoft.editor.renderer.SceneLoader()
        menuScene = null
        optionScene = null
        scoreScene = com.darkoverlordofdata.entitas.demo.MenuScene(sceneLoader, com.darkoverlordofdata.entitas.demo.ScoreUI(this, sceneLoader))
        this.setScreen(scoreScene)
        gameScene = null
    }

    fun playGame() {
        menuScene = null
        optionScene = null
        scoreScene = null
        gameScene = com.darkoverlordofdata.entitas.demo.GameController()
        this.setScreen(gameScene)
    }


}

