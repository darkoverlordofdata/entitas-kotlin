package com.darkoverlordofdata.entitas.demo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.Json
import com.uwsoft.editor.renderer.data.CompositeItemVO
import com.uwsoft.editor.renderer.data.ProjectInfoVO
import com.uwsoft.editor.renderer.data.SceneVO

enum class Layer {
    DEFAULT,
    BACKGROUND,
    ACTORS_1,
    ACTORS_2,
    ACTORS_3,
    PARTICLES
}


/**
 * Hack Overlap2D json data to load our assets
 *
 */
object O2d {
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

    fun getLayer(name:String):Layer? {
        val layerName = getItem(name)!!.layerName
        return when (layerName) {
            "battle" -> Layer.PARTICLES
            "player" -> Layer.ACTORS_3
            "enemy1" -> Layer.ACTORS_3
            "enemy2" -> Layer.ACTORS_2
            "enemy3" -> Layer.ACTORS_1
            else -> null
        }
    }
}

