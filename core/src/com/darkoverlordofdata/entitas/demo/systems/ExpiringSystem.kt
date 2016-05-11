package com.darkoverlordofdata.entitas.demo.systems

/**
 * EExpiringSystem
 *
 * Terminate entities after specified time count
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.IExecuteSystem
import com.darkoverlordofdata.entitas.Matcher
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.demo.Expires
import com.darkoverlordofdata.entitas.demo.expires

class ExpiringSystem(pool: Pool)
      : IExecuteSystem {

    val pool = pool
    val group = pool.getGroup(Matcher.allOf(Matcher.Expires))

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