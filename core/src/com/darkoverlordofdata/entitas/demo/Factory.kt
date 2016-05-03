package com.darkoverlordofdata.entitas.demo

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

/**
 * Load values from Overlap2D Library
 */
fun Pool.createEntityFromLibrary(name:String): Entity {
    val layer = O2dLibrary.getLayer(name)
    val entity = createEntity(name)
    entity.addResource(O2dLibrary.getResource(name))
    entity.addLayer(layer)
    val sprite = O2dLibrary.sprites.createSprite(entity.resource.name)
    entity.addBounds(sprite.width/4)
    println(entity.toString())
    entity.addView(sprite)
    return entity

}

fun Pool.createPlayer(width:Float, height:Float): Entity {
    return createEntityFromLibrary("player")
        .addPosition(width/2, 80f)
        .addScore(0)
        .setPlayer(true)
}

fun Pool.createBullet(x:Float, y:Float): Entity {
    return createEntityFromLibrary("bullet")
        .addExpires(.5f)
        .addPosition(x, y)
        .addSoundEffect(Effect.PEW)
        .addTint(1f, 1f, 115f/255f, 1f)
        .addVelocity(0f, -800f)
        .setBullet(true)
}

fun Pool.createSmallExplosion(x:Float, y:Float): Entity {
    val scale = 1f
    return createEntityFromLibrary("bang")
        .addExpires(0.5f)
        .addPosition(x, y)
        .addScale(scale, scale)
        .addSoundEffect(Effect.SMALLASPLODE)
        .addTint(1f, 1f, 39/255f, .5f)
        .addTween(scale / 100, scale, -3f, false, true)
}

fun Pool.createBigExplosion(x:Float, y:Float): Entity {
    val scale = .5f
    return createEntityFromLibrary("explosion")
        .addExpires(.5f)
        .addPosition(x, y)
        .addScale(scale, scale)
        .addSoundEffect(Effect.ASPLODE)
        .addTint(1f, 1f, 39/255f, .5f)
        .addTween(scale / 100, scale, -3f, false, true)
}

fun Pool.createEnemy1(width:Float, height:Float): Entity {
    val entity = createEntityFromLibrary("enemy1")
    return entity
        .addHealth(10f, 10f)
        .addPosition(Random.nextFloat()*width, height-entity.bounds.radius)
        .addVelocity(0f, 40f)
        .setEnemy(true)
}

fun Pool.createEnemy2(width:Float, height:Float): Entity {
    val entity = createEntityFromLibrary("enemy2")
    return entity
        .addHealth(20f, 20f)
        .addPosition(Random.nextFloat()*width, height-entity.bounds.radius)
        .addVelocity(0f, 30f)
        .setEnemy(true)
}

fun Pool.createEnemy3(width:Float, height:Float): Entity {
    val entity = createEntityFromLibrary("enemy3")
    return entity
        .addHealth(60f, 60f)
        .addPosition(Random.nextFloat()*width, height-entity.bounds.radius)
        .addVelocity(0f, 10f)
        .setEnemy(true)
}