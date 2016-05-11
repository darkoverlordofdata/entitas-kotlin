package com.darkoverlordofdata.entitas.demo.systems

/**
 * ScaleTweenSystem
 *
 * Tween the scale - used for explosions
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.IExecuteSystem
import com.darkoverlordofdata.entitas.Matcher
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.demo.*

class ScaleTweenSystem(pool: Pool)
      : IExecuteSystem {

    val group = pool.getGroup(Matcher.allOf(Matcher.Scale, Matcher.Tween))

    override fun execute() {
        val delta = Gdx.graphics.deltaTime

        for (entity in group.entities) {
            val tween = entity.tween
            val scale = entity.scale
            var x = scale.x
            var y = scale.y
            var active = tween.active

            x += (tween.speed * delta)
            y += (tween.speed * delta)
            if (x > tween.max) {
                x = tween.max
                y = tween.max
                active = false
            } else if (x < tween.min) {
                x = tween.min
                y = tween.min
                active = false
            }
            scale.x = x
            scale.y = y
            tween.active = active

        }
    }
}