package com.darkoverlordofdata.entitas.demo

import com.darkoverlordofdata.entitas.Entity
import com.darkoverlordofdata.entitas.Pool

enum class Enemies {
    Enemy1,
    Enemy2,
    Enemy3
}
enum class Layer {
    DEFAULT,
    BACKGROUND,
    ACTORS_1,
    ACTORS_2,
    ACTORS_3,
    PARTICLES
}

enum class Effect {
    PEW,
    ASPLODE,
    SMALLASPLODE
}

val Random = java.util.Random()
val Tau = Math.PI*2f


fun Pool.createPlayer(width:Float, height:Float): Entity {
    val name = "player"
    return createEntity(name)
        .addBounds(43f)
        .addPosition(width/4, 80f)          // project.libraryItems["player"].x
        .addLayer(Layer.ACTORS_3.ordinal)   // project.libraryItems["player"].layerName
        .addResource(O2d.getResource(name))
        .addScore(0)
        .setPlayer(true)
}

fun Pool.createBullet(x:Float, y:Float): Entity {
    val name = "bullet"
    return createEntity(name)
        .addPosition(x, y)
        .addVelocity(0f, -800f)
        .addBounds(5f)
        .addExpires(.5f)
        .addSoundEffect(Effect.PEW.ordinal)
        .addLayer(Layer.PARTICLES.ordinal)
        .addResource(O2d.getResource(name))
        .addTint(1f, 1f, 115f/255f, 1f)
        .setBullet(true)
}


fun Pool.createSmallExplosion(x:Float, y:Float): Entity {
    val name = "bang"
    val scale = 1f
    return createEntity(name)
        .addExpires(0.5f)
        .addScaleAnimation(scale / 100, scale, -3f, false, true)
        .addPosition(x, y)
        .addScale(scale, scale)
        .addLayer(Layer.PARTICLES.ordinal)
        .addTint(1f, 1f, 39/255f, .5f)
        .addResource(O2d.getResource(name))
}

fun Pool.createBigExplosion(x:Float, y:Float): Entity {
    val name = "explosion"
    val scale = .5f
    return createEntity(name)
        .addExpires(0.5f)
        .addScaleAnimation(scale / 100, scale, -3f, false, true)
        .addPosition(x, y)
        .addScale(scale, scale)
        .addLayer(Layer.PARTICLES.ordinal)
        .addTint(1f, 1f, 39/255f, .5f)
        .addResource(O2d.getResource(name))
}

fun Pool.createEnemy1(width:Float, height:Float): Entity {
    val name = "enemy1"
    val x = Random.nextInt(width.toInt())
    val y = height-50f
    return createEntity(name)
        .addBounds(20f)
        .addPosition(x.toFloat(), y)
        .addVelocity(0f, 40f)
        .addLayer(Layer.ACTORS_1.ordinal)
        .addResource(O2d.getResource(name))
        .addHealth(10f, 10f)
        .setEnemy(true)
}

fun Pool.createEnemy2(width:Float, height:Float): Entity {
    val name = "enemy2"
    val x = Random.nextInt(width.toInt())
    val y = height-50f
    return createEntity(name)
        .addBounds(50f)
        .addPosition(x.toFloat(), y)
        .addVelocity(0f, 30f)
        .addLayer(Layer.ACTORS_1.ordinal)
        .addResource(O2d.getResource(name))
        .addHealth(20f, 20f)
        .setEnemy(true)
}

fun Pool.createEnemy3(width:Float, height:Float): Entity {
    val name = "enemy3"
    val x = Random.nextInt(width.toInt())
    val y = height-50f
    return createEntity(name)
        .addBounds(70f)
        .addPosition(x.toFloat(), y)
        .addVelocity(0f, 20f)
        .addLayer(Layer.ACTORS_2.ordinal)
        .addResource(O2d.getResource(name))
        .addHealth(60f, 60f)
        .setEnemy(true)
}