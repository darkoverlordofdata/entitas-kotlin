package com.darkoverlordofdata.entitas
import java.util.ArrayList
import kotlin.collections.MutableList
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle


class Systems(textureAtlas : TextureAtlas, font: BitmapFont, width:Int, height:Int, width2:Int, height2:Int) {

    val Timer1 = 2f
    val Timer2 = 6f
    val Timer3 = 12f
    val FireRate = .1f
    val width = width
    val height = height
    val width2 = width2
    val height2 = height2
    val Random = java.util.Random()
    val textureAtlas = textureAtlas
    val font = font
    val bullets:MutableList<Position> = ArrayList(listOf())
    val enemies1:MutableList<Position> = ArrayList(listOf())
    val enemies2:MutableList<Position> = ArrayList(listOf())
    val enemies3:MutableList<Position> = ArrayList(listOf())
    val bangs:MutableList<Position> = ArrayList(listOf())
    val explosions:MutableList<Position> = ArrayList(listOf())
    val deactivate:MutableList<Int> = ArrayList(listOf())

    var hasPlayer = false
    var shoot = false
    var enemyt1 = Timer1
    var enemyt2 = Timer2
    var enemyt3 = Timer3
    var mouseX = 0f
    var mouseY = 0f
    var timeToFire = 0f
    var level:List<Entity> = createLevel()
    val scale = 2f/3f /* Global scaling factor */

    /**
     * Call every frame to re-calc the game state
     *
     * @param delta the time since last update
     */
    fun update(delta: Float) {
        level = collisionSystem(
            enemySpawningSystem(level, delta)
                .map { inputSystem(it, delta) }
                .map { entitySystem(it, delta) }
                .map { movementSystem(it, delta) }
                .map { tweenSystem(it, delta) }
                .map { expiringSystem(it, delta) }
                .map { removeOffscreenSystem(it, delta) }, delta)
    }

    /**
     * Call every frame to render the game
     *
     * @param mainBatch LibGDX sprite batch
     */
    fun render(mainBatch: SpriteBatch) {
        level.filter { it.active }
            .sortedBy { it.layer }
            .map { spriteRenderSystem(it, mainBatch) }
            .map { healthRenderSystem(it, mainBatch) }

    }

    /**
     * Rectangle that contains the entity
     *
     * @param entity
     * @return new Rectangle
     */
    fun BoundingRect(entity:Entity): Rectangle {
        return if (entity.bounds != null && entity.position != null) {
            val (radius) = entity.bounds
            val (x, y) = entity.position
            val r2 = radius * 2f
            Rectangle(x-radius, y-radius, r2, r2)
        } else Rectangle()
    }

    /**
     * Check all entities for collisions
     *
     * @param entities the entire level
     * @param delta time since last update
     * @returns the entire level
     */
    fun collisionSystem(entities: List<Entity>, delta: Float): List<Entity> {

        fun onCollision(enemy:Entity, bullet:Entity):Entity = when {
            (  enemy.entityType == EntityType.Enemy
            && enemy.health != null
            && enemy.active
            && bullet.entityType == EntityType.Bullet
            && bullet.position != null
            && bullet.active) -> {

                val (x, y) = bullet.position
                val (current, maximum) = enemy.health

                deactivate.add(bullet.id)
                bangs.add(Position(x, y))

                if (current <= 0) {
                    deactivate.add(enemy.id)
                    explosions.add(Position(x, y))
                    enemy
                } else enemy.copy(health = Health(current - 1, maximum))
            }
            else -> enemy
        }

        tailrec fun checkCollisions(entity:Entity, entities: List<Entity>):Entity {
            return when (entities.isEmpty()) {
                true -> entity
                false -> {
                    val b = entities.last()
                    val e = if (entity.active) if (BoundingRect(entity).overlaps(BoundingRect(b))) {
                        onCollision(entity, b)
                    } else entity else entity
                    checkCollisions(e, entities.dropLast(1))
                }
            }
        }

        tailrec fun eachEntity(fromList: List<Entity>, toList: List<Entity>): List<Entity> {
            return when (fromList.isEmpty()) {
                true -> toList
                false -> {
                    val e = checkCollisions(fromList.last(), toList)
                    val toList2 = toList.toMutableList()
                    toList2.add(e)
                    eachEntity(fromList.dropLast(1), toList2)
                }
            }
        }

        return eachEntity(entities, arrayListOf())
    }

    /**
     * Spawn Enemy Entities
     *
     * @param entities the entire level
     * @param delta time since last update
     * @returns the entire level
     */
    fun enemySpawningSystem(entities: List<Entity>, delta: Float): List<Entity> {

        fun spawnEnemy(t:Float, enemy:Enemies): Float {
            val d = t - delta
            return if (d < 0) {
                when (enemy) {
                    Enemies.Enemy1 -> {
                        enemies1.add(Position(Random.nextInt(width2-43).toFloat(), (height2-50).toFloat()))
                        Timer1
                    }
                    Enemies.Enemy2 -> {
                        enemies2.add(Position(Random.nextInt(width2-86).toFloat(), (height2-50).toFloat()))
                        Timer2
                    }
                    Enemies.Enemy3 -> {
                        enemies3.add(Position(Random.nextInt(width2-160).toFloat(), (height2-50).toFloat()))
                        Timer3
                    }
                }
            } else d
        }
        enemyt1 = spawnEnemy(enemyt1, Enemies.Enemy1)
        enemyt2 = spawnEnemy(enemyt2, Enemies.Enemy2)
        enemyt3 = spawnEnemy(enemyt3, Enemies.Enemy3)
        return entities
    }

    /**
     * Check Player Input
     *
     * @param entity each entity
     * @param delta time since last update
     * @returns each entity
     */
    fun inputSystem(entity: Entity, delta: Float): Entity = when (entity.entityType) {
        EntityType.Player -> {
            if (shoot) {
                timeToFire = timeToFire - delta
                if (timeToFire < 0) {
                    bullets.add(Position(mouseX-27f, mouseY-10))
                    bullets.add(Position(mouseX+27f, mouseY-10))
                    timeToFire = FireRate
                }
            }
            if (entity.sprite != null) {
                entity.copy(position = Position(mouseX.toFloat(), mouseY.toFloat()))
            } else entity
        }
        else -> entity
    }


    /**
     * Deactivate entities that are past their expire time
     *
     * @param entity each entity
     * @param delta time since last update
     * @returns each entity
     */
    fun expiringSystem(entity: Entity, delta: Float): Entity = when {
        (entity.active
        && entity.expires != null) -> {

            val value = entity.expires.value - (delta*10)
            val active = if (value>0) true else false
            if (!active) deactivate.add(entity.id)
            entity.copy(active = active, expires = Expires(value))
        }
        else -> entity
    }


    /**
     * Move entities that have velocity
     *
     * @param entity each entity
     * @param delta time since last update
     * @returns each entity
     */
    fun movementSystem(entity: Entity, delta: Float): Entity = when {
        (entity.position != null
        && entity.velocity != null) -> {

            var (x, y) = entity.position
            val velocity = entity.velocity
            x += velocity.x * delta
            y += velocity.y * delta
            entity.copy(position = Position(x, y))
        }
        else -> entity
    }


    fun healthRenderSystem(entity: Entity, batch : SpriteBatch): Entity = when {
        (entity.active
        && entity.entityType == EntityType.Enemy
        && entity.position != null
        && entity.health != null) -> {
            val position = entity.position
            val health = entity.health

            val percentage = MathUtils.round(health.current/health.maximum*100f).toInt()
            font.draw(batch, "$percentage%", position.x, position.y)
            entity
        }
        else -> entity
    }
    /**
     * Render an entities sprite
     *
     * @param entity each entity
     * @param delta time since last update
     * @returns each entity
     */
    fun spriteRenderSystem(entity: Entity, batch : SpriteBatch): Entity = when {
        (entity.sprite != null
        && entity.position != null) -> {

            val sprite = entity.sprite.sprite
            if (entity.scale != null)
                sprite.setScale(entity.scale.x*scale, entity.scale.y*scale)
            else sprite.setScale(scale)

            val x = sprite.width/2f
            val y = sprite.height/2f

            sprite.setPosition(entity.position.x-x, entity.position.y-y)
            val color = batch.color
            if (entity.tint != null)
                sprite.setColor(entity.tint.r, entity.tint.g, entity.tint.b, entity.tint.a)

            sprite.draw(batch)
            batch.color = color
            entity
        }
        else -> entity
    }


    /**
     * Remove entities that have wandered off the screen
     *
     * @param entity each entity
     * @param delta time since last update
     * @returns each entity
     */
    fun removeOffscreenSystem(entity: Entity, delta: Float): Entity = when {
        (entity.active
        && entity.position != null
        && entity.entityType == EntityType.Enemy) -> {

            if (entity.position.y < 0) deactivate.add(entity.id)
            entity
        }

        (entity.active
        && entity.position != null
        && entity.entityType == EntityType.Bullet) -> {

            if (entity.position.y > height) deactivate.add(entity.id)
            entity
        }

        else -> entity
    }

    /**
     * Interpolate all tweened values
     *
     * @param entity each entity
     * @param delta time since last update
     * @returns each entity
     */
    fun tweenSystem(entity: Entity, delta: Float): Entity = when {
        (entity.active
        && entity.tween != null
        && entity.scale != null) -> {

            val tween = entity.tween
            var (x, y) = entity.scale
            var active = tween.active
            x += (tween.speed * delta)
            y += (tween.speed * delta)
            if (x > tween.max) {
                x = tween.max
                y = tween.max
                active = false
            } else if (x < tween.min) {
                x = tween.min
                y = tween.min
                active = false
            }
            entity.copy(
                scale=Scale(x, y),
                tween=Tween(tween.min, tween.max, tween.speed, tween.repeat, active)
            )
        }
        else -> entity
    }


    /**
     * Process new and deactivate entity requests
     *
     * @param entity each entity
     * @param delta time since last update
     * @returns each entity
     */
    fun entitySystem(entity: Entity, delta: Float): Entity = when {
        (entity.active) -> {
            if (deactivate.remove(entity.id)) entity.copy(active = false)
            else entity
        }
        else -> {
            when (entity.layer) {
                SpriteLayer.BULLET -> {
                    if (bullets.isEmpty()) entity
                    else {
                        val bullet = bullets.removeAt(0)
                        createBullet(textureAtlas, entity, bullet.x, bullet.y)
                    }
                }
                SpriteLayer.PLAYER -> {
                    if (hasPlayer) entity
                    else {
                        hasPlayer = true
                        createPlayer(textureAtlas, entity, width/2f, 80f)
                    }
                }
                SpriteLayer.ENEMY1 -> {
                    if (enemies1.isEmpty()) entity
                    else {
                        val enemy = enemies1.removeAt(0)
                        createEnemy1(textureAtlas, entity, enemy.x, enemy.y)
                    }
                }
                SpriteLayer.ENEMY2 -> {
                    if (enemies2.isEmpty()) entity
                    else {
                        val enemy = enemies2.removeAt(0)
                        createEnemy2(textureAtlas, entity, enemy.x, enemy.y)
                    }
                }
                SpriteLayer.ENEMY3 -> {
                    if (enemies3.isEmpty()) entity
                    else {
                        val enemy = enemies3.removeAt(0)
                        createEnemy3(textureAtlas, entity, enemy.x, enemy.y)
                    }
                }
                SpriteLayer.BANG -> {
                    if (bangs.isEmpty()) entity
                    else {
                        val bang = bangs.removeAt(0)
                        createBang(textureAtlas, entity, bang.x, bang.y)
                    }
                }
                SpriteLayer.EXPLOSION -> {
                    if (explosions.isEmpty()) entity
                    else {
                        val explosion = explosions.removeAt(0)
                        createExplosion(textureAtlas, entity, explosion.x, explosion.y)
                    }
                }
            }
        }
    }
}
