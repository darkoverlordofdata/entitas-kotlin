package com.darkoverlordofdata.entitas.demo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.Json
import com.uwsoft.editor.renderer.data.ProjectInfoVO
import com.uwsoft.editor.renderer.data.SceneVO

/**
 * Hack Overlap2D json data to load our assets
 *
 * todo:
 *  level
 *  position (x,y)
 *  bounds (width/2)
 *  tint (r-g-b-a)
 *  scaleX, scaleY
 *  use custom data to store health points, expires
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
        return project.libraryItems[name]!!.composite.sImages[0].imageName
    }

}


