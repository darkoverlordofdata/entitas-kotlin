package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.darkoverlordofdata.entitas.demo.*
import com.darkoverlordofdata.entitas.ecs.*

class SpriteRenderSystem()
    : IInitializeSystem, IExecuteSystem, ISetPool {

    private var group: Group? = null
    private lateinit var pool: Pool
    private lateinit var batch: SpriteBatch

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Position, Matcher.View))
    }

    override fun initialize() {
        batch = SpriteBatch()
    }

    override fun execute() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        val entities = group?.getEntities()
        if (entities != null) {
            for (entity:Entity in entities) {
                val sprite = entity.view.sprite
                if (sprite != null) {
                    sprite.x = entity.position.x
                    sprite.y = entity.position.y
                    sprite.draw(batch)
                }
            }
        }
        batch.end()

    }
}