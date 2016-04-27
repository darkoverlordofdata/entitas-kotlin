package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.demo.Expires
import com.darkoverlordofdata.entitas.demo.expires
import com.darkoverlordofdata.entitas.ecs.*

class ExpiringSystem()
    : IExecuteSystem, ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Expires))
    }

    override fun execute() {
        val delta = Gdx.graphics.deltaTime
        for (entity in group.getEntities()) {
            val value = entity.expires.delay - delta
            entity.expires.delay = value
            if (value < 0) {
                pool.destroyEntity(entity)
            }
        }
    }

}