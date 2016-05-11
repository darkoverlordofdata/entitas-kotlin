package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.darkoverlordofdata.entitas.IExecuteSystem
import com.darkoverlordofdata.entitas.Matcher
import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.demo.*

class SoundEffectSystem(pool: Pool)
      : IExecuteSystem {

    val group = pool.getGroup(Matcher.allOf(Matcher.SoundEffect))

    val pew: Sound by lazy {
        Gdx.audio.newSound(Gdx.files.internal("sfx/pew.ogg"))
    }
    val asplode: Sound by lazy {
        Gdx.audio.newSound(Gdx.files.internal("sfx/asplode.ogg"))
    }
    val smallasplode: Sound by lazy {
        Gdx.audio.newSound(Gdx.files.internal("sfx/smallasplode.ogg"))
    }


    override fun execute() {
        for (entity in group.entities) {
            when (entity.soundEffect.effect) {
                Effect.PEW -> pew
                Effect.ASPLODE -> smallasplode
                Effect.SMALLASPLODE -> asplode
                else -> null
            }?.play()
            entity.removeSoundEffect()
        }
    }

}