package com.darkoverlordofdata.entitas
import java.util.ArrayList

interface EventArgs {}
/**
 * Base Event Object
 */
open class Event<eventArg : EventArgs> {
    private val handlers = ArrayList<((eventArg) -> Unit)>()

    operator fun plusAssign(handler: (eventArg) -> Unit) {
        handlers.add(handler)
    }

    operator fun minusAssign(handler: (eventArg) -> Unit) {
        handlers.remove(handler)
    }

    operator fun invoke(value: eventArg) {
        for (handler in handlers) handler(value)
    }
}