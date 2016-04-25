package com.darkoverlordofdata.entitas.ecs

import java.util.*

class Entity (totalComponents:Int) {

    val onEntityReleased = Event<EntityReleasedArgs>()
    val onComponentAdded = Event<EntityChangedArgs>()
    val onComponentRemoved = Event<EntityChangedArgs>()
    val onComponentReplaced = Event<ComponentReplacedArgs>()

    val totalComponents = totalComponents
    val components: Array<IComponent?> = Array(totalComponents, { i-> null })
    val componentsCache: MutableList<IComponent> = ArrayList(listOf())
    var toStringCache = ""
    var refCount = 0
    var id = ""
    var name = ""
    var isEnabled = false
    var _creationIndex = 0

    val creationIndex:Int get() = _creationIndex

    fun addComponent(index:Int, component:IComponent):Entity {
        if (!isEnabled)
            throw EntityIsNotEnabledException("Cannot add component!")
        if (hasComponent(index)) {
            val errorMsg = "Cannot add component at index $index to ${toString()}"
            throw EntityAlreadyHasComponentException(errorMsg, index)
        }
        components[index] = component
        componentsCache.clear()
        toStringCache = ""
        onComponentAdded(EntityChangedArgs(this, index, component))
        return this
    }
    fun removeComponent(index:Int):Entity {
        if (!isEnabled)
            throw EntityIsNotEnabledException("Entity is disabled, cannot remove component")
        if (!hasComponent(index)) {
            val errorMsg = "Cannot remove component at index $index from ${toString()}"
            throw EntityDoesNotHaveComponentException(errorMsg, index)
        }
        _replaceComponent(index, null)
        return this
    }

    fun replaceComponent(index:Int, component: IComponent?):Entity {
        if (!isEnabled)
            throw EntityIsNotEnabledException("Entity is disabled, cannot replace at index $index, ${toString()}")

        if (hasComponent(index)) {
            _replaceComponent(index, component)
        } else if (component != null) {
            addComponent(index, component)
        }
        return this
    }

    fun getComponent(index:Int):IComponent? {
        if (!hasComponent(index)) {
            val errorMsg = "Cannot get component at index $index from ${toString()}"
            throw EntityDoesNotHaveComponentException(errorMsg, index)
        }

        return components[index]
    }

    fun getComponents():MutableList<IComponent> {
        if (componentsCache.size == 0) {
            componentsCache.addAll(components.filterNotNull().toMutableList())
        }
        return componentsCache
    }

    fun hasComponent(index:Int):Boolean {
        return components[index] != null

    }

    fun hasComponents(indices:IntArray):Boolean {
        for (index in indices)
            if (components[index] == null) return false
        return true
    }

    fun hasAnyComponent(indices:IntArray):Boolean {
        for (index in indices)
            if (components[index] != null) return true
        return false
    }

    fun removeAllComponents() {
        for (index in 0..totalComponents-1)
            if (components[index] != null)
                _replaceComponent(index, null)
    }

    fun retain():Entity {
        refCount += 1
        return this
    }

    fun release() {
        refCount -= 1
        if (refCount == 0) {
            onEntityReleased(EntityReleasedArgs(this))
        } else if (refCount < 0)
            throw Exception("Entity is already released ${toString()}")
    }

    override fun toString():String {
        if (this.toStringCache == "") {
            val sb = StringBuilder()
            sb.append("Entity_")
            sb.append(name)
            sb.append("(")
            sb.append(id.toString())
            sb.append(")(")
            for (i in 0..totalComponents-1) {
                sb.append(components[i]?.javaClass?.typeName)
            }
            sb.append(")")
            toStringCache = sb.toString()
        }
        return toStringCache
    }

    fun destroy() {
        removeAllComponents()
        componentsCache.clear()
        name = ""
        isEnabled = false
    }


    private fun _replaceComponent(index:Int, replacement: IComponent?):Entity {
        val previousComponent = components[index]
        if (previousComponent != null) {
            if (previousComponent.equals(replacement)) {
                onComponentReplaced(ComponentReplacedArgs(this, index, previousComponent, replacement))
            } else {
                components[index] = replacement
                componentsCache.clear()
                toStringCache = ""
                if (replacement == null)
                    onComponentRemoved(EntityChangedArgs(this, index, previousComponent))
                else
                    onComponentReplaced(ComponentReplacedArgs(this, index, previousComponent, replacement))
            }
        }

        return this
    }
}