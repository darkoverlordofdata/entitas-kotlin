package com.darkoverlordofdata.entitas.demo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.darkoverlordofdata.entitas.Systems
import com.darkoverlordofdata.entitas.demo.systems.*
import com.darkoverlordofdata.entitas.demo.systems.SpriteRenderSystem
import com.darkoverlordofdata.entitas.Pool

class GameController : Screen {

    val rnd = java.util.Random()
    val width:Int by lazy { Gdx.graphics.width }
    val height:Int by lazy { Gdx.graphics.height }
    val pixelFactor:Int by lazy { if (Gdx.graphics.density > 1f) 2 else 1 }


    lateinit var pool: Pool
    lateinit var systems: Systems
    lateinit var textureAtlas: TextureAtlas
    lateinit var spriteRenderSystem: SpriteRenderSystem

    //override fun create() {
    init {

        textureAtlas = TextureAtlas("level1.atlas")

        pool = Pool(Component.TotalComponents.ordinal)
        systems = createSystems(pool)
        systems.initialize()
        pool.createPlayer(width.toFloat(), height.toFloat())
    }

    fun createSystems(pool: Pool): Systems {
        spriteRenderSystem = SpriteRenderSystem(this)
        return Systems()
            .add(pool.createSystem(MovementSystem()))
            .add(pool.createSystem(PlayerInputSystem(this)))
            .add(pool.createSystem(SoundEffectSystem()))
            .add(pool.createSystem(CollisionSystem()))
            .add(pool.createSystem(ExpiringSystem()))
            .add(pool.createSystem(EntitySpawningTimerSystem(this)))
            .add(pool.createSystem(ScaleAnimationSystem()))
            .add(pool.createSystem(RemoveOffscreenShipsSystem()))
            .add(pool.createSystem(spriteRenderSystem))
            .add(pool.createSystem(AddViewSystem(this)))
            .add(pool.createSystem(HealthRenderSystem()))
            .add(pool.createSystem(HudRenderSystem()))
            .add(pool.createSystem(DestroySystem()))
    }

    override fun render(delta: Float) {
        systems.execute()
    }

    override fun dispose() {}
    override fun show() {}
    override fun hide() {}
    override fun pause() {}
    override fun resize(width: Int, height:Int) {
        spriteRenderSystem.resize(width, height)
    }
    override fun resume() {}


}
