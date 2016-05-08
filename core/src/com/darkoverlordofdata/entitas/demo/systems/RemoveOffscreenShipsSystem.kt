package com.darkoverlordofdata.entitas.demo.systems

/**
 * RemoveOffscreenShipsSystem
 *
 * Remove enemy ships when they wander off the screen
 */

import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.entitas.demo.Position
import com.darkoverlordofdata.entitas.demo.isEnemy
import com.darkoverlordofdata.entitas.demo.position

class RemoveOffscreenShipsSystem()
      : IExecuteSystem,
        ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Position))
    }

    override fun execute() {
        for (entity in group.entities) {
            if (entity.isEnemy) {
                if (entity.position.y < 0) {
                    pool.destroyEntity(entity)
                }
            }
        }
    }
}