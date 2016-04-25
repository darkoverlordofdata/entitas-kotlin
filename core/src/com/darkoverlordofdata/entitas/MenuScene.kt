package com.darkoverlordofdata.entitas
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.uwsoft.editor.renderer.SceneLoader

class MenuScene(sceneLoader: SceneLoader, ui: Stage) : Screen {

    val sceneLoader = sceneLoader
    val ui = ui

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        sceneLoader.engine.update(delta)
        ui.act()
        ui.draw()

    }
    override fun dispose() {}
    override fun show() {}
    override fun hide() {}
    override fun pause() {}
    override fun resize(width: Int, height:Int) {}
    override fun resume() {}

}