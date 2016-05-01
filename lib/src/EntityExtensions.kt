package com.darkoverlordofdata.entitas.demo
/**
 * Entitas Generated Entity Extensions for com.darkoverlordofdata.entitas.demo
 *
 * do not edit this file
 */

import java.util.*
import com.darkoverlordofdata.entitas.Entity

/** Entity: Bounds methods*/

val Entity_boundsComponentPool:MutableList<BoundsComponent> = ArrayList(listOf())

val Entity.bounds:BoundsComponent
    get() = getComponent(Component.Bounds.ordinal) as BoundsComponent

val Entity.hasBounds:Boolean
    get() = hasComponent(Component.Bounds.ordinal)

fun Entity.clearBoundsComponentPool() {
    Entity_boundsComponentPool.clear()
}

fun Entity.addBounds(radius:Float):Entity {
    val component = if (Entity_boundsComponentPool.size > 0) Entity_boundsComponentPool.last() else BoundsComponent()
    component.radius = radius
    addComponent(Component.Bounds.ordinal, component)
    return this
}

fun Entity.replaceBounds(radius:Float):Entity {
    val previousComponent = if (hasBounds) bounds else null
    val component = if (Entity_boundsComponentPool.size > 0) Entity_boundsComponentPool.last() else BoundsComponent()
    component.radius = radius
    replaceComponent(Component.Bounds.ordinal, component)
    if (previousComponent != null)
        Entity_boundsComponentPool.add(previousComponent)
    return this
}

fun Entity.removeBounds():Entity {
    val component = bounds
    removeComponent(Component.Bounds.ordinal)
    Entity_boundsComponentPool.add(component)
    return this
}

/** Entity: Bullet methods*/

val Entity_bulletComponentPool:MutableList<BulletComponent> = ArrayList(listOf())

val Entity.bullet:BulletComponent
    get() = getComponent(Component.Bullet.ordinal) as BulletComponent

val Entity.hasBullet:Boolean
    get() = hasComponent(Component.Bullet.ordinal)

fun Entity.clearBulletComponentPool() {
    Entity_bulletComponentPool.clear()
}

fun Entity.addBullet(false:undefined):Entity {
    val component = if (Entity_bulletComponentPool.size > 0) Entity_bulletComponentPool.last() else BulletComponent()
    component.false = false
    addComponent(Component.Bullet.ordinal, component)
    return this
}

fun Entity.replaceBullet(false:undefined):Entity {
    val previousComponent = if (hasBullet) bullet else null
    val component = if (Entity_bulletComponentPool.size > 0) Entity_bulletComponentPool.last() else BulletComponent()
    component.false = false
    replaceComponent(Component.Bullet.ordinal, component)
    if (previousComponent != null)
        Entity_bulletComponentPool.add(previousComponent)
    return this
}

fun Entity.removeBullet():Entity {
    val component = bullet
    removeComponent(Component.Bullet.ordinal)
    Entity_bulletComponentPool.add(component)
    return this
}

/** Entity: Destroy methods*/

val Entity_destroyComponentPool:MutableList<DestroyComponent> = ArrayList(listOf())

val Entity.destroy:DestroyComponent
    get() = getComponent(Component.Destroy.ordinal) as DestroyComponent

val Entity.hasDestroy:Boolean
    get() = hasComponent(Component.Destroy.ordinal)

fun Entity.clearDestroyComponentPool() {
    Entity_destroyComponentPool.clear()
}

fun Entity.addDestroy(false:undefined):Entity {
    val component = if (Entity_destroyComponentPool.size > 0) Entity_destroyComponentPool.last() else DestroyComponent()
    component.false = false
    addComponent(Component.Destroy.ordinal, component)
    return this
}

fun Entity.replaceDestroy(false:undefined):Entity {
    val previousComponent = if (hasDestroy) destroy else null
    val component = if (Entity_destroyComponentPool.size > 0) Entity_destroyComponentPool.last() else DestroyComponent()
    component.false = false
    replaceComponent(Component.Destroy.ordinal, component)
    if (previousComponent != null)
        Entity_destroyComponentPool.add(previousComponent)
    return this
}

fun Entity.removeDestroy():Entity {
    val component = destroy
    removeComponent(Component.Destroy.ordinal)
    Entity_destroyComponentPool.add(component)
    return this
}

/** Entity: Enemy methods*/

val Entity_enemyComponentPool:MutableList<EnemyComponent> = ArrayList(listOf())

val Entity.enemy:EnemyComponent
    get() = getComponent(Component.Enemy.ordinal) as EnemyComponent

val Entity.hasEnemy:Boolean
    get() = hasComponent(Component.Enemy.ordinal)

fun Entity.clearEnemyComponentPool() {
    Entity_enemyComponentPool.clear()
}

fun Entity.addEnemy(false:undefined):Entity {
    val component = if (Entity_enemyComponentPool.size > 0) Entity_enemyComponentPool.last() else EnemyComponent()
    component.false = false
    addComponent(Component.Enemy.ordinal, component)
    return this
}

fun Entity.replaceEnemy(false:undefined):Entity {
    val previousComponent = if (hasEnemy) enemy else null
    val component = if (Entity_enemyComponentPool.size > 0) Entity_enemyComponentPool.last() else EnemyComponent()
    component.false = false
    replaceComponent(Component.Enemy.ordinal, component)
    if (previousComponent != null)
        Entity_enemyComponentPool.add(previousComponent)
    return this
}

fun Entity.removeEnemy():Entity {
    val component = enemy
    removeComponent(Component.Enemy.ordinal)
    Entity_enemyComponentPool.add(component)
    return this
}

/** Entity: Expires methods*/

val Entity_expiresComponentPool:MutableList<ExpiresComponent> = ArrayList(listOf())

val Entity.expires:ExpiresComponent
    get() = getComponent(Component.Expires.ordinal) as ExpiresComponent

val Entity.hasExpires:Boolean
    get() = hasComponent(Component.Expires.ordinal)

fun Entity.clearExpiresComponentPool() {
    Entity_expiresComponentPool.clear()
}

fun Entity.addExpires(delay:Float):Entity {
    val component = if (Entity_expiresComponentPool.size > 0) Entity_expiresComponentPool.last() else ExpiresComponent()
    component.delay = delay
    addComponent(Component.Expires.ordinal, component)
    return this
}

fun Entity.replaceExpires(delay:Float):Entity {
    val previousComponent = if (hasExpires) expires else null
    val component = if (Entity_expiresComponentPool.size > 0) Entity_expiresComponentPool.last() else ExpiresComponent()
    component.delay = delay
    replaceComponent(Component.Expires.ordinal, component)
    if (previousComponent != null)
        Entity_expiresComponentPool.add(previousComponent)
    return this
}

fun Entity.removeExpires():Entity {
    val component = expires
    removeComponent(Component.Expires.ordinal)
    Entity_expiresComponentPool.add(component)
    return this
}

/** Entity: Firing methods*/

val Entity_firingComponentPool:MutableList<FiringComponent> = ArrayList(listOf())

val Entity.firing:FiringComponent
    get() = getComponent(Component.Firing.ordinal) as FiringComponent

val Entity.hasFiring:Boolean
    get() = hasComponent(Component.Firing.ordinal)

fun Entity.clearFiringComponentPool() {
    Entity_firingComponentPool.clear()
}

fun Entity.addFiring(false:undefined):Entity {
    val component = if (Entity_firingComponentPool.size > 0) Entity_firingComponentPool.last() else FiringComponent()
    component.false = false
    addComponent(Component.Firing.ordinal, component)
    return this
}

fun Entity.replaceFiring(false:undefined):Entity {
    val previousComponent = if (hasFiring) firing else null
    val component = if (Entity_firingComponentPool.size > 0) Entity_firingComponentPool.last() else FiringComponent()
    component.false = false
    replaceComponent(Component.Firing.ordinal, component)
    if (previousComponent != null)
        Entity_firingComponentPool.add(previousComponent)
    return this
}

fun Entity.removeFiring():Entity {
    val component = firing
    removeComponent(Component.Firing.ordinal)
    Entity_firingComponentPool.add(component)
    return this
}

/** Entity: Health methods*/

val Entity_healthComponentPool:MutableList<HealthComponent> = ArrayList(listOf())

val Entity.health:HealthComponent
    get() = getComponent(Component.Health.ordinal) as HealthComponent

val Entity.hasHealth:Boolean
    get() = hasComponent(Component.Health.ordinal)

fun Entity.clearHealthComponentPool() {
    Entity_healthComponentPool.clear()
}

fun Entity.addHealth(currentHealth:Float, maximumHealth:Float):Entity {
    val component = if (Entity_healthComponentPool.size > 0) Entity_healthComponentPool.last() else HealthComponent()
    component.currentHealth = currentHealth
    component.maximumHealth = maximumHealth
    addComponent(Component.Health.ordinal, component)
    return this
}

fun Entity.replaceHealth(currentHealth:Float, maximumHealth:Float):Entity {
    val previousComponent = if (hasHealth) health else null
    val component = if (Entity_healthComponentPool.size > 0) Entity_healthComponentPool.last() else HealthComponent()
    component.currentHealth = currentHealth
    component.maximumHealth = maximumHealth
    replaceComponent(Component.Health.ordinal, component)
    if (previousComponent != null)
        Entity_healthComponentPool.add(previousComponent)
    return this
}

fun Entity.removeHealth():Entity {
    val component = health
    removeComponent(Component.Health.ordinal)
    Entity_healthComponentPool.add(component)
    return this
}

/** Entity: Layer methods*/

val Entity_layerComponentPool:MutableList<LayerComponent> = ArrayList(listOf())

val Entity.layer:LayerComponent
    get() = getComponent(Component.Layer.ordinal) as LayerComponent

val Entity.hasLayer:Boolean
    get() = hasComponent(Component.Layer.ordinal)

fun Entity.clearLayerComponentPool() {
    Entity_layerComponentPool.clear()
}

fun Entity.addLayer(ordinal:com.darkoverlordofdata.entitas.demo.Layer):Entity {
    val component = if (Entity_layerComponentPool.size > 0) Entity_layerComponentPool.last() else LayerComponent()
    component.ordinal = ordinal
    addComponent(Component.Layer.ordinal, component)
    return this
}

fun Entity.replaceLayer(ordinal:com.darkoverlordofdata.entitas.demo.Layer):Entity {
    val previousComponent = if (hasLayer) layer else null
    val component = if (Entity_layerComponentPool.size > 0) Entity_layerComponentPool.last() else LayerComponent()
    component.ordinal = ordinal
    replaceComponent(Component.Layer.ordinal, component)
    if (previousComponent != null)
        Entity_layerComponentPool.add(previousComponent)
    return this
}

fun Entity.removeLayer():Entity {
    val component = layer
    removeComponent(Component.Layer.ordinal)
    Entity_layerComponentPool.add(component)
    return this
}

/** Entity: Player methods*/

val Entity_playerComponentPool:MutableList<PlayerComponent> = ArrayList(listOf())

val Entity.player:PlayerComponent
    get() = getComponent(Component.Player.ordinal) as PlayerComponent

val Entity.hasPlayer:Boolean
    get() = hasComponent(Component.Player.ordinal)

fun Entity.clearPlayerComponentPool() {
    Entity_playerComponentPool.clear()
}

fun Entity.addPlayer(false:undefined):Entity {
    val component = if (Entity_playerComponentPool.size > 0) Entity_playerComponentPool.last() else PlayerComponent()
    component.false = false
    addComponent(Component.Player.ordinal, component)
    return this
}

fun Entity.replacePlayer(false:undefined):Entity {
    val previousComponent = if (hasPlayer) player else null
    val component = if (Entity_playerComponentPool.size > 0) Entity_playerComponentPool.last() else PlayerComponent()
    component.false = false
    replaceComponent(Component.Player.ordinal, component)
    if (previousComponent != null)
        Entity_playerComponentPool.add(previousComponent)
    return this
}

fun Entity.removePlayer():Entity {
    val component = player
    removeComponent(Component.Player.ordinal)
    Entity_playerComponentPool.add(component)
    return this
}

/** Entity: Position methods*/

val Entity_positionComponentPool:MutableList<PositionComponent> = ArrayList(listOf())

val Entity.position:PositionComponent
    get() = getComponent(Component.Position.ordinal) as PositionComponent

val Entity.hasPosition:Boolean
    get() = hasComponent(Component.Position.ordinal)

fun Entity.clearPositionComponentPool() {
    Entity_positionComponentPool.clear()
}

fun Entity.addPosition(x:Float, y:Float):Entity {
    val component = if (Entity_positionComponentPool.size > 0) Entity_positionComponentPool.last() else PositionComponent()
    component.x = x
    component.y = y
    addComponent(Component.Position.ordinal, component)
    return this
}

fun Entity.replacePosition(x:Float, y:Float):Entity {
    val previousComponent = if (hasPosition) position else null
    val component = if (Entity_positionComponentPool.size > 0) Entity_positionComponentPool.last() else PositionComponent()
    component.x = x
    component.y = y
    replaceComponent(Component.Position.ordinal, component)
    if (previousComponent != null)
        Entity_positionComponentPool.add(previousComponent)
    return this
}

fun Entity.removePosition():Entity {
    val component = position
    removeComponent(Component.Position.ordinal)
    Entity_positionComponentPool.add(component)
    return this
}

/** Entity: Resource methods*/

val Entity_resourceComponentPool:MutableList<ResourceComponent> = ArrayList(listOf())

val Entity.resource:ResourceComponent
    get() = getComponent(Component.Resource.ordinal) as ResourceComponent

val Entity.hasResource:Boolean
    get() = hasComponent(Component.Resource.ordinal)

fun Entity.clearResourceComponentPool() {
    Entity_resourceComponentPool.clear()
}

fun Entity.addResource(name:String):Entity {
    val component = if (Entity_resourceComponentPool.size > 0) Entity_resourceComponentPool.last() else ResourceComponent()
    component.name = name
    addComponent(Component.Resource.ordinal, component)
    return this
}

fun Entity.replaceResource(name:String):Entity {
    val previousComponent = if (hasResource) resource else null
    val component = if (Entity_resourceComponentPool.size > 0) Entity_resourceComponentPool.last() else ResourceComponent()
    component.name = name
    replaceComponent(Component.Resource.ordinal, component)
    if (previousComponent != null)
        Entity_resourceComponentPool.add(previousComponent)
    return this
}

fun Entity.removeResource():Entity {
    val component = resource
    removeComponent(Component.Resource.ordinal)
    Entity_resourceComponentPool.add(component)
    return this
}

/** Entity: Scale methods*/

val Entity_scaleComponentPool:MutableList<ScaleComponent> = ArrayList(listOf())

val Entity.scale:ScaleComponent
    get() = getComponent(Component.Scale.ordinal) as ScaleComponent

val Entity.hasScale:Boolean
    get() = hasComponent(Component.Scale.ordinal)

fun Entity.clearScaleComponentPool() {
    Entity_scaleComponentPool.clear()
}

fun Entity.addScale(x:Float, y:Float):Entity {
    val component = if (Entity_scaleComponentPool.size > 0) Entity_scaleComponentPool.last() else ScaleComponent()
    component.x = x
    component.y = y
    addComponent(Component.Scale.ordinal, component)
    return this
}

fun Entity.replaceScale(x:Float, y:Float):Entity {
    val previousComponent = if (hasScale) scale else null
    val component = if (Entity_scaleComponentPool.size > 0) Entity_scaleComponentPool.last() else ScaleComponent()
    component.x = x
    component.y = y
    replaceComponent(Component.Scale.ordinal, component)
    if (previousComponent != null)
        Entity_scaleComponentPool.add(previousComponent)
    return this
}

fun Entity.removeScale():Entity {
    val component = scale
    removeComponent(Component.Scale.ordinal)
    Entity_scaleComponentPool.add(component)
    return this
}

/** Entity: Score methods*/

val Entity_scoreComponentPool:MutableList<ScoreComponent> = ArrayList(listOf())

val Entity.score:ScoreComponent
    get() = getComponent(Component.Score.ordinal) as ScoreComponent

val Entity.hasScore:Boolean
    get() = hasComponent(Component.Score.ordinal)

fun Entity.clearScoreComponentPool() {
    Entity_scoreComponentPool.clear()
}

fun Entity.addScore(value:Int):Entity {
    val component = if (Entity_scoreComponentPool.size > 0) Entity_scoreComponentPool.last() else ScoreComponent()
    component.value = value
    addComponent(Component.Score.ordinal, component)
    return this
}

fun Entity.replaceScore(value:Int):Entity {
    val previousComponent = if (hasScore) score else null
    val component = if (Entity_scoreComponentPool.size > 0) Entity_scoreComponentPool.last() else ScoreComponent()
    component.value = value
    replaceComponent(Component.Score.ordinal, component)
    if (previousComponent != null)
        Entity_scoreComponentPool.add(previousComponent)
    return this
}

fun Entity.removeScore():Entity {
    val component = score
    removeComponent(Component.Score.ordinal)
    Entity_scoreComponentPool.add(component)
    return this
}

/** Entity: SoundEffect methods*/

val Entity_soundEffectComponentPool:MutableList<SoundEffectComponent> = ArrayList(listOf())

val Entity.soundEffect:SoundEffectComponent
    get() = getComponent(Component.SoundEffect.ordinal) as SoundEffectComponent

val Entity.hasSoundEffect:Boolean
    get() = hasComponent(Component.SoundEffect.ordinal)

fun Entity.clearSoundEffectComponentPool() {
    Entity_soundEffectComponentPool.clear()
}

fun Entity.addSoundEffect(effect:com.darkoverlordofdata.entitas.demo.Effect):Entity {
    val component = if (Entity_soundEffectComponentPool.size > 0) Entity_soundEffectComponentPool.last() else SoundEffectComponent()
    component.effect = effect
    addComponent(Component.SoundEffect.ordinal, component)
    return this
}

fun Entity.replaceSoundEffect(effect:com.darkoverlordofdata.entitas.demo.Effect):Entity {
    val previousComponent = if (hasSoundEffect) soundEffect else null
    val component = if (Entity_soundEffectComponentPool.size > 0) Entity_soundEffectComponentPool.last() else SoundEffectComponent()
    component.effect = effect
    replaceComponent(Component.SoundEffect.ordinal, component)
    if (previousComponent != null)
        Entity_soundEffectComponentPool.add(previousComponent)
    return this
}

fun Entity.removeSoundEffect():Entity {
    val component = soundEffect
    removeComponent(Component.SoundEffect.ordinal)
    Entity_soundEffectComponentPool.add(component)
    return this
}

/** Entity: Tint methods*/

val Entity_tintComponentPool:MutableList<TintComponent> = ArrayList(listOf())

val Entity.tint:TintComponent
    get() = getComponent(Component.Tint.ordinal) as TintComponent

val Entity.hasTint:Boolean
    get() = hasComponent(Component.Tint.ordinal)

fun Entity.clearTintComponentPool() {
    Entity_tintComponentPool.clear()
}

fun Entity.addTint(r:Float, g:Float, b:Float, a:Float):Entity {
    val component = if (Entity_tintComponentPool.size > 0) Entity_tintComponentPool.last() else TintComponent()
    component.r = r
    component.g = g
    component.b = b
    component.a = a
    addComponent(Component.Tint.ordinal, component)
    return this
}

fun Entity.replaceTint(r:Float, g:Float, b:Float, a:Float):Entity {
    val previousComponent = if (hasTint) tint else null
    val component = if (Entity_tintComponentPool.size > 0) Entity_tintComponentPool.last() else TintComponent()
    component.r = r
    component.g = g
    component.b = b
    component.a = a
    replaceComponent(Component.Tint.ordinal, component)
    if (previousComponent != null)
        Entity_tintComponentPool.add(previousComponent)
    return this
}

fun Entity.removeTint():Entity {
    val component = tint
    removeComponent(Component.Tint.ordinal)
    Entity_tintComponentPool.add(component)
    return this
}

/** Entity: Tween methods*/

val Entity_tweenComponentPool:MutableList<TweenComponent> = ArrayList(listOf())

val Entity.tween:TweenComponent
    get() = getComponent(Component.Tween.ordinal) as TweenComponent

val Entity.hasTween:Boolean
    get() = hasComponent(Component.Tween.ordinal)

fun Entity.clearTweenComponentPool() {
    Entity_tweenComponentPool.clear()
}

fun Entity.addTween(min:Float, max:Float, speed:Float, repeat:Boolean, active:Boolean):Entity {
    val component = if (Entity_tweenComponentPool.size > 0) Entity_tweenComponentPool.last() else TweenComponent()
    component.min = min
    component.max = max
    component.speed = speed
    component.repeat = repeat
    component.active = active
    addComponent(Component.Tween.ordinal, component)
    return this
}

fun Entity.replaceTween(min:Float, max:Float, speed:Float, repeat:Boolean, active:Boolean):Entity {
    val previousComponent = if (hasTween) tween else null
    val component = if (Entity_tweenComponentPool.size > 0) Entity_tweenComponentPool.last() else TweenComponent()
    component.min = min
    component.max = max
    component.speed = speed
    component.repeat = repeat
    component.active = active
    replaceComponent(Component.Tween.ordinal, component)
    if (previousComponent != null)
        Entity_tweenComponentPool.add(previousComponent)
    return this
}

fun Entity.removeTween():Entity {
    val component = tween
    removeComponent(Component.Tween.ordinal)
    Entity_tweenComponentPool.add(component)
    return this
}

/** Entity: Velocity methods*/

val Entity_velocityComponentPool:MutableList<VelocityComponent> = ArrayList(listOf())

val Entity.velocity:VelocityComponent
    get() = getComponent(Component.Velocity.ordinal) as VelocityComponent

val Entity.hasVelocity:Boolean
    get() = hasComponent(Component.Velocity.ordinal)

fun Entity.clearVelocityComponentPool() {
    Entity_velocityComponentPool.clear()
}

fun Entity.addVelocity(x:Float, y:Float):Entity {
    val component = if (Entity_velocityComponentPool.size > 0) Entity_velocityComponentPool.last() else VelocityComponent()
    component.x = x
    component.y = y
    addComponent(Component.Velocity.ordinal, component)
    return this
}

fun Entity.replaceVelocity(x:Float, y:Float):Entity {
    val previousComponent = if (hasVelocity) velocity else null
    val component = if (Entity_velocityComponentPool.size > 0) Entity_velocityComponentPool.last() else VelocityComponent()
    component.x = x
    component.y = y
    replaceComponent(Component.Velocity.ordinal, component)
    if (previousComponent != null)
        Entity_velocityComponentPool.add(previousComponent)
    return this
}

fun Entity.removeVelocity():Entity {
    val component = velocity
    removeComponent(Component.Velocity.ordinal)
    Entity_velocityComponentPool.add(component)
    return this
}

/** Entity: View methods*/

val Entity_viewComponentPool:MutableList<ViewComponent> = ArrayList(listOf())

val Entity.view:ViewComponent
    get() = getComponent(Component.View.ordinal) as ViewComponent

val Entity.hasView:Boolean
    get() = hasComponent(Component.View.ordinal)

fun Entity.clearViewComponentPool() {
    Entity_viewComponentPool.clear()
}

fun Entity.addView(sprite:com.badlogic.gdx.graphics.g2d.Sprite):Entity {
    val component = if (Entity_viewComponentPool.size > 0) Entity_viewComponentPool.last() else ViewComponent()
    component.sprite = sprite
    addComponent(Component.View.ordinal, component)
    return this
}

fun Entity.replaceView(sprite:com.badlogic.gdx.graphics.g2d.Sprite):Entity {
    val previousComponent = if (hasView) view else null
    val component = if (Entity_viewComponentPool.size > 0) Entity_viewComponentPool.last() else ViewComponent()
    component.sprite = sprite
    replaceComponent(Component.View.ordinal, component)
    if (previousComponent != null)
        Entity_viewComponentPool.add(previousComponent)
    return this
}

fun Entity.removeView():Entity {
    val component = view
    removeComponent(Component.View.ordinal)
    Entity_viewComponentPool.add(component)
    return this
}
