package com.darkoverlordofdata.entitas.demo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.darkoverlordofdata.entitas.demo.systems.*
import com.darkoverlordofdata.entitas.Systems
import com.darkoverlordofdata.entitas.Pool

class GameScene : Screen {

    val rnd = java.util.Random()
    val width:Int by lazy { Gdx.graphics.width }
    val height:Int by lazy { Gdx.graphics.height }
    val pixelFactor:Int by lazy { if (Gdx.graphics.density > 1f) 2 else 1 }


    lateinit var pool: Pool
    lateinit var systems: Systems
    lateinit var spriteRenderSystem: SpriteRenderSystem
    lateinit var camera: OrthographicCamera

    init {

        camera = OrthographicCamera(width.toFloat()/pixelFactor, height.toFloat()/pixelFactor)

        pool = Pool(Component.TotalComponents.ordinal)
        systems = createSystems(pool)
        systems.initialize()

    }

    fun createSystems(pool: Pool): Systems {
        spriteRenderSystem = SpriteRenderSystem(this)
        return Systems()
            .add(pool.createSystem(MovementSystem()))
            .add(pool.createSystem(AddViewSystem()))
            .add(pool.createSystem(PlayerInputSystem(this)))
            .add(pool.createSystem(SoundEffectSystem()))
            .add(pool.createSystem(CollisionSystem()))
            .add(pool.createSystem(ExpiringSystem()))
            .add(pool.createSystem(EntitySpawningTimerSystem(this)))
            .add(pool.createSystem(ScaleAnimationSystem()))
            .add(pool.createSystem(RemoveOffscreenShipsSystem()))
            .add(pool.createSystem(spriteRenderSystem))
            .add(pool.createSystem(HealthRenderSystem(this)))
            .add(pool.createSystem(HudRenderSystem(this)))
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
