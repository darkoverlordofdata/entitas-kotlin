package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.demo.*
import com.darkoverlordofdata.entitas.ecs.*

class RemoveOffscreenShipsSystem()
    : IExecuteSystem, ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Position))
    }

    override fun execute() {
        for (entity in group.getEntities()) {
            if (entity.isEnemy) {
                if (entity.position.y < 0) {
                    pool.destroyEntity(entity)
                }
            }
        }
    }
}