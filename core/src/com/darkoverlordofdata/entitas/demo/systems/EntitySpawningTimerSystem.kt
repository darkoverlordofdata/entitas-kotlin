package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.demo.*
import com.darkoverlordofdata.entitas.ISetPool
import com.darkoverlordofdata.entitas.IExecuteSystem
import com.darkoverlordofdata.entitas.IInitializeSystem
import com.darkoverlordofdata.entitas.Pool

class EntitySpawningTimerSystem(game: GameScene)
      : IInitializeSystem,
        IExecuteSystem,
        ISetPool {

    private val game = game
    private val Timer1 = 2f
    private val Timer2 = 6f
    private val Timer3 = 12f
    private var t1 = 0f
    private var t2 = 0f
    private var t3 = 0f
    private lateinit var pool: Pool

    val toWorldX:Float get() = 320f/game.width
    val toWorldY:Float get() = 480f/game.height
    val width:Float get() = game.width*toWorldX
    val height:Float get() = game.height*toWorldY


    override fun setPool(pool: Pool) {
        this.pool = pool
    }

    override fun initialize() {
        t1 = Timer1
        t2 = Timer2
        t3 = Timer3
    }

    override fun execute() {
        val delta = Gdx.graphics.deltaTime
        t1 = spawnEnemy(delta, t1, Enemies.Enemy1)
        t2 = spawnEnemy(delta, t2, Enemies.Enemy2)
        t3 = spawnEnemy(delta, t3, Enemies.Enemy3)
    }

    fun spawnEnemy(delta: Float, t:Float, enemy: Enemies): Float {
        val remaining = t - delta
        return if (remaining < 0) {
            when (enemy) {
                Enemies.Enemy1 -> {
                    pool.createEnemy1(width, height)
                    Timer1
                }
                Enemies.Enemy2 -> {
                    pool.createEnemy2(width, height)
                    Timer2
                }
                Enemies.Enemy3 -> {
                    pool.createEnemy3(width, height)
                    Timer3
                }
            }
        } else remaining
    }
}