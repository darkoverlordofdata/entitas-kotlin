package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.demo.*
import com.darkoverlordofdata.entitas.ecs.*

class DestroySystem()
    : IExecuteSystem, ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Destroy))
    }

    override fun execute() {
        for (entity in group.getEntities()) {
            if (entity.hasView) {
                val sprite = entity.view.sprite
                if (sprite != null) sprite.texture.dispose()
                entity.view.sprite = null
            }
            pool.destroyEntity(entity)
        }
    }

}