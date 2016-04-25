package com.darkoverlordofdata.entitas.ecs
import java.util.*
/**
 *
 * A GroupObserver can observe one or more groups and collects changed entities based on the specified eventType.
 */
class GroupObserver(groups:Array<Group>, eventTypes:Array<GroupEventType>) {

    private var _collectedEntities:HashSet<Entity> = hashSetOf()
    private val groups = groups
    private val eventTypes = eventTypes

    val collectedEntities:HashSet<Entity> get() = _collectedEntities

    init {
        if (groups.size != eventTypes.size) {
            throw GroupObserverException("Unbalanced count with groups (${groups.size}) and event types (${eventTypes.size})")
        }
        activate()
    }

    val addEntity = {e:GroupChangedArgs ->
        if (e.entity !in _collectedEntities) {
            _collectedEntities.add(e.entity)
            e.entity.retain()
        }
    }

    /**
     *
     * Activates the GroupObserver (GroupObserver are activated by default) and will start collecting changed entities.
     */
    fun activate() {
        for (i in 0..groups.size-1) {
            val group = groups[i]
            val eventType = eventTypes[i]
            when (eventType) {
                GroupEventType.OnEntityAdded -> {
                    group.onEntityAdded -= addEntity
                    group.onEntityAdded += addEntity
                }
                GroupEventType.OnEntityRemoved -> {
                    group.onEntityRemoved -= addEntity
                    group.onEntityRemoved += addEntity
                }
                GroupEventType.OnEntityAddedOrRemoved -> {
                    group.onEntityAdded -= addEntity
                    group.onEntityAdded += addEntity
                    group.onEntityRemoved -= addEntity
                    group.onEntityRemoved += addEntity
                }
                else -> throw Exception("Invalid eventType $eventType in GroupObserver::activate")
            }

        }
    }

    /**
     *
     * Deactivates the GroupObserver (GroupObserver are activated by default).
     * This will also clear all collected entities.
     */
    fun deactivate() {
        for (group in groups) {
            group.onEntityAdded -= addEntity
            group.onEntityRemoved -= addEntity
        }
        clearCollectedEntities()
    }

    /**
     *
     * Clears all collected entities.
     */
    fun clearCollectedEntities() {
        for (entity:Entity in _collectedEntities)
            entity.release()
        _collectedEntities = hashSetOf()
    }


}