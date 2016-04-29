package com.darkoverlordofdata.entitas.demo

import com.darkoverlordofdata.entitas.Entity
import com.darkoverlordofdata.entitas.Pool

val Random = java.util.Random()
enum class Effect {
    PEW,
    ASPLODE,
    SMALLASPLODE
}


fun Pool.createEntityFromTemplate(name:String): Entity {
    val entity = createEntity(name)
    entity.addResource(O2d.getResource(name))
    entity.addLayer(O2d.getLayer(name))
    val sprite = O2d.sprites.createSprite(entity.resource.name)
    entity.addBounds(sprite.width/4)
    entity.addView(sprite)
    return entity

}

fun Pool.createPlayer(width:Float, height:Float): Entity {
    return createEntityFromTemplate("player")
        .addPosition(width/2, 80f)
        .addScore(0)
        .setPlayer(true)
}


fun Pool.createBullet(x:Float, y:Float): Entity {
    return createEntityFromTemplate("bullet")
        .addExpires(.5f)
        .addPosition(x, y)
        .addSoundEffect(Effect.PEW)
        .addTint(1f, 1f, 115f/255f, 1f)
        .addVelocity(0f, -800f)
        .setBullet(true)
}


fun Pool.createSmallExplosion(x:Float, y:Float): Entity {
    val scale = 1f
    return createEntityFromTemplate("bang")
        .addExpires(0.5f)
        .addPosition(x, y)
        .addScale(scale, scale)
        .addScaleAnimation(scale / 100, scale, -3f, false, true)
        .addSoundEffect(Effect.SMALLASPLODE)
        .addTint(1f, 1f, 39/255f, .5f)
}

fun Pool.createBigExplosion(x:Float, y:Float): Entity {
    val scale = .5f
    return createEntityFromTemplate("explosion")
        .addExpires(.5f)
        .addPosition(x, y)
        .addScale(scale, scale)
        .addScaleAnimation(scale / 100, scale, -3f, false, true)
        .addSoundEffect(Effect.ASPLODE)
        .addTint(1f, 1f, 39/255f, .5f)
}

fun Pool.createEnemy1(width:Float, height:Float): Entity {
    val entity = createEntityFromTemplate("enemy1")
    return entity
        .addHealth(10f, 10f)
        .addPosition(Random.nextFloat()*width, height-entity.bounds.radius)
        .addVelocity(0f, 40f)
        .setEnemy(true)
}

fun Pool.createEnemy2(width:Float, height:Float): Entity {
    val entity = createEntityFromTemplate("enemy2")
    return entity
        .addHealth(20f, 20f)
        .addPosition(Random.nextFloat()*width, height-entity.bounds.radius)
        .addVelocity(0f, 30f)
        .setEnemy(true)
}

fun Pool.createEnemy3(width:Float, height:Float): Entity {
    val entity = createEntityFromTemplate("enemy3")
    return entity
        .addHealth(60f, 60f)
        .addPosition(Random.nextFloat()*width, height-entity.bounds.radius)
        .addVelocity(0f, 10f)
        .setEnemy(true)
}