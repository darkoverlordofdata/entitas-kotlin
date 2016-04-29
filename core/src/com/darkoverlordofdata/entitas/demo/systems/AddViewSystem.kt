package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.darkoverlordofdata.entitas.GroupChangedArgs
import com.darkoverlordofdata.entitas.ISetPool
import com.darkoverlordofdata.entitas.ISystem
import com.darkoverlordofdata.entitas.Matcher
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.demo.*

class AddViewSystem(game: GameScene)
      : ISystem,
        ISetPool {

    val game = game


    override fun setPool(pool: Pool) {
        val group = pool.getGroup(Matcher.View)
        group.onEntityAdded += {e: GroupChangedArgs ->

            val entity = e.entity

            val layer = entity.layer.ordinal
            val sprite = entity.view.sprite
            if (sprite != null) {
                if (entity.hasPosition) {
                    val pos = entity.position
                    sprite.x = pos.x
                    sprite.y = pos.y
                }
                if (entity.hasScale) {
                    val scale = entity.scale
                    sprite.setScale(scale.x, scale.y)
                }
            }
        }
    }

}
