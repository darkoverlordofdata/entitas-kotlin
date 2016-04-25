package com.darkoverlordofdata.entitas.ecs

import java.util.*

class Matcher : IAllOfMatcher, IAnyOfMatcher, INoneOfMatcher {
    companion object static {
        private var _uniqueId = 0
        fun uniqueId():Int {return _uniqueId++}
        fun distinctIndices(indices:IntArray):IntArray {
            val indicesSet:HashSet<Int> = hashSetOf()
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
        fun allOf(vararg args:Int):IAllOfMatcher {
            val matcher = Matcher()
            matcher.allOfIndices = Matcher.distinctIndices(args)
            return matcher
        }
        fun allOf(vararg args:IMatcher):IAllOfMatcher {
            val result:MutableList<IMatcher> = ArrayList(listOf())
            for (arg in args) result.add(arg)
            return Matcher.allOf(*Matcher.mergeIndices(result.toTypedArray()))
        }

        fun anyOf(vararg args:Int):IAnyOfMatcher {
            val matcher = Matcher()
            matcher.anyOfIndices = Matcher.distinctIndices(args)
            return matcher
        }
        fun anyOf(vararg args:IMatcher):IAnyOfMatcher {
            val result:MutableList<IMatcher> = ArrayList(listOf())
            for (arg in args) result.add(arg)
            return Matcher.anyOf(*Matcher.mergeIndices(result.toTypedArray()))
        }
        private fun appendIndices(sb:StringBuilder, prefix:String, indexArray:IntArray) {
            sb.append(prefix)
            sb.append("(")
            val last = indexArray.size - 1
            for (i in 0..last) {
                sb.append(indexArray[i].toString())
                if (i < last) sb.append(", ")
            }
            sb.append(")")
        }
    }

    val _id = uniqueId()
    var _indices = intArrayOf()
    override var allOfIndices = intArrayOf()
    override var anyOfIndices = intArrayOf()
    override var noneOfIndices = intArrayOf()
    var toStringCache = ""

    override val id:Int get() = _id
    override val indices:IntArray
        get() {
            if (_indices.size == 0)
                _indices = mergeIndices()
            return _indices
        }


    override fun matches(entity:Entity):Boolean {
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

        return Matcher.distinctIndices(indicesList)
    }

    override fun anyOf(indices: Array<IMatcher>): IAnyOfMatcher {
        return anyOf(Matcher.mergeIndices(indices))
    }

    override fun anyOf(indices: IntArray): IAnyOfMatcher {
        anyOfIndices = Matcher.distinctIndices(indices)
        _indices = intArrayOf()
        return this
    }

    override fun noneOf(indices: Array<IMatcher>): INoneOfMatcher {
        return noneOf(Matcher.mergeIndices(indices))

    }

    override fun noneOf(indices: IntArray): INoneOfMatcher {
        noneOfIndices = Matcher.distinctIndices(indices)
        _indices = intArrayOf()
        return this
    }


    override fun toString():String {
        if (toStringCache == "") {
            val sb = StringBuilder()
            if (anyOfIndices.size == 0) {
                if (allOfIndices.size == 0) {
                    sb.append(".")
                }
                Matcher.appendIndices(sb, "AnyOf", anyOfIndices)
            }
            if (noneOfIndices.size == 0) {
                Matcher.appendIndices(sb, ".NoneOf", noneOfIndices)
            }
            toStringCache = sb.toString()
        }
        return toStringCache
    }

    fun onEntityAdded():TriggerOnEvent{
        return TriggerOnEvent(this, GroupEventType.OnEntityAdded)
    }

    fun onEntityRemoved():TriggerOnEvent{
        return TriggerOnEvent(this, GroupEventType.OnEntityRemoved)
    }

    fun onEntityAddedOrRemoved():TriggerOnEvent{
        return TriggerOnEvent(this, GroupEventType.OnEntityAddedOrRemoved)
    }
}