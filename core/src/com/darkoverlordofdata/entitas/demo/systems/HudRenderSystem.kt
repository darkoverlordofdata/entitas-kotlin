package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.ISetPool
import com.darkoverlordofdata.entitas.IExecuteSystem
import com.darkoverlordofdata.entitas.IInitializeSystem
import com.darkoverlordofdata.entitas.IReactiveExecuteSystem
import com.darkoverlordofdata.entitas.IMultiReactiveSystem
import com.darkoverlordofdata.entitas.IReactiveSystem
import com.darkoverlordofdata.entitas.IEnsureComponents
import com.darkoverlordofdata.entitas.IExcludeComponents
import com.darkoverlordofdata.entitas.IClearReactiveSystem
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.demo.GameController

class HudRenderSystem(game: GameController)
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