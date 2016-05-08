package com.darkoverlordofdata.entitas.demo.systems

/**
 * ViewManagerSystem
 *
 * Modify view appearance when components are added or removed
 *
 */

import com.darkoverlordofdata.entitas.ISetPool
import com.darkoverlordofdata.entitas.ISystem
import com.darkoverlordofdata.entitas.Matcher
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.demo.*

class ViewManagerSystem()
      : ISystem,
        ISetPool {

    override fun setPool(pool: Pool) {

        /**
         * Fix up the initial sprite position
         * when a PositionComponent is added
         */
        pool.getGroup(Matcher.Position).onEntityAdded += {
            val entity = it.entity
            val sprite = entity.view.sprite
            if (sprite != null) {
                if (entity.hasPosition) {
                    val pos = entity.position
                    sprite.x = pos.x
                    sprite.y = pos.y
                }
            }
        }

        /**
         * Fix up the initial sprite color
         * when a TintComponent is added
         */
        pool.getGroup(Matcher.Tint).onEntityAdded += {
            val entity = it.entity
            val sprite = entity.view.sprite
            if (entity.hasTint) {
                val tint = entity.tint
                sprite?.setColor(tint.r, tint.g, tint.b, tint.a)
            }
        }

        /**
         * Remove the sprite color
         * when a TintComponent is removed
         */
        pool.getGroup(Matcher.Tint).onEntityRemoved += {
            val entity = it.entity
            val sprite = entity.view.sprite
            sprite?.setColor(0f, 0f, 0f, 0f)
        }

        /**
         * Fix up the initial sprite scale
         * when a ScaleComponent is added
         */
        pool.getGroup(Matcher.Scale).onEntityAdded += {
            val entity = it.entity
            val sprite = entity.view.sprite
            if (entity.hasScale) {
                val scale = entity.scale
                sprite?.setScale(scale.x, scale.y)
            }
        }
    }

}
