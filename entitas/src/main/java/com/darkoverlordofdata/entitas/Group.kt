package com.darkoverlordofdata.entitas
import java.util.*
/**
 *
 * Use pool.GetGroup(matcher) to get a group of _entities which match the specified matcher.
 * Calling pool.GetGroup(matcher) with the same matcher will always return the same instance of the group.
 * The created group is managed by the pool and will always be up to date.
 * It will automatically add _entities that match the matcher or remove _entities as soon as they don't match the matcher anymore.
 */
class Group(matcher: IMatcher) {

    val count:Int get() = _entities.size
    val onEntityAdded = Event<GroupChangedArgs>()
    val onEntityRemoved = Event<GroupChangedArgs>()
    val onEntityUpdated = Event<GroupUpdatedArgs>()
    internal val matcher = matcher
    private var _entities: HashSet<Entity> = hashSetOf()
    private var _toStringCache = ""
    private var _entitiesCache: Array<Entity> = arrayOf()
    private var _singleEntityCache: Entity? = null

    /**
     * Custom Sort for Groups
     *
     *  group.setSort({entities: Array<Entity> ->
     *      entities.sortBy { e:Entity -> e.layer.ordinal }
     *  })
     *
     */
    private var _sortEntities: ((entities: Array<Entity>) -> Unit) = {}

    val entities:Array<Entity>
        get() {
            if (_entitiesCache.size == 0) {
                _entitiesCache = _entities.toTypedArray()
                _sortEntities(_entitiesCache)
            }
            return _entitiesCache
        }

    val singleEntity: Entity?
        get() = _entities.singleOrNull()

    fun createObserver(eventType: GroupEventType): GroupObserver {
        return GroupObserver(arrayOf(this), arrayOf(eventType))
    }


    fun setSort(sorter: (entities: Array<Entity>)-> Unit) {
        _sortEntities = sorter
    }


    fun handleEntitySilently(entity: Entity) {
        if (matcher.matches(entity))
            addEntitySilently(entity)
        else
            removeEntitySilently(entity)
    }

    fun handleEntity(entity: Entity, index:Int, component: IComponent) {
        if (matcher.matches(entity))
            addEntity(entity, index, component)
        else
            removeEntity(entity, index, component)
    }


    fun updateEntity(entity: Entity, index:Int, previousComponent: IComponent, newComponent: IComponent?) {
        if (entity in _entities) {
            onEntityRemoved(GroupChangedArgs(this, entity, index, previousComponent))
            onEntityAdded(GroupChangedArgs(this, entity, index, newComponent))
            onEntityUpdated(GroupUpdatedArgs(this, entity, index, previousComponent, newComponent))
        }
    }

    fun addEntitySilently(entity: Entity) {
        if (entity !in _entities) {
            _entities.add(entity)
            _entitiesCache = arrayOf()
            _toStringCache = ""
            entity.retain()
        }
    }

    fun addEntity(entity: Entity, index:Int, component: IComponent) {
        if (entity !in _entities) {
            _entities.add(entity)
            _entitiesCache = arrayOf()
            _toStringCache = ""
            entity.retain()
            onEntityAdded(GroupChangedArgs(this, entity, index, component))
        }
    }

    fun removeEntitySilently(entity: Entity) {
        if (entity in _entities) {
            _entities.remove(entity)
            _entitiesCache = arrayOf()
            _singleEntityCache = null
            entity.release()
        }
    }

    fun removeEntity(entity: Entity, index:Int, component: IComponent) {
        if (entity in _entities) {
            _entities.remove(entity)
            _entitiesCache = arrayOf()
            _singleEntityCache = null
            onEntityRemoved(GroupChangedArgs(this, entity, index, component))
            entity.release()
        }
    }

    fun containsEntity(entity: Entity):Boolean {
        return entity in _entities
    }


    override fun toString():String {
        if (_toStringCache == "") {
            _toStringCache = "Group(${matcher.toString()})"
        }
        return _toStringCache
    }
}