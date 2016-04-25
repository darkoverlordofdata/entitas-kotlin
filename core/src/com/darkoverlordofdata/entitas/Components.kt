package com.darkoverlordofdata.entitas
import com.badlogic.gdx.graphics.g2d.TextureAtlas

enum class EntityType {
    Bullet,
    Enemy,
    Explosion,
    Player
}

enum class Enemies {
    Enemy1,
    Enemy2,
    Enemy3
}

enum class SpriteLayer {
    ENEMY1,
    ENEMY2,
    ENEMY3,
    BULLET,
    BANG,
    EXPLOSION,
    PLAYER
}

data class Bounds(val radius: Float)
data class Expires(val value: Float)
data class Health(val current: Float, val maximum: Float)
data class Position(val x: Float, val y: Float)
data class Scale(val x: Float, val y:Float)
data class Sprite(val sprite: com.badlogic.gdx.graphics.g2d.Sprite)
data class Tint(val r: Float, val g: Float, val b: Float, val a: Float)
data class Tween(val min:Float, val max:Float, val speed:Float, val repeat:Boolean, val active:Boolean)
data class Velocity(val x: Float, val y: Float)
data class Entity(
    val id:         Int,            /* Unique id */
    val name:       String,         /* Display name */
    val active:     Boolean,        /* In use? */
    val entityType: EntityType,     /* EntityType Enum */
    val layer:      SpriteLayer,    /* Display Layer Enum */
                                    /* <<C O M P O N E N T S>> */
    val bounds:     Bounds?,        /* Radius */
    val expires:    Expires?,       /* Entity expiration timer */
    val health:     Health?,        /* Health counter */
    val position:   Position?,      /* Screen x,y */
    val scale:      Scale?,         /* Size scale */
    val sprite:     Sprite?,
    val tint:       Tint?,          /* Color tint */
    val tween:      Tween?,         /* Tweener */
    val velocity:   Velocity?      /* Speed x,y */
)
var uniqueId: Int = 0

fun createEntity(entityType: EntityType, layer : SpriteLayer, name : String):Entity {
    uniqueId += 1
    return Entity(uniqueId, name, false, entityType, layer, null, null, null, null, null, null, null, null, null)
}

fun createPlayer(textureAtlas : TextureAtlas, entity:Entity, x: Float, y: Float):Entity {
    return entity.copy(
        active      = true,
        bounds      = Bounds(43f),
        health      = Health(100f, 100f),
        position    = Position(x, y),
        sprite      = Sprite(textureAtlas.createSprite(entity.name)),
        velocity    = Velocity(0f, 0f)
    )
}

fun createEnemy1(textureAtlas : TextureAtlas, entity:Entity, x: Float, y: Float):Entity =
    when (entity.sprite) {
        null -> {
            entity.copy(
                active      = true,
                bounds      = Bounds(20f),
                health      = Health(10f, 10f),
                position    = Position(x, y),
                sprite      = Sprite(textureAtlas.createSprite(entity.name)),
                velocity    = Velocity(0f, -40f)
            )
        }
        else -> {
            entity.copy(
                active      = true,
                bounds      = Bounds(20f),
                health      = Health(10f, 10f),
                position    = Position(x, y),
                velocity    = Velocity(0f, -40f)
            )
        }
    }


fun createEnemy2(textureAtlas : TextureAtlas, entity:Entity, x: Float, y: Float):Entity =
    when (entity.sprite) {
        null -> {
            entity.copy(
                active      = true,
                bounds      = Bounds(40f),
                health      = Health(20f, 20f),
                position    = Position(x, y),
                sprite      = Sprite(textureAtlas.createSprite(entity.name)),
                velocity    = Velocity(0f, -30f)
            )
        }
        else -> {
            entity.copy(
                active      = true,
                bounds      = Bounds(40f),
                health      = Health(20f, 20f),
                position    = Position(x, y),
                velocity    = Velocity(0f, -30f)
            )
        }
    }


fun createEnemy3(textureAtlas : TextureAtlas, entity:Entity, x: Float, y: Float):Entity =
    when (entity.sprite) {
        null -> {
            entity.copy(
                active      = true,
                bounds      = Bounds(70f),
                health      = Health(60f, 60f),
                position    = Position(x, y),
                sprite      = Sprite(textureAtlas.createSprite(entity.name)),
                velocity    = Velocity(0f, -20f)
            )
        }
        else -> {
            entity.copy(
                active      = true,
                bounds      = Bounds(70f),
                health      = Health(60f, 60f),
                position    = Position(x, y),
                velocity    = Velocity(0f, -20f)
            )
        }
    }


fun createBullet(textureAtlas : TextureAtlas, entity:Entity, x: Float, y: Float):Entity =
    when (entity.sprite) {
        null -> {
            entity.copy(
                active      = true,
                bounds      = Bounds(5f),
                //expires   = Expires(1.5f),
                position    = Position(x, y),
                sprite      = Sprite(textureAtlas.createSprite(entity.name)),
                tint        = Tint(1f, 1f, 115f/255f, 1f),
                velocity    = Velocity(0f, 800f)
            )
        }
        else -> {
            entity.copy(
                active      = true,
                bounds      = Bounds(5f),
                //expires   = Expires(1.5f),
                position    = Position(x, y),
                tint        = Tint(1f, 1f, 115f/255f, 1f),
                velocity    = Velocity(0f, 800f)
            )
        }
    }


fun createExplosion(textureAtlas : TextureAtlas, entity:Entity, x: Float, y: Float):Entity =
    when (entity.sprite) {
        null -> {
            entity.copy(
                active       = true,
                expires     = Expires(0.5f),
                position    = Position(x, y),
                sprite      = Sprite(textureAtlas.createSprite(entity.name)),
                scale       = Scale(1f, 1f),
                tint        = Tint(1f, 1f, 39f/255f, .5f),
                tween       = Tween(.01f, 1f, -.003f, false, true)
            )
        }
        else -> {
            entity.copy(
                active      = true,
                expires     = Expires(0.5f),
                position    = Position(x, y),
                scale       = Scale(1f, 1f),
                tint        = Tint(1f, 1f, 39f/255f, .5f),
                tween       = Tween(.01f, 1f, -.003f, false, true)
            )
        }
    }

fun createBang(textureAtlas : TextureAtlas, entity:Entity, x: Float, y: Float):Entity =
    when (entity.sprite) {
        null -> {
            entity.copy(
                active      = true,
                expires     = Expires(0.5f),
                position    = Position(x, y),
                sprite      = Sprite(textureAtlas.createSprite(entity.name)),
                scale       = Scale(2f, 2f),
                tint        = Tint(1f, 1f, 77f/255f, .5f),
                tween       = Tween(.02f, 2f, -.003f, false, true)
            )
        }
        else -> {
            entity.copy(
                active      = true,
                expires     = Expires(0.5f),
                position    = Position(x, y),
                scale       = Scale(2f, 2f),
                tint        = Tint(1f, 1f, 77f/255f, .5f),
                tween       = Tween(.02f, 2f, -.003f, false, true)
            )
        }
    }

fun createLevel(): List<Entity> = arrayListOf(
        createEntity(EntityType.Player, SpriteLayer.PLAYER, "fighter"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY1, "enemy1"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY1, "enemy1"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY1, "enemy1"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY1, "enemy1"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY1, "enemy1"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY1, "enemy1"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY2, "enemy2"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY2, "enemy2"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY2, "enemy2"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY2, "enemy2"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY3, "enemy3"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY3, "enemy3"),
        createEntity(EntityType.Enemy, SpriteLayer.ENEMY3, "enemy3"),

        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),

//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//        createEntity(EntityType.Bullet, SpriteLayer.BULLET, "bullet"),
//
        createEntity(EntityType.Explosion, SpriteLayer.BANG, "bang"),
        createEntity(EntityType.Explosion, SpriteLayer.BANG, "bang"),
        createEntity(EntityType.Explosion, SpriteLayer.EXPLOSION, "explosion"),
        createEntity(EntityType.Explosion, SpriteLayer.EXPLOSION, "explosion")

    )


