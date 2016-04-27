package com.darkoverlordofdata.entitas
/**
 * Entitas Exceptions
 *
 */
class EntityAlreadyHasComponentException(message:String, index:Int)
    : Exception("$message\nEntity already has a component at index ($index)") {}

class EntityDoesNotHaveComponentException(message:String, index:Int)
    : Exception("$message\nEntity does not have a component at index ($index)") {}

class EntityIsAlreadyReleasedException()
    : Exception("Entity is already released!"){}

class EntityIsNotDestroyedException(message:String)
    : Exception("$message\nEntity is not destroyed yet!"){}

class EntityIsNotEnabledException(message:String)
    : Exception("$message\nEntity is not enabled"){}

class GroupObserverException(message:String)
    : Exception("$message"){}

class MatcherException(matcher: IMatcher)
    : Exception("matcher.indices.length must be 1 but was ${matcher.indices.size}"){}

class PoolDoesNotContainEntityException(entity: Entity, message:String)
    : Exception("$message\nPool does not contain entity ${entity.toString()}"){}

class SingleEntityException(matcher: IMatcher)
    : Exception("Multiple entities exist matching ${matcher.toString()}"){}


