package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.ecs.ISetPool
import com.darkoverlordofdata.entitas.ecs.IExecuteSystem
import com.darkoverlordofdata.entitas.ecs.IInitializeSystem
import com.darkoverlordofdata.entitas.ecs.IReactiveExecuteSystem
import com.darkoverlordofdata.entitas.ecs.IMultiReactiveSystem
import com.darkoverlordofdata.entitas.ecs.IReactiveSystem
import com.darkoverlordofdata.entitas.ecs.IEnsureComponents
import com.darkoverlordofdata.entitas.ecs.IExcludeComponents
import com.darkoverlordofdata.entitas.ecs.IClearReactiveSystem
import com.darkoverlordofdata.entitas.ecs.Pool

class EntitySpawningTimerSystem()
    : IInitializeSystem, IExecuteSystem, ISetPool {

    private lateinit var pool: Pool

    override fun setPool(pool: Pool) {
        this.pool = pool
    }

    override fun initialize() {
    }

    override fun execute() {
    }

}