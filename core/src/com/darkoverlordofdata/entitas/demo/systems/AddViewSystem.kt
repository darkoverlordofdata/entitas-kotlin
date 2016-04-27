package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.demo.*
import com.darkoverlordofdata.entitas.ecs.*

class AddViewSystem(game: GameController)
    : ISystem, ISetPool {

    val game = game

    override fun setPool(pool: Pool) {
        val group = pool.getGroup(Matcher.Resource)
        group.onEntityAdded += {e:GroupChangedArgs ->

            val entity = e.entity

            val layer = entity.layer.ordinal
            val sprite = game.textureAtlas.createSprite(entity.resource.name)
            if (entity.hasPosition) {
                val pos = entity.position
                sprite.x = pos.x
                sprite.y = pos.y
            }
            if (entity.hasScale) {
                val scale = entity.scale
                sprite.setScale(scale.x, scale.y)
            }
            val ignore = entity.addView(layer, sprite)
        }
    }

}
