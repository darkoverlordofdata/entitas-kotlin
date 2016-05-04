package com.darkoverlordofdata.entitas
import java.util.*
/**
 * Matcher
 *
 * Select entities by component makeup
 *
 */
class Matcher : IAllOfMatcher, IAnyOfMatcher, INoneOfMatcher {
    override var allOfIndices = intArrayOf()
    override var anyOfIndices = intArrayOf()
    override var noneOfIndices = intArrayOf()

    override val id:Int get() = _id
    override val indices:IntArray
        get() {
            if (_indices.size == 0)
                _indices = mergeIndices()
            return _indices
        }

    private val _id = uniqueId()
    private var _indices = intArrayOf()
    private var toStringCache = ""

    override fun matches(entity: Entity):Boolean {
        val matchesAllOf = if (allOfIndices.size == 0) true else entity.hasComponents(allOfIndices)
        val matchesAnyOf = if (anyOfIndices.size == 0) true else entity.hasAnyComponent(anyOfIndices)
        val matchesNoneOf = if (noneOfIndices.size == 0) true else !entity.hasAnyComponent(noneOfIndices)
        return matchesAllOf && matchesAnyOf && matchesNoneOf
    }

    fun mergeIndices():IntArray {
        var indicesList = intArrayOf()

        if (allOfIndices.size > 0) indicesList += allOfIndices
        if (anyOfIndices.size > 0) indicesList += anyOfIndices
        if (noneOfIndices.size > 0) indicesList += noneOfIndices

        return static.distinctIndices(indicesList)
    }

    override fun anyOf(indices: Array<IMatcher>): IAnyOfMatcher {
        return anyOf(static.mergeIndices(indices))
    }

    override fun anyOf(indices: IntArray): IAnyOfMatcher {
        anyOfIndices = static.distinctIndices(indices)
        _indices = intArrayOf()
        return this
    }

    override fun noneOf(indices: Array<IMatcher>): INoneOfMatcher {
        return noneOf(static.mergeIndices(indices))

    }

    override fun noneOf(indices: IntArray): INoneOfMatcher {
        noneOfIndices = static.distinctIndices(indices)
        _indices = intArrayOf()
        return this
    }


    override fun toString():String {
        if (toStringCache == "") {
            val sb = StringBuilder()
            toStringHelper(sb, "AllOf", allOfIndices)
            toStringHelper(sb, "AnyOf", anyOfIndices)
            toStringHelper(sb, "NoneOf", noneOfIndices)
            toStringCache = sb.toString()
        }
        return toStringCache
    }

    fun onEntityAdded(): TriggerOnEvent {
        return TriggerOnEvent(this, GroupEventType.OnEntityAdded)
    }

    fun onEntityRemoved(): TriggerOnEvent {
        return TriggerOnEvent(this, GroupEventType.OnEntityRemoved)
    }

    fun onEntityAddedOrRemoved(): TriggerOnEvent {
        return TriggerOnEvent(this, GroupEventType.OnEntityAddedOrRemoved)
    }

    private fun toStringHelper(sb:StringBuilder, prefix:String, indexArray:IntArray) {
        if (indexArray.size > 0) {
            sb.append(prefix)
            sb.append("(")
            for (i in 0..indexArray.size-1) {
                //sb.append(indexArray[i].toString())
                sb.append(Pool.instance!!.componentName(i))
                if (i < indexArray.size-1) sb.append(",")
            }
            sb.append(")")
        }

    }
    companion object static {
        private var _uniqueId = 0
        fun uniqueId():Int {return _uniqueId++}
        fun distinctIndices(indices:IntArray):IntArray {
            val indicesSet: HashSet<Int> = hashSetOf()
            for (index in indices) indicesSet.add(index)
            return indicesSet.toIntArray()
        }
        fun mergeIndices(matchers:Array<IMatcher>):IntArray {
            val indices:MutableList<Int> = ArrayList(listOf())
            for (matcher in matchers) {
                if (matcher.indices.size != 1)
                    throw MatcherException(matcher)
                indices.add(matcher.indices[0])
            }
            return indices.toIntArray()

        }
        fun allOf(vararg args:Int): IAllOfMatcher {
            val matcher = Matcher()
            matcher.allOfIndices = static.distinctIndices(args)
            return matcher
        }
        fun allOf(vararg args: IMatcher): IAllOfMatcher {
            val result:MutableList<IMatcher> = ArrayList(listOf())
            for (arg in args) result.add(arg)
            return static.allOf(*static.mergeIndices(result.toTypedArray()))
        }

        fun anyOf(vararg args:Int): IAnyOfMatcher {
            val matcher = Matcher()
            matcher.anyOfIndices = static.distinctIndices(args)
            return matcher
        }
        fun anyOf(vararg args: IMatcher): IAnyOfMatcher {
            val result:MutableList<IMatcher> = ArrayList(listOf())
            for (arg in args) result.add(arg)
            return static.anyOf(*static.mergeIndices(result.toTypedArray()))
        }
    }
}