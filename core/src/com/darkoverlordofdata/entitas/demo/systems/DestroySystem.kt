package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.IExecuteSystem
import com.darkoverlordofdata.entitas.Matcher
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.demo.*

class DestroySystem(pool: Pool)
      : IExecuteSystem {

    val pool = pool
    val group = pool.getGroup(Matcher.allOf(Matcher.Destroy))

    override fun execute() { group.entities.map { pool.destroyEntity(it)} }

}