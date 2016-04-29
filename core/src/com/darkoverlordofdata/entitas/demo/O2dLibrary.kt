package com.darkoverlordofdata.entitas.demo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.Json
import com.uwsoft.editor.renderer.data.CompositeItemVO
import com.uwsoft.editor.renderer.data.ProjectInfoVO
import com.uwsoft.editor.renderer.data.SceneVO

/**
 * Hack Overlap2D json data to load our assets
 *
 */
object O2dLibrary {
    val project: ProjectInfoVO by lazy {
        val file = Gdx.files.internal("project.dt")
        val json = Json()
        json.fromJson(ProjectInfoVO::class.java, file.readString())
    }
    val scene: SceneVO by lazy {
        val file = Gdx.files.internal("scenes/MainScene.dt")
        val json = Json()
        json.fromJson(SceneVO::class.java, file.readString())
    }
    val sprites: TextureAtlas by lazy {
        val packFile = Gdx.files.internal("orig/pack.atlas")
        TextureAtlas(packFile)
    }
    fun getResource(name:String):String {
        return getItem(name)!!.composite.sImages[0].imageName
    }

    fun getItem(name:String):CompositeItemVO? {
        return project.libraryItems[name]
    }

    fun getLayer(name:String):Layer {
        val layerName = getItem(name)!!.layerName
        return when (layerName) {
            "battle" -> Layer.BATTLE
            "player" -> Layer.PLAYER
            "enemy1" -> Layer.ENEMY1
            "enemy2" -> Layer.ENEMY2
            "enemy3" -> Layer.ENEMY3
            else -> Layer.DEFAULT
        }
    }
}

