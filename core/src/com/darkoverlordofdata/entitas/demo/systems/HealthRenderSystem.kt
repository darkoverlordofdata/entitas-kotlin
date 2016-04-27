package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.demo.Enemy
import com.darkoverlordofdata.entitas.demo.Health
import com.darkoverlordofdata.entitas.demo.Position
import com.darkoverlordofdata.entitas.demo.resource
import com.darkoverlordofdata.entitas.ecs.*

class HealthRenderSystem()
    : IExecuteSystem, ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Position, Matcher.Health))
        val enemies = pool.getGroup(Matcher.Enemy)
        if (enemies != null) {
            enemies.onEntityAdded += onEntityAdded
            enemies.onEntityRemoved += onEntityRemoved
        }
    }

    val onEntityAdded = {e:GroupChangedArgs ->
    }

    val onEntityRemoved = {e:GroupChangedArgs ->
    }

    override fun execute() {
    }
}