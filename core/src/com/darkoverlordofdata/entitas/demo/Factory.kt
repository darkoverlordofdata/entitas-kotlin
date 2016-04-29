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
    entity.addView(entity.layer.ordinal, sprite)
    return entity

}

fun Pool.createPlayer(width:Float, height:Float): Entity {
    return createEntityFromTemplate("player")
        .addPosition(width/4, 80f)
        .addScore(0)
        .setPlayer(true)
}


fun Pool.createBullet(x:Float, y:Float): Entity {
    return createEntityFromTemplate("bullet")
        .addPosition(x, y)
        .addVelocity(0f, -800f)
        .addExpires(.5f)
        .addSoundEffect(Effect.PEW.ordinal)
        .addTint(1f, 1f, 115f/255f, 1f)
        .setBullet(true)
}


fun Pool.createSmallExplosion(x:Float, y:Float): Entity {
    val scale = 1f
    return createEntityFromTemplate("bang")
        .addExpires(0.5f)
        .addScaleAnimation(scale / 100, scale, -3f, false, true)
        .addPosition(x, y)
        .addSoundEffect(Effect.SMALLASPLODE.ordinal)
        .addScale(scale, scale)
        .addTint(1f, 1f, 39/255f, .5f)
}

fun Pool.createBigExplosion(x:Float, y:Float): Entity {
    val scale = .5f
    return createEntityFromTemplate("explosion")
        .addExpires(0.5f)
        .addScaleAnimation(scale / 100, scale, -3f, false, true)
        .addPosition(x, y)
        .addSoundEffect(Effect.ASPLODE.ordinal)
        .addScale(scale, scale)
        .addTint(1f, 1f, 39/255f, .5f)
}

fun Pool.createEnemy1(width:Float, height:Float): Entity {
    val entity = createEntityFromTemplate("enemy1")
    return entity
        .addPosition(Random.nextFloat()*width, height-entity.bounds.radius)
        .addVelocity(0f, 40f)
        .addHealth(10f, 10f)
        .setEnemy(true)
}

fun Pool.createEnemy2(width:Float, height:Float): Entity {
    val entity = createEntityFromTemplate("enemy2")
    return entity
        .addPosition(Random.nextFloat()*width, height-entity.bounds.radius)
        .addVelocity(0f, 30f)
        .addHealth(20f, 20f)
        .setEnemy(true)
}

fun Pool.createEnemy3(width:Float, height:Float): Entity {
    val entity = createEntityFromTemplate("enemy3")
    return entity
        .addPosition(Random.nextFloat()*width, height-entity.bounds.radius)
        .addVelocity(0f, 10f)
        .addHealth(60f, 60f)
        .setEnemy(true)
}