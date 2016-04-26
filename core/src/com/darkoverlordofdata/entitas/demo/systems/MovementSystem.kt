package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.demo.Position
import com.darkoverlordofdata.entitas.demo.Velocity
import com.darkoverlordofdata.entitas.demo.position
import com.darkoverlordofdata.entitas.demo.velocity
import com.darkoverlordofdata.entitas.ecs.ISetPool
import com.darkoverlordofdata.entitas.ecs.IExecuteSystem
import com.darkoverlordofdata.entitas.ecs.Pool
import com.darkoverlordofdata.entitas.ecs.Group
import com.darkoverlordofdata.entitas.ecs.Matcher

class MovementSystem()
    : IExecuteSystem, ISetPool {

    var group: Group? = null

    override fun setPool(pool:Pool) {
        group = pool.getGroup(Matcher.allOf(Matcher.Position, Matcher.Velocity))
    }

    override fun execute() {
        val entities = group?.getEntities()
        if (entities != null) {

            val delta = Gdx.graphics.deltaTime
            for (e in entities) {
                e.position.x += (e.velocity.x * delta)
                e.position.y -= (e.velocity.y * delta)
            }
        }
    }
}