package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.Group
import com.darkoverlordofdata.entitas.IExecuteSystem
import com.darkoverlordofdata.entitas.ISetPool
import com.darkoverlordofdata.entitas.Matcher
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.demo.*

class DestroySystem()
    : IExecuteSystem, ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Destroy))
    }

    override fun execute() {
        for (entity in group.entities) {
            pool.destroyEntity(entity)
        }
    }

}