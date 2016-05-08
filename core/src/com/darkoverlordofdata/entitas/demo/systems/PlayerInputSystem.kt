package com.darkoverlordofdata.entitas.demo.systems

/**
 * PlayerInputSystem
 *
 * Respond to the players touch input
 */

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.darkoverlordofdata.entitas.*
import com.darkoverlordofdata.entitas.demo.*

class PlayerInputSystem(game: GameScene)
      : IInitializeSystem,
        IExecuteSystem,
        ISetPool,
        InputProcessor {

    val width = game.width
    val height = game.height
    val pixelFactor = game.pixelFactor
    val FireRate = .1f

    private lateinit var pool: Pool
    private lateinit var group: Group
    private var shoot = false
    private var mouseX = 0
    private var mouseY = 0
    private var timeToFire = 0f

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.Player))
    }

    override fun initialize() {
        Gdx.input.inputProcessor = this
        pool.createPlayer(width.toFloat(), height.toFloat())
    }

    override fun execute() {
        val player = group.singleEntity

        if (player != null) {
            player.position.x = mouseX.toFloat()
            player.position.y = mouseY.toFloat()
            if (shoot) {
                timeToFire -= Gdx.graphics.deltaTime
                if (timeToFire < 0) {
                    pool.createBullet(mouseX - 27f, mouseY - 10f)
                    pool.createBullet(mouseX + 27f, mouseY - 10f)
                    timeToFire = FireRate
                }
            }
        }
    }

    override fun touchDown(x: Int, y: Int, pointer: Int, button: Int) : Boolean {
        shoot = true
        moveTo(x, y)
        return false
    }
    override fun touchUp(x: Int, y: Int, pointer: Int, button: Int) : Boolean {
        shoot = false
        return true
    }
    override fun touchDragged(x: Int, y: Int, pointer: Int) : Boolean {
        moveTo(x, y)
        return false
    }
    override fun mouseMoved(x: Int, y: Int) : Boolean {
        moveTo(x, y)
        return false
    }
    override fun keyDown(keycode: Int) : Boolean {
        if (Input.Keys.Z == keycode) shoot = true
        return true
    }
    override fun keyUp(keycode: Int) : Boolean  {
        if (Input.Keys.Z == keycode) shoot = false
        return true
    }
    override fun keyTyped(character: Char) : Boolean {
        return false
    }
    override fun scrolled(amount: Int) : Boolean {
        return false
    }

    fun moveTo(x: Int, y:Int) {
        mouseX = x/pixelFactor
        mouseY = (height - y)/pixelFactor
    }

}