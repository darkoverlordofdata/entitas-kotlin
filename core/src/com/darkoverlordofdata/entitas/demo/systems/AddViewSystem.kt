package com.darkoverlordofdata.entitas.demo.systems

/**
 * Entitas Generated Systems for com.darkoverlordofdata.entitas.demo
 *
 */

import com.darkoverlordofdata.entitas.demo.Resource
import com.darkoverlordofdata.entitas.demo.resource
import com.darkoverlordofdata.entitas.ecs.*

class AddViewSystem()
    : ISystem, ISetPool {

    override fun setPool(pool: Pool) {
        val group = pool.getGroup(Matcher.Resource)
        if (group != null) group.onEntityAdded += onEntityAdded
    }

    /**
     * OnEntityAdded - Resource component.
     *
     * Load & configure the sprite for this resource component
     *
     * @param e.group
     * @param e.entity
     * @param e.index
     * @param e.component
     */
    val onEntityAdded = {e:GroupChangedArgs ->
        println(e.entity.resource.name)
    }

}
