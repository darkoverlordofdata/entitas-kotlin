package com.darkoverlordofdata.entitas
import java.util.*
/**
 *
 * A ReactiveSystem manages your implementation of a IReactiveSystem or a IMultiReactiveSystem subsystem.
 * It will only call subsystem.Execute() if there were changes based on the triggers and eventTypes specified by your subsystem
 * and will only pass in changed entities. A common use-case is to react to changes,
 * e.g. a change of the position of an entity to update the gameObject.transform.position of the related gameObject.
 * Recommended way to create systems in general: pool.CreateSystem<RenderPositionSystem>();
 */
class ReactiveSystem(pool: Pool, subsystem: IReactiveExecuteSystem) : IExecuteSystem {

    private val pool = pool
    private val _subsystem = subsystem
    private var _clearAfterExecute:Boolean = false
    private var _buffer:MutableList<Entity> = ArrayList(listOf())
    private var _ensureComponents: IMatcher? = null
    private var _excludeComponents: IMatcher? = null
    private lateinit var _observer: GroupObserver

    val subsystem: IReactiveExecuteSystem
        get() = _subsystem

    init {
        val triggers = if (subsystem is IReactiveSystem) arrayOf(subsystem.trigger)
                        else if (subsystem is IMultiReactiveSystem) subsystem.triggers
                        else arrayOf()

        if (subsystem is IEnsureComponents) _ensureComponents = subsystem.ensureComponents
        if (subsystem is IExcludeComponents) _excludeComponents = subsystem.excludeComponents
        if (subsystem is IClearReactiveSystem) _clearAfterExecute = true

        val groups:MutableList<Group> = ArrayList(listOf())
        val eventTypes:MutableList<GroupEventType> = ArrayList(listOf())
        for (trigger in triggers) {
            val group = pool.getGroup(trigger.trigger)
            if (group != null) {
                groups.add(group)
                eventTypes.add(trigger.eventType)
            }

        }
        _observer = GroupObserver(groups.toTypedArray(), eventTypes.toTypedArray())


    }

    /**
     *
     * Activates the ReactiveSystem (ReactiveSystem are activated by default) and starts observing changes
     * based on the triggers and eventTypes specified by the subsystem.
     */
    fun activate() {
        _observer.activate()
    }

    /**
     *
     * Deactivates the ReactiveSystem (ReactiveSystem are activated by default).
     * No changes will be tracked while deactivated.
     * This will also clear the ReactiveSystems.
     */
    fun deactivate() {
        _observer.deactivate()
    }

    /**
     *
     * Clears all accumulated changes.
     */
    fun clear() {
        _observer.clearCollectedEntities()
    }

    /**
     *
     * Will call subsystem.Execute() with changed entities if there are any. Otherwise it will not call subsystem.Execute().
     */
    override fun execute() {
        val collectedEntities = _observer.collectedEntities
        if (collectedEntities.size != 0) {
            if (_ensureComponents != null) {
                if (_excludeComponents != null) {
                    for (e in collectedEntities) {
                        if (_ensureComponents!!.matches(e)
                        && !_excludeComponents!!.matches(e))
                            _buffer.add(e.retain())
                    }
                } else {
                    for (e in collectedEntities) {
                        if (_ensureComponents!!.matches(e))
                            _buffer.add(e.retain())
                    }
                }
            } else if (_excludeComponents != null) {
                for (e in collectedEntities) {
                    if (_excludeComponents!!.matches(e))
                        _buffer.add(e.retain())
                }
            } else {
                for (e in collectedEntities) {
                    _buffer.add(e.retain())
                }
            }
        }
        _observer.clearCollectedEntities()
        if (_buffer.size != 0) {
            _subsystem.execute(_buffer.toTypedArray())
            for (buf in _buffer) buf.release()
            _buffer.clear()
            if (_clearAfterExecute) _observer.clearCollectedEntities()
        }
    }
}