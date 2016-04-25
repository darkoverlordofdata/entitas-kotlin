package com.darkoverlordofdata.entitas.ecs

import java.util.*


class Group (matcher:IMatcher) {
    val onEntityAdded = Event<GroupChangedArgs>()
    val onEntityRemoved = Event<GroupChangedArgs>()
    val onEntityUpdated = Event<GroupUpdatedArgs>()

    var entities:HashSet<Entity> = hashSetOf()
    val count:Int get() = entities.size
    val matcher = matcher
    var singleEntityCache:Entity? = null
    var entitiesCache: Array<Entity>? = null
    var toStringCache = ""

    fun createObserver(eventType:GroupEventType):GroupObserver {
        return GroupObserver(arrayOf(this), arrayOf(eventType))
    }

    fun handleEntitySilently(entity:Entity) {
        if (matcher.matches(entity))
            addEntitySilently(entity)
        else
            removeEntitySilently(entity)
    }

    fun handleEntity(entity:Entity, index:Int, component:IComponent) {
        if (matcher.matches(entity))
            addEntity(entity, index, component)
        else
            removeEntity(entity, index, component)
    }


    fun updateEntity(entity:Entity, index:Int, previousComponent:IComponent, newComponent:IComponent?) {
        if (entity in entities) {
            onEntityRemoved(GroupChangedArgs(this, entity, index, previousComponent))
            onEntityAdded(GroupChangedArgs(this, entity, index, newComponent))
            onEntityUpdated(GroupUpdatedArgs(this, entity, index, previousComponent, newComponent))
        }
    }

    fun addEntitySilently(entity:Entity) {
        if (entity !in entities) {
            entities.add(entity)
            entitiesCache = null
            toStringCache = ""
            entity.retain()
        }
    }

    fun addEntity(entity:Entity, index:Int, component:IComponent) {
        if (entity !in entities) {
            entities.add(entity)
            entitiesCache = null
            toStringCache = ""
            entity.retain()
            onEntityAdded(GroupChangedArgs(this, entity, index, component))
        }
    }

    fun removeEntitySilently(entity:Entity) {
        if (entity in entities) {
            entities.remove(entity)
            entitiesCache = null
            singleEntityCache = null
            entity.release()
        }
    }

    fun removeEntity(entity:Entity, index:Int, component:IComponent) {
        if (entity in entities) {
            entities.remove(entity)
            entitiesCache = null
            singleEntityCache = null
            onEntityRemoved(GroupChangedArgs(this, entity, index, component))
            entity.release()
        }
    }

    fun containsEntity(entity:Entity):Boolean {
        return entity in entities
    }

    fun getEntities():Array<Entity>? {
        if (entitiesCache == null) {
            entitiesCache = entities.toTypedArray()
        }
        return entitiesCache
    }

    fun getSingleEntity():Entity? {
        if (singleEntityCache == null) {
            when (entities.size) {
                0 -> {
                    singleEntityCache = null
                }
                1 -> {
                    singleEntityCache = entities.toTypedArray()[0]
                }
                else -> throw SingleEntityException(matcher)
            }
        }
        return singleEntityCache
    }

    override fun toString():String {
        if (toStringCache == "") {
            toStringCache = "Group(${matcher.toString()})"
        }
        return toStringCache
    }
}