package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.demo.Bounds
import com.darkoverlordofdata.entitas.demo.Health
import com.darkoverlordofdata.entitas.demo.Position
import com.darkoverlordofdata.entitas.demo.Velocity
import com.darkoverlordofdata.entitas.ecs.*

class RemoveOffscreenShipsSystem()
    : IExecuteSystem, ISetPool {

    private lateinit var pool: Pool
    private var group: Group? = null

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Velocity, Matcher.Position, Matcher.Health, Matcher.Bounds))
    }

    override fun execute() {
    }
}