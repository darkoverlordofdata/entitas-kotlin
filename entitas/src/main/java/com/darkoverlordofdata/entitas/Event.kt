package com.darkoverlordofdata.entitas
import java.util.*

interface EventArgs {}
/**
 * Base Event Object
 */
open class Event<E : EventArgs> {
    private val handlers = ArrayList<((E) -> Unit)>()

    /**
     * + operator
     *
     * @param[handler] event handler to add
     */
    operator fun plusAssign(handler: (E) -> Unit) {
        handlers.add(handler)
    }

    /**
     * - operator
     *
     * @param[handler] event handler to remove
     */
    operator fun minusAssign(handler: (E) -> Unit) {
        handlers.remove(handler)
    }

    /**
     * invoke all event handlers
     */
    operator fun invoke(value: E) {
        for (handler in handlers) handler(value)
    }

    /**
     * remove all event handlers
     */
    fun clear() {
        handlers.clear()
    }
}