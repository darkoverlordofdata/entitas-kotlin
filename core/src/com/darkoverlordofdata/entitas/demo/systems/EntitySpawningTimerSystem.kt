package com.darkoverlordofdata.entitas.demo.systems

/**
 * EntitySpawningTimerSystem
 *
 * Periodically spawn enemy ships
 *
 */

import com.badlogic.gdx.Gdx
import com.darkoverlordofdata.entitas.demo.*
import com.darkoverlordofdata.entitas.IExecuteSystem
import com.darkoverlordofdata.entitas.Pool

class EntitySpawningTimerSystem(game: GameScene, pool: Pool)
      : IExecuteSystem {

    private val game = game
    private val pool = pool
    private val Timer1 = 2f
    private val Timer2 = 6f
    private val Timer3 = 12f
    private var t1 = Timer1
    private var t2 = Timer2
    private var t3 = Timer3

    val toWorldX:Float get() = 320f/game.width
    val toWorldY:Float get() = 480f/game.height
    val width:Float get() = game.width*toWorldX
    val height:Float get() = game.height*toWorldY

    enum class Enemies {
        Enemy1,
        Enemy2,
        Enemy3
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