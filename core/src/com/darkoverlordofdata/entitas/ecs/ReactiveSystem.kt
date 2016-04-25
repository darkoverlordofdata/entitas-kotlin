package com.darkoverlordofdata.entitas.ecs

import java.util.*


class ReactiveSystem(pool:Pool, subsystem: IReactiveExecuteSystem) : IExecuteSystem {

    val pool = pool
    val _subsystem = subsystem
    var _clearAfterExecute:Boolean = false
    var _buffer:MutableList<Entity> = ArrayList(listOf())
    var _ensureComponents:IMatcher? = null
    var _excludeComponents:IMatcher? = null
    lateinit var _observer:GroupObserver


    val subsystem:IReactiveExecuteSystem
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

    fun activate() {
        _observer.activate()
    }

    fun deactivate() {
        _observer.deactivate()
    }

    fun clear() {
        _observer.clearCollectedEntities()
    }

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