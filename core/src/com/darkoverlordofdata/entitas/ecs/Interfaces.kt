package com.darkoverlordofdata.entitas.ecs
/**
 * Entitas Interfaces
 *
 */
interface IComponent {}
interface IMatcher {
    val id:Int
    val indices:IntArray
    fun matches(entity:Entity):Boolean
}
interface ICompoundMatcher : IMatcher {
    val allOfIndices:IntArray
    val anyOfIndices:IntArray
    val noneOfIndices:IntArray
}
interface INoneOfMatcher : ICompoundMatcher {}
interface IAnyOfMatcher : ICompoundMatcher {
    fun noneOf(indices:Array<IMatcher>):INoneOfMatcher
    fun noneOf(indices:IntArray):INoneOfMatcher
}
interface IAllOfMatcher : ICompoundMatcher {
    fun anyOf(indices:Array<IMatcher>):IAnyOfMatcher
    fun anyOf(indices:IntArray):IAnyOfMatcher
    fun noneOf(indices:Array<IMatcher>):INoneOfMatcher
    fun noneOf(indices:IntArray):INoneOfMatcher
}
interface ISystem {}
interface ISetPool{
    fun setPool(pool:Pool)
}
interface IInitializeSystem : ISystem {
    fun initialize()
}
interface IExecuteSystem : ISystem {
    fun execute()
}
interface IReactiveExecuteSystem : ISystem {
    fun execute(entities:Array<Entity>)
}
interface IMultiReactiveSystem : IReactiveExecuteSystem {
    val triggers:Array<TriggerOnEvent>
}
interface IReactiveSystem : IReactiveExecuteSystem {
    val trigger:TriggerOnEvent
}
interface IEnsureComponents {
    val ensureComponents:IMatcher
}
interface IExcludeComponents {
    val excludeComponents:IMatcher
}
interface IClearReactiveSystem {
    val clearAfterExecute:Boolean
}
