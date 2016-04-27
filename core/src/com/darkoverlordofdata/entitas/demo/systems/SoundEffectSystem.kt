package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.demo.SoundEffect
import com.darkoverlordofdata.entitas.ecs.*

class SoundEffectSystem()
    : IInitializeSystem, IExecuteSystem, ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.SoundEffect))
    }

    override fun initialize() {
    }

    override fun execute() {
    }

}