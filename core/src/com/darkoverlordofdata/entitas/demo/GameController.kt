package com.darkoverlordofdata.entitas.demo

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.darkoverlordofdata.entitas.ecs.Systems
import com.darkoverlordofdata.entitas.demo.systems.*
import com.darkoverlordofdata.entitas.ecs.Pool

class GameController : ApplicationAdapter() {

    val rnd = java.util.Random()
    val width = 320
    val height = 480

    lateinit var pool:Pool
    lateinit var systems: Systems
    lateinit var textureAtlas : TextureAtlas

    override fun create() {

        textureAtlas = TextureAtlas("level1.atlas")

        pool = Pool(Component.TotalComponents.ordinal)
        systems = createSystems(pool)
        systems.initialize()
    }

    fun createSystems(pool:Pool):Systems {
        return Systems()
            .add(pool.createSystem(MovementSystem()))
            .add(pool.createSystem(PlayerInputSystem()))
            .add(pool.createSystem(SoundEffectSystem()))
            .add(pool.createSystem(CollisionSystem()))
            .add(pool.createSystem(ExpiringSystem()))
            .add(pool.createSystem(EntitySpawningTimerSystem(this)))
            .add(pool.createSystem(ScaleAnimationSystem()))
            .add(pool.createSystem(RemoveOffscreenShipsSystem()))
            .add(pool.createSystem(SpriteRenderSystem()))
            .add(pool.createSystem(AddViewSystem(this)))
            .add(pool.createSystem(HealthRenderSystem()))
            .add(pool.createSystem(HudRenderSystem()))
            .add(pool.createSystem(DestroySystem()))
    }

    override fun render() {
        systems.execute()
    }
}
