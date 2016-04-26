package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.demo.Destroy
import com.darkoverlordofdata.entitas.ecs.*

class DestroySystem()
    : IExecuteSystem, ISetPool {

    private lateinit var pool: Pool
    private var group: Group? = null

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Destroy))
    }

    override fun execute() {
    }

}