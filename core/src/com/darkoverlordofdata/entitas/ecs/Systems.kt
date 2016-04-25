package com.darkoverlordofdata.entitas.ecs

import java.util.*

class Systems : IInitializeSystem, IExecuteSystem {


    var _initializeSystems:MutableList<IInitializeSystem> = ArrayList(listOf())
    var _executeSystems:MutableList<IExecuteSystem> = ArrayList(listOf())
    var executeSystems:Array<IExecuteSystem> = arrayOf()

    fun add(system:ISystem):Systems {
        val reactiveSystem = if (system is ReactiveSystem) system else null

        val initializeSystem = if (reactiveSystem != null) {
            if (reactiveSystem.subsystem is IInitializeSystem)
                reactiveSystem.subsystem as IInitializeSystem
            else null
        } else {
            if (system is IInitializeSystem) system else null
        }

        if (initializeSystem != null)
            _initializeSystems.add(initializeSystem)

        val executeSystem = if (system is IExecuteSystem) system else null
        if (executeSystem != null) {
            _executeSystems.add(executeSystem)
        }
        return this
    }



    override fun initialize() {
        _initializeSystems.map { initialize() }
        executeSystems = _executeSystems.toTypedArray()
    }

    override fun execute() {
        executeSystems.map { execute() }
    }

    fun clearReactiveSystems() {
        for (system in _executeSystems) {
            if (system is ReactiveSystem) system.clear()
            if (system is Systems) system.clearReactiveSystems()
        }

    }

}