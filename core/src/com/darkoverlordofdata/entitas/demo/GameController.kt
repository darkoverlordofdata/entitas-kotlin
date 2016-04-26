package com.darkoverlordofdata.entitas.demo

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.darkoverlordofdata.entitas.ecs.Systems
import com.darkoverlordofdata.entitas.demo.systems.MovementSystem
import com.darkoverlordofdata.entitas.demo.systems.*
import com.darkoverlordofdata.entitas.ecs.Pool

class GameController : ApplicationAdapter() {
    internal lateinit var batch: SpriteBatch
    internal lateinit var img: Texture

    internal lateinit var pool:Pool
    internal lateinit var systems: Systems

    override fun create() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")


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
            .add(pool.createSystem(EntitySpawningTimerSystem()))
            .add(pool.createSystem(ScaleAnimationSystem()))
            .add(pool.createSystem(RemoveOffscreenShipsSystem()))
            .add(pool.createSystem(SpriteRenderSystem()))
            .add(pool.createSystem(AddViewSystem()))
            .add(pool.createSystem(HealthRenderSystem()))
            .add(pool.createSystem(HudRenderSystem()))
            .add(pool.createSystem(DestroySystem()))
    }

    override fun render() {
        systems.execute()
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        batch.draw(img, 0f, 0f)
        batch.end()
    }
}
