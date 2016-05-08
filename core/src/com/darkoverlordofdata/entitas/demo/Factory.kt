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
    it.addPosition(width/2, 80f)
    it.addScore(0)
    it.setPlayer(true)
}

fun Pool.createBullet(x:Float, y:Float): Entity = prefab("bullet") {
    it.addExpires(.5f)
    it.addPosition(x, y)
    it.addSoundEffect(Effect.PEW)
    it.addTint(1f, 1f, 115f/255f, 1f)
    it.addVelocity(0f, -800f)
    it.setBullet(true)
}


fun Pool.createSmallExplosion(x:Float, y:Float): Entity = prefab("bang") {
    val scale = 1f
    it.addExpires(.5f)
    it.addPosition(x, y)
    it.addScale(scale, scale)
    it.addSoundEffect(Effect.SMALLASPLODE)
    it.addTint(1f, 1f, 39/255f, .5f)
    it.addTween(scale / 100, scale, -3f, false, true)
}

fun Pool.createBigExplosion(x:Float, y:Float): Entity = prefab("explosion") {
    val scale = .5f
    it.addExpires(.5f)
    it.addPosition(x, y)
    it.addScale(scale, scale)
    it.addSoundEffect(Effect.ASPLODE)
    it.addTint(1f, 1f, 39/255f, .5f)
    it.addTween(scale / 100, scale, -3f, false, true)
}


fun Pool.createEnemy1(width:Float, height:Float): Entity = prefab("enemy1") {
    it.addHealth(10f, 10f)
    it.addPosition(Random.nextFloat()*width, height-it.bounds.radius)
    it.addVelocity(0f, 40f)
    it.setEnemy(true)
}


fun Pool.createEnemy2(width:Float, height:Float): Entity = prefab("enemy2") {
    it.addHealth(20f, 20f)
    it.addPosition(Random.nextFloat()*width, height-it.bounds.radius)
    it.addVelocity(0f, 30f)
    it.setEnemy(true)
}


fun Pool.createEnemy3(width:Float, height:Float): Entity = prefab("enemy3") {
    it.addHealth(60f, 60f)
    it.addPosition(Random.nextFloat()*width, height-it.bounds.radius)
    it.addVelocity(0f, 10f)
    it.setEnemy(true)
}


