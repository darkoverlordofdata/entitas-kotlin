package com.darkoverlordofdata.entitas.demo.systems

/**
 * HealthRenderSystem
 *
 * display the remaining health for enemy ships
 *
 */

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.entitas.demo.*

class HealthRenderSystem(game: GameScene)
      : IInitializeSystem,
        IExecuteSystem,
        ISetPool {

    val game = game
    private lateinit var pool: Pool
    private lateinit var group: Group
    private lateinit var batch: SpriteBatch
    private lateinit var fontTexture: Texture
    private lateinit var fontRegion: TextureRegion
    private lateinit var font: BitmapFont
    private lateinit var camera: OrthographicCamera

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Position, Matcher.Health))
    }

    override fun initialize() {
        camera = game.camera
        batch = SpriteBatch()
        fontTexture = Texture(Gdx.files.internal("fonts/normal_0.png"))
        fontTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.MipMapLinearLinear)
        fontRegion = TextureRegion(fontTexture)
        font = BitmapFont(Gdx.files.internal("fonts/normal.fnt"), fontRegion, false)
        font.setUseIntegerPositions(false)
    }

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