package com.darkoverlordofdata.entitas.demo.systems

/**
 * PhysicsSystem
 *
 * Simplest possible motion physics
 * Calculates new position based on velocity
 *
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.IExecuteSystem
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.Matcher
import com.darkoverlordofdata.entitas.demo.*

class PhysicsSystem(pool: Pool)
      : IExecuteSystem {

    val group = pool.getGroup(Matcher.allOf(Matcher.Position, Matcher.Velocity))

    override fun execute() {
        val delta = Gdx.graphics.deltaTime
        for (entity in group.entities) {
            entity.position.x += (entity.velocity.x * delta)
            entity.position.y -= (entity.velocity.y * delta)
        }
    }
}