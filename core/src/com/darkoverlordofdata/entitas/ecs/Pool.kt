package com.darkoverlordofdata.entitas.ecs
import com.badlogic.ashley.utils.Bag
import java.util.*
/**
 *
 * A pool manages the lifecycle of entities and groups.
 * You can create and destroy entities and get groups of entities.
 * The prefered way is to use the generated methods from the code generator to create a Pool, e.g. var pool = Pools.pool;
 */
class Pool(totalComponents:Int, startCreationIndex:Int=0) {


    val totalComponents = totalComponents
    val startCreationIndex = startCreationIndex
    val count:Int get() = _entities.size
    val reusableEntitiesCount:Int get() = _reusableEntities.size()
    val retainedEntitiesCount:Int get() = _retainedEntities.size

    internal val onEntityCreated = Event<PoolEntityChangedArgs>()
    internal val onEntityWillBeDestroyed = Event<PoolEntityChangedArgs>()
    internal val onEntityDestroyed = Event<PoolEntityChangedArgs>()
    internal val onGroupCreated = Event<PoolGroupChangedArgs>()
    internal var _creationIndex:Int = startCreationIndex
    internal val _entities: HashSet<Entity> = hashSetOf()
    internal val _groups: HashMap<IMatcher,Group> = hashMapOf()
    internal val _groupsForIndex: Bag<Bag<Group>> = Bag()
    internal val _reusableEntities: Bag<Entity> = Bag()
    internal val _retainedEntities: HashSet<Entity> = hashSetOf()
    internal val _entitiesCache: Bag<Entity> = Bag()
    internal lateinit var onEntityReleasedCache : (e:EntityReleasedArgs) -> Unit

    /**
     * Event onEntityReleased
     */
    val onEntityReleased = {e:EntityReleasedArgs ->
        if (e.entity.isEnabled)
            throw EntityIsNotDestroyedException("Cannot release entity.")

        e.entity.onEntityReleased -= onEntityReleasedCache
        _retainedEntities.remove(e.entity)
        _reusableEntities.add(e.entity)

    }

    /**
     * Event updateGroupsComponentAddedOrRemoved
     */
    val updateGroupsComponentAddedOrRemoved = {e:EntityChangedArgs ->
        if (_groupsForIndex[e.index] != null) {
            for (i in 0.._groupsForIndex[e.index].size()) {
                val group = _groupsForIndex[e.index][i]
                if (e.component != null)
                    group!!.handleEntity(e.entity, e.index, e.component)
            }
        }
    }

    /**
     * Event updateGroupsComponentReplaced
     */
    val updateGroupsComponentReplaced = {e:ComponentReplacedArgs ->
        if (_groupsForIndex[e.index] != null) {
            for (i in 0.._groupsForIndex[e.index].size()) {
                val group = _groupsForIndex[e.index][i]
                group.updateEntity(e.entity, e.index, e.previous, e.replacement)
            }
        }
    }


    init {
        onEntityReleasedCache = onEntityReleased
        Pool._instance = this
    }

    /**
     *
     * Creates a new entity or gets a reusable entity from the internal ObjectPool for entities.
     */
    fun createEntity(name: String): Entity {
        val entity = if (_reusableEntities.size() > 0) _reusableEntities.removeLast() else Entity(totalComponents)
        entity.isEnabled = true
        entity.name = name
        entity._creationIndex = _creationIndex++
        entity.id = UUID.randomUUID().toString()
        entity.retain()
        _entities.add(entity)
        _entitiesCache.clear()
        onEntityCreated(PoolEntityChangedArgs(this, entity))
        return entity
    }

    /**
     *
     * Destroys the entity, removes all its components and pushs it back to the internal ObjectPool for entities.
     */
    fun destroyEntity(entity: Entity?) {
        if (entity == null) return
        if (entity !in _entities) {
            throw PoolDoesNotContainEntityException(entity, "Could not destroy entity!")
        }
        _entities.remove(entity)
        _entitiesCache.clear()
        onEntityWillBeDestroyed(PoolEntityChangedArgs(this, entity))
        entity.destroy()
        onEntityDestroyed(PoolEntityChangedArgs(this, entity))

        if (entity.refCount == 1) {
            entity.onEntityReleased -= onEntityReleased
            _reusableEntities.add(entity)
        } else {
            _retainedEntities.add(entity)
        }
    }


    /**
     *
     * Destroys all entities in the pool.
     */
    fun destroyAllEntities() {
        val entities = getEntities()
        for (i in 0..entities.size())
            destroyEntity(entities.get(i))
    }

    /**
     *
     * Determines whether the pool has the specified entity.
     */
    fun hasEntity(entity: Entity): Boolean {
        return entity in _entities
    }

    fun getEntities(): Bag<Entity> {
        if (_entitiesCache.size() == 0) {
            for (entity in _entities)
                _entitiesCache.add(entity)
        }
        return _entitiesCache
    }

    fun getEntities(matcher:IMatcher): Array<Entity>? {
        return getGroup(matcher)!!.getEntities()
    }

    fun createSystem(system:ISystem):ISystem {
        Pool.setPool(system, this)
        if (system is IReactiveSystem) {
            return ReactiveSystem(this, system)
        }
        if (system is IMultiReactiveSystem) {
            return ReactiveSystem(this, system)
        }
        return system
    }

    /**
     *
     * Returns a group for the specified matcher.
     * Calling pool.GetGroup(matcher) with the same matcher will always return the same instance of the group.
     */
    fun getGroup(matcher: IMatcher):Group? {
        var group:Group? = null

        if (matcher in _groups) {
            group = _groups[matcher]
        } else {
            group = Group(matcher)
            val entities = getEntities()
            for (i in 0..entities.size()-1)
                group.handleEntitySilently(entities.get(i))
            _groups[matcher] = group
            for (index in matcher.indices) {
                if (_groupsForIndex[index] == null)
                    _groupsForIndex[index] = Bag()
                _groupsForIndex[index].add(group)
            }
            onGroupCreated(PoolGroupChangedArgs(this, group))
        }

        return group
    }

    companion object static {
        private var _instance:Pool? = null
        val instance :Pool? get() = Pool._instance
        fun setPool(system:ISystem, pool:Pool) {
            if (system is ISetPool) {
                system.setPool(pool)
            }
        }

    }

}