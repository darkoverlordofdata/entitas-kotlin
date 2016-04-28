package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.entitas.demo.*

class ScoreRenderSystem(game: GameScene)
      : IInitializeSystem,
        IExecuteSystem,
        ISetPool {

    val game = game
    val width = game.width
    val height = game.height
    val pixelFactor = game.pixelFactor

    private lateinit var pool: Pool
    private lateinit var group: Group
    private lateinit var batch: SpriteBatch
    private lateinit var fontTexture: Texture
    private lateinit var fontRegion: TextureRegion
    private lateinit var font: BitmapFont
    private lateinit var camera: OrthographicCamera

    /**
     * ISetPool::setPool
     */
    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Player, Matcher.Score))
    }

    /**
     * IInitializeSystem::initialize
     */
    override fun initialize() {
        camera = game.camera
        batch = SpriteBatch()
        fontTexture = Texture(Gdx.files.internal("fonts/hud_0.png"))
        fontTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.MipMapLinearLinear)
        fontRegion = TextureRegion(fontTexture)
        font = BitmapFont(Gdx.files.internal("fonts/hud.fnt"), fontRegion, false)
        font.setUseIntegerPositions(false)
    }

    /**
     * IExecuteSystem::execute
     */
    override fun execute() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        val player = group.singleEntity
        if (player != null) {
            font.draw(batch, "${player.score.value}", width/(2f*pixelFactor), (height/pixelFactor)-10f)
        }
        batch.end();
    }
}