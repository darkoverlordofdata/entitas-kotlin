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

class RemoveOffscreenShipsSystem(pool: Pool)
      : IExecuteSystem {

    val pool = pool
    val group = pool.getGroup(Matcher.allOf(Matcher.Position))

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