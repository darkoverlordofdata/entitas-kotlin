package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.darkoverlordofdata.entitas.Group
import com.darkoverlordofdata.entitas.IExecuteSystem
import com.darkoverlordofdata.entitas.ISetPool
import com.darkoverlordofdata.entitas.Matcher
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.demo.*

class SoundEffectSystem()
      : IExecuteSystem,
        ISetPool {

    private lateinit var pool: Pool
    private lateinit var group: Group

    val pew: Sound by lazy {
        Gdx.audio.newSound(Gdx.files.internal("sfx/pew.ogg"))
    }
    val asplode: Sound by lazy {
        Gdx.audio.newSound(Gdx.files.internal("sfx/asplode.ogg"))
    }
    val smallasplode: Sound by lazy {
        Gdx.audio.newSound(Gdx.files.internal("sfx/smallasplode.ogg"))
    }

    override fun setPool(pool: Pool) {
        this.pool = pool
        group = pool.getGroup(Matcher.allOf(Matcher.SoundEffect))
    }

    override fun execute() {
        for (entity in group.entities) {
            when (entity.soundEffect.effect) {
                Effect.PEW.ordinal -> pew.play()
                Effect.ASPLODE.ordinal -> smallasplode.play()
                Effect.SMALLASPLODE.ordinal -> asplode.play()
                else -> Unit
            }
            entity.removeSoundEffect()
        }
    }

}