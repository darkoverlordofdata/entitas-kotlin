package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.entitas.demo.*

class ScoreRenderSystem(game: GameScene, pool: Pool)
      : IExecuteSystem {

    val pool = pool
    val group = pool.getGroup(Matcher.allOf(Matcher.Player, Matcher.Score))
    val width = game.width
    val height = game.height
    val pixelFactor = game.pixelFactor
    val camera = game.camera
    val batch = SpriteBatch()
    val font = CreateFont("fonts/hud")

    /**
     * IExecuteSystem::execute
     */
    override fun execute() {
        batch.projectionMatrix = camera.combined
        batch.begin();
        val player = group.singleEntity
        if (player != null)
            font.draw(batch, "${player.score.value}", width/(2f*pixelFactor), (height/pixelFactor)-10f)

        batch.end();
    }
}