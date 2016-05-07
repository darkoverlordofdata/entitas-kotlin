package com.darkoverlordofdata.entitas.demo.systems

/**
 * EExpiringSystem
 *
 * Terminate entities after specified time count
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.Group
import com.darkoverlordofdata.entitas.IExecuteSystem
import com.darkoverlordofdata.entitas.ISetPool
import com.darkoverlordofdata.entitas.Matcher
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.demo.Expires
import com.darkoverlordofdata.entitas.demo.expires

class ExpiringSystem()
      : IExecuteSystem,
        ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Expires))
    }

    override fun execute() {
        val delta = Gdx.graphics.deltaTime
        for (entity in group.entities) {
            entity.expires.delay -= delta
            if (entity.expires.delay < 0) {
                pool.destroyEntity(entity)
            }
        }
    }

}