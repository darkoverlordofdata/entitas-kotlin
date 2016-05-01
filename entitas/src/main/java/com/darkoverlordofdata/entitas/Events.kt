package com.darkoverlordofdata.entitas

enum class GroupEventType {
    OnEntityAdded,
    OnEntityRemoved,
    OnEntityAddedOrRemoved
}

class EntityReleasedArgs(entity: Entity) : EventArgs {
    val entity = entity
}

class EntityChangedArgs(entity: Entity, index:Int, component: IComponent?) : EventArgs {
    val entity = entity
    val index = index
    val component = component
}

class ComponentReplacedArgs(entity: Entity, index:Int, previous: IComponent, replacement: IComponent?) : EventArgs {
    val entity = entity
    val index = index
    val previous = previous
    val replacement = replacement
}

class GroupChangedArgs(group: Group, entity: Entity, index:Int, newComponent: IComponent?) : EventArgs {
    val group = group
    val entity = entity
    val index = index
    val newComponent = newComponent
}

class GroupUpdatedArgs(group: Group, entity: Entity, index:Int, prevComponent: IComponent, newComponent: IComponent?) : EventArgs {
    val group = group
    val entity = entity
    val index = index
    val prevComponent = prevComponent
    val newComponent = newComponent
}

class PoolEntityChangedArgs(pool: Pool, entity: Entity) : EventArgs {
    val pool = pool
    val entity = entity
}

class PoolGroupChangedArgs(pool: Pool, group: Group) : EventArgs {
    val pool = pool
    val group = group
}

class TriggerOnEvent(trigger: Matcher, eventType: GroupEventType) {
    val trigger = trigger
    val eventType = eventType
}