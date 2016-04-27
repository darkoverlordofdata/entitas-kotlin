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
    return createEntity("Player")
        .addBounds(43f)
        .addVelocity(0f, 0f)
        .addPosition(width/4, 80f)
        .addLayer(Layer.ACTORS_3.ordinal)
        .addResource("fighter")
        .toPlayer(true)
}

fun Pool.createBullet(x:Float, y:Float): Entity {
    return createEntity("Bullet")
        .addPosition(x, y)
        .addVelocity(0f, -800f)
        .addBounds(5f)
        .addExpires(.5f)
        .addSoundEffect(Effect.PEW.ordinal)
        .addLayer(Layer.PARTICLES.ordinal)
        .addResource("bullet")
        .toBullet(true)
    
}

fun Pool.createParticle(x:Float, y:Float): Entity {
    val radians = Random.nextDouble() * Tau
    val magnitude = Random.nextInt(200)
    val velocityX = magnitude * Math.cos(radians)
    val velocityY = magnitude * Math.sin(radians)
    val scale = Random.nextDouble() * .5f + .5f
    
    return createEntity("Particle")
        .addExpires(1f)
        .addColorAnimation(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f, -1f, false, false, false, true, true)
        .addPosition(x, y)
        .addVelocity(velocityX.toFloat(), velocityY.toFloat())
        .addScale(scale.toFloat(), scale.toFloat())
        .addLayer(Layer.PARTICLES.ordinal)
        .addResource("particle")
}

fun Pool.createSmallExplosion(x:Float, y:Float): Entity {
    val scale = .2f
    return createEntity("SmallExp")
        .addExpires(0.5f)
        .addScaleAnimation(scale / 100, scale, -3f, false, true)
        .addPosition(x, y)
        .addScale(scale, scale)
        .addLayer(Layer.PARTICLES.ordinal)
        .addResource("explosion")
    
}

fun Pool.createBigExplosion(x:Float, y:Float): Entity {
    val scale = .5f
    return createEntity("BigExp")
        .addExpires(0.5f)
        .addScaleAnimation(scale / 100, scale, -3f, false, true)
        .addPosition(x, y)
        .addScale(scale, scale)
        .addLayer(Layer.PARTICLES.ordinal)
        .addResource("explosion")
    
}

fun Pool.createEnemy1(width:Float, height:Float): Entity {
    val x = Random.nextInt(width.toInt())
    val y = height
    return createEntity("Enemy1")
        .addBounds(20f)
        .addPosition(x.toFloat(), y)
        .addVelocity(0f, 40f)
        .addLayer(Layer.ACTORS_1.ordinal)
        .addResource("enemy1")
        .addHealth(10f, 10f)
        .toEnemy(true)

}

fun Pool.createEnemy2(width:Float, height:Float): Entity {
    val x = Random.nextInt(width.toInt())
    val y = height
    return createEntity("Enemy2")
        .addBounds(50f)
        .addPosition(x.toFloat(), y)
        .addVelocity(0f, 30f)
        .addLayer(Layer.ACTORS_1.ordinal)
        .addResource("enemy2")
        .addHealth(20f, 20f)
        .toEnemy(true)

}

fun Pool.createEnemy3(width:Float, height:Float): Entity {
    val x = Random.nextInt(width.toInt())
    val y = height
    return createEntity("Enemy3")
        .addBounds(70f)
        .addPosition(x.toFloat(), y)
        .addVelocity(0f, 20f)
        .addLayer(Layer.ACTORS_2.ordinal)
        .addResource("enemy3")
        .addHealth(60f, 60f)
        .toEnemy(true)

}