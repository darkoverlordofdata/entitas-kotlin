package com.darkoverlordofdata.entitas.demo.systems

/**
 * HealthRenderSystem
 *
 * display the remaining health for enemy ships
 *
 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.entitas.demo.*


class HealthRenderSystem(game: GameScene, pool: Pool)
      : IExecuteSystem {

    val pool = pool
    val group = pool.getGroup(Matcher.allOf(Matcher.Position, Matcher.Health))
    val camera = game.camera
    val batch = SpriteBatch()
    val font = CreateFont("fonts/normal")

    override fun execute() {
        // This overlays over the game
        // Draw batch; don't clear screen first.
        batch.projectionMatrix = camera.combined
        batch.begin();
        for (entity in group.entities) {
            val health = entity.health
            val position = entity.position

            val percentage = MathUtils.round(health.currentHealth/health.maximumHealth*100f).toInt()
            font.draw(batch, "$percentage%", position.x, position.y)
        }
        batch.end();

    }
}