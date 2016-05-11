package com.darkoverlordofdata.entitas.demo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.darkoverlordofdata.entitas.Entity
import com.darkoverlordofdata.entitas.Pool

val Random = java.util.Random()

enum class Effect {
    PEW,
    ASPLODE,
    SMALLASPLODE
}

enum class Layer {
    DEFAULT,
    BACKGROUND,
    ENEMY3,
    ENEMY2,
    ENEMY1,
    PLAYER,
    BATTLE
}
fun CreateFont(file:String): BitmapFont {
    val fontTexture = Texture(Gdx.files.internal("${file}_0.png"))
    fontTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.MipMapLinearLinear)
    val fontRegion = TextureRegion(fontTexture)
    val font = BitmapFont(Gdx.files.internal("${file}.fnt"), fontRegion, false)
    font.setUseIntegerPositions(false)
    return font
}



/**
 * Load prefab values from Overlap2D Library
 */
fun Pool.prefab(name:String, template:(entity:Entity)-> Entity): Entity {
    val entity = createEntity(name)
    entity.addResource(O2dLibrary.getResource(name))
    entity.addLayer(O2dLibrary.getLayer(name))
    val sprite = O2dLibrary.sprites.createSprite(entity.resource.name)
    entity.addBounds(sprite.width/4)
    entity.addView(sprite)
    return template(entity)
}

fun Pool.createPlayer(width:Float, height:Float): Entity = prefab("player") {
    with (it) {
        addPosition(width/2, 80f)
        addScore(0)
        setPlayer(true)
    }
}

fun Pool.createBullet(x:Float, y:Float): Entity = prefab("bullet") {
    with (it) {
        addExpires(.5f)
        addPosition(x, y)
        addSoundEffect(Effect.PEW)
        addTint(1f, 1f, 115f / 255f, 1f)
        addVelocity(0f, -800f)
        setBullet(true)
    }
}


fun Pool.createSmallExplosion(x:Float, y:Float): Entity = prefab("bang") {
    val scale = 1f
    with (it) {
        addExpires(.5f)
        addPosition(x, y)
        addScale(scale, scale)
        addSoundEffect(Effect.SMALLASPLODE)
        addTint(1f, 1f, 39 / 255f, .5f)
        addTween(scale / 100, scale, -3f, false, true)
    }
}

fun Pool.createBigExplosion(x:Float, y:Float): Entity = prefab("explosion") {
    val scale = .5f
    with (it) {
        addExpires(.5f)
        addPosition(x, y)
        addScale(scale, scale)
        addSoundEffect(Effect.ASPLODE)
        addTint(1f, 1f, 39 / 255f, .5f)
        addTween(scale / 100, scale, -3f, false, true)
    }
}


fun Pool.createEnemy1(width:Float, height:Float): Entity = prefab("enemy1") {
    with (it) {
        addHealth(10f, 10f)
        addPosition(Random.nextFloat() * width, height - bounds.radius)
        addVelocity(0f, 40f)
        setEnemy(true)
    }
}


fun Pool.createEnemy2(width:Float, height:Float): Entity = prefab("enemy2") {
    with (it) {
        addHealth(20f, 20f)
        addPosition(Random.nextFloat() * width, height - bounds.radius)
        addVelocity(0f, 30f)
        setEnemy(true)
    }
}


fun Pool.createEnemy3(width:Float, height:Float): Entity = prefab("enemy3") {
    with (it) {
        addHealth(60f, 60f)
        addPosition(Random.nextFloat() * width, height - bounds.radius)
        addVelocity(0f, 10f)
        setEnemy(true)
    }
}


