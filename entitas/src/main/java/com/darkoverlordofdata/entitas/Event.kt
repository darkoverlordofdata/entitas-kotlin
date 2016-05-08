package com.darkoverlordofdata.entitas
import java.util.*

interface EventArgs {}
/**
 * Base Event Object
 */
open class Event<E : EventArgs> {
    private val handlers = ArrayList<((E) -> Unit)>()

    operator fun plusAssign(handler: (E) -> Unit) {
        handlers.add(handler)
    }

    operator fun minusAssign(handler: (E) -> Unit) {
        handlers.remove(handler)
    }

    operator fun invoke(value: E) {
        for (handler in handlers) handler(value)
    }

    fun clear() {
        handlers.clear()
    }
}