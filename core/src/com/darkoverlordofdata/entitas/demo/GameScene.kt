package com.darkoverlordofdata.entitas.demo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.Systems
import com.darkoverlordofdata.entitas.demo.systems.*

class GameScene : Screen {

    val width:Int by lazy { Gdx.graphics.width }
    val height:Int by lazy { Gdx.graphics.height }
    val pixelFactor:Int by lazy { if (Gdx.graphics.density > 1f) 2 else 1 }


    lateinit var camera: OrthographicCamera
    lateinit var pool: Pool
    lateinit var systems: Systems
    lateinit var spriteRenderSystem: SpriteRenderSystem

    init {
        camera = OrthographicCamera(width.toFloat()/pixelFactor, height.toFloat()/pixelFactor)
        pool = Pool(Component.TotalComponents.ordinal, 0, {Component.values()[it].name})
        spriteRenderSystem = SpriteRenderSystem(this)
        systems = createSystems(pool, spriteRenderSystem)
        systems.initialize()
    }

    fun createSystems(pool: Pool, spriteRenderSystem: SpriteRenderSystem): Systems {
        return Systems()
            .add(pool.createSystem(PhysicsSystem()))
            .add(pool.createSystem(ViewManagerSystem()))
            .add(pool.createSystem(PlayerInputSystem(this)))
            .add(pool.createSystem(SoundEffectSystem()))
            .add(pool.createSystem(CollisionSystem()))
            .add(pool.createSystem(ExpiringSystem()))
            .add(pool.createSystem(EntitySpawningTimerSystem(this)))
            .add(pool.createSystem(ScaleTweenSystem()))
            .add(pool.createSystem(RemoveOffscreenShipsSystem()))
            .add(pool.createSystem(spriteRenderSystem))
            .add(pool.createSystem(HealthRenderSystem(this)))
            .add(pool.createSystem(ScoreRenderSystem(this)))
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
