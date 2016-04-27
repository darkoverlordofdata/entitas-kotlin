package com.darkoverlordofdata.entitas.demo

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.darkoverlordofdata.entitas.ecs.Systems
import com.darkoverlordofdata.entitas.demo.systems.*
import com.darkoverlordofdata.entitas.demo.systems.SpriteRenderSystem
import com.darkoverlordofdata.entitas.ecs.Pool

class GameController : ApplicationAdapter() {

    val rnd = java.util.Random()
    val width:Int by lazy { Gdx.graphics.width }
    val height:Int by lazy { Gdx.graphics.height }
    val pixelFactor:Int by lazy { if (Gdx.graphics.density > 1f) 2 else 1 }


    lateinit var pool: Pool
    lateinit var systems: Systems
    lateinit var textureAtlas: TextureAtlas
    lateinit var spriteRenderSystem: SpriteRenderSystem

    override fun create() {

        textureAtlas = TextureAtlas("level1.atlas")

        pool = Pool(Component.TotalComponents.ordinal)
        systems = createSystems(pool)
        systems.initialize()
        pool.createPlayer(width.toFloat(), height.toFloat())
    }

    fun createSystems(pool:Pool):Systems {
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

    override fun resize(width: Int, height: Int) {
        spriteRenderSystem.resize(width, height)
    }

    override fun render() {
        systems.execute()
    }
}
